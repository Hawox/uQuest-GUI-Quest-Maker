package hawox.guiquestmaker;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class NameTextPanel extends JPanel{
	JLabel label;
	JTextField text;
	
	public NameTextPanel(String labelName, Color backgroundColor){
		this.setBackground(backgroundColor);
		label = new JLabel(labelName);
		text = new JTextField(20);
		
		this.add(label);
		this.add(text);
	}
	
	public NameTextPanel(String labelName, int space, Color backgroundColor){
		this.setBackground(backgroundColor);
		label = new JLabel(labelName);
		text = new JTextField(space);
		
		this.add(label);
		this.add(text);
	}
	
	/*public void paintComponent(Graphics g){
		g.setColor(new Color(0,255,255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}*/

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}

	public JTextField getText() {
		return text;
	}

	public void setText(JTextField text) {
		this.text = text;
	}
	
}
