import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ClientClassController {

    @FXML
    private ToggleGroup Answers;
    @FXML
    private RadioButton ansFour;
    @FXML
    private RadioButton ansOne;	 
    @FXML
    private RadioButton ansTwo;
    @FXML
    private RadioButton ansThree;
    @FXML
    private Text answerA;
    @FXML
    private Text answerB;
    @FXML
    private Text answerC;
    @FXML
    private Text answerD;
    @FXML
	private Canvas questionsArea;
    @FXML
    private ProgressBar progBar;
    private GraphicsContext gc;
    private int _remainingTime;
    private int _questionTime;
    private final double NUM_OF_QUESTIONS = 20;
    private Timer _timer;
    private int  _score;
    private boolean _onTime = true;
    private String _correctAnswer ;
    private int  _questionsCount ;
    private String _ip ;
    private int _port; 
    
    
    public void initialize() {
    	getInput() ;
    	gc = questionsArea.getGraphicsContext2D();
	   _score = 0;
	   _questionsCount = (int)NUM_OF_QUESTIONS;
	   _remainingTime = _questionTime;
	   timerInit();
	   new ClientThread(this,_ip,_port).start();
	}
    
    @FXML
    void submitPressed(ActionEvent event) {
    	
    	if(Answers.getSelectedToggle()==null&&_onTime)
    		return; 	
    	_timer.stop();
    	gc.setFill(Color.BLACK);
    	_remainingTime =_questionTime;
    	timerInit();    	
    	isOnTime();
    	_questionsCount--;
    	progBar.setProgress(progBar.getProgress()+(1/NUM_OF_QUESTIONS));
    	gc.clearRect(0, 0, questionsArea.getWidth(),questionsArea.getHeight()); 
    	gc.fillText("Total Score: "+_score, 180, 20);	   
    	new ClientThread(this,_ip,_port).start();
    	Answers.selectToggle(null);
    	if(_questionsCount == 0) {
    		done();
    		new ClientThread(this,_ip,_port).start();	
    	}
    }
   
    private void isOnTime() {
    	if(!_onTime)
    		_score-=5;
    	else if(isCorrect()) { 
    		_score+=10;
    		
    	}
    	_onTime =true;
    }
    private String getAnsQuestion() {
    	if(Answers.getSelectedToggle().equals(ansOne))
    		return answerA.getText();
    	else if(Answers.getSelectedToggle().equals(ansTwo))
    		return answerB.getText();
    	else if(Answers.getSelectedToggle().equals(ansThree))
    		return answerC.getText();
    	return answerD.getText();
     }
   
   public void showQuestion(Question q) {
	   gc.clearRect(0, 0, questionsArea.getWidth(),questionsArea.getHeight());
       gc.setFont(Font.font(null, 15));
       gc.fillText(q.getQuestion(), 50,questionsArea.getHeight()/2);
       gc.setFont(Font.font(null, 15));
       gc.fillText("Total Score: "+_score, 10, 20);
   	   timerInit();
	   _timer.start();
	   clearBoard();
	   _correctAnswer = q.getAnswers()[0];
	   setAnswers(q);
	   
   }
   private Boolean isCorrect() {
	   return (getAnsQuestion().equals(_correctAnswer));    
   }
  
   	private void clearBoard() {
   		answerA.setText(" ");
	   	answerB.setText(" ");
	   	answerC.setText(" ");
	   	answerD.setText(" "); 
   }
	private void setAnswers(Question q) {
		answerA.setFont(Font.font(null, 15));
		answerB.setFont(Font.font(null, 15));
		answerC.setFont(Font.font(null, 15));
		answerD.setFont(Font.font(null, 15));
	
		answerA.setText(generateAnswer(q));
		answerB.setText(generateAnswer(q));
		answerC.setText(generateAnswer(q));
		answerD.setText(generateAnswer(q)); 
	}
  	
	private String generateAnswer(Question q) {
  		boolean found = false;
  		String answer = "";
  		while(!found) {
  			Random r = new Random();
  			int i = r.nextInt(4);
  			 answer = q.getAnswers()[i];
  			if(!answer.equals(" ")) {
  				q.getAnswers()[i] = " ";
  				found = !found;
  			}
  		}
  		return answer;
    }
   	
	private Alert setAlert() {
		Alert a =  new Alert(AlertType.CONFIRMATION);
		a.getButtonTypes().set(0, ButtonType.YES);
		a.getButtonTypes().set(1, ButtonType.NO);
		a.setTitle("Game Over");
		a.setGraphic(null);
		a.setHeaderText("Total points: "+_score);
		a.setContentText("Would you like to play again ?");
		return a;
	}
	private void done() {
		Alert a = setAlert();
		 gc.clearRect(0, 0, questionsArea.getWidth(),questionsArea.getHeight());
		a.showAndWait();
		if(a.getResult().equals(ButtonType.YES)) {
			_score = 0;
			_questionsCount =(int) NUM_OF_QUESTIONS ;
			_remainingTime = _questionTime;
			timerInit();
			progBar.setProgress(0);
		 }
		 else {
			 System.exit(0);
		 }
	}
	private void outOfTime() {
		Alert a =  new Alert(AlertType.CONFIRMATION);
       	a.setTitle("");
       	a.setGraphic(null);
		a.setHeaderText("Time is up - you lost 5 points");
       	a.setContentText(" Correct answer: "+_correctAnswer);
       	a.getButtonTypes().set(0, ButtonType.NEXT);
       	a.getButtonTypes().remove(1);
       	_timer.stop();
       	_onTime = false;
       	a.showAndWait();
       	submitPressed(null);
	} 
		   
	   private void getInput() {
		   TextInputDialog dialog = new TextInputDialog();
		   _ip = "127.0.0.1";
		   _port = 3333;
		   setIpDialog(dialog);
			try {
				 setPortDialog(dialog);
				 setTimeDialog(dialog);
			}
			catch(NumberFormatException e) {
				errorAlert();
				System.exit(0);
			}
		}  
	  
	   private void setIpDialog(TextInputDialog dialog) {
		  	dialog.setGraphic(null);
			dialog.setHeaderText("Please provide IP address\nPress Ok for default address ");
			dialog.showAndWait();
			if(!dialog.getResult().equals(""))
				_ip = dialog.getResult();
  
	   }
	   private void setPortDialog(TextInputDialog dialog) throws NumberFormatException {
		   dialog.getEditor().setText("");
		   dialog.setHeaderText("Please provide port number\nPress Ok for default port");
		   dialog.showAndWait();	
		   if(!dialog.getResult().equals(""))
			   _port = Integer.parseInt(dialog.getResult());
	   }
	   private void setTimeDialog(TextInputDialog dialog) throws NumberFormatException {
		   dialog.getEditor().setText("");
		   dialog.setHeaderText("Please provide asnwer time");
		   dialog.showAndWait();
		   _questionTime = Integer.parseInt(dialog.getResult());
	   }
	   
	   private void timerInit() {
		   gc.fillText(_remainingTime+"", questionsArea.getWidth()-50, 20);
		   gc.fillText("Time left: ", questionsArea.getWidth()-150, 20);
		   _timer = new Timer(1000, new ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					timerOperation();         
				}
		   });
	   }
	   
	   private void timerOperation() {
			gc.clearRect(questionsArea.getWidth()-50,0, 30, 30);	
			_remainingTime--;
			if(_remainingTime==_questionTime/2)
				gc.setFill(Color.ORANGE);
			if(_remainingTime==_questionTime/4)
				gc.setFill(Color.RED);
			 gc.fillText(_remainingTime+"", questionsArea.getWidth()-50, 20);   
			  if (_remainingTime == 0) {
								  Platform.runLater(()->{
					 outOfTime(); 
				  });
			  }
	   }
	   protected  void errorAlert() {
		   Alert a =  new Alert(AlertType.ERROR);
		   a.setHeaderText("Error: Could not establish connection, wrong network detalis");
		   a.showAndWait();
	   }
      
}







