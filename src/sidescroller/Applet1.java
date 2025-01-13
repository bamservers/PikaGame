package sidescroller;
import java.awt.*;
import java.awt.image.*;
import java.applet.*;

public class Applet1 extends Applet implements ImageObserver
	{
	//public static AudioClip StartSound;
	public static Image bg; // The background image
	public static Image pika_buffer;
	public static Image pikaright[]=new Image [4];
	public static Image pikaleft[]=new Image [4];
	public static Image enemy[]= new Image [4];
	public static Image enem_buffer;
	public static Image block;
	public static Image cup;
	public static Image EndScreen;
	public static Image WinScreen;
	public static Image StartScreen;
	public static int bgPos = 0; // Holds the background's x co-ordinate
	public static int pikax =0; // Pika's x co-ordinate
	public static int pikay =0; // Pika's y co-ordinate
	public static int enx =0; // Enemy's x co-ordinate
	public static int eny =0; // Enemy's y co-ordinate
	public static String PikaAni = new String ("wr"); // tells if Pikachu is walking left or right
	public char lastinput; // the last command entered (used for jumping)
	public char input; // Keeps the user's input
	public static String map1 [] = new String [11];
	public static String map2 [] = new String [11];
	public int PikaSpeed = 0;
	public static Graphics offscreenG;
	public static Image offscreenImage;
	public static boolean isFalling=false;
	public static boolean CanGoLeft=true;
	public static boolean CanGoRight=true;
	public static int Jump;
	public static boolean CanJump = true;
	public static boolean dead = false;
	public static boolean win = false;
	public boolean startup = true;
	public Thread simpleThread;
	simpleThread th1=new simpleThread(this);
	public Timer t = new Timer ();
	
	public void destroy ()
		{
		th1.stop();
		t.stop();
		}
	public void init()
		{
		// the map is 450 long and 11 high (each block is 20x20)
		// Thread runner = new Thread (new run());
		// runner.start();
		map1 [0] =  "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000111111111111111111111111111111111111111111000000000000000000000000000000000000000000000000000000000000000000000000000000111111111111111111111111111111";
		map1 [1] =  "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000111111111111111111111111111111111111111111000000000000000000000000000000000000000000000000000000000000000000000000000000111111111111111111111111111111";
		map1 [2] =  "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000111111111111000000000111111111111111111111000000000000000000000000000000000000000000000000000000000000000000000000000000111111111111111111111111111111";
		map1 [3] =  "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000111111100000000000000000001111111111111111000000000000000000000000000000000000000000000000000000000000000000000000000000111111111111111111111111111111";
		map1 [4] =  "0000000000000000000000000000000000000011000001111111111100000000000000000000000000000000000000000000000000000000110000000000000000000000000000011111111111000000000000000000000000000000011111100000000000000000000000000000000000000000111111111111111111111111111111";
		map1 [5] =  "0000000000000000000000000000000000000110000000000000000000000000000000000000000000011110000111111111100000000000000000000000000111110000000000000000000000000000000000000000000000000000000000000000000000000100000000000000000000000000111111111111111111111111111111";
		map1 [6] =  "0000000000000000000000011100001111111100000000000000000000000000000000000000001100000000000000000000000000000000000000000011111111111111100000000000000000000000000000000001111111110000000000000000000000001100000001100000000000000000111111111111111111111111111111";
		map1 [7] =  "0000000000000000000000110000000000000000000000000000000000000000000000000000011100000000000000000000000000000000000001111111111111111111111111000000000000000000000000000000000000000000000000000000000000011100000001110000000000000200111111111111111111111111111111";
		map1 [8] =  "0000000000000000000001110000000000000000000000000000000000000000000000000000011100000000000000000000000000000000111111111111111111111111111111111111111111111111111000000000000000000000000000000000000000111100000001111000000000000000111111111111111111111111111111";
		map1 [9] =  "1111111110000111111111111111111111111111111111111111111111111111100001111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";
		map1 [10] = "1111111110000111111111111111111111111111111111111111111111111111100001111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";

//		map2 [0] =  "000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000011111111111111111111111111111111111111111100000000000000000000000000000000000000000000000000000000000000000000000000000011100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
//		map2 [1] =  "000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000011111111111111111111111111111111111111111100000000000000000000000000000000000000000000000000000000000000000000000000000011100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
//		map2 [2] =  "000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000011111111111111111111111111111111111111111100000000000000000000000000000000000000000000000000000000000000000000000000000011100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
//		map2 [3] =  "000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000011111111111111111111111111111111111111111100000000000000000000000000000000000000000000000000000000000000000000000000000011100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
//		map2 [4] =  "000000000000000000000000000000000000001100000111111111110000000000000000000000000000000000000000000000000000000011111111111111111111111111111111111111111100000000000000000000000000000001111110000000000000000000000000000000000000000011100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
//		map2 [5] =  "000000000000000000000000000000000000011000000000000000000000000000000000000000000001111000011111111110000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000010000000000000000000000000011100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
//		map2 [6] =  "000000000000000000000001110000111111110000000000000000000000000000000000000000110000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000111111111000000000000000000000000110000000110000000000000000011100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
//		map2[7] =  "000000000000000000000011000000000000000000000000000000000000000000000000000001110000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001110000000111000000000000020011100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
//		map2 [8] =  "000000000000000000000111000000000000000000000000000000000000000000000000000001110000000000000000000000000000000011111111111111111111111111111111111111111111111111100000000000000000000000000000000000000011110000000111100000000000000011100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
//		map2 [9] =  "111111111000011111111111111111111111111111111111111111111111111110000111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";
//		map2 [10] = "111111111000011111111111111111111111111111111111111111111111111110000111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";

		cup = getImage(getCodeBase(), "images/cup.gif");
		bg = getImage(getCodeBase(), "images/background1.gif");
		block = getImage(getCodeBase(), "images/block.gif");//block
		pikaright[0] = getImage(getCodeBase(), "images/q1.gif");
		pikaright[1] = getImage(getCodeBase(), "images/q2.gif");
		pikaright[2] = getImage(getCodeBase(), "images/q3.gif");
		pikaright[3] = getImage(getCodeBase(), "images/q4.gif");
		pikaleft[0] = getImage(getCodeBase(), "images/p1.gif");
		pikaleft[1] = getImage(getCodeBase(), "images/p2.gif");
		pikaleft[2] = getImage(getCodeBase(), "images/p3.gif");
		pikaleft[3] = getImage(getCodeBase(), "images/p4.gif");
		enemy [0] = getImage (getCodeBase(), "images/star4.gif");
		enemy [1] = getImage (getCodeBase(), "images/star3.gif");
		enemy [2] = getImage (getCodeBase(), "images/star2.gif");
		enemy [3] = getImage (getCodeBase(), "images/star1.gif");
		EndScreen = getImage (getCodeBase(), "images/ending.gif");
		WinScreen = getImage (getCodeBase(), "images/winning.gif");
		StartScreen = getImage (getCodeBase(), "images/splash.gif");
		//StartSound = getAudioClip (getCodeBase(), "sounds/pikastart3.wav");
//		StartScreen = getImage (getCodeBase(), "images/char_025.gif");
		//StartSound.loop();
		
		offscreenImage = createImage(320, 220);
		offscreenG = offscreenImage.getGraphics();
		offscreenG.drawString ("Loading...", 100, 100);
		repaint();
		prepareImage (block, this);
		prepareImage (pikaright[0], this);
		prepareImage (bg, this);
		prepareImage (EndScreen, this);
		prepareImage (WinScreen, this);
		prepareImage (StartScreen, this);

		requestFocus(); // gives applet focus
		pika_buffer=pikaright[0];
		Jump = 0;
		bgPos = 0;
		pikax = 0;
		enx = 300;
		eny = 10;
		pikay=50;//135
		PikaAni = new String ("wr");
		PikaSpeed = 8;
		isFalling=false;
		CanGoLeft=true;
		CanGoRight=true;
		CanJump = true;
		dead = false;
		win = false;
		th1.start();
 		}
	
	public boolean keyDown(Event evt, int key)
		{
        char currkey;
		switch(key)
			{
			default: currkey = (char)key;
			}
		input = currkey;
		if (t.started == false)
			{
			t.start();
			t.started = true;
			
			}
		startup = false;
		// Right
		if(input == 1007 && CanGoRight == true) 
			{
			if (PikaAni == "wl") pikax -= PikaSpeed;
			PikaAni = "wr";
			
			if (Jump == 0&& isFalling ==false) Pikachu.animation();
			
			pika_buffer=pikaright[Pikachu.picnum];
			if (pikax>=120) bgPos += PikaSpeed; //pikapos+=5;
			else pikax += PikaSpeed;
			}
		// Up
		if(input == 1004 && Jump == 0 && CanJump == true && isFalling ==false) 
		{
			Jump = 1;
			Pikachu.picnum = 2;
		}
		// Down
		if(input == 1005) ;
		// Left
		if(input == 1006 && CanGoLeft == true) 
			{
			if (PikaAni == "wr") pikax += PikaSpeed;
			PikaAni = "wl";
			if (Jump == 0&& isFalling ==false)Pikachu.animation();
			pika_buffer=pikaleft[Pikachu.picnum];
			if (pikax<=50)
				{
				if (bgPos>0) bgPos -= PikaSpeed;
				//pikapos+=5;
				}
			else pikax -= PikaSpeed;
			}
		HitEnemy();
		HasCup();
		repaint ();
		return true;
		}
	
	public void paint(Graphics g)
		{
		if (startup)
			{
			offscreenG.drawImage (StartScreen, 0, 0, this);
			}
		else if (dead == true)
			{
			offscreenG.setColor (new Color(255, 255, 255));
			offscreenG.fillRect (0, 0, 320, 240);
			offscreenG.drawImage (EndScreen, 0, 0, this);
			t.stoptimer();
			}
		else if (win == true)
			{
			offscreenG.setColor (new Color(255, 255, 255));
			offscreenG.fillRect (0, 0, 320, 240);
			offscreenG.drawImage (WinScreen, 0, 0, this);
			t.stoptimer();
			}
		else
			{
			offscreenG.drawImage(bg, -bgPos, 0, this);
			DrawBlocks(offscreenG, map1);
			
			int tmp = 0;
			if (PikaAni.equalsIgnoreCase ("wl"))
				{
				tmp = 20;
				
				}
			//offscreenG.drawRect (pikax + 40 - tmp, pikay + 23, 30, 20);
			//offscreenG.drawRect (enx - bgPos, eny, 30, 30);
			
			offscreenG.drawImage(pika_buffer, pikax, pikay, this);
			offscreenG.drawImage(enem_buffer, enx - bgPos, eny, this);
			}
			offscreenG.setFont (new Font ("Times New Roman", Font.BOLD, 20));
		if (startup == false)offscreenG.drawString (t.min + ":" + t.sec + ":" + t.milisec, 0, 15);
		g.drawImage(offscreenImage, 0, 0, this);
		}
	
	public static void HitEnemy ()
		{
		int tmp = 0;
		if (PikaAni.equalsIgnoreCase ("wl"))
			{
			tmp = 20;
			
			}
		//if (pikax + bgPos < enx && pikax + 50 + bgPos > enx)
		if (pikax + 40 + bgPos - tmp< enx  + 30&& pikax + 40 + 20 + bgPos - tmp> enx)
			{
			//if (pikay < eny && pikay + 50 > eny)
			if (pikay + 23 + 5 < eny + 30 && pikay + 23 + 20 - 5> eny)
				{
				dead = true;
				}
			}
		}

	public void DrawBlocks(Graphics g, String m [])
		{
		
		int l_max = (int) ((pikax + 350 + bgPos) /20);
		int l_min = (int) ((pikax - 350 + bgPos) / 20);
		for (int y = 0; y < 11;y++)
			{
			for (int x = 0;x < l_max;x++)
				{
				if (m [y].charAt(x) == '1') 
					g.drawImage(block, x * 20 - bgPos, y * 20, this);
				else if (m [y].charAt(x) == '2') 
					g.drawImage(cup, x * 20 - bgPos, y * 20, this);
				}
			}
		}
	
	public void update(Graphics g) 
		{paint(g);}
	
	public void HasCup ()
		{
		double tempx1,tempx2,tempy;
		int	arraynumx1, arraynumx2, arraynumy, temp, backwards;
		// these 2 lines fix the falling problem when Pika walks left
		backwards = 0;
		if (Applet1.PikaAni == "wl") backwards = 20;
		
		tempx1=(double)(pikax + bgPos + 40 - backwards)/20;
		tempx2=(double)(pikax + bgPos + 60 - backwards)/20;
		tempy =(double) (pikay + 5)/20;
		arraynumx1=(int) tempx1;
		arraynumx2=(int) tempx2;
		arraynumy=(int) tempy + 1;
			
		for (int x = arraynumx2 - 1; x < arraynumx2 + 1;x++)
			{
			for (int y = arraynumy - 2; y < arraynumy + 2;y++)
				{
				if (map1[y].charAt (x) == '2') win = true;
				//offscreenG.drawString ("x " + x, 0, 120);
				}
			}
		}
	}

