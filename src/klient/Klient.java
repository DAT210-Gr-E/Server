package klient;

import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JFrame;

public class Klient extends JFrame {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Klient();
	}
	
	
	KlientGUI gui;
	
	public Klient()
	{
		super("Klient");
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		gui = new KlientGUI();
		setContentPane(gui);
	    pack();
	    setVisible(true);
	    
	    run();
	}

	private void run() {
		URL[] linker = new URL[2];
		try {
			linker[0] = new URL("http://www.wallng.com/images/2013/08/image-explosion-colors-background-beautiful-263613.jpg");
			linker[1] = new URL("http://www.nasa.gov/images/content/693952main_pia15817-full_full.jpg");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		gui.GiBilder(linker);
	}

}
