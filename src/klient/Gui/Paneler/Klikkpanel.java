package klient.Gui.Paneler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import klient.Gui.BildeBufferAdmin;

public class Klikkpanel extends JPanel {

	private BildeBufferAdmin bilder;
	private int nr;
	private JCheckBox knapp;

	public Klikkpanel(BildeBufferAdmin b, int n)
	{
		bilder = b;
		nr = n;

		setLayout(new BorderLayout());
		knapp = new JCheckBox(bilder.HentFilnavn(nr), bilder.erInkludert(nr));
		if(erValgt())
			knapp.setBackground(Color.black);
		this.setPreferredSize(new Dimension(100, 124));
		add(knapp, BorderLayout.PAGE_END);
	}

	public boolean erValgt()
	{
		return knapp.isSelected();
	}
	
	public URL getURL()
	{
		return bilder.HentURL(nr);
	}


	@Override
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.black);
		g.drawRect(0, 0, this.getWidth()-1, 100-1);
		g.setColor(Color.lightGray);
		g.drawRect(1, 1, this.getWidth()-3, 100-3);
		BufferedImage bilde = bilder.HentBilde(nr);
		if(bilde !=null)
			if(bilde.getHeight()*this.getWidth() > this.getHeight()*bilde.getWidth())
				g.drawImage(bilde, (100-bilde.getWidth()*96/bilde.getHeight())/2, 2, bilde.getWidth()*96/bilde.getHeight(), 96, this);
			else
				g.drawImage(bilde, 2, (100-bilde.getHeight()*96/bilde.getWidth())/2, 96, bilde.getHeight()*96/bilde.getWidth(), this);
		else if(bilder.erFeilet(nr))
		{
			g.setColor(Color.red);
			g.drawString("X", 45, 56);
		}
		else if(bilder.Laster(nr))
		{
			g.setColor(Color.white);
			g.drawString(". . .", 40, 56);
		}
	}
}
