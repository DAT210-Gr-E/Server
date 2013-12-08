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
import klient.Defs.*;
import klient.Gui.Paneler.*;

public class KlientGUI extends JPanel implements ActionListener, MouseMotionListener, MouseListener {

	private Klient vindu;

	boolean[] forespoerseler = new boolean[8];
	private int visning;
	private BildeBuffer bilder;
	private Thread bbtraad;
	private BildeBufferAdmin adminbilder;
	private Thread abbtraad;
	boolean loginpaagaar = false;

	private GUI_Modus guiModus;
	private Timer timer;
	private MenyPanel meny;
	private BildePanel indikator;
	private JTextField passord = new JTextField(10);
	private JTextPane defaultsoek = new JTextPane();
	private JScrollPane defaultsoekpane = new JScrollPane(defaultsoek);
	private JSlider defaulttidbar = new JSlider(SwingConstants.HORIZONTAL,1,20,5);
	private JButton login = new JButton("Logg inn");
	private JButton logut = new JButton("Logg ut");
	private JButton soekknapp = new JButton("Last bilder fra Server");
	private JButton defaultknappl = new JButton("Last Default tags");
	private JButton defaultknapps = new JButton("Lagre Default tags");
	private JButton tidknappl = new JButton("Last Default tid");
	private JButton tidknapps = new JButton("Lagre Default tid");
	private JButton lagreknapp = new JButton("Lagre endringer i svarteliste");
	private JButton tilbake = new JButton("Tilbake");
	private Cursor gjennomsiktigPeker;
	private BildevelgerPanel valgliste;
	private JScrollPane valglistepane;
	private boolean klokkekjoere = true;
	private int teller = 0;

	private final int bilderfoerbytt = 100;

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
		valglistepane = new JScrollPane(valgliste);
		forespoerseler[Forespoersel.TID] = true;
		forespoerseler[Forespoersel.BILDER] = true;

