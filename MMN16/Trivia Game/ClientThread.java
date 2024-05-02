import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientThread extends Thread {
	private ClientClassController _cont;
	private String _ip;
	private int _port;
	public ClientThread(ClientClassController cont,String ip,int port) {
		this._cont = cont;
		this._ip = ip;
		_port = port;
	}
	
	public void run () {
		super.run();
		readFromObject();
	}
	
	private void readFromObject() {
	    Socket s = null;
	    InputStream inputStream = null;
	    ObjectInputStream objInpStream = null;
	    try {
	        s = new Socket(_ip, _port);
	        inputStream = s.getInputStream();
	        objInpStream = new ObjectInputStream(inputStream);
	        Question a = (Question) objInpStream.readObject();
	        _cont.showQuestion(a);
	
	    } catch (Exception  e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (objInpStream != null)
	                objInpStream.close();
	            if (inputStream != null)
	                inputStream.close();
	            if (s != null)
	                s.close();
	        } catch (IOException e) {
	        }
	    }
	}
}
