import javax.imageio.ImageIO; // It imports Image package to import the characters
import javax.swing.*; // Import the package to add button, title and border on the window of GUI
import java.io.*; // It imports the input/output package  
import java.awt.*; // To present the GUI to the user
import java.awt.event.*; // Package for performing an event (key or mouse) 
import java.awt.event.KeyAdapter; //Package for listening to the key pressed
import java.awt.event.KeyEvent; //Package for performing the event
import java.awt.image.BufferedImage; // Package for importing image
import sun.audio.*; // Package for playing the audio
import java.util.Random; //Packge for randamizing a variable

public class Key extends JPanel 
	{
		BufferedImage Background;
		BufferedImage TreasureOpened;
		BufferedImage bob;
		BufferedImage TreasureKey;
		
		BufferedImage Fruit1;
		BufferedImage Fruit2;
		BufferedImage Fruit3;
		
		public boolean up=false, down=false, right=false, left=false;
		public int x=32,y=620;
		Random r = new Random();
		Random r2 = new Random();
		int x1 = 1200;int y1 = 550;
		int count=0;
		int dangerCount=0;
		int i=1;
		int p = 500;
		int q = 550;
		int fruitCount=0;

		public Key(PlayRegion p1, Image i)
			{
				//FETCHING REQUIRED FILES
				try
				{
					Background=ImageIO.read(Image.class.getResourceAsStream("icons/MyMaze.png"));
					bob=ImageIO.read(Image.class.getResourceAsStream("icons/bob.png"));
					TreasureOpened =ImageIO.read(Image.class.getResourceAsStream("icons/TreasureOpened.png")); 			
					TreasureKey =ImageIO.read(Image.class.getResourceAsStream("icons/TreasureKey.png"));
					Fruit1 =ImageIO.read(Image.class.getResourceAsStream("icons/Fruit1.png")); 			
					Fruit2 =ImageIO.read(Image.class.getResourceAsStream("icons/Fruit2.png")); 			
					Fruit3 =ImageIO.read(Image.class.getResourceAsStream("icons/Fruit3.png")); 			
					
				}
				catch(Exception e)
				{
				System.out.println("FILE NOT FOUND, please relocate the file missing " );
				}
				
				//DELEGATION EVENT, INNER CLASS CONCEPT
	
				p1.addKeyListener(new KeyAdapter() 	
						{
						
						//when Navigation keys are pressed.	

							public void keyPressed(KeyEvent k1)
							{
								if(k1.getKeyCode()==KeyEvent.VK_UP)
								{
									up=true;
								}
								
								if(k1.getKeyCode()==KeyEvent.VK_DOWN)
								{
									down=true;
								}
									
								if(k1.getKeyCode()==KeyEvent.VK_RIGHT)
								{
									right=true;
								}
							
								if(k1.getKeyCode()==KeyEvent.VK_LEFT)
								{
									left=true;
								}
							}
		
							//when Navigation keys are released.	
					
							public void keyReleased(KeyEvent k2)
							{
								if(k2.getKeyCode()==KeyEvent.VK_UP)
								{
									up=false;
								}
								
								if(k2.getKeyCode()==KeyEvent.VK_DOWN)
								{
									down=false;
								}
								
								if(k2.getKeyCode()==KeyEvent.VK_RIGHT)
								{
									right=false;
								}
							
								if(k2.getKeyCode()==KeyEvent.VK_LEFT)
								{
									left=false;
								}
							}
						}		
					);
					BGM();
			} //EOCONSTRUCTOR
		
		
		
	public void paintComponent(Graphics g)
		{
			g.drawImage(Background, 0, 0, this); //For making the background as the image set
			g.setFont( new Font("Times New Roman" , Font.BOLD, 20)); //Set the font  
			g.setColor(Color.yellow);
        		
			int a1 =x; int b1=y;	
			
			if(( (p-20) < a1) && (a1< (p+45) ) && (( (q-20) < b1) && (b1< (q+45))))
			{
				p = r.nextInt(1200); q = r.nextInt(620);
				i = r2.nextInt(4);
				
				//code for collecting 20 fruits to get a life.
				if(fruitCount < 20) fruitCount++;
				
				if(dangerCount>0 && dangerCount<3)
				{
					if(fruitCount==20)
					{
						dangerCount--;
						fruitCount=0;
					}
				}
			}
							
			if( i <= 1)
				g.drawImage(Fruit1, p,q,this);								
			if (i ==2)
				g.drawImage(Fruit2, p,q,this);								
			if(i==3)
				g.drawImage(Fruit3, p,q,this);								 
				
			g.drawString("FruitCount="+fruitCount , 1000, 690);
		
			
			//code to show the number of chances left.			

			if(dangerCount<=0)
			{
	  			g.drawImage(bob, 1290, 670, this);
	  			g.drawImage(bob, 1255, 670, this);
	  			g.drawImage(bob, 1220, 670, this);
			}

			if(dangerCount==1)
			{
				g.drawImage(bob, 1220, 670, this);
	  			g.drawImage(bob, 1255, 670, this);
  			}

			if(dangerCount==2)
			{
				g.drawImage(bob, 1220, 670, this);
  			}

			
			//when all the chances are done, --Game ends--

			if(dangerCount>2)
			{
				g.setFont(new Font("sanserif",Font.BOLD,40));
				g.setColor(Color.red);
				g.drawString("-GAME-OVER-",510,338);
			}

			// To get Keys at different locations on the screen.
			g.drawImage(bob, x, y, this);
			g.setColor(Color.black);
			
			//g.drawString(""+x+ "  " + y, 200,200);
			int a =x; int b=y;	
			if(( (x1-20) < a) && (a< (x1+45) ) && (( (y1-20) < b) && (b< (y1+45))) && count < 4)
			{
				x1 = r.nextInt(1285); y1 = r.nextInt(640);
				count++;
			}
			
			if(count ==4)
			{
				x1=700; y1 = 350;
				count++;
			}
			
			if(count <4)
			{
				g.drawImage(TreasureKey, x1, y1, this);  
			}
			
			if(count ==4)
			{
				g.drawImage(TreasureKey, 700, 350, this); count++;
			}
						
			//when all the keys are collected.
				
			if(count ==5)
			{
				g.setFont(new Font("CHILLER",Font.ITALIC,40));
				g.setColor(Color.orange);
				g.drawString("TREASURE FOUND!",510,338);
				g.drawImage(TreasureOpened, 700, 350, this); 
				g.setFont(new Font("CHILLER",Font.HANGING_BASELINE,25));
				g.setColor(Color.black);
				g.drawString("WANNA PLAY AGAIN?",480,430);// End message to be displayed to the user
				g.drawString("YES",510,460); //Yes message displayed		
				g.drawString("NO",555,460); //No message displayed		
			}
			
			if((x>505) && (x<520) && (y>450) && (y<460) && (count==5)) count=0;
			
			if((x>550) && (x<565) && (y>450) && (y<460) && (count>=5))
			{ 
				g.setFont(new Font("chiller",Font.ITALIC,50));
				g.setColor(Color.BLACK); //Set the image to black
				g.drawString("WELL PLAYED!!",510,358); //Message displayed when user selects 'No'
				count++; 
			}
		
			//what should happen when navigation keys are pressed or released.	
	
			if(up && y!=0 &&count <=5 && dangerCount<=2)
			{
        			y-=1;
					if(y==34 && x>=-100 && x<= 1290)
					{
						x=50; y=620 ; dangerCount++;        		
        			}
					
					if(y==138 && x>=460 && x<= 716)
					{
        				x=50; y=620; dangerCount++;
        			}
        			
					if(y==153 && x>=179 && x<=255 )
					{
        				 y= 154;        		
        			}
        			
					if(y==244 && x>=120 && x<= 448)
					{
        				 y= 245;        		
        			}
        			
					if(y==304 && x>= 596&& x<= 655)
					{
							x=50; y=620; dangerCount++;
       				}
 
					if(y==264 && x>= 716&& x<= 832)
					{
        					x=50; y=620; dangerCount++;
        			}
        			
					if(y== 195 && x>= 930&& x<=1229 )
					{
        					x=50; y=620; dangerCount++;
        			}
        			
					if(y==304 && x>= 1170  && x<=1290 )
					{
        					x=50; y=620; dangerCount++;
        			}
        			
					if(y== 418 && x>= 1162 && x<= 1222)
					{
        					x=50; y=620; dangerCount++;
        			}
        			
					if(y== 426 && x>= 775 && x<= 1078)
					{
        					x=50; y=620; dangerCount++;
        			}
        			
					if(y== 395 && x>= 568 && x<= 632)
					{
        				 y= 396;        		
        			}
        			
					if(y==383 && x>=333 && x<= 505)
					{
        				 y= 384;        		
        			}
        			
					if(y== 367 && x>=210 && x<= 267)
					{
        				 y= 368;        		
        			
					}
        			
					if(y==431 && x>=0 && x<= 119)
					{
        				 y=432;        		
        			}
        			
					if(y==534 && x>=96 && x<= 389)
					{
        				 y= 535;        		
        			
					}
        			
					if(y==574 && x>=632 && x<= 845)
					{
        				 y= 575;        		
        			}
        			
					if(y== 549 && x>= 1014 && x<= 1290)
					{
							x=50; y=620; dangerCount++;
        			}
        			
					if(y== 595 && x>= 1290 && x<= 1350)
					{
        					x=50; y=620; dangerCount++;
        			}
					
					if(y== 316 && x>= 904 && x<= 960)
					{
        					x=50; y=620; dangerCount++;
        			}
					
					if(y==112 && x>=815 && x<= 876)
					{
        					x=50; y=620; dangerCount++;
        			}	        				     				
       		}      
   			
			if(down && y!= 673 &&count <=5 &&dangerCount<=2)
			{
        			y+=1;      
					if(y== 75 && x>= 0&& x<= 33)
					{
        				 y=74 ;  
					}	
        	
					if(y== 96 && x>= 120 && x<= 255)
					{
        				 y= 95;  
					}	
        			
					if(y== 187 && x>= 121 && x<= 505)
					{
        				 y= 186;  
					}	
        			
					if(y== 81 && x>= 460 && x<= 776)
					{
        				x=50; y=620; dangerCount++;
        			}	
        			
					if(y== 211 && x>= 720 && x<= 831)
					{
        				x=50; y=620; dangerCount++;
        			
					}	
        			
					if(y== 140 && x>= 902 && x<= 1229)
					{
        				x=50; y=620; dangerCount++;
        			}	
        			
					if(y== 179 && x>= 591 && x<= 655)
					{
        				x=50; y=620; dangerCount++;
        			}	
        			
					if(y== 247 && x>= 1164 && x<= 1350)
					{
        				x=50; y=620; dangerCount++;
        			}	
        			
					if(y== 371 && x>= 780 && x<= 1024)
					{
        				x=50; y=620; dangerCount++;
        			}	
        			
					if(y== 493 && x>= 1014 && x<= 1350)
					{
        				x=50; y=620; dangerCount++;
        			}	
        			
					if(y== 642 && x>= 0 && x<= 1400 )
					{
       					 y= 641;  
					}	
        			
					if(y== 477 && x>= 96 && x<= 333)
					{
        				 y= 476;  
					}	
        			
					if(y== 375  && x>= 0 && x<= 119 )
					{
        				 y= 374;  
					}	
        			
					if(y== 329 && x>= 333 && x<= 448)
					{
        				 y= 328;  
					}	
        			
					if(y== 339 && x>= 568 && x<= 689)
					{
        				 y= 338;  
					}	
        			
					if(y== 505 && x>= 444 && x<= 502)
					{
        				 y= 504;  
					}	
        			
					if(y== 517 && x>= 632 && x<= 845)
					{
        				 y= 516;  
					}	
        	}
	    			
		 	if(right && x!=1338 && count <=5 &&dangerCount<=2) // NOT LETTING THE BALL TO GO RIGHT TO OUR BOX
			{
        			x+=1; 
	  		
        			if(x== 117 && y <= 35 && y>= 0)
					{
        				x=116;
        			}
					
        			if(x== 1338 && y <= 641 && y>= 596)
					{
        				x=-10; y=47;
        			}
	  				
        			if(x== 329 && y <=186 && y>=35 )
					{
        				x=50; y=620; dangerCount++;
        			}
	  				
        			if(x== 334 && y <=476 && y>=327 )
					{
        				x=333;
        			}
	  				
        			if(x== 444 && y <=641 && y>=504 )
					{
        				x=443;
        			}
	  				
        			if(x== 449 && y <=328 && y>=245 )
					{
        				x=448;
        			}
	  				
        			if(x== 121 && y <=246 && y>=95 )
					{
        				x=120;
        			}
	  				
        			if(x== 816 && y <=114 && y>=35 )
					{
        				x=50; y=620; dangerCount++;
        			}
					
        			if(x== 569 && y <=396 && y>=338)
					{
        				x=568;
        			}
	  				
        			if(x== 1291 && y <=594 && y>=0 )
					{
        				x=50; y=620; dangerCount++;
        			}
	  				
        			if(x== 461 && y <=139 && y>=80)
					{
        				x=50; y=620; dangerCount++;
        			}
	  				
        			if(x==717 && y <=265 && y>=139 )
					{
        				x=50; y=620; dangerCount++;
        			}
	  				
        			if(x== 776 && y <=427 && y>=265 )
					{
        				x=50; y=620; dangerCount++;
        			}
	  				
        			if(x== 899 && y <=427 && y>=673 )
					{
        				x=50; y=620; dangerCount++;
        			}
	  				
        			if(x== 903 && y <=317 && y>=139 )
					{
        				x=50; y=620; dangerCount++;
        			}
	  				
        			if(x==1025 && y <=370 && y>=196 )
					{
        				x=50; y=620; dangerCount++;
        			}
	  				
        			if(x==1163 && y <=419 && y>=246 )
					{
        				x=50; y=620; dangerCount++;
        			}
	  				
        			if(x==1015 && y <=550 && y>=492 )
					{
        				x=50; y=620; dangerCount++;
        			}
	  				
        			if(x== 723 && y <=641 && y>=575 )
					{
        				x=722;
        			}
	  				
        			if(x== 444 && y <=641 && y>=504 )
					{
        				x=443;
        			}
	  				
        			if(x== 211 && y <=368 && y>=245 )
					{
        				x=210; 
        			}
	  				
        			if(x== 97 && y <=535 && y>=476 )
					{
        				x=96; 
        			}
					
        			if(x== 597 && y <=305 && y>=178)
					{
        				x=50; y=620; dangerCount++;
        			}
					
        			if(x== 633 && y <=575 && y>=396)
					{
        				x=632; 
        			}
					
        			if(x==899 && y <=641 && y>=427)
					{
        				x=50; y=620; dangerCount++;
        			}
        	}
        		
			if(left  && count <=5 && dangerCount<=2) // NOT LETTING THE BALL TO GO LEFT TO OUR BOX
			{
        			x-=1;      	
        			
        			if(x== 1221 && y <=419  && y>= 305)
					{
        				x=50; y=620; dangerCount++;
        			}
			
        			if(x== -10 && y <=74  && y>= 35)
					{
        				x=1320; y=630;
        			}
        			
	  				if(x== 1077 && y <= 427 && y>=196 )
					{
        				x=50; y=620; dangerCount++;
        			}
        			
	  				if(x==1228  && y <= 196 && y>= 139)
					{
        				x=50; y=620; dangerCount++;
        			}
        			
	  				if(x== 959 && y <= 317 && y>=196 )
					{
        				x=50; y=620; dangerCount++;
        			}
        			
	  				if(x== 875 && y <= 113 && y>=35 )
					{
        				x=50; y=620; dangerCount++;
        			}
        			
	  				if(x== 954 && y <= 690 && y>=427 )
					{
        				x=50; y=620; dangerCount++;
        			}
        			
	  				if(x== 844 && y <= 575 && y>=516 )
					{
        				x=845;
        			}
        			
	  				if(x== 780 && y >=575  && y<= 700)
					{
        				x=781;
        			}
        			
	  				if(x==773  && y >=80  && y<= 210)
					{
        				x=50; y=620; dangerCount++;
        			}
        			
	  				if(x== 829 && y >= 210 && y<=370 )
					{
        				x=830;
        			}
        			
	  				if(x==  688 && y >=  338 && y<= 516)
					{
        				x=689;
        			}
        			
	  				if(x== 501 && y >=  504 && y<=700 )
					{
        				x=502;
        			}
        			
	  				if(x==654  && y>=  178 && y<= 305)
					{
        				x=50; y=620; dangerCount++;
        			}
        			
	  				if(x== 504 && y >=  186 && y<= 384)
					{
        				x=505;
        			}
        			
	  				if(x==386  && y >= 0 && y<= 186)
					{
        				x=50; y=620; dangerCount++;
        			}
        			
	  				if(x== 388 && y >=  384 && y<= 535)
					{
        				x=389;
        			}
        			
	  				if(x==  266 && y >=  281 && y<= 368)
					{
        				x=267;
        			}
        			
	  				if(x== 254 && y >= 95 && y<= 154)
					{
        				x=255;
        			}
        			
	  				if(x== 179 && y >=  154 && y<= 186)
					{
        				x=180;
        			}
        			
	  				if(x==  118 && y >=  374 && y<= 432)
					{
        				x=119;
        			}
        			
	  				if(x==  32 && y >=  74 && y<= 700)
					{
        				x=33;
        			}
        			
			}

			repaint();
 			
		}


	// Audio generation 

	public  void BGM()
	{
		try
		{
			InputStream input = new FileInputStream("Audio.wav"); //instantiate InputStream class
			AudioStream as = new AudioStream(input); //instantiate AudioStream class        		
			AudioPlayer.player.start(as); //start the player
		}
		
		catch(Exception e)
		{
		
		}
		
	}	

}
