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

import klient.Klient;
import klient.Gui.Paneler.BildePanel;
import klient.Gui.Paneler.MenyPanel;


public class KlientGUI extends JPanel implements ActionListener, MouseMotionListener, MouseListener {

	private Klient vindu;

	private String[] tags;
	private int visning;
	private BildeBuffer bilder;
	private Thread bbtraad;
	boolean loginikkelest = false;
	boolean loginpaagaar = false;

	private int guiModus;
	private Timer timer;
	private MenyPanel meny;
	private BildePanel indikator;
	private JTextField passord = new JTextField(10);
	private JButton login = new JButton("Logg in");
	private JButton logut = new JButton("Logg ut");
	private JButton tilbake = new JButton("Tilbake");
	private Cursor gjennomsiktigPeker;

	public KlientGUI(Klient k)
	{
		vindu = k;
		this.setPreferredSize(new Dimension(getToolkit().getScreenSize().width,getToolkit().getScreenSize().height));

		tags = new String[1];
		tags[0] = "null";
		bilder = new BildeBuffer(1000);
		bbtraad = new Thread(bilder);
		bbtraad.start();

		gjennomsiktigPeker = getToolkit().createCustomCursor(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB), new Point(0, 0),"null");

		meny = new MenyPanel(this);
		meny.setPreferredSize(new Dimension(350,100));
		indikator = new BildePanel("");
		indikator.addMouseListener(this);
		indikator.setPreferredSize(new Dimension(50,50));
		login.addActionListener(this);
		logut.addActionListener(this);
		tilbake.addActionListener(this);

		meny.addMouseMotionListener(this);
		indikator.addMouseMotionListener(this);
		addMouseMotionListener(this);
		addMouseListener(this);
		timer = new Timer(2500, this);

		ByggGUI(1);
	}

	public void ByggGUI(int modusnr)
	{
		setCursor(Cursor.getDefaultCursor());
		loginikkelest = false;
		loginpaagaar = false;
		removeAll();
		this.setLayout(new GridBagLayout());
		GridBagConstraints k = new GridBagConstraints();
		if(modusnr == 1)
		{
			Play();
			guiModus = modusnr;
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
		else if(modusnr == 2 && (guiModus == 1 || guiModus == 2))
		{
			Pause();
			guiModus = modusnr;
			k.gridx = 0;
			k.gridy = 0;
			k.insets = new Insets(0,0,0,5);
			add(passord,k);
			k.insets = new Insets(0,5,0,5);
			k.gridx = 1;
			add(login,k);
			k.insets = new Insets(0,5,0,0);
			k.gridx = 2;
			add(tilbake,k);
		}
		else if(modusnr == 3 || (modusnr == 2 && guiModus == 3))
		{
			guiModus = modusnr;
			add(logut,k);
		}
		vindu.pack();
		vindu.requestFocus();
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

	private void VisNesteBilde()
	{
		visning++;
		if(visning>=bilder.Bufferedlength())
			visning = 0;
		repaint();
	}

	public void VisNesteBildePause()
	{
		Pause();
		visning++;
		if(visning>=bilder.Bufferedlength())
			visning = 0;
		repaint();
	}

	public void VisForrigeBilde()
	{
		Pause();
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

	public String sjekkLogin()
	{
		if(loginikkelest)
		{
			loginikkelest = false;
			return passord.getText();
		}
		else
			return "";
	}

	public void Login()
	{
		if(loginpaagaar)
			ByggGUI(3);
	}

	BufferedImage bilde = null;

	@Override
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		if(guiModus == 1)
		{
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
		else if (guiModus == 2)
		{

		}
		else if (guiModus == 3)
		{
			g.setColor(Color.white);
			g.drawRect(10, 10, 10, 10);
		}
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(guiModus == 1)
		{
			indikator.SkalVises(false);
			if(meny.SkalVises(false))
				setCursor(gjennomsiktigPeker);
			if(arg0.getSource() == timer)
				VisNesteBilde();
		}
		if(guiModus == 2)
		{
			if(arg0.getSource() == login)
			{
				loginikkelest = true;
				loginpaagaar = true;
			}
			else
				ByggGUI(1);
		}
		if(guiModus == 3)
		{
			if(arg0.getSource() == logut)
				ByggGUI(1);
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
			if(meny.SkalVises(false))
				setCursor(gjennomsiktigPeker);
			repaint();
		}
		else
			vindu.requestFocus();
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
