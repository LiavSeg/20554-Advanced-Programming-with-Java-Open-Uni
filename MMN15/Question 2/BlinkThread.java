import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BlinkThread extends Thread {
	
	private final long BLINK_TIME = 222;
	private final int LEFT_RIGHT = 2;
	private final int UP_DOWN = 1;
	private GraphicsContext gc;
	private double c1[];
	private double c[];
	private int turn;
	
	public BlinkThread(GraphicsContext gc, double c[], double c1[]) {
		this.gc = gc;
		this.c1 = c1;
		this.c = c;
		turn = 1;
	}

	public void run() {
		super.run();
		blinkLights();
	}

	public void switchBlink(double c[], double c1[], int turn) {
		this.c = c;
		this.c1 = c1;
		this.turn = turn;
	}

	private void goToSleep() {
		try {
			Thread.sleep(BLINK_TIME);
		} catch (InterruptedException e) {
			// TODOa Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//draws a walking human figure on a green light 
	private void walkingMan(double[] c, int k) {// drawing a man walking
		gc.setFill(Color.BLACK);
		gc.fillOval(c[0]+58-k,c[1]+30,5,5);// head
		gc.setStroke(Color.BLACK);
		gc.strokeLine(c[0]+60-k,c[1]+35,c[0]+60-k,c[1]+45);// body
		gc.strokeLine(c[0]+61-k,c[1]+45,c[0]+64-k,c[1]+50);// rLeg
		gc.strokeLine(c[0]+56-k,c[1]+50,c[0]+60-k,c[1]+45);// lLeg
		gc.strokeLine(c[0]+58-k,c[1]+40,c[0]+60-k,c[1]+34);// lArm
		gc.strokeLine(c[0]+63-k,c[1]+40,c[0]+58-k,c[1]+34); // rArm
	}

	private void blinkLights() {
		while (true) {
			if (turn == UP_DOWN) {
				turnOnGreenLight(50,50,0);;
				goToSleep();
				turnOffGreenLight(50,50);
				goToSleep();

			} else if (turn == LEFT_RIGHT) {
				turnOnGreenLight(50,-38,88);
				goToSleep();
				turnOffGreenLight(50,-38);
				goToSleep();
			}
		}

	}
	private void turnOnGreenLight(int i,int j,int scale) {
		gc.setFill(Color.GREEN);
		gc.fillRect(c[0] + i, c[1] + 30, 20, 20);
		gc.fillRect(c1[0] + j, c1[1] + 30, 20, 20);
		walkingMan(c,0);
		walkingMan(c1,0+scale);	
	}
	private void turnOffGreenLight(int i,int j) {
		gc.setFill(Color.BLACK);
		gc.fillRect(c[0] + i, c[1] + 30, 20, 20);
		gc.fillRect(c1[0] + j, c1[1] + 30, 20, 20);
	}

}
