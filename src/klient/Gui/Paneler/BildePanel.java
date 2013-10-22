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

	public BildePanel(BufferedImage b)
	{
		vis = false;
		bilde = b;
		if(bilde != null)
			this.setPreferredSize(new Dimension(bilde.getWidth(), bilde.getHeight()));
	}

	public BildePanel(String s)
	{
		vis = false;
		tekst = s;
	}

	public void Oppdater(BufferedImage b)
	{
		bilde = b;
		this.setPreferredSize(new Dimension(bilde.getWidth(), bilde.getHeight()));
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
			if(highlight)
				g.setColor(Color.lightGray);
			else
				g.setColor(Color.darkGray);
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
			g.drawRect(1, 1, this.getWidth()-3, this.getHeight()-3);
			if(bilde!=null)
				g.drawImage(bilde, 0, 0, this.getWidth(), this.getHeight(), this);
			else
				g.drawString(tekst, (this.getWidth()/2)-5, (this.getHeight()/2)+6);
		}
		else
		{
			g.setClip(0, 0, this.getWidth(), this.getHeight());
		}
	}

	public void Highlight(boolean b) {
		highlight = b;
	}

}
