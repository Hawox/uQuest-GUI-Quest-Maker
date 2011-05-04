package hawox.guiquestmaker;

import java.awt.Color;

import javax.swing.*;

@SuppressWarnings("serial")
public class NameComboBoxPanel extends JPanel{
	JLabel label;
	JComboBox comboBox;
	
	public NameComboBoxPanel(String labelName, Object[] comboBoxContents, Color backgroundColor){
		this.setBackground(backgroundColor);
		label = new JLabel(labelName);
		comboBox = new JComboBox(comboBoxContents);
		
		this.add(label);
		this.add(comboBox);
		//this.setVisible(true);
		//this.validate();
		//this.repaint();
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

	public JComboBox getComboBox() {
		return comboBox;
	}

	public void setComboBox(JComboBox comboBox) {
		this.comboBox = comboBox;
	}
	
}
