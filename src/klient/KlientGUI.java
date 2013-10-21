package klient;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.*;

public class KlientGUI extends JPanel implements ActionListener {

	private URL[] linker;
	private String[] tags;
	
	public KlientGUI()
	{
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
	}
	
	@Override
	public void paintComponents(Graphics g)
	{
		// Tegn bilder
	}
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// Gjør ting her
	}
	
}
