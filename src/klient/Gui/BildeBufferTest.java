package klient.Gui;

import java.net.MalformedURLException;
import java.net.URL;

public class BildeBufferTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		BildeBuffer b = new BildeBuffer(100);

		URL[] urls = new URL[5];
		try {
			urls[0] = new URL("http://t.facdn.net/10602790@400-1368613549.jpg");
			urls[1] = new URL("http://t.facdn.net/12069061@400-1384472441.jpg");
			urls[2] = new URL("http://t.facdn.net/11831897@400-1381785863.jpg");
			urls[3] = new URL("http://t.facdn.net/11041487@400-1373404117.jpg");
			urls[4] = new URL("http://t.facdn.net/11460782@400-1377561946.jpg");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		System.out.print((b.length() == 0) + " ");
		System.out.print((b.Bufferedlength() == 0) + " ");
		System.out.print((b.erAlleLastet() == true) + " ");
		System.out.println(b.HentBilde(0) == null);

		Thread t = new Thread(b);
		t.start();

		for(int j = 0; j<10; j++)
		{
			b.Oppdater(urls);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.print((b.length() == 5) + " ");
			System.out.print((b.Bufferedlength() == 0) + " ");
			System.out.print((b.erAlleLastet() == false) + " ");
			for(int i = 0; i<b.length(); i++)
				System.out.print((b.HentBilde(i) == null) + " ");
			System.out.println("");
			while(!b.erAlleLastet())
			{
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.print(b.Bufferedlength() + " ");
			}
			System.out.println("");
			System.out.print((b.length() == 5) + " ");
			System.out.print((b.Bufferedlength() == 5) + " ");
			System.out.print((b.erAlleLastet() == true) + " ");
			for(int i = 0; i<b.length(); i++)
				System.out.print((b.HentBilde(i) != null) + " ");
			System.out.println("");

			if(j%2 == 1)
			{
				b.Kast();
				try {
					Thread.sleep(200);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.print((b.length() == 0) + " ");
				System.out.print((b.Bufferedlength() == 0) + " ");
				System.out.print((b.erAlleLastet() == true) + " ");
				System.out.println(b.HentBilde(0) == null);
			}
		}
		System.exit(0);
	}

}
