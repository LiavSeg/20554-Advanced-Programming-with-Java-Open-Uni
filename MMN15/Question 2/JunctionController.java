import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class JunctionController implements Runnable{

	@FXML
    private Canvas roadCanv;
	private double UpCoordinates[];
	private double DownCoordinates[];
	private double leftCoordinates[];
	private double rightCoordinates[];
	private final long UP_DOWN_SLEEP = 2220;
	private final long LEFT_RIGHT_SLEEP = 3330;
	private final int UP_DOWN = 1;
	private final int LEFT_RIGHT = 2;
	private GraphicsContext gc;
	private BlinkThread blink;
	private Thread junctionThread;
	
    public void initialize() {
    	gc = roadCanv.getGraphicsContext2D();
    	initCordinates();
    	initRoad();
    	strokeTrafficLights();	
    	blink = new BlinkThread(gc,UpCoordinates,DownCoordinates);
  	    junctionThread = new Thread(this);
        junctionThread.start();
        Platform.runLater(()->{
        	blink.start();
        });	
    }
    
    public void run() {
    	
    	while(true) {
    	
    		junctionOperation(UP_DOWN);
    		goToSleep(UP_DOWN_SLEEP);
	   		blink.switchBlink(leftCoordinates,rightCoordinates,LEFT_RIGHT);
	   		
	   		junctionOperation(LEFT_RIGHT);
	    	goToSleep(LEFT_RIGHT_SLEEP);
      		blink.switchBlink(UpCoordinates,DownCoordinates,UP_DOWN);
      	}
    }    
   
    private void initRoad() {
    	gc.fillRect(0, 0, roadCanv.getWidth(), roadCanv.getHeight());
    	gc.setFill(Color.WHITE);
    	for(int i = 0,k=0;i<30;i++,k+=50) {
    		gc.fillRect(k, roadCanv.getHeight()/2-32, 30, 10);
    		gc.fillRect(roadCanv.getWidth()/2, k, 10, 30);
    	}
    }
    
    private void junctionOperation(int turn) {
	   	if(turn == UP_DOWN) {
	   		turnOnTrafficRedLight(UpCoordinates,DownCoordinates,50,50,0,0);
	   		turnOnTrafficGreenLight(rightCoordinates,leftCoordinates);
	   		turnOnPedestrianRedLight(leftCoordinates,rightCoordinates,50,-38);
	   	}
	   else if(turn == LEFT_RIGHT) {
		   turnOnTrafficRedLight(leftCoordinates,rightCoordinates,50,-38,0,0);
		   turnOnTrafficGreenLight(UpCoordinates ,DownCoordinates);
		   turnOnPedestrianRedLight(UpCoordinates,DownCoordinates,50,50);  
	   }  
   }
    
    private void goToSleep(long time) {
	   try {
		   Thread.sleep(time);
	   } 
	   catch (InterruptedException e) {
			e.printStackTrace();
	   }
   }
    private void turnOnPedestrianRedLight(double x1[] ,double x2[],int k,int j) {
    	gc.setFill(Color.RED);
		gc.fillRect(x1[0]+k, x1[1], 20, 20);//turns on pedestrians red lights 
		gc.fillRect(x2[0]+j, x2[1], 20, 20);//turns on pedestrians red lights 
		standingMan(x1,0);//draws a standing human figure on the red light 
		standingMan(x2,88);//draws a standing human figure on the red light 
		standingMan(x2,0);//draws a standing human figure on the red light 
		
		gc.setFill(Color.BLACK); 
		gc.fillRect( x1[0]+k,x1[1]+30, 20, 20);//turns off the pedestrian green lights
		gc.fillRect( x2[0]+j,x2[1]+30, 20, 20);//turns off the pedestrian green lights
   }
    private void turnOnTrafficRedLight(double x1[],double x2[],int k,int j,int k1,int j1) {
   	   gc.setFill(Color.RED);
   	   gc.fillOval(x1[0],x1[1], 30, 30);//turns on traffic red lights 
   	   gc.fillOval(x2[0],x2[1], 30, 30);//turns on traffic red lights 
   	  
   	   gc.setFill(Color.BLACK);	
   	   gc.fillOval(x1[0],x1[1]+30, 30, 30);//turns off the  traffic green lights 
   	   gc.fillOval(x2[0],x2[1]+30, 30, 30); //turns off the  traffic green lights 
 	   gc.fillRect(x1[0]+k,x1[1]+k1 ,20, 20);//turns off the pedestrians red lights 
 	   gc.fillRect(x2[0]+j,x2[1]+j1, 20, 20);//turns off the pedestrians red lights 
   }
    private void turnOnTrafficGreenLight(double x1[],double x2[]) {
       gc.setFill(Color.BLACK);	
   	   gc.fillOval(x1[0],x1[1], 30, 30); //turns off the traffic red lights 
   	   gc.fillOval(x2[0],x2[1], 30, 30); //turns off the traffic red lights 
	  
   	   gc.setFill(Color.GREEN);
   	   gc.fillOval(x1[0],x1[1]+30, 30, 30);//turns on the traffic green lights 
   	   gc.fillOval(x2[0],x2[1]+30, 30, 30);//turns on the traffic green lights 
	   
   }
  
   
  //Draws traffic light frames and poles and pedestrians light frames
    private void strokeTrafficLights() {
    
    	gc.setStroke(Color.WHITE);
    	gc.strokeRect( UpCoordinates[0]-5, UpCoordinates[1]-10,40,75);//Traffic light frame
    	gc.strokeRect( UpCoordinates[0]+10, UpCoordinates[1]+66,10,50);//Traffic light pole
    	gc.strokeRect( UpCoordinates[0]+44, UpCoordinates[1]-5,32,58);//Pedestrian light frame

    	gc.strokeRect( DownCoordinates[0]-5, DownCoordinates[1]-10,40,75);//Traffic light frame
    	gc.strokeRect( DownCoordinates[0]+10, DownCoordinates[1]+66,10,50);//Traffic light pole
   		gc.strokeRect( DownCoordinates[0]+44, DownCoordinates[1]-5,32,58);//Pedestrian light frame

   		gc.strokeRect( leftCoordinates[0]-5, leftCoordinates[1]-10,40,75);//Traffic light frame
   		gc.strokeRect( leftCoordinates[0]+10, leftCoordinates[1]+66,10,50);//Traffic light pole
   		gc.strokeRect( leftCoordinates[0]+44, leftCoordinates[1]-5,32,58);//Pedestrian light frame

   		gc.strokeRect( rightCoordinates[0]-5, rightCoordinates[1]-10,40,75);//Traffic light frame
   		gc.strokeRect( rightCoordinates[0]+10, rightCoordinates[1]+66,10,50);//Traffic light pole
   		gc.strokeRect( rightCoordinates[0]-44, rightCoordinates[1]-5,32,58);//Pedestrian light frame
   }
 
    //Coordinates initialization 
    private void initCordinates () {
    	UpCoordinates =  new double[2];
    	UpCoordinates[0] = roadCanv.getWidth()/2+50;
    	UpCoordinates[1] = 20;

    	DownCoordinates =  new double[2];
    	DownCoordinates[0] = roadCanv.getWidth()/2-90; 
    	DownCoordinates[1] = 420;
    	
    	leftCoordinates =  new double[2];
    	leftCoordinates[0] = 10;
    	leftCoordinates[1] = 130;
    	
    	rightCoordinates =  new double[2];
    	rightCoordinates[0] =roadCanv.getWidth()-50 ;
    	rightCoordinates[1] = 260;
    }
 
    //draws a standing human figure on the red light 
    private void standingMan(double[] c,int k) {
		gc.setFill(Color.BLACK);
		gc.fillOval(c[0]+58-k,c[1], 5, 5);//head
		gc.setStroke(Color.BLACK);
		gc.strokeLine( c[0]+60-k,c[1]+5,  c[0]+60-k, c[1]+15);//body
		gc.strokeLine( c[0]+61-k,c[1]+5,  c[0]+62-k, c[1]+10);//rArm
		gc.strokeLine( c[0]+58-k,c[1]+10,  c[0]+59-k, c[1]+5);//lArm
		gc.strokeLine( c[0]+61-k,c[1]+15,  c[0]+62-k, c[1]+20);//rLeg
		gc.strokeLine( c[0]+58-k,c[1]+20,  c[0]+59-k, c[1]+15);//lLeg
		
	}
}
