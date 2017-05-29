import java.awt.*; //To show the GUI to the User
import javax.swing.*; //To build the window to add button, image into the GUI

public class PlayRegion extends JFrame
{
	public JPanel JP=(JPanel)getGlassPane(); //calling getGlassPane() method of JPanel
	public Image i;
	public Key k;
	public int x=180; public int y=180;

	public PlayRegion()
	{
		
		i=new Image();
		JP.setVisible(true);
		k=new Key(this,i);
		
		JP.setLayout(new GridLayout(1,1,0,0));
		this.setLayout(new GridLayout(1,1,0,0));
		
		JP.add(k);
		this.add(i);	
		
	}

}
