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

import klient.Defs.Forespoersel;
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
			if(gui.erForespoersel(Forespoersel.TID))
				transaksjoner[Forespoersel.TID] = transaksjonsteller++;
			if(gui.erForespoersel(Forespoersel.BILDER))
				transaksjoner[Forespoersel.BILDER] = transaksjonsteller++;
			if(gui.erForespoersel(Forespoersel.LOGIN))
			{
				transaksjoner[Forespoersel.LOGIN] = transaksjonsteller++;
				passordut = gui.sjekkLogin();
			}
			if(gui.erForespoersel(Forespoersel.TAGS))
				transaksjoner[Forespoersel.TAGS] = transaksjonsteller++;
			if(gui.erForespoersel(Forespoersel.ADMIN_BILDER))
				transaksjoner[Forespoersel.ADMIN_BILDER] = transaksjonsteller++;
			if(gui.erForespoersel(Forespoersel.ADMIN_SET_TAGS))
				nettUt.sendtags(gui.LesDefaulttags());
			if(gui.erForespoersel(Forespoersel.ADMIN_SET_TID))
				nettUt.sendtid(gui.LesTid());
			if(gui.erForespoersel(Forespoersel.ADMIN_SET_SVARTELISTE))
				nettUt.sendinkluderte(gui.lesAdminUrls(), gui.lesInkluderte());
			
			if(nettInn.getID(Forespoersel.TID) < transaksjoner[Forespoersel.TID])
				nettUt.spoertid(transaksjoner[Forespoersel.TID]);
			if(nettInn.getID(Forespoersel.BILDER) < transaksjoner[Forespoersel.BILDER])
				nettUt.spoerbilder(transaksjoner[Forespoersel.BILDER]);
			if(nettInn.getID(Forespoersel.LOGIN) < transaksjoner[Forespoersel.LOGIN])
				nettUt.spoerlogin(passordut, transaksjoner[Forespoersel.LOGIN]);
			if(nettInn.getID(Forespoersel.TAGS) < transaksjoner[Forespoersel.TAGS])
				nettUt.spoertags(transaksjoner[Forespoersel.TAGS]);
			if(nettInn.getID(Forespoersel.ADMIN_BILDER) < transaksjoner[Forespoersel.ADMIN_BILDER])
				nettUt.spoeradminbilder(transaksjoner[Forespoersel.ADMIN_BILDER]);
	
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if(nettInn.getIDr(Forespoersel.TID) == transaksjoner[Forespoersel.TID] && transaksjoner[Forespoersel.TID] != 0)
			{
				gui.setTid(nettInn.getTidsInterval());
				transaksjoner[Forespoersel.TID] = 0;
			}
			if(nettInn.getIDr(Forespoersel.BILDER) == transaksjoner[Forespoersel.BILDER] && transaksjoner[Forespoersel.BILDER] != 0)
			{
				gui.GiBilder(nettInn.getURLs());
				transaksjoner[Forespoersel.BILDER] = 0;
			}
			if(nettInn.getIDr(Forespoersel.LOGIN) == transaksjoner[Forespoersel.LOGIN] && transaksjoner[Forespoersel.LOGIN] != 0)
			{
				if(nettInn.getLoginSuksess() && passordut.equals(nettInn.getLoginPassord()))
					gui.Login(nettInn.getLoginPassord());
				else
					gui.LoginFail();
				transaksjoner[Forespoersel.LOGIN] = 0;
			}
			if(nettInn.getIDr(Forespoersel.TAGS) == transaksjoner[Forespoersel.TAGS] && transaksjoner[Forespoersel.TAGS] != 0)
			{
				gui.setDefaultTags(nettInn.getTags());
				transaksjoner[Forespoersel.TAGS] = 0;
			}
			if(nettInn.getIDr(Forespoersel.ADMIN_BILDER) == transaksjoner[Forespoersel.ADMIN_BILDER] && transaksjoner[Forespoersel.ADMIN_BILDER] != 0)
			{
				gui.GiBilder(nettInn.getAdminURLs(), nettInn.getInkluderteURLer());
				transaksjoner[Forespoersel.ADMIN_BILDER] = 0;
			}
		}
	}


	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_ESCAPE)
			System.exit(0);
		else if(arg0.getKeyCode() == KeyEvent.VK_A)
			gui.GaaTilLogin();
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
