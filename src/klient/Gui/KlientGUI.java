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
	private Thread bbtraad;

	private int guiModus;
	private Timer timer;
	private MenyPanel meny;
	private BildePanel indikator;
	private Cursor gjennomsiktigPeker;

	public KlientGUI()
	{
		this.setPreferredSize(new Dimension(getToolkit().getScreenSize().width,getToolkit().getScreenSize().height));

		tags = new String[1];
		tags[0] = "null";
		bilder = new BildeBuffer(1000);
		bbtraad = new Thread(bilder);
		bbtraad.start();

		addMouseMotionListener(this);
		addMouseListener(this);
		meny = new MenyPanel(this);
		meny.addMouseMotionListener(this);
		meny.setPreferredSize(new Dimension(350,100));
		indikator = new BildePanel("");
		indikator.addMouseMotionListener(this);
		indikator.addMouseListener(this);
		indikator.setPreferredSize(new Dimension(50,50));
		gjennomsiktigPeker = getToolkit().createCustomCursor(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB), new Point(0, 0),"null");

		ByggGUI(1);

		timer = new Timer(2500, this);
		Play();
	}

	public void ByggGUI(int modusnr)
	{
		removeAll();
		guiModus = modusnr;
		if(modusnr == 1)
		{
			this.setLayout(new GridBagLayout());
			GridBagConstraints k = new GridBagConstraints();
			k.gridx = 0;
			k.gridy = 0;
			k.weightx = 1.0;
			k.weighty = 1.0;
			k.anchor = GridBagConstraints.FIRST_LINE_START;
			k.insets = new Insets(25,25,0,0);
			add(indikator, k);
			k.gridx = 0;
			k.gridy = 1;
			k.weightx = 0;
			k.weighty = 1.0;
			k.anchor = GridBagConstraints.PAGE_END;
			add(meny, k);
		}
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

	public void GiBilder(URL[] linker)
	{
		bilder.Oppdater(linker);
		if(!bbtraad.isAlive())
		{
			bbtraad = new Thread(bilder);
			bbtraad.start();
		}
		visning = 0;
	}

	public void GiBilder(URL[] linker, int max)
	{
		if(linker.length < max)
			max = linker.length;
		URL[] tmp = new URL[max];
		for(int i=0; i<max;i++)
			tmp[i] = linker[i];
		GiBilder(tmp);
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
			Pause();
		else
			Play();
	}

	public void Play()
	{
		indikator.Oppdater("|>");
		indikator.SkalVises(true);
		repaint();
		timer.start();
	}

	public void Pause()
	{
		indikator.Oppdater("||");
		indikator.SkalVises(true);
		repaint();
		timer.stop();
	}

	BufferedImage bilde = null;

	@Override
	public void paintComponent(Graphics g)
	{
		if(guiModus == 1)
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
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(guiModus == 1)
		{
			meny.SkalVises(false);
			indikator.SkalVises(false);
			setCursor(gjennomsiktigPeker);
			if(arg0.getSource() == timer)
				VisNesteBilde();
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(guiModus == 1)
		{
			meny.SkalVises(true);
			setCursor(Cursor.getDefaultCursor());
			repaint();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(guiModus == 1)
		{
			meny.SkalVises(false);
			setCursor(gjennomsiktigPeker);
			repaint();
		}
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
