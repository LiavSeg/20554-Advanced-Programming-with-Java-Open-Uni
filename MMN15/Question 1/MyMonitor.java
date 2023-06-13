
public class MyMonitor {
	private int nextTurn ;
	private int _rowLength;	
	public MyMonitor( int length) {
		nextTurn =1;
		_rowLength = length;
	} 
	public synchronized void waitForMyTurn(int threadNumber) {
		while(threadNumber >nextTurn)
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
	public synchronized void imDone() {
		if(nextTurn%_rowLength==0)
			System.out.print(" |\n     ");
		nextTurn++;
		notifyAll();
	}
}
