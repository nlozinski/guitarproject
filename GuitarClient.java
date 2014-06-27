import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GuitarClient {
	public static void main(String[] argv) throws IOException {
		JFrame f = new JFrame ();
	 	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    f.setTitle ("Client");
	    f.setResizable (true);
	    f.setSize (500, 300);
	
	    JTextField portNum=new JTextField("Enter port #");
	    JTextField host=new JTextField("");
	
	    final JLabel label=new JLabel();
	    final JButton connect=new JButton("Connect");
	    final String hostString=host.getText();
	    String portNumString=portNum.getText();

	    //final int portNumInt = Integer.parseInt(portNumString);
	    connect.addActionListener(new ActionListener(){
	    	@Override
	    	public void actionPerformed (ActionEvent e){
	    		//checks to see if connect or disconnect
	    		if (connect.getText()=="Connect"){
	    		try{
	    			
	    			final Socket sock = new Socket(hostString, 5555);
	    			
	    			InetAddress remoteMachine = sock.getInetAddress();
	    			final PrintWriter pw = new PrintWriter(sock.getOutputStream(), true);
	    			//pw.flush ();
	    			label.setText("Connection made");
	    			final JFrame n=new JFrame();
	    			n.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    		    n.setTitle ("Client");
	    		    n.setResizable (true);
	    		    n.setSize (500, 300);
	    		    n.setVisible(true);
	    			connect.setText("Disconnect");
	    			
	    		n.addKeyListener(new KeyListener() {

						@Override
						public void keyPressed(KeyEvent arg0) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void keyReleased(KeyEvent e) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void keyTyped(KeyEvent e) {
							
							if (e.getKeyChar()=='d'){
								pw.println('q');
								pw.println('w');
								pw.println('e');
								pw.println('r');
								pw.println('t');
								pw.println('y');
								pw.println('u');
								pw.println('i');
								pw.println('o');
								pw.println('p');
								pw.println('[');
								pw.println(']');
							}
							pw.println(e.getKeyChar());
							pw.flush();
						}
	    			
	                });
	    		
	    			
	    		}
	    		catch (IOException f) {
	    			label.setText("Connection denied");
	    		}
	    	}
	    		else {
	    			//try {
						System.out.println ("second actionperformed");
					//sock.close();
					//n.dispose();
					//}
					//catch (IOException f){
						
					//}
	    		}
	    	}
	    });
	    f.add (portNum);
	    f.add (host);
	    f.add (connect);
	    f.add(label);
	   
	    f.setLayout(new GridLayout(2,0));  
	    f.setVisible(true);

			
}
}