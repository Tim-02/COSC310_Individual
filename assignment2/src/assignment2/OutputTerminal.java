package assignment2;

import java.awt.Color;

import javax.swing.JLabel;
/*
 * OutputTerminal is where users questions and the bots answers will be displayed
 */

public class OutputTerminal extends JLabel{
	ChatBot chatbot = new ChatBot();
	
	public OutputTerminal() {
		setBackground(Color.white);
		setOpaque(true);
		setVerticalAlignment(JLabel.TOP);
		setText("<html>");
		
	}
	/*
	 * takes in String send it to chat bot then prints both input String and chatBot output in Terminal area
	 */
	public void askQuestion(String s) {
		String response = chatbot.getResponse(s);
		if(getText().length() > 6) {
			setText(getText().substring(0, getText().length()-7));
		}
		setText(getText() + "<br> human: " + s);
		setText(getText() + "<br> Bot: " + response + "<br></html>");
		
	}
	

}
