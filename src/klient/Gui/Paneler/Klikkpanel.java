package klient.Gui.Paneler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

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
		this.setPreferredSize(new Dimension(100, 124));
		add(knapp, BorderLayout.PAGE_END);
	}

	public boolean erValgt()
	{
		return knapp.isSelected();
	}


	@Override
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.darkGray);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.black);
		g.drawRect(0, 0, this.getWidth()-1, 100-1);
		g.setColor(Color.white);
		g.drawRect(1, 1, this.getWidth()-3, 100-3);
		if(bilder.HentBilde(nr) !=null)
			g.drawImage(bilder.HentBilde(nr), 0, 0, 100, 100, this);
	}
}
