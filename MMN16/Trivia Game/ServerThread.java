
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;


public class ServerThread extends Thread {
	
	private Socket _s;
	private ArrayList<Question> _questions;
	
	public ServerThread(Socket s,ArrayList<Question> q) {
		_s = s;
		_questions = q;
	}
	
	public void run () {
		super.run();
		if(_questions.size()==0)
			return;
		try {
			writeToObject();
		} catch (Exception e) {e.printStackTrace();}	
	}

	private synchronized void writeToObject() throws Exception{
		OutputStream  outputStream = _s.getOutputStream();
		ObjectOutputStream  objOutStream = new ObjectOutputStream(outputStream);
		objOutStream.writeObject(generateQuestion());
		objOutStream.close();
		outputStream.close();
		
	}
	private Question generateQuestion() {
		int index;
		Random r = new Random();
		index  = r.nextInt(_questions.size());
		Question q = _questions.get(index);
		_questions.remove(index);
		return q;
	}	
}
