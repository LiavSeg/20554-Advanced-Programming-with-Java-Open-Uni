import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/*	This is the client thread class  
 * 	it has two responsibilities:
 *  to send the server thread a connection\disconnection request 
 *  to receive from the server the broadcasted message 
 * 	these responsibilities achieved with UDP communication protocol
 */
public class ClientThread extends Thread {
	private	DatagramSocket _clientSocket;
	private	String _clientStatus;
	private	ClientController _cont;
	private	InetAddress _ip; 
	private	Boolean _added = false;
	private	int _port ;

	public ClientThread(InetAddress ip,int port,ClientController clientCont,String addOrRemove)  {
		try {
			_clientSocket = new DatagramSocket();
			_clientStatus = addOrRemove;
			_cont = clientCont;
			_ip =ip;
			_port = port;
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void run() {
		super.run();
		addMe(); // sends a connection request 
		//receiveMessage();// waits for a response from the server 
		while(_added)//if added successfully keep waiting for new broadcasted messages  
			receiveMessage();
		 diconnectMe();//_added is false the client sent a disconnection signal  
	}
	
	private void addMe() {
		try {
	    	byte[] send = _clientStatus.getBytes();
	        DatagramPacket packet = new DatagramPacket(send, send.length,_ip,_port);
	        _clientSocket.send(packet);
	      
	    } catch (Exception e) {
	    	_clientSocket.close();
	    	e.printStackTrace();
	    }
		receiveMessage();
	}
	// this functions used by the controller class client wants to disconnect 
	public void stopMe() {
		  _added = false;//stops waiting for new messages 
	}
	private void diconnectMe() {
	 
	  try {
		  byte[] send = "disconnect".getBytes();
	      DatagramPacket packet = new DatagramPacket(send, send.length,_ip,_port);
	       _clientSocket.send(packet);
	       _clientSocket.close();
	       _cont.setStatus(_added);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
	
	private void receiveMessage() {
		byte[] buffer = new byte[1024];
		try {
            DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
            _clientSocket.receive(receivePacket);
            String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
           if(receivedMessage.equals("connect")) {
        		_cont.setStatus(true);
        		_added = true;
           }
           else if(receivedMessage.equals("disconnect")) {
       		_cont.setStatus(true);
       		_added = true;
          }
           else if(_added) {
        	   _cont.setMsg(receivedMessage);
           }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	

}
