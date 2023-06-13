
public class MatrixMult extends Thread {
	private  int[] _row;
	private  int[] _column;
	private MyMonitor _monitor;
	private int _num;
	private final int VECTOR_LENGTH;
	
	public  MatrixMult(int[] A,int[] B,MyMonitor m,int num){
		_row = A;
		_column = B;
		VECTOR_LENGTH = A.length;
		_monitor = m;
		_num= num;
		}
	
	public void run() {
		_monitor.waitForMyTurn(_num);
		mult();
		_monitor.imDone();
	}
	
	private  void  mult(){
		String line = " | ";
		int dotProduct = 0;
		for(int k = 0;k<VECTOR_LENGTH;k++) {
			dotProduct += _row[k]*_column[k];
		}
		if(dotProduct>=100)
			line = " |";
		else if((dotProduct<=9))
			line = " |  ";
		System.out.print(line+dotProduct);
	}
	private static boolean isDefined(Matrix a,Matrix b) {
		if(!a.isValidMatrix()||!b.isValidMatrix())
			return false;
		
		if(a.getColumns()!=b.getRows()) {
			System.out.println(" Undefined multiplication of matrices 'A' and 'B':\n Number of columns in matrix 'A' "
					+ "must be equal to the number of rows in matrix 'B'");
			return false;
		}
		
		System.out.print("A = ");
		a.printMat();
		System.out.print("B = ");
		b.printMat();
		return true;
	
	}
	private static void threadMultInit(Matrix A,Matrix B,MatrixMult[][] mult ,MyMonitor m) {
		int num =  1;
		for(int i = 0;i<A.getRows();i++) {
			int[] row = A.getRow(i);
			for(int j = 0;j<B.getColumns();j++) {
				 mult[i][j] = new MatrixMult(row,B.getColumn(j),m,num);
				 num++;
			}
		}
	}
	private static void startMult(MatrixMult[][] mult) {
		for(int i = 0;i<mult.length;i++) {
			for(int j = 0;j<mult[i].length;j++) {	
				mult[i][j].start();
			}
		}
	}
	private static Boolean numOfArgsValidation(String[] args) {
	
		if (args.length == 0) {
		    System.out.println("Matrices dimensions are missing - cannot proceed");
		    System.out.println("Please provide the dimensions via command-line arguments in order to proceed");
		    System.out.println("To provide the dimensions in Eclipse,please follow these steps:\n");
		    System.out.println("1) Right-click on the project folder");
		    System.out.println("2) Select 'Run As' and 'Run Configurations'");
		    System.out.println("3) In the 'Arguments' tab -> program arguments, enter the dimensions as command-line arguments");
		    return false;
		}
		else if(args.length<4) {
			System.out.println("Matrices dimensions are missing - can't procced");
			return false;
		}
		else if(args.length>4) {
			System.out.println("Excessive matrix dimensions detected - ignoring any excessive values \n");
		}
		return true;
	}
	
	
	public static void main(String[] args) {
		if(!numOfArgsValidation(args)) 
			return ;
		String iA = args[0];// number of rows in matrix A
		String jA = args[1];// number of columns in matrix A
		String iB = args[2];// number of rows in matrix B
		String jB = args[3];// number of columns in matrix B
		
		Matrix A = new Matrix(iA,jA);
		Matrix B = new Matrix(iB,jB);

		if(isDefined(A,B)) {	
			MyMonitor m = new MyMonitor(A.getRows());
			MatrixMult[][] mult =  new MatrixMult[A.getRows()][B.getColumns()];
			System.out.print("AB = ");
			threadMultInit(A,B,mult,m);
			startMult(mult);
		}
	}
}
	

