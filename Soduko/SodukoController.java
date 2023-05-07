
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;import javafx.scene.text.Font;

public class SodukoController {

    @FXML
    
    private GridPane numGrid;
    @FXML
    private Button setBtn;
    private final int SIZE = 9;
    private SodukoBoard _Board ;
    private final int INIT_STYLE =21;
    
    private  int _count = 0;
    
    public void initialize() {
    	 _Board = new  SodukoBoard();
    	 for(int i = 0;i<SIZE;i++) {
 			for(int j = 0;j<SIZE;j++) {
 				_Board._SodukoBoard[i][j]= new TextField();
 				_Board._SodukoBoard[i][j].setAlignment(Pos.CENTER);
 				_Board._SodukoBoard[i][j].setFont((Font.font(24)));
 				_Board._SodukoBoard[i][j].setPrefSize(numGrid.getPrefWidth()/SIZE, numGrid.getPrefHeight()/SIZE);
 				paintBlocks(i,j);
 				_Board._SodukoBoard[i][j].setOnKeyPressed(new EventHandler<KeyEvent>(){
					@Override
					public void handle(KeyEvent event) {
						validEnterPressed(event);
					}
 				});
    			numGrid.add(_Board._SodukoBoard[i][j], i, j);
    		}
    	 }
    }

    @FXML
    void clearPressed(ActionEvent event) {
    	_count = 0;
    	for(int i = 0;i<SIZE*SIZE;i++) {
    		TextField temp=(TextField) numGrid.getChildren().get(i);
    		if(!temp.isEditable()) {
    			temp.setEditable(true);
    			temp.setStyle(temp.getStyle().substring(INIT_STYLE));
    		}
    		temp.clear();
    		 _Board._logicBoard[i/SIZE][i%SIZE]=0;
    	}
    	setBtn.setDisable(false);
    }
    
    

    @FXML
    void setPressed(ActionEvent event) {
    	TextField temp;
    	String style = "";
    	setBtn.setDisable(true);
    	for(int i = 0;i<SIZE*SIZE;i++) {
    		temp=(TextField) numGrid.getChildren().get(i);
    		if(!temp.getText().equals("")) {
    			style = "-fx-text-fill: green;"+temp.getStyle();
    			numGrid.getChildren().get(i).setStyle(style);
    			TextField a =(TextField) numGrid.getChildren().get(i);
    			a.setEditable(false);
    			
    		}
    		
    	}
    }

    @FXML
    void validEnterPressed(KeyEvent event) throws NumberFormatException{
    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Error");
		alert.setHeaderText("Invalid Input");
		alert.setContentText("Please provide x such that x∈[1,9]");	
    	if(event.getCode()==KeyCode.ENTER) {
    			TextField current = (TextField)event.getSource();
    			int i =GridPane.getRowIndex(current);
				int j = GridPane.getColumnIndex(current);	
				try {
    				int temp = Integer.parseInt(current.getText());
    				if((temp>9||temp<1)) {
    					alert.showAndWait();
    					current.clear();
    					return;
    				}
    				else {
    					_Board._logicBoard[i][j]=temp;
    					if(_Board.isValidInput(i,j)==false) {
    						current.clear();
        					_Board._logicBoard[i][j]=0;
    					}
    					else  
    						_count++;
    				}
    			}
    			catch(Exception e) {
    				alert.showAndWait();
    				current.clear();
    			}
    		}	
    		if(_count==SIZE*SIZE)
        		System.out.println("Congratulations,You are an expert Sudoko Solver!");	
    	}
    private void paintBlocks(int i,int j) {
    	if((i>=0&&i<=2||i>=6&&i<=8)&&(j>=0&&j<=2||j>=6&&j<=8))//colors every other 3x3 block in gray
				_Board._SodukoBoard[i][j].setStyle("-fx-border-color: gray;-fx-background-color: lightgray");
			else if(i>=3&&i<=5&&j>=3&&j<=5)
				_Board._SodukoBoard[i][j].setStyle("-fx-border-color: gray ;-fx-background-color: lightgray");
    	
    }
    	
}


