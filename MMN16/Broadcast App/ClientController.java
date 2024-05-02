import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/*	This is the client controller class the second out of two JavaFX instances 
 * 	While initializing this class a connection request is sent to the UDP server 
 * 	that was launched by the server manager 
 */
public class ClientController {
	
	private InetAddress _ip;
	
	private int _port;

	private ClientThread _current;
	@FXML
    private TextArea msgArea;
    @FXML
    private Button statusButton;
    @FXML
    private Text statusBar;
    @FXML
    void clearPressed(ActionEvent event) {
    	msgArea.clear();
    }
    @FXML //sends a disconnection/connection request 
    void removePressed(ActionEvent event) {
    	if(statusButton.getText().equals("Disconnect")) {
    		_current.stopMe();
    		statusButton.setText("Connect");    		
    		statusButton.setDisable(true);
    		statusBar.setFill(Color.RED);
    		statusBar.setText("Disconnecting");
    		}
    	else {
    		statusBar.setFill(Color.BLACK);
    		statusBar.setText("Connecting...");
    		_current = new ClientThread(_ip,_port,this,"connect");
			_current.start();
			statusButton.setText("Disconnect");	
    	}
    		
    }
    public void initialize()  {
    	statusBar.setText("Connecting");
		getInput();
		_current = new ClientThread(_ip,_port,this,"connect");
		_current.start();
    }
    
    public void setMsg(String p) {
    	msgArea.setText(msgArea.getText()+p);
    }
   
    public void setStatus(Boolean status) {
    	Date d = new Date();
    	if(status==true) {
    		statusBar.setFill(Color.GREEN);
    		statusBar.setText("Online");
    		setMsg("* \n");
    	}
    	else {
    		statusBar.setFill(Color.RED);
    		statusBar.setText("Offline");
    		setMsg("Disconnected "+d+"\n");
    		statusButton.setDisable(false);
    		
    	}
    }
    
    private void getInput() {
		
    	TextInputDialog dialog = new TextInputDialog();
		dialog.setGraphic(null);
		dialog.setHeaderText("Please provide IP address\nPress Ok for default address ");
		dialog.showAndWait();
		try {
			_ip = InetAddress.getLocalHost();
			_port = 6666;
			if(!dialog.getResult().equals(""))
				_ip = InetAddress.getByName(dialog.getResult());
			dialog.getEditor().setText("");
			dialog.setHeaderText("Please provide port number \nPress Ok for default port");
			dialog.showAndWait();
			if(!dialog.getResult().equals(""))
				_port = Integer.parseInt(dialog.getResult());
		
		}catch(Exception e) {
			Alert a =  new Alert(AlertType.ERROR);
			a.setHeaderText("Error: Could not establish connection, wrong network detalis");
			a.showAndWait();
			System.exit(0);
		}
	}  
   
}
