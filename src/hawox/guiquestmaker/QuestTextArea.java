package hawox.guiquestmaker;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class QuestTextArea extends JPanel{
	JTextArea text;
	JScrollPane scroll;
	
	public QuestTextArea(Color backgroundColor){
		this.setBackground(backgroundColor);
		this.setLayout(new BorderLayout());
		text = new JTextArea(30,30);
		text.setLineWrap(false);
		scroll = new JScrollPane(text);
		
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		this.add(scroll);
	}

	public JTextArea getText() {
		return text;
	}

	public void setText(JTextArea text) {
		this.text = text;
	}

	public JScrollPane getScroll() {
		return scroll;
	}

	public void setScroll(JScrollPane scroll) {
		this.scroll = scroll;
	}
	
	/*public void paintComponent(Graphics g){
		g.setColor(new Color(0,255,255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}*/
}
