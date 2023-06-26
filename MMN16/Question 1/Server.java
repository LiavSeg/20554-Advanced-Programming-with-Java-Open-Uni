import java.io.File;
import java.io.FileNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {
	
	private ArrayList<Question> questions;
	private final int NUM_OF_QUESTIONS = 20;
	private final int PORT = 3333;
	
	public Server() {
		ServerSocket sc =null; //host
		Socket s = null; //waiter 
		questions = new ArrayList<Question>();
		fillQuestions();
		try {
			sc = new ServerSocket(PORT);
			while(true) {
				while(questions.size()>0) {	
					s = sc.accept();
					new ServerThread(s,questions).start();	
				}
				fillQuestions();
			}
		}
		catch (Exception e) {
			try {
				sc.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} 
	}
	private void fillQuestions() {
		String question = null;
		File file = new File("file.txt");
		Scanner rd = null;
		try {
			rd = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("Could not find the questions file - can't proceed");
			System.exit(0);
		}	
		for(int i = 0 ; i < NUM_OF_QUESTIONS ; i++){
			if(rd.hasNextLine()) {
				question = rd.nextLine();
				String answers[] = getAnswers(rd);
				questions.add(new Question(question,answers));
			}
		}
		 rd.close();
	}

	private String[] getAnswers(Scanner rd) {
		String answers[] = new String[4];
		answers[0] = rd.nextLine();
		answers[1] = rd.nextLine();
		answers[2] = rd.nextLine();
		answers[3] = rd.nextLine();
		return answers;
	}
	
	
	public static void main(String[] args) {
		new Server();
	}
}