		gjennomsiktigPeker = getToolkit().createCustomCursor(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB), new Point(0, 0),"null");

		meny = new MenyPanel(this);
		indikator = new BildePanel("");
		indikator.addMouseListener(this);
		indikator.setPreferredSize(new Dimension(50,50));
		login.addActionListener(this);
		logut.addActionListener(this);
		tilbake.addActionListener(this);
		soekknapp.addActionListener(this);
		defaultknappl.addActionListener(this);
		defaultknapps.addActionListener(this);
		lagreknapp.addActionListener(this);
		tidknapps.addActionListener(this);
		tidknappl.addActionListener(this);

		defaultsoekpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		valglistepane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		valglistepane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		meny.addMouseMotionListener(this);
		indikator.addMouseMotionListener(this);
		addMouseMotionListener(this);
		addMouseListener(this);
		timer = new Timer(2500, this);

		ByggGUI(GUI_Modus.VISNING);
	}

	private void ByggGUI(GUI_Modus modusnr)
	{
		setCursor(Cursor.getDefaultCursor());
		forespoerseler[Forespoersel.LOGIN] = false;
		loginpaagaar = false;
		removeAll();
		setLayout(new GridBagLayout());
		passord.setText("");
		GridBagConstraints k = new GridBagConstraints();
		if(modusnr == GUI_Modus.VISNING)
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
		else if(modusnr == GUI_Modus.LOGIN && (guiModus == GUI_Modus.VISNING || guiModus == GUI_Modus.LOGIN))
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
		else if(modusnr == GUI_Modus.ADMIN || (modusnr == GUI_Modus.LOGIN && guiModus == GUI_Modus.ADMIN))
		{
			k.gridx = 0;
			k.gridy = 0;
			k.weightx = 1;
			k.weighty = 1;
			k.gridheight = GridBagConstraints.REMAINDER;
			k.fill = GridBagConstraints.BOTH;
			add(valglistepane,k);
			k.weightx = 0;
			k.weighty = 0;
			k.gridheight = 1;
			k.gridx = 1;

			k.insets = new Insets(50,0,0,0);
			k.fill = GridBagConstraints.HORIZONTAL;
			k.gridwidth = 2;
			k.gridy = 0;
			k.fill = GridBagConstraints.NONE;
			add(soekknapp,k);
			k.insets = new Insets(50,0,0,0);
			k.gridy = 1;
			k.fill = GridBagConstraints.HORIZONTAL;
			add(new JLabel("Defaulte tags:"),k);
			k.insets = new Insets(0,0,0,0);
			k.ipady = 80;
			k.gridy = 2;
			add(defaultsoekpane,k);
			k.ipady = 0;
			k.gridx = 1;
			k.gridy = 3;
			k.fill = GridBagConstraints.NONE;
			k.ipadx = 90;
			k.gridwidth = 1;
			add(defaultknappl,k);
			k.gridx = 2;
			k.ipadx = 0;
			k.fill = GridBagConstraints.HORIZONTAL;
			add(defaultknapps,k);
			k.gridx = 1;
			k.gridy = 4;
			k.gridwidth = 2;
			k.insets = new Insets(50,0,0,0);
			add(new JLabel("Default tid:"),k);
			k.insets = new Insets(0,0,0,0);
			k.gridy = 5;
			add(defaulttidbar,k);
			k.gridy = 6;
			k.insets = new Insets(0,17,0,0);
			add(new JLabel("1.0s"),k);
			k.insets = new Insets(0,55,0,0);
			add(new JLabel("2.0s"),k);
			k.insets = new Insets(0,93,0,0);
			add(new JLabel("3.0s"),k);
			k.insets = new Insets(0,131,0,0);
			add(new JLabel("4.0s"),k);
			k.insets = new Insets(0,169,0,0);
			add(new JLabel("5.0s"),k);
			k.insets = new Insets(0,207,0,0);
			add(new JLabel("6.0s"),k);
			k.insets = new Insets(0,245,0,0);
			add(new JLabel("7.0s"),k);
			k.insets = new Insets(0,283,0,0);
			add(new JLabel("8.0s"),k);
			k.insets = new Insets(0,321,0,0);
			add(new JLabel("9.0s"),k);
			k.insets = new Insets(0,0,0,0);
			k.ipady = 0;
			k.gridx = 1;
			k.gridy = 7;
			k.gridwidth = 1;
			add(tidknappl,k);
			k.gridx = 2;
			add(tidknapps,k);
			k.insets = new Insets(50,0,0,0);
			k.gridwidth = 2;
			k.gridx = 1;
			k.gridy = 8;
			add(lagreknapp,k);
			k.insets = new Insets(0,0,0,0);
			k.ipadx = 300;
			k.ipady = 40;
			k.gridy = 10;
			k.anchor = GridBagConstraints.PAGE_END;
			add(logut,k);
			guiModus = modusnr;
		}
		vindu.pack();
		vindu.setFocusable(true);
		vindu.requestFocus();
		repaint();
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

	public void GaaTilLogin() {
		ByggGUI(GUI_Modus.LOGIN);
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
		if(loginpaagaar)
			if(p.equals(passord.getText()))
			{
				forespoerseler[Forespoersel.TAGS] = true;
				forespoerseler[Forespoersel.ADMIN_BILDER] = true;
				ByggGUI(GUI_Modus.ADMIN);
			}
			else
				LoginFail();
	}

	public void LoginFail() {
		passord.setText("Feil Passord!");
	}

	BufferedImage bilde = null;

	@Override
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		if(guiModus == GUI_Modus.VISNING)
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
		else if (guiModus == GUI_Modus.LOGIN)
		{

		}
		else if (guiModus == GUI_Modus.ADMIN)
		{
			g.setColor(Color.lightGray);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
		}
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(guiModus == GUI_Modus.VISNING)
		{
			indikator.SkalVises(false);
			if(meny.SkalVises(false))
				setCursor(gjennomsiktigPeker);
			if(arg0.getSource() == timer)
				VisNesteBilde();
			if(bilder.erAlleLastet())
				teller++;
			if(teller > bilderfoerbytt)
			{
				forespoerseler[Forespoersel.TID] = true;
				forespoerseler[Forespoersel.BILDER] = true;
			}
		}
		if(guiModus == GUI_Modus.LOGIN)
		{
			if(arg0.getSource() == login)
			{
				forespoerseler[Forespoersel.LOGIN] = true;
				loginpaagaar = true;
			}
			else
				ByggGUI(GUI_Modus.VISNING);
		}
		if(guiModus == GUI_Modus.ADMIN)
		{
			if(arg0.getSource() == logut)
				ByggGUI(GUI_Modus.VISNING);
			if(arg0.getSource() == soekknapp)
				forespoerseler[Forespoersel.ADMIN_BILDER] = true;
			if(arg0.getSource() == defaultknappl)
				forespoerseler[Forespoersel.TAGS] = true;
			if(arg0.getSource() == defaultknapps)
				forespoerseler[Forespoersel.ADMIN_SET_TAGS] = true;
			if(arg0.getSource() == tidknappl)
				forespoerseler[Forespoersel.TID] = true;
			if(arg0.getSource() == tidknapps)
				forespoerseler[Forespoersel.ADMIN_SET_TID] = true;
			if(arg0.getSource() == lagreknapp)
				forespoerseler[Forespoersel.ADMIN_SET_SVARTELISTE] = true;

		}
		vindu.requestFocus();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(guiModus == GUI_Modus.VISNING)
		{
			meny.SkalVises(true);
			setCursor(Cursor.getDefaultCursor());
			repaint();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(guiModus == GUI_Modus.VISNING)
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

	public boolean erForespoersel(int i) {
		if(i<Forespoersel.ANTALL)
		{
			boolean tmp = forespoerseler[i];
			forespoerseler[i] = false;
			return tmp;
		}
		else return false;
	}

	//
	//
	// Getters og setters av default tid
	//
	//
	public int LesTid() {
		return defaulttidbar.getValue()*500;
	}

	public void setTid(int t) {
		teller = 0;
		boolean kjoerer = false;
		if (timer.isRunning())
		{
			kjoerer = true;
			timer.stop();
		}
		defaulttidbar.setValue(t/500);
		timer = new Timer(t, this);
		if(kjoerer)
			timer.start();
	}

	//
	//
	// Getters og setters for default tags
	//
	//
	public String[] LesDefaulttags() {
		return ListeTilTags(defaultsoek.getText());
	}

	public void setDefaultTags(String[] intags) {
		defaultsoek.setText(TagsTilListe(intags));
	}

	private String TagsTilListe(String[] intags) {
		String tmp = "";
		for(int i = 0; i<intags.length; i++)
			tmp = tmp + "#" + intags[i] + " ";
		return tmp;
	}

	private String[] ListeTilTags(String intags) {
		String[] tmp = intags.split("#");
		int a = 0;
		for(int i = 0; i<tmp.length; i++)
			if(tmp[i] != null)
				if(!tmp[i].trim().equals(""))
					tmp[a++] = tmp[i].trim();
		String[] tmp2 = new String[a];
		for(int i = 0; i<a; i++)
			tmp2[i] = tmp[i];
		return tmp2;
	}


	//
	//
	// Komunikasjon for oppdatering av svarteliste
	//  - Brukes ved forespoerseler[6] = true;
	//
	//
	public boolean[] lesInkluderte() {
		return valgliste.lesInkluderte();
	}

	public URL[] lesAdminUrls() {
		return valgliste.lesAdminUrls();
	}
}
