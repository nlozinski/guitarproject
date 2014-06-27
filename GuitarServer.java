import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;



class GuitarListener implements Runnable {
	private Socket sock;
	private GuitarListenerGui gui;
	private Guitar guitar;

	public GuitarListener(Socket s, GuitarListenerGui g, Guitar gt) {
		this.sock = s;
		this.gui = g;
		this.guitar = gt;
	}

	public synchronized void run() {
		boolean loop=true;
		try {
			PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			System.out.println("Got connection from " + sock.getInetAddress());
			// read in the string plucked
			//is constantly reading in for new keys, first client does not loop through unless new key is pressed, rest do
			while (loop){
			char key=(char) in.read();
			
			Guitar.notePlayed(key);
			//gui.setText(key);
			//out.flush();
			//System.out.println ("reached this point");
			}
		
			out.close();
			in.close();
			sock.close();
			System.out.println("Thread done.");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

public class GuitarServer {

	public GuitarServer() {
		GuitarListenerGui gui = new GuitarListenerGui();

		ServerSocket serverSocket = null;
		boolean listening = true;

		System.out.println("Waiting for connections...");

		try {
			serverSocket = new ServerSocket(5555);
		} catch (IOException e) {
			System.err.println("Could not listen on port: 5555.");
			System.exit(-1);
		}
		//constantly checks port to see if new client is trying to connect
		while (listening) {
			try {
				
				String keyboard ="qwertyuiop[]";
				GuitarString[] gStrings = new GuitarString[keyboard.length()];
				Guitar guitar = new Guitar(gStrings, keyboard);
				GuitarListener job = new GuitarListener(serverSocket.accept(), gui, guitar);
			
				// start a new thread to handle the connection
				Thread t = new Thread(job);
				t.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		new GuitarServer();

	}

}