package klient.Gui;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class BildeBuffer implements Runnable
{

	private URL[] innkommendelinker;
	private URL[] linker;
	private BufferedImage[] bilder;
	private int antallfeil;
	private int antall;
	private int idletid;
	private boolean oppdatering;

	public BildeBuffer(int ventetid)
	{
		linker = new URL[0];
		idletid = ventetid;
		oppdatering = false;
		antall = 0;
	}
	
	public void Oppdater(URL[] l)
	{
		innkommendelinker = l;
		oppdatering = true;
	}
	
	public void Kast()
	{
		innkommendelinker = new URL[0];
		oppdatering = true;
	}
	
	public BufferedImage HentBilde(int nr)
	{
		if(nr<antall-antallfeil && nr>= 0)
			return bilder[nr];
		else
			return null;
	}
	
	public int length()
	{
		if(linker != null)
			return linker.length;
		else
			return 0;
	}
	
	public int Bufferedlength()
	{
		return antall-antallfeil;
	}

	@Override
	public void run() {
		int timeout = 0;
		while(true)
		{
			if(oppdatering)
			{
				antall = 0;
				antallfeil = 0;
				timeout = 0;
				linker = innkommendelinker;
				bilder = new BufferedImage[linker.length];
				oppdatering = false;
			}
			if(antall < linker.length)
				try {
					bilder[antall-antallfeil] = ImageIO.read(linker[antall]);
					antall++;
					timeout = 0;
				} catch (IOException e) {
					timeout++;
					System.out.println("Feil under lasting av bilde " + antall + " fra " + linker[antall].getProtocol() + "://" + linker[antall].getHost() + linker[antall].getPath());
					if (timeout>10)
					{
						System.out.println("Gir opp å laste bilde " + antall);
						antall++;
						antallfeil++;
						timeout = 0;
					}
				}
			//else
				try {
					Thread.sleep(idletid);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}
