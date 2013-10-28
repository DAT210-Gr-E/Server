package klient;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

import javax.swing.JFrame;

import klient.Gui.KlientGUI;
import klient.Nettverk.KlientNettInn;
import klient.Nettverk.KlientNettUt;

public class Klient extends JFrame implements KeyListener {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Klient();
	}

	KlientGUI gui;
	KlientNettInn nettInn;
	KlientNettUt nettUt;

	public Klient()
	{
		super("Klient");
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		gui = new KlientGUI(this);
		setContentPane(gui);
		pack();
		setVisible(true);

		nettInn = new KlientNettInn();
		nettUt = new KlientNettUt();
		Thread ni = new Thread(nettInn);
		Thread nu = new Thread(nettUt);
		ni.start();
		nu.start();
		this.addKeyListener(this);

		run();
	}

	private void run() {
		int[] transaksjoner = {-1, -1, -1, -1, -1, -1, -1};
		String[] tagsut = {};
		String[] admintagsut = {};
		String[] defaulttagsut = {};
		int tidut = -1;
		URL[] inklurlsut = {};
		boolean[] inkludertut = {};
		String passordut = "";

		int transaksjonsteller = 1;


		while(true)
		{
			if(gui.erForespoersel(0))
				transaksjoner[0] = transaksjonsteller++;
			if(gui.erForespoersel(1))
			{
				transaksjoner[1] = transaksjonsteller++;
				tidut = gui.LesTid();
			}
			if(gui.erForespoersel(2))
			{
				transaksjoner[2] = transaksjonsteller++;
				tagsut = gui.LesTags();
			}
			if(gui.erForespoersel(3))
			{
				transaksjoner[3] = transaksjonsteller++;
				passordut = gui.sjekkLogin();
			}
			if(gui.erForespoersel(4))
			{
				transaksjoner[4] = transaksjonsteller++;
				admintagsut = gui.LesAdminTags();
			}
			if(gui.erForespoersel(5))
			{
				transaksjoner[5] = transaksjonsteller++;
				defaulttagsut = gui.LesDefaulttags();
			}
			if(gui.erForespoersel(6))
			{
				transaksjoner[6] = transaksjonsteller++;
				inkludertut = gui.lesInkluderte();
				inklurlsut = gui.lesAdminUrls();
			}

			if(nettInn.getID(0) < transaksjoner[0])
				nettUt.poke(transaksjoner[0]);
			if(nettInn.getID(1) < transaksjoner[1])
				nettUt.send(tidut, transaksjoner[1]);
			if(nettInn.getID(2) < transaksjoner[2])
				nettUt.send(tagsut,transaksjoner[2]);
			if(nettInn.getID(3) < transaksjoner[3])
				nettUt.sendLogin(passordut,transaksjoner[3]);
			if(nettInn.getID(4) < transaksjoner[4])
				nettUt.sendadmin(admintagsut, transaksjoner[4]);
			if(nettInn.getID(5) < transaksjoner[5])
				nettUt.sendadmindefault(defaulttagsut, transaksjoner[5]);
			if(nettInn.getID(6) < transaksjoner[6])
				nettUt.sendadmininkludert(inklurlsut, inkludertut, transaksjoner[6]);

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			int tmp = nettInn.getID(0);
			if(tmp == transaksjoner[0] && tmp != 0)
			{
				transaksjoner[0] = 0;
				System.out.println(tmp + " Timer satt til default " + nettInn.getTidsInterval() + "ms");
				System.out.println(tmp + " Nye defaulte URL'er mottat og defaulte tags satt til " + Klient.PrintStr(nettInn.getTags()));
				gui.setTid(nettInn.getTidsInterval());
				gui.GiBilder(nettInn.getURLs());
				gui.setDefaultTags(nettInn.getTags());
			}
			tmp = nettInn.getID(1);
			if((tmp == transaksjoner[1] && tmp != 0) || tmp == -1)
			{
				transaksjoner[1] = 0;
				if(tmp != -1)
					System.out.println(tmp + " Timer tvunget til " + nettInn.getTidsInterval() + "ms");
				else
					System.out.println(tmp + " Visningstid oppdatert, timer satt til " + nettInn.getTidsInterval() + "ms");
				gui.setTid(nettInn.getTidsInterval());
			}
			tmp = nettInn.getID(2);
			if((tmp == transaksjoner[2] && tmp != 0) || tmp == -1)
			{
				transaksjoner[2] = 0;
				if(tmp != -1)
					System.out.println(tmp + " Søk ferdig, nye URL'er mottat " + Klient.PrintStr(nettInn.getTags()));
				else
					System.out.println(tmp + " Nye URL'er mottat " + Klient.PrintStr(nettInn.getTags()));
				gui.GiBilder(nettInn.getURLs());
			}
			tmp = nettInn.getID(3);
			if(tmp == transaksjoner[3] && tmp != 0)
			{
				transaksjoner[3] = 0;
				if(nettInn.getLoginSuksess() && passordut.equals(nettInn.getLoginPassord()))
				{
					System.out.println(tmp + " Login med passord " + nettInn.getLoginPassord() + " var suksessfult!");
					gui.Login(nettInn.getLoginPassord());
				}
				else
					System.out.println(tmp + " Login med passord " + nettInn.getLoginPassord() + " feilet!");
			}
			tmp = nettInn.getID(4);
			if(tmp == transaksjoner[4] && tmp != 0)
			{
				transaksjoner[4] = 0;
				System.out.println(tmp + " Nye URL'er mottat for Admin " + Klient.PrintStr(nettInn.getAdminTags()));
				gui.GiBilder(nettInn.getAdminURLs(), nettInn.getInkluderteURLer());
			}
			tmp = nettInn.getID(5);
			if(tmp == transaksjoner[5] && tmp != 0)
			{
				transaksjoner[5] = 0;
				System.out.println(tmp + " Tags oppdatert, nye URL'er mottat og defaulte tags satt til " + Klient.PrintStr(nettInn.getTags()));
				gui.GiBilder(nettInn.getURLs());
				gui.setDefaultTags(nettInn.getTags());
			}
			tmp = nettInn.getID(6);
			if(tmp == transaksjoner[6] && tmp != 0)
			{
				transaksjoner[6] = 0;
				System.out.println(tmp + " Blockliste oppdatert, nye URL'er mottat " + Klient.PrintStr(nettInn.getTags()));
				System.out.println(tmp + " Nye URL'er mottat for Admin " + Klient.PrintStr(nettInn.getAdminTags()));
				gui.GiBilder(nettInn.getURLs());
				gui.GiBilder(nettInn.getAdminURLs(), nettInn.getInkluderteURLer());
			}
		}
	}

	private boolean erUlike(String[] tags, String[] nyetags) {
		if(tags.length != nyetags.length)
			return false;
		boolean ret = true;
		for(int i = 0; i<tags.length; i++)
		{
			boolean funnet = false;
			for(int j = 0; j<tags.length; j++)
			{
				if(tags[i].toUpperCase().equals(nyetags[j].toUpperCase()))
				{
					funnet = true;
					break;
				}
			}
			if(!funnet)
			{
				ret=false;
				break;
			}
		}
		return !ret;
	}

	private int flagg = 0;

	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_ESCAPE)
			System.exit(0);
		else if(arg0.isControlDown() && arg0.isAltDown() && arg0.getKeyCode() == KeyEvent.VK_0)
			flagg = 1;
		else if(flagg == 1 && arg0.getKeyCode() == KeyEvent.VK_A)flagg = 2;
		else if(flagg == 2 && arg0.getKeyCode() == KeyEvent.VK_D)flagg = 3;
		else if(flagg == 3 && arg0.getKeyCode() == KeyEvent.VK_M)flagg = 4;
		else if(flagg == 4 && arg0.getKeyCode() == KeyEvent.VK_I)flagg = 5;
		else if(flagg == 5 && arg0.getKeyCode() == KeyEvent.VK_N)flagg = 6;
		else if(flagg == 6 && arg0.getKeyCode() == KeyEvent.VK_I)flagg = 7;
		else if(flagg == 7 && arg0.getKeyCode() == KeyEvent.VK_S)flagg = 8;
		else if(flagg == 8 && arg0.getKeyCode() == KeyEvent.VK_T)flagg = 9;
		else if(flagg == 9 && arg0.getKeyCode() == KeyEvent.VK_R)flagg = 10;
		else if(flagg == 10 && arg0.getKeyCode() == KeyEvent.VK_A)flagg = 11;
		else if(flagg == 11 && arg0.getKeyCode() == KeyEvent.VK_T)flagg = 12;
		else if(flagg == 12 && arg0.getKeyCode() == KeyEvent.VK_O)flagg = 13;
		else if(/*flagg == 13 &&*/ arg0.getKeyCode() == KeyEvent.VK_R) //////////////////////////////////////////// Test
		{
			gui.ByggGUI(2);
			flagg = 0;
		}
		else
			flagg = 0;

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	public static String PrintStr(String[] tags)
	{
		String tmp = "{";
		for(int i = 0; i<tags.length-1; i++)
			tmp = tmp + tags[i] + ", ";
		if(tags.length>0)
			tmp = tmp + tags[tags.length-1];
		tmp = tmp + "}";
		return tmp;
	}

}
