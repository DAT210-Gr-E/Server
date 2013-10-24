package klient.Gui.Paneler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import klient.Gui.BildeBufferAdmin;

public class BildevelgerPanel extends JPanel {

	private BildeBufferAdmin bilder;
	private Klikkpanel[] bildevalg;

	public BildevelgerPanel(BildeBufferAdmin b)
	{
		bilder = b;
		Rebuild();
	}
	
	public void Rebuild()
	{
		removeAll();
		//this.setLayout(new GridBagLayout());
		bildevalg = new Klikkpanel[bilder.length()];
		for(int i = 0; i<bilder.length(); i++)
		{
			bildevalg[i] = new Klikkpanel(bilder, i);
			add(bildevalg[i]);
		}
		repaint();
	}

	public boolean[] erValgt()
	{
		return null;
	}


	@Override
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.darkGray);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.black);
		g.drawRect(0, 0, this.getWidth()-1, this.getHeight()-1);
		g.setColor(Color.white);
		g.drawRect(1, 1, this.getWidth()-3, this.getHeight()-3);
	}
}
