package klient;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

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

	private KlientGUI gui;
	private KlientNettInn nettInn;
	private KlientNettUt nettUt;

	private String SIP = "localhost";
	private int SPORT = 9091;
	
	public Klient()
	{
		super("Klient");
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		gui = new KlientGUI(this);
		setContentPane(gui);
		pack();
		
		Socket socket = null;
		ObjectInputStream fraServer = null;
		ObjectOutputStream tilServer = null;
		
		try {
			socket = new Socket(SIP, SPORT);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Kunne ikke kontakte Server!");
			System.exit(0);
		}
		try {
			tilServer = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fraServer = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		nettInn = new KlientNettInn(fraServer);
		nettUt = new KlientNettUt(tilServer);
		Thread ni = new Thread(nettInn);
		Thread nu = new Thread(nettUt);
		ni.start();
		nu.start();
		this.addKeyListener(this);

		setVisible(true);
		
		run();
	}

	private void run() {
		int[] transaksjoner = {0, 0, 0, 0, 0};
		String passordut = "";

		int transaksjonsteller = 1;


		while(true)
		{
			if(gui.erForespoersel(0))
				transaksjoner[0] = transaksjonsteller++;
			if(gui.erForespoersel(1))
				transaksjoner[1] = transaksjonsteller++;
			if(gui.erForespoersel(2))
			{
				transaksjoner[2] = transaksjonsteller++;
				passordut = gui.sjekkLogin();
			}
			if(gui.erForespoersel(3))
				transaksjoner[3] = transaksjonsteller++;
			if(gui.erForespoersel(4))
				transaksjoner[4] = transaksjonsteller++;
			if(gui.erForespoersel(5))
				nettUt.sendtags(gui.LesDefaulttags());
			if(gui.erForespoersel(6))
				nettUt.sendtid(gui.LesTid());
			if(gui.erForespoersel(7))
				nettUt.sendinkluderte(gui.lesAdminUrls(), gui.lesInkluderte());
			
			if(nettInn.getID(0) < transaksjoner[0])
				nettUt.spoertid(transaksjoner[0]);
			if(nettInn.getID(1) < transaksjoner[1])
				nettUt.spoerbilder(transaksjoner[1]);
			if(nettInn.getID(2) < transaksjoner[2])
				nettUt.spoerlogin(passordut, transaksjoner[2]);
			if(nettInn.getID(3) < transaksjoner[3])
				nettUt.spoertags(transaksjoner[3]);
			if(nettInn.getID(4) < transaksjoner[4])
				nettUt.spoeradminbilder(transaksjoner[4]);
	
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if(nettInn.getIDr(0) == transaksjoner[0] && transaksjoner[0] != 0)
			{
				gui.setTid(nettInn.getTidsInterval());
				transaksjoner[0] = 0;
			}
			if(nettInn.getIDr(1) == transaksjoner[1] && transaksjoner[1] != 0)
			{
				gui.GiBilder(nettInn.getURLs());
				transaksjoner[1] = 0;
			}
			if(nettInn.getIDr(2) == transaksjoner[2] && transaksjoner[2] != 0)
			{
				if(nettInn.getLoginSuksess() && passordut.equals(nettInn.getLoginPassord()))
					gui.Login(nettInn.getLoginPassord());
				else
					gui.LoginFail();
				transaksjoner[2] = 0;
			}
			if(nettInn.getIDr(3) == transaksjoner[3] && transaksjoner[3] != 0)
			{
				gui.setDefaultTags(nettInn.getTags());
				transaksjoner[3] = 0;
			}
			if(nettInn.getIDr(4) == transaksjoner[4] && transaksjoner[4] != 0)
			{
				gui.GiBilder(nettInn.getAdminURLs(), nettInn.getInkluderteURLer());
				transaksjoner[4] = 0;
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

	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_ESCAPE)
			System.exit(0);
		else if(arg0.getKeyCode() == KeyEvent.VK_A)
			gui.ByggGUI(2);
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
