package klient.Gui.Paneler;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;

import klient.Gui.BildeBufferAdmin;

public class BildeVelgerPanelTest{

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		JFrame f = new JFrame();
		JPanel p = new JPanel();
		p.setPreferredSize(new Dimension(640,400));
		f.setContentPane(p);
		f.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);
		
		BildeBufferAdmin ba = new BildeBufferAdmin(100, p);
		BildevelgerPanel bv = new BildevelgerPanel(ba);
		p.setLayout(new GridBagLayout());
		GridBagConstraints k = new GridBagConstraints();
		k.fill = GridBagConstraints.BOTH;
		k.weightx = 1;
		k.weighty = 1;
		p.add(bv, k);

		URL[] urls = new URL[5];
		URL[] urls2 = new URL[4];
		boolean[] b = {true, false, true, false, true};
		boolean[] b2 = {true, true, false, false};
		try {
			urls[0] = new URL("http://t.facdn.net/10602790@400-1368613549.jpg");
			urls[1] = new URL("http://t.facdn.net/12069061@400-1384472441.jpg");
			urls[2] = new URL("http://t.facdn.net/11831897@400-1381785863.jpg");
			urls[3] = new URL("http://t.facdn.net/11041487@400-1373404117.jpg");
			urls[4] = new URL("http://t.facdn.net/11460782@400-1377561946.jpg");
			
			urls2[3] = new URL("http://t.facdn.net/12069061@400-1384472441.jpg");
			urls2[2] = new URL("http://t.facdn.net/11831897@400-1381785863.jpg");
			urls2[1] = new URL("http://t.facdn.net/11041487@400-1373404117.jpg");
			urls2[0] = new URL("http://t.facdn.net/11460782@400-1377561946.jpg");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		System.out.print((ba.length() == 0) + " ");
		System.out.print((ba.erInkludert(0) == false) + " ");
		System.out.print((ba.HentURL(0) == null) + " ");
		System.out.println(ba.HentBilde(0) == null);

		Thread t = new Thread(ba);
		t.start();

		ba.Oppdater(urls, b);
		bv.Rebuild();
		f.pack();
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0; i<bv.lesInkluderte().length; i++)
			System.out.print((bv.lesInkluderte()[i] == b[i]) + " ");
		System.out.println("");
		for(int i = 0; i<bv.lesAdminUrls().length; i++)
			System.out.print((bv.lesAdminUrls()[i].equals(urls[i])) + " ");
		System.out.println("");
		for(int i = 0; i<ba.length(); i++)
			System.out.print((ba.erInkludert(i) == b[i]) + " ");
		System.out.println("");
		for(int i = 0; i<ba.length(); i++)
			System.out.print((ba.HentURL(i) == urls[i]) + " ");
		System.out.println("");
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ba.Kast();
		bv.Rebuild();
		f.pack();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print((ba.length() == 0) + " ");
		System.out.print((ba.erInkludert(0) == false) + " ");
		System.out.print((ba.HentURL(0) == null) + " ");
		System.out.println(ba.HentBilde(0) == null);
		
		
		ba.Oppdater(urls2, b2);
		bv.Rebuild();
		f.pack();
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0; i<bv.lesInkluderte().length; i++)
			System.out.print((bv.lesInkluderte()[i] == b2[i]) + " ");
		System.out.println("");
		for(int i = 0; i<bv.lesAdminUrls().length; i++)
			System.out.print((bv.lesAdminUrls()[i].equals(urls2[i])) + " ");
		System.out.println("");
		for(int i = 0; i<ba.length(); i++)
			System.out.print((ba.erInkludert(i) == b2[i]) + " ");
		System.out.println("");
		for(int i = 0; i<ba.length(); i++)
			System.out.print((ba.HentURL(i) == urls2[i]) + " ");
		System.out.println("");
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ba.Oppdater(urls, b);
		bv.Rebuild();
		f.pack();
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0; i<bv.lesInkluderte().length; i++)
			System.out.print((bv.lesInkluderte()[i] == b[i]) + " ");
		System.out.println("");
		for(int i = 0; i<bv.lesAdminUrls().length; i++)
			System.out.print((bv.lesAdminUrls()[i].equals(urls[i])) + " ");
		System.out.println("");
		for(int i = 0; i<ba.length(); i++)
			System.out.print((ba.erInkludert(i) == b[i]) + " ");
		System.out.println("");
		for(int i = 0; i<ba.length(); i++)
			System.out.print((ba.HentURL(i) == urls[i]) + " ");
		System.out.println("");

	}
}
