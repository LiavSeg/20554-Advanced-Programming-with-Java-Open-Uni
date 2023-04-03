import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
/* This class is a controller class for TmprGrph project 
 	Draws a graph of average temperatures over five years 
 	uses Database class to get temperature and years data 
  */
 
public class TmprGrphController {

    @FXML
    private Canvas canv;//fxid of the graph canvas
    @FXML
    private Canvas headLine;//fxid of the headline canvas
    @FXML
    private Canvas monthsNum;//fxid of the months canvas
    private GraphicsContext gc;
    private GraphicsContext gcHead;
    private GraphicsContext gcMonths;
    private final double  WIDTH =20;//A constant width for each graph
    private final double  MONTHS =12;
    private final double  GAP =50;//A constant gap between each graph
    private final int TEMP_GAP = 10;//Constant gap for the temperature display 
    private final double SCALE = 5;//Scaler for the temperature graphs 
    private final int HEAD_SCALER = 3;//Scaler for the headline's position 
    private int _j=0;	
    private DataBase _yearTemps = new DataBase();
    public void initialize() {//INI
    	gc = canv.getGraphicsContext2D(); 
    	gcHead = headLine.getGraphicsContext2D(); 
    	gcMonths = monthsNum.getGraphicsContext2D(); 
    	 }
   
    
    @FXML
    void nextGrph(ActionEvent event){
    	int[] arr = _yearTemps.getData(j);
    	int k,max,min;
    	k=min=max=1;
    	
    	gcHead.clearRect(0, 0,canv.getWidth(), canv.getHeight());//Clearing previous headline
    	gcMonths.clearRect(0, 0,monthsNum.getWidth(), monthsNum.getHeight());//Clearing previous months 
    	gc.clearRect(0, 0,canv.getWidth(), canv.getHeight());//Clearing previous graphs
    	gcHead.strokeText("Monthly Climate Graphs -"+arr[0],headLine.getWidth()/HEAD_SCALER,GAP-WIDTH);//Note:first value of the array stores the current year 
    
    	while(k<=MONTHS){
    		if(arr[min]>arr[k])
    			min=k;
    		if(arr[max]<arr[k])
        		max=k;
    		k++;
    		
    	}
    	
    	//Draws the graph columns using Database class
    	for(int i = 1;i<=MONTHS;i++){//Graphs layout 
    		gc.setFill(Color.GRAY);
    		gc.fillRect(GAP*(i), canv.getHeight()-arr[i]*SCALE ,WIDTH,  canv.getHeight());
    		gcMonths.strokeText(""+i, GAP*(i), monthsNum.getHeight()-WIDTH);
    		}
    	//Temperatures layout matching the scaled graphs
    	for(int i =0;i<=5;i++) {
    		gc.strokeText(""+i*TEMP_GAP, TEMP_GAP, canv.getHeight()-i*TEMP_GAP*SCALE);//
    	}
		//Marks the lowest average temperature of the current year displayed in red
    	gc.setFill(Color.RED);
		gc.fillRect(GAP*(max), canv.getHeight()-arr[max]*SCALE ,WIDTH,  canv.getHeight());
		gcMonths.strokeText(""+max, GAP*(max), monthsNum.getHeight()-WIDTH);
		//Marks the lowest average temperature of the current year displayed in blue
		gc.setFill(Color.BLUE);
		gc.fillRect(GAP*(min), canv.getHeight()-arr[min]*SCALE ,WIDTH,  canv.getHeight());
		gcMonths.strokeText(""+min, GAP*(min), monthsNum.getHeight()-WIDTH);
    	_j++;
    	//In case more than one month is minimum/max average temperature mark the rest
    	for( k = 1 ;k<=MONTHS;k++) {
    		int maxTemp = arr[max];
    		int minTemp  =  arr[min];
    		if(arr[k]==maxTemp&&max!=k) {
    			gc.setFill(Color.RED);
    			gc.fillRect(GAP*(k), canv.getHeight()-arr[k]*SCALE ,WIDTH,  canv.getHeight());
    			gcMonths.strokeText(""+k, GAP*(k), monthsNum.getHeight()-WIDTH);
    		}
    		if(arr[k]==minTemp&min!=k) {
    			gc.setFill(Color.BLUE);
    			gc.fillRect(GAP*(k), canv.getHeight()-arr[k]*SCALE ,WIDTH,  canv.getHeight());
    			gcMonths.strokeText(""+k, GAP*(k), monthsNum.getHeight()-WIDTH);
    		}
    	}	
    	
    	//condition for a repetitive display of the annual climate graphs - 5 years in total
    	if(_j==SCALE)
    		_j=0;	
   } 
}
