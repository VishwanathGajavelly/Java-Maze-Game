import javax.swing.*; //To show the GUI to the User

public class Maze 
{
	public static PlayRegion p1=new PlayRegion();
	public static String TitleOfGame="--FIND THE TREASURE--"; //Title of the Game
	public static int width=1400;
	public static int height=750;		

	public static void main(String[] args)
	{
	
		p1.setTitle(TitleOfGame);
		p1.setSize(width, height);
		p1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //To close the Frame
		p1.setVisible(true);
		p1.setResizable(false);
		p1.setLocationRelativeTo(null);
		p1.setAlwaysOnTop(false);
	}	
}


