package hawox.guiquestmaker;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class NameBoxWithButton extends NameComboBoxPanel{
	JButton addOne;
	public NameBoxWithButton(String labelName, Object[] comboBoxContents, Color backgroundColor, String buttonName, int id) {
		super(labelName, comboBoxContents, backgroundColor);
		addOne = new JButton(buttonName);
		switch(id){
		case 0:
			addOne.addActionListener(new addButtonListenerObjective());
			break;
		case 1:
			addOne.addActionListener(new addButtonListenerReward());
			break;
		}
		this.add(addOne);
	}

	public class addButtonListenerObjective implements ActionListener{
		public void actionPerformed(ActionEvent event){
			
		}
	}
	public class addButtonListenerReward implements ActionListener{
		public void actionPerformed(ActionEvent event){
			
		}
	}
}
