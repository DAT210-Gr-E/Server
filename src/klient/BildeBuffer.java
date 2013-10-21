package klient;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class BildeBuffer implements Runnable{

	private URL[] innkommendelinker;
	private URL[] linker;
	private BufferedImage[] bilder;
	private int antall;
	private int idletid;
	private boolean oppdatering;

	BildeBuffer(int ventetid)
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
	
	public BufferedImage HentBilde(int nr)
	{
		if(nr<antall)
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
		return antall;
	}

	@Override
	public void run() {
		while(true)
		{
			if(oppdatering)
			{
				antall = 0;
				linker = innkommendelinker;
				bilder = new BufferedImage[linker.length];
				oppdatering = false;
			}
			if(antall < linker.length)
				try {
					bilder[antall] = ImageIO.read(linker[antall]);
					System.out.println("Bilde "+ antall + " lastet!");
					antall++;
				} catch (IOException e) {
					System.out.println("Feil under lesing av bilde på " + linker[antall].getPath());
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
}
