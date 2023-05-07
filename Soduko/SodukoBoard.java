import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class SodukoBoard {
	private final int SIZE =9;
	protected TextField _SodukoBoard[][];
	protected int _logicBoard[][];
	
	public SodukoBoard() {
		_SodukoBoard= new TextField[SIZE][SIZE];
		 _logicBoard = new int[SIZE][SIZE];
			for(int  i = 0 ;i<SIZE;i++) {
				for(int  j = 0 ;j<SIZE;j++) {
					_logicBoard[i][j]=0;			
				}
			}
	}
	
	public  boolean isValidInput(int i,int j) {
		int blockLocaton[] = getBlockLocation(i,j); 
		int inputNum = this._logicBoard[i][j];
		int columnCheck =-1,rowCheck=-1;
		int k=0,t = 0;
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Error");
		alert.setHeaderText("Invalid Input");
		alert.setContentText("\nPlease provide a valid number");
		while(k<=SIZE-1){
			 if(this._logicBoard[k][j]==inputNum&&k!=i) {
				columnCheck =this._logicBoard[k][j];
				break;
			 	}
			k++;
		}
		while(t<=SIZE-1){
			 if(this._logicBoard[i][t]==inputNum&&t!=j) {
				 rowCheck =this._logicBoard[i][t];
				 break;
			 	}
			t++;
		}
			
			if(inputNum==columnCheck||inputNum==rowCheck) {
				if(columnCheck>0) {
					alert.setContentText(inputNum+" "+"already exists on the "+k+","+j+" box "+alert.getContentText());
					alert.showAndWait();
					return false;
				}
				else {
					alert.setContentText( inputNum+ " "+"already exists on the "+i+","+t+" box "+alert.getContentText());
					alert.showAndWait();
					return false;
				}
			}
			for(k = blockLocaton[0];k<=blockLocaton[0]+2;k++) {
				for(t= blockLocaton[1];t<=blockLocaton[1]+2;t++) {
					if(this._logicBoard[k][t]==inputNum&&k!=i&&t!=j) {
						alert.setContentText(inputNum+" "+"already exists on the "+k+","+t+" box "+alert.getContentText());
						alert.showAndWait();
						return false;
					}
				}	
			}
		return true;
	}
		
	public  int[] getBlockLocation(int i, int j) {
		int BlockLocation[]= new int[2];
		if(i>=0&&i<=2) {
			 BlockLocation[0] = 0;
			if(j>=0&&j<=2)
				BlockLocation[1] = 0;
			else if(j>=3&&j<=5)
				 BlockLocation[1] = 3;
			else
				 BlockLocation[1] = 6;	
		}
		else if(i>=3&&i<=5) {
			BlockLocation[0] = 3;	
			if(j>=0&&j<=2)
				BlockLocation[1] = 0;
			else if(j>=3&&j<=5)
				 BlockLocation[1] = 3;
			else
				 BlockLocation[1] = 6;
		}
		else if(i>=6&&i<=8) {
			BlockLocation[0] = 6;
			if(j>=0&&j<=2)
				BlockLocation[1] = 0;
			else if(j>=3&&j<=5)
				 BlockLocation[1] = 3;
			else
				 BlockLocation[1] = 6;	
		}
		return 
				BlockLocation;
	}
	
}