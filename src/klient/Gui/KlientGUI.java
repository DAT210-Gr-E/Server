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
import klient.Gui.Paneler.*;

public class KlientGUI extends JPanel implements ActionListener, MouseMotionListener, MouseListener {

	private Klient vindu;

	////////////////////////////////////////////////////////////////////////////// Disse vil antakelig være linket til Jtextfields
	private String[] tags = {"null"};
	private String[] admintags = {"null"};
	
	private int defaulttid = 2500;
	private String[] defaulttags = {};
	
	boolean[] forespoerseler = new boolean[7];
	private int visning;
	private BildeBuffer bilder;
	private Thread bbtraad;
	private BildeBufferAdmin adminbilder;
	private Thread abbtraad;
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
	private BildevelgerPanel valgliste;
	private boolean klokkekjoere = true;

	public KlientGUI(Klient k)
	{
		vindu = k;
		this.setPreferredSize(new Dimension(getToolkit().getScreenSize().width,getToolkit().getScreenSize().height));

		bilder = new BildeBuffer(1000);
		bbtraad = new Thread(bilder);
		bbtraad.start();
		adminbilder = new BildeBufferAdmin(1000, this);
		abbtraad = new Thread(adminbilder);
		abbtraad.start();
		valgliste = new BildevelgerPanel(adminbilder);
		forespoerseler[0] = true;
		
		gjennomsiktigPeker = getToolkit().createCustomCursor(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB), new Point(0, 0),"null");

		meny = new MenyPanel(this);
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
		forespoerseler[3] = false;
		loginpaagaar = false;
		removeAll();
		setLayout(new GridBagLayout());
		passord.setText("");
		GridBagConstraints k = new GridBagConstraints();
		if(modusnr == 1)
		{
			if(klokkekjoere)
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
			timer.stop();
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
			k.gridx = 0;
			k.gridy = 0;
			k.weightx = 1;
			k.weighty = 1;
			k.fill = GridBagConstraints.BOTH;
			add(valgliste,k);
			k.weightx = 0;
			k.weighty = 1;
			k.gridx = 1;
			k.gridy = 0;
			k.ipadx = 300;
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

	public String[] LesAdminTags()
	{
		return admintags;
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
	}
	
	public void GiBilder(URL[] linker, boolean[] inkludert)
	{
		
		adminbilder.Kast();
		adminbilder.Oppdater(linker, inkludert);
		valgliste.Rebuild();
		if(!abbtraad.isAlive())
		{
			abbtraad = new Thread(adminbilder);
			abbtraad.start();
		}
		ByggGUI(guiModus);
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
			if(bilder.Bufferedlength() != 0)
				visning = bilder.Bufferedlength()-1;
			else
				visning = 0;
		repaint();
	}

	public void PlayPause()
	{
		if(klokkekjoere)
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
		klokkekjoere=true;
	}

	public void Pause()
	{
		indikator.Oppdater("||");
		indikator.SkalVises(true);
		repaint();
		timer.stop();
		klokkekjoere=false;
	}

	public String sjekkLogin()
	{
		if(loginpaagaar)
			return passord.getText();
		else
			return "";
	}

	public void Login(String p)
	{
		if(loginpaagaar && p.equals(passord.getText()))
		{
			admintags = defaulttags;
			forespoerseler[4] = true;
			ByggGUI(3);
		}
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
				forespoerseler[3] = true;
				loginpaagaar = true;
				//Login(passord.getText()); ////////////////////////////////////////////////////////////////////// Test
			}
			else
				ByggGUI(1);
		}
		if(guiModus == 3)
		{
			if(arg0.getSource() == logut)
				ByggGUI(1);
		}
		vindu.requestFocus();
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

	public void setTimer(int t) {
		boolean kjoerer = false;
		if (timer.isRunning())
		{
			kjoerer = true;
			timer.stop();
		}
		defaulttid = t;
		timer = new Timer(t, this);
		if(kjoerer)
			timer.start();
	}

	public boolean erForespoersel(int i) {
		boolean tmp = forespoerseler[i];
		forespoerseler[i] = false;
		return tmp;
	}

	public int LesTid() {
		return defaulttid;
	}

	public boolean[] lesInkluderte() {
		////////////////////////////////////////////////////////////////////////////////////////
		return null;
	}

	public URL[] getAdminUrls() {
		////////////////////////////////////////////////////////////////////////////////////////
		return null;
	}

	public void setDefaultTags(String[] intags) {
		defaulttags = intags;
	}

}
