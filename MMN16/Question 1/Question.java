import java.io.Serializable;

public class Question  implements Serializable{
	private String _question;
	private String[] _answers;

	public Question (String question,String[] answers) {
		_question = question;
		_answers = answers;
	}
	
	public String getQuestion() {
		return _question;	
	}
	public String[] getAnswers() {
		return _answers;	
	}
	public String getCorrect() {
		return _answers[0];	
	}
}