class Enemy 
	{
	public static int picnum = 0;
	public static void animation ()
		{
		if (picnum >= 3)picnum = 0;
		else picnum++;
		}
	}
class Pikachu 
	{
	public static int picnum = 0;
	public static void animation ()
		{
		if (picnum >= 3)picnum = 0;
		else picnum++;
		}
	}

class simpleThread extends Thread
	{
	Applet1 parent;
	public simpleThread (Applet1 f) {parent = f;}
	
	public void run()
		{
		//20 48
		
		while (true)
			{
			double tempx1,tempx2,tempy;
			int	arraynumx1, arraynumx2, arraynumy, temp, backwards;
			// these 2 lines fix the falling problem when Pika walks left
			backwards = 0;
			if (Applet1.PikaAni == "wl") backwards = 20;
			
			tempx1=(double)(Applet1.pikax + Applet1.bgPos + 40 - backwards)/20;
			tempx2=(double)(Applet1.pikax + Applet1.bgPos + 60 - backwards)/20;
			tempy =(double) (Applet1.pikay + 5)/20;
			arraynumx1=(int) tempx1;
			arraynumx2=(int) tempx2;
			arraynumy=(int) tempy + 1;
															//9
			if (arraynumy >= 10) Applet1.dead = true;
			else 
				{
				if (Applet1.map1[arraynumy + 1].charAt(arraynumx1)!='1' && Applet1.map1[arraynumy + 1].charAt(arraynumx2)!='1')
					Applet1.isFalling=true;
				else Applet1.isFalling = false;
				if (Applet1.isFalling == true) Applet1.pikay += 5;
				
				tempx1=(double)(Applet1.pikax + Applet1.bgPos + 60)/20;
				tempx2=(double)(Applet1.pikax + Applet1.bgPos + 35)/20;
				arraynumx1=(int) tempx1;
				arraynumx2=(int) tempx2;
				
				
				if (Applet1.map1[arraynumy].charAt(arraynumx1 + 1)!='1') Applet1.CanGoRight = true;
				else Applet1.CanGoRight = false;
				
				if (Applet1.map1[arraynumy].charAt(arraynumx2 - 1)!='1') Applet1.CanGoLeft = true;
				else Applet1.CanGoLeft = false;
				
				if (arraynumy -2 <= 0) Applet1.Jump = 0;
				else
					{
					if (Applet1.Jump >= 1 && Applet1.map1[arraynumy - 2].charAt(arraynumx2)=='0')
						{
						Applet1.pikay-= 15;
						Applet1.Jump += 3;
						}
					else Applet1.Jump = 0;
					}
				if (Applet1.Jump >= 20)
					{
					Applet1.Jump = 0;//Pika is done jumping, he'll fall now
					}
				if (arraynumy > 1)
					{
					if (Applet1.PikaAni == "wr")
						{
						if (Applet1.map1[arraynumy - 2].charAt(arraynumx1)=='1' && 
							//map1[arraynumy - 2].charAt(arraynumx1 + 1)=='1' &&
							Applet1.map1[arraynumy - 3].charAt(arraynumx1 + 4)=='1') Applet1.CanJump = false;
						else Applet1.CanJump = true;
						}
					else if (Applet1.PikaAni == "wl")
						{
						if (Applet1.map1[arraynumy - 2].charAt(arraynumx1)=='1' && 
							Applet1.map1[arraynumy - 2].charAt(arraynumx1 - 1)=='1' &&
							Applet1.map1[arraynumy - 2].charAt(arraynumx1 - 2)=='1') Applet1.CanJump = false;
						else Applet1.CanJump = true;
						}
					}
				}
			if (Applet1.enx <= Applet1.bgPos - 60)
				{
				Applet1.eny = (int)(Math.random() * 200);
				Applet1.enx = 330 + Applet1.bgPos; 
				}
			Enemy.animation();
			Applet1.enem_buffer=Applet1.enemy[Enemy.picnum];
			
			if (parent.startup == false) Applet1.enx -= 5;
			Applet1.HitEnemy();// collision detection with enemy
			parent.repaint();
			try {simpleThread.sleep (100);}
			catch (InterruptedException e){}
			}
		}	
	}	

class Timer extends Thread implements Runnable
{
	int milisec = 0;
	int sec = 0;
	int min = 0;
	boolean started = false;
	boolean on = true;
	public Applet1 parent;
	
	public void Timer (Applet1 f)
		{
		parent = f;
		}
	
	public void run ()
		{
		while (on)
			{
			try {simpleThread.sleep (90);}
			catch (InterruptedException e){}
			milisec += 1;
			if (milisec > 9)
				{
				milisec = 0;
				sec += 1;
				}
			if (sec > 59)
				{
				min += 1;
				sec = 0;
				}
			if (min > 9)
				{
				parent.dead = true;
				
				}
			}
		}
		
	public void stoptimer ()
		{
		on = false;
		}
}
