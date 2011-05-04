package hawox.guiquestmaker;

import javax.swing.JApplet;


@SuppressWarnings("serial")
public class AppletLauncher extends JApplet{
	//Used when run as an applet
	@Override
	public void init(){
		GUI gui = new GUI();
		gui.go();
	}
}
