package klient.Gui.Paneler;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class BildePanel extends JPanel {

	private BufferedImage bilde;
	private String tekst;
	private boolean vis;
	private boolean highlight;
	private boolean trykket;
	private boolean bg;

	public BildePanel(BufferedImage b)
	{
		bg = false;
		vis = false;
		highlight = false;
		trykket = false;
		bilde = b;
		tekst = "";
		if(bilde != null)
			this.setPreferredSize(new Dimension(bilde.getWidth(), bilde.getHeight()));
	}

	public BildePanel(String s)
	{
		bg = true;
		vis = false;
		highlight = false;
		trykket = false;
		tekst = s;
	}

	public void Oppdater(BufferedImage b)
	{
		bilde = b;
		this.setPreferredSize(new Dimension(bilde.getWidth(), bilde.getHeight()));
	}

	public void Oppdater(String s)
	{
		tekst = s;
	}

	public void SkalVises(boolean v)
	{
		vis = v;
		repaint();
	}

	@Override
	public void paintComponent(Graphics g)
	{
		if(vis)
		{
			if(bg)
			{
				if(highlight)
					g.setColor(Color.lightGray);
				else
					g.setColor(Color.darkGray);
				if(trykket)
					g.setColor(Color.black);
				g.fillRect(0, 0, this.getWidth(), this.getHeight());
				if(highlight)
					g.setColor(Color.white);
				else
					g.setColor(Color.black);
				g.drawRect(0, 0, this.getWidth()-1, this.getHeight()-1);
				if(highlight)
					g.setColor(Color.black);
				else
					g.setColor(Color.white);
				if(trykket)
					g.setColor(Color.white);
				g.drawRect(1, 1, this.getWidth()-3, this.getHeight()-3);
			}
			if(bilde!=null)
				g.drawImage(bilde, 0, 0, this.getWidth(), this.getHeight(), this);
			else
				g.drawString(tekst, (this.getWidth()/2)-5, (this.getHeight()/2)+6);
		}
	}

	public void Highlight(boolean b) {
		highlight = b;
	}

	public boolean erTrykket() {
		return trykket;
	}

	public void Trykket(boolean b) {
		trykket = b;
	}



}
