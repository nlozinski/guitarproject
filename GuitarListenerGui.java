
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

class GuitarListenerGui extends JFrame {
	private JTextArea messageArea;

	
	public synchronized void setText(char string) {
		messageArea.setText("");
		messageArea.setText("String " + string + " was plucked");
	}
	
	
	public GuitarListenerGui() {
		this.setSize(600, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container cPane = this.getContentPane();

		messageArea = new JTextArea();

		cPane.add(messageArea);


		messageArea.append("Waiting for clients...\n");
		messageArea.setWrapStyleWord(true);
		messageArea.setEditable(false);
		messageArea.setLineWrap(true);
		messageArea.setFont(new Font("SansSerif", Font.BOLD, 32));

		this.setVisible(true);
	}
}

