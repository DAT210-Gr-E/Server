package klient.Gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.swing.*;

import klient.Gui.Paneler.BildePanel;
import klient.Gui.Paneler.MenyPanel;


public class KlientGUI extends JPanel implements ActionListener, MouseMotionListener, MouseListener {

	private String[] tags;
	private int visning;
	private BildeBuffer bilder;
	Thread bbtraad;
	
	private Timer timer;
	private MenyPanel meny;

	public KlientGUI()
	{
		this.setPreferredSize(new Dimension(500,500));
		
		bilder = new BildeBuffer(1000);
		bbtraad = new Thread(bilder);
		bbtraad.start();

		addMouseMotionListener(this);
		addMouseListener(this);
		meny = new MenyPanel(this);
		meny.addMouseMotionListener(this);
		meny.setPreferredSize(new Dimension(300,100));
		
		ByggGUI(1);
		
		timer = new Timer(2500, this);
		timer.start();
	}
	
	public void ByggGUI(int modusnr)
	{
		removeAll();
		if(modusnr == 1)
			add(meny);
		repaint();
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
		bilder.Oppdater(l);
		if(!bbtraad.isAlive())
		{
			bbtraad = new Thread(bilder);
			bbtraad.start();
		}
		visning = 0;
	}

	public void VisNesteBilde()
	{
		visning++;
		if(visning>=bilder.Bufferedlength())
			visning = 0;
		repaint();
	}
	
	public void VisForrigeBilde()
	{
		visning--;
		if(visning<0)
			visning = bilder.Bufferedlength()-1;
		repaint();
	}

	public void PlayPause()
	{
		if(timer.isRunning())
			timer.stop();
		else
			timer.start();
	}
	
	public void Play()
	{
		timer.start();
	}
	
	public void Pause()
	{
			timer.stop();
	}
	
	BufferedImage bilde = null;
	
	@Override
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		BufferedImage tmpbilde = bilder.HentBilde(visning);
		if(tmpbilde != null)
			bilde = tmpbilde;
		if(bilde != null)
			if(bilde.getHeight()*this.getWidth() > this.getHeight()*bilde.getWidth())
				g.drawImage(bilde, (this.getWidth()-bilde.getWidth()*this.getHeight()/bilde.getHeight())/2, 0, bilde.getWidth()*this.getHeight()/bilde.getHeight(), this.getHeight(), this);
			else
				g.drawImage(bilde, 0, (this.getHeight()-bilde.getHeight()*this.getWidth()/bilde.getWidth())/2, this.getWidth(), bilde.getHeight()*this.getWidth()/bilde.getWidth(), this);
		else
		{
			g.setColor(Color.white);
			g.drawString("Laster bilder fra nettet, venligst vent...", (this.getWidth()/2)-100, (this.getHeight()/2)+6);
		}
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		meny.SkalVises(false);
		if(arg0.getSource() == timer)
			VisNesteBilde();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		meny.SkalVises(true);
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		meny.SkalVises(false);
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
