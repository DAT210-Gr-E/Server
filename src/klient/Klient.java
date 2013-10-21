package klient;

import javax.swing.JFrame;

public class Klient extends JFrame {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Klient();
	}
	
	public Klient()
	{
		super("Klient");
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		KlientGUI visning = new KlientGUI();
		setContentPane(visning);
	    pack();
	    setVisible(true);
	}

}
