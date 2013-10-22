package klient.Gui.Paneler;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import klient.Gui.KlientGUI;

public class MenyPanel extends JPanel implements MouseListener {

	private boolean vis;
	private boolean laasVis;
	private BildePanel tilbakeknapp;
	private BildePanel playknapp;
	private BildePanel pauseknapp;
	private BildePanel nesteknapp;
	private KlientGUI gui;

	public MenyPanel(KlientGUI g)
	{
		gui = g;
		
		tilbakeknapp = new BildePanel("<<");
		tilbakeknapp.setPreferredSize(new Dimension(50,50));
		tilbakeknapp.addMouseListener(this);
		tilbakeknapp.addMouseMotionListener(gui);
		add(tilbakeknapp);

		playknapp = new BildePanel("|>");
		playknapp.setPreferredSize(new Dimension(50,50));
		playknapp.addMouseListener(this);
		playknapp.addMouseMotionListener(gui);
		add(playknapp);
		
		pauseknapp = new BildePanel("||");
		pauseknapp.setPreferredSize(new Dimension(50,50));
		pauseknapp.addMouseListener(this);
		pauseknapp.addMouseMotionListener(gui);
		add(pauseknapp);
		
		nesteknapp = new BildePanel(">>");
		nesteknapp.setPreferredSize(new Dimension(50,50));
		nesteknapp.addMouseListener(this);
		nesteknapp.addMouseMotionListener(gui);
		add(nesteknapp);
		
		addMouseListener(this);
		vis = false;
		laasVis = false;
	}

	public void SkalVises(boolean v)
	{
		if(!laasVis)
		{
			vis = v;
			tilbakeknapp.SkalVises(v);
			playknapp.SkalVises(v);
			pauseknapp.SkalVises(v);
			nesteknapp.SkalVises(v);
		}
		repaint();
	}

	@Override
	public void paintComponent(Graphics g)
	{
		if(vis)
		{
			g.setColor(Color.darkGray);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.setColor(Color.black);
			g.drawRect(0, 0, this.getWidth()-1, this.getHeight()-1);
			g.setColor(Color.white);
			g.drawRect(1, 1, this.getWidth()-3, this.getHeight()-3);
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getSource() == tilbakeknapp)
		{
			gui.VisForrigeBilde();
		}
		if(arg0.getSource() == playknapp)
		{
			gui.Play();
		}
		if(arg0.getSource() == pauseknapp)
		{
			gui.Pause();
		}
		if(arg0.getSource() == nesteknapp)
		{
			gui.VisNesteBilde();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		if(vis)
			laasVis = true;
		if(arg0.getSource() instanceof BildePanel)
			((BildePanel)arg0.getSource()).Highlight(true);
		repaint();
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		laasVis = false;
		if(arg0.getSource() instanceof BildePanel)
		{
			((BildePanel)arg0.getSource()).Highlight(false);
			((BildePanel)arg0.getSource()).Trykket(false);
		}
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		if(arg0.getSource() instanceof BildePanel)
			((BildePanel)arg0.getSource()).Trykket(true);
		repaint();
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		if(arg0.getSource() instanceof BildePanel)
			((BildePanel)arg0.getSource()).Trykket(false);
		repaint();
	}
}
