import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;  


/*	This is the manager thread class  
 * 	it has one responsibility -  to send the broadcast message to the UDP server
 * 	this responsibility achieved with thread work
 */
public class ManagerThread extends Thread {
	
	private String _msg;
	private DatagramSocket _managerSocket;
	private final int PORT = 6666;
	
	public ManagerThread(String msg) {
		Date d = new Date();
		_msg = msg+"  "+d+"\n";
		 try {
			_managerSocket = new DatagramSocket();
		} catch(SocketException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		broadCast();
	}
	
	private void broadCast() {
		try {
			InetAddress ip = InetAddress.getByName("localhost");
			byte[] send = _msg.getBytes();
			DatagramPacket buff = new DatagramPacket(send,send.length,ip,PORT);
			_managerSocket.send(buff);
			_managerSocket.close();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
