package klient.Gui.Paneler;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import klient.Gui.KlientGUI;

//TODO: Knappene/(panelene) (individuelt) har bakgrunn, ønsker å fjerne dette.
//TODO: Bildene er laget for høy oppløsning, lag de omigjen for lavere oppløsning (eller gjør knappene større(?)).
//TODO: Lage select-hover og confirm-click bilder.
public class MenyPanel extends JPanel implements MouseListener {

	private boolean vis;
	private boolean laasVis;
	private int widthButton = 110;
	private int heightButton = 80;
	private BildePanel tilbakeknapp;
	private BildePanel playknapp;
	private BildePanel pauseknapp;
	private BildePanel nesteknapp;
	private KlientGUI gui;
	
	public MenyPanel(KlientGUI g)
	{
		setPreferredSize(new Dimension((int)(widthButton * 4 * 1.6), (int)(heightButton * 1.6)));

		gui = g;

		
		try {
			tilbakeknapp = new BildePanel(ImageIO.read(getClass().getResource("/klient/Gui/Paneler/tilbakeKnappBilde.png")));
			playknapp = new BildePanel(ImageIO.read(getClass().getResource("/klient/Gui/Paneler/playKnappBilde.png")));
			pauseknapp = new BildePanel(ImageIO.read(getClass().getResource("/klient/Gui/Paneler/pauseKnappBilde.png")));
			nesteknapp = new BildePanel(ImageIO.read(getClass().getResource("/klient/Gui/Paneler/nesteKnappBilde.png")));
			
		} catch (IOException e) {
			tilbakeknapp = new BildePanel("<<");
			playknapp = new BildePanel("|>");
			pauseknapp = new BildePanel("||");
			nesteknapp = new BildePanel(">>");
		}
		
		tilbakeknapp.setPreferredSize(new Dimension(widthButton,heightButton));
		tilbakeknapp.addMouseListener(this);
		tilbakeknapp.addMouseMotionListener(gui);
		add(tilbakeknapp);

		playknapp.setPreferredSize(new Dimension(widthButton,heightButton));
		playknapp.addMouseListener(this);
		playknapp.addMouseMotionListener(gui);
		add(playknapp);

		pauseknapp.setPreferredSize(new Dimension(widthButton,heightButton));
		pauseknapp.addMouseListener(this);
		pauseknapp.addMouseMotionListener(gui);
		add(pauseknapp);

		nesteknapp.setPreferredSize(new Dimension(widthButton,heightButton));
		nesteknapp.addMouseListener(this);
		nesteknapp.addMouseMotionListener(gui);
		add(nesteknapp);

		addMouseListener(this);
		vis = false;
		laasVis = false;
	}

	public boolean SkalVises(boolean v)
	{
		boolean suksess = false;
		if(!laasVis)
		{
			suksess = true;
			vis = v;
			tilbakeknapp.SkalVises(v);
			playknapp.SkalVises(v);
			pauseknapp.SkalVises(v);
			nesteknapp.SkalVises(v);
		}
		repaint();

		return suksess;
	}

	@Override
	public void paintComponent(Graphics g)
	{
		if(vis)
		{
//			TODO: Viser bakgrunnspanel til knappene i samling, unødvendig?
			g.setColor(Color.darkGray);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.setColor(Color.black);
			g.drawRect(0, 0, this.getWidth()-1, this.getHeight()-1);
			g.setColor(Color.white);
			g.drawRect(1, 1, this.getWidth()-3, this.getHeight()-3);
		}
	}

	boolean over = false;

	@Override
	public void mouseClicked(MouseEvent arg0) {

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
		{
			BildePanel b = (BildePanel)arg0.getSource();
			if(b.erTrykket())
				if(b == tilbakeknapp)
				{
					gui.VisForrigeBilde();
				}
				else if(b == playknapp)
				{
					gui.Play();
				}
				else if(b == pauseknapp)
				{
					gui.Pause();
				}
				else if(b == nesteknapp)
				{
					gui.VisNesteBildePause();
				}
			b.Trykket(false);
		}
		repaint();
	}
}
