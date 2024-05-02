import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;

public class ServerThread extends Thread{
	private ArrayList<ClientObject>  _clients;
	private DatagramSocket _serverSocket;
	
	public ServerThread(DatagramSocket serverSocket ,ArrayList<ClientObject> clients){
		_serverSocket = serverSocket;
		_clients  = clients;	
	}
	
	public void run() {
		super.run();
		while(true)
			recivePacket();
	}
		
	
	public void recivePacket() {
		try {
            byte send[] = new byte[1024];
            DatagramPacket packet = new DatagramPacket(send, send.length);
            _serverSocket.receive(packet);
            String s = new String(packet.getData()).substring(0, packet.getLength());
     
            if(s.equals("connect")||s.equals("disconnect")) {// message received for the client
            	ClientObject temp = new ClientObject(packet.getAddress(),packet.getPort());
            	sendConnectionStatus(temp,s);//sends a confirmation for the client's request
            	manageClientList(temp,s);//updates the list of clients
            }
            else
            	sendBroadcastMessage(s);// message received from the server manager
         
		} catch (IOException e) {
            e.printStackTrace();
        }
	}
	public void sendBroadcastMessage(String s) { //handles the manager's messages
		try {
			byte send[] = new byte[1024];
			send = s.getBytes();
	        for(ClientObject client:_clients) {
	        	 DatagramPacket packet = new DatagramPacket(send, send.length,client.get_ip(),client.get_port());
	        	_serverSocket.send(packet);
	        }
		} catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	private void sendConnectionStatus(ClientObject temp,String status) { // client's status confirmation   
		try {
			byte send[] = new byte[1024];
			send = status.getBytes();        
			DatagramPacket packet = new DatagramPacket(send, send.length,temp.get_ip(),temp.get_port());
			_serverSocket.send(packet);
	        
		} catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	private void manageClientList(ClientObject temp,String status) {
		
		if(status.equals("connect"))
			_clients.add(temp);	
		else 
			_clients.remove(temp);
	}
}
