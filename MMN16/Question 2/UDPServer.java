import java.net.DatagramSocket;
import java.util.ArrayList;
/*
 * This is the UDP server class
 * it contains an array list of Client class 
 * which stores essential details for registered clients
 * listens on port 6666 as requested 
 * if any exception occurs socket is closed and the program is terminated 
 */

public class UDPServer {

	private ArrayList<ClientObject> _clients;
	private DatagramSocket _serverSocket;
	private final int PORT = 6666;
	public UDPServer() {
		_clients = new 	ArrayList<ClientObject>();
		try {
			_serverSocket = new DatagramSocket(PORT);
			new ServerThread(_serverSocket,_clients).start();
			
		} catch (Exception e) {
			_serverSocket.close();
			System.exit(0);
		}
		
		
	}
		
}
