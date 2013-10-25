package klient.Gui;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class BildeBufferAdmin implements Runnable
{

	private URL[] linker;
	private URL[] innlinker;
	private boolean[] inkludert;
	private BufferedImage[] bilder;
	private int antall;
	private int idletid;
	private KlientGUI gui;
	private boolean oppdater = false;

	public BildeBufferAdmin(int ventetid, KlientGUI g)
	{
		innlinker = new URL[0];
		inkludert = new boolean[0];
		idletid = ventetid;
		antall = 0;
		oppdater = true;
		gui = g;
	}

	public void Oppdater(URL[] l, boolean[] i)
	{
		if(l.length == i.length)
		{
			innlinker = l;
			inkludert = i;
			oppdater = true;
		}
	}

	public void Kast()
	{
		innlinker = new URL[0];
		inkludert = new boolean[0];
		bilder = new BufferedImage[bilder.length];
		oppdater = true;
	}

	public BufferedImage HentBilde(int nr)
	{
		if(nr<antall)
			return bilder[nr];
		else
			return null;
	}

	public String HentFilnavn(int nr)
	{
		if(nr<innlinker.length)
		{
			if(innlinker[nr] != null)
			{
				String tmp[] = innlinker[nr].getFile().split("/");
				return tmp[tmp.length-1];
			}
			else
				return null;
		}
		else
			return null;
	}

	public boolean erInkludert(int nr)
	{
		if(nr<innlinker.length)
			return inkludert[nr];
		else
			return false;
	}

	public int length()
	{
		if(innlinker != null)
			return innlinker.length;
		else
			return 0;
	}

	@Override
	public void run() {
		int timeout = 0;
		while(true)
		{
			if(oppdater)
			{
				antall = 0;
				timeout = 0;
				linker = innlinker;
				bilder = new BufferedImage[linker.length];
				oppdater = false;
			}
			if(antall < linker.length)
				try {
					bilder[antall] = ImageIO.read(linker[antall]);
					antall++;
					timeout = 0;
					gui.repaint();
				} catch (IOException e) {
					timeout++;
					System.out.println("Feil under lasting av bilde " + antall + " fra " + linker[antall].getProtocol() + "://" + linker[antall].getHost() + linker[antall].getPath());
					if (timeout>10)
					{
						System.out.println("Gir opp å laste bilde " + antall);
						antall++;
						timeout = 0;
					}
				}
			else
				try {
					Thread.sleep(idletid);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	public boolean Laster(int nr) {
		return nr == antall;
	}

	public boolean erFeilet(int nr) {
		return (nr<antall && bilder[nr] == null);
	}
}
