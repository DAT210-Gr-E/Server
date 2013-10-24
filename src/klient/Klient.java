package klient;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
		String[] tags = {""};
		String[] atags = {""};
		String forrigelogin = "";

		nettUt.poke();

		while(true)
		{
			/*String[] nyetags = gui.LesTags();   // Hvis klienten skal kunne søke med custom tags
			if(erUlike(tags, nyetags))
				nettUt.send(nyetags);*/

			String[] nyeatags = gui.LesAdminTags();
			if(erUlike(atags, nyeatags))
				nettUt.sendadmin(nyeatags);

			String login = gui.sjekkLogin();
			if(!login.equals(""))
			{
				nettUt.sendLogin(login);
				forrigelogin = login;
			}

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			int tid = nettInn.getTidsInterval();
			if(tid != -1)
				gui.setTimer(tid);

			String[] inntags = nettInn.getTags();
			if(erUlike(inntags, tags) /*&& !erUlike(inntags, nyetags)*/)  // komentert ut kode er for hvis klienten skal kunne søke med custom tags
			{
				gui.GiBilder(nettInn.getURLs());
				tags = inntags.clone();
			}
			
			String[] innatags = nettInn.getAdminTags();
			if(erUlike(innatags, atags) && !erUlike(innatags, nyeatags))
			{
				gui.GiBilder(nettInn.getAdminURLs(), nettInn.getInkluderteURLer());
				atags = innatags.clone();
			}

			if(nettInn.getLoginSuksess() && nettInn.getLoginPassord().equals(forrigelogin))
			{
				gui.Login(forrigelogin);
				forrigelogin = "";
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

}
