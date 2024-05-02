import java.net.InetAddress;
/*This class represents a client 
 * used by the UDPserver class to maintain a list of clients 
 */

public class ClientObject {
	InetAddress _ip;
	int _port;
	
	public ClientObject(InetAddress ip,int port){
		_ip = ip;
		_port = port;
	}

	@Override
	public boolean equals(Object arg0) {
		if(arg0 instanceof ClientObject) {
			return (this._ip ==((ClientObject)arg0)._ip&&this._port==((ClientObject)arg0).get_port());	
		}
		return false;
	}

	public InetAddress get_ip() {
		return _ip;
	}

	public int get_port() {
		return _port;
	}

	
}
