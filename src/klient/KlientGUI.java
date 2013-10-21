package klient;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

public class KlientGUI extends JPanel implements ActionListener {

	private URL[] linker;
	private String[] tags;
	private int visning;
	private BufferedImage bilde;


	public KlientGUI()
	{
		this.setPreferredSize(new Dimension(500,500));
		
		linker = new URL[1];
		try {
			linker[0] = new URL("http://www.wallng.com/images/2013/08/image-explosion-colors-background-beautiful-263613.jpg");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		VisNesteBilde();
		// Lag GUIKomponenter og sett GUI modus
	}

	private void ByggGUI(int modusnr)
	{
		removeAll();
		// Bygg GUI for spesifik modus
	}

	public String[] LesTags()
	{
		return tags;
	}


	public String[] LesTags(int max)
	{
		if(tags.length < max)
			max = tags.length;
		String[] tmp = new String[max];
		for (int i = 0; i<max; i++)
			tmp[i] = tags[i];
		return tmp;
	}

	public void GiBilder(URL[] l)
	{
		linker = l;
		visning = -1;
	}

	private void VisNesteBilde()
	{
		visning++;
		if(visning>=linker.length)
			visning = 0;
		VisBilde(visning);
	}

	private void VisBilde(int nr)
	{
		if(nr<linker.length)
		{
			try {
				bilde = ImageIO.read(linker[nr]);
				repaint();
			} catch (IOException e) {
				System.out.println("Feil under lesing av bilde på " + linker[nr].getPath());
			}
		}
	}

	@Override
	public void paintComponent(Graphics g)
	{
		g.drawImage(bilde, 0, 0, this.getWidth(), this.getHeight(), this);
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// Gjør ting her
	}

}
