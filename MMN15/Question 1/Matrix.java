import java.util.Random;
import java.util.Scanner;

public class Matrix extends Thread {
	private int[][] _matrix;
	private  int _rows;
	private int _columns;
	Scanner scan;
	
	public Matrix(String i,String j){
		parseMatrixDimensions(i,j);
		if(isValidMatrix()) {
			_matrix = new int[_rows][_columns];
			fillCells();
		}
	
	}

	public int getRows() {
		return _rows; 
	}
	public int[] getRow(int i) {
		return _matrix[i]; 
	}
	public int[] getColumn(int j) {
		int column[] = new int[_matrix.length];
		for(int i = 0 ; i <_matrix.length;i++)
			column[i] = _matrix[i][j];
			return column; 
	}
	
	public int getColumns() {
		return _columns; 
	}
	public int getScaler(int i,int j) {
		return _matrix[i][j]; 
	}
	public void setScaler(int i,int j,int scaler) {
		 _matrix[i][j] = scaler; 
	}
	public void printMat() {	
		for(int i = 0;i<_rows;i++) {
			System.out.print(" | ");
			for(int j = 0;j<_columns;j++) {
				System.out.print(_matrix[i][j]);
				if(_matrix[i][j]==10)
				System.out.print("| ");
				else
					System.out.print(" | ");
			}
				System.out.print("\n    ");
		}	
		System.out.print("\n");
		
	}
	
	private void parseMatrixDimensions(String i,String j) {
		try {
			_rows = Integer.parseInt(i);
			_columns = Integer.parseInt(j);
			if(_rows<=0 || _columns<= 0) {
				System.out.println("*Invalid matrix dimensions - The dimensions of a "
						+ "matrix must be positive integer");
			}
		}
		catch (NumberFormatException e) {
			System.out.println("*Invalid matrix dimensions - The dimensions of a matrix must "
					+ "be positive integer values greater than 0");
			_rows = _columns = 0;
		}
	}
	
	public Boolean isValidMatrix() {
		if(_rows>0&&_columns>0)
			return true;
		return false;
	}
	
	private void fillCells() {
		Random r = new Random();
		for(int i = 0;i<this._rows;i++) {
			for(int j = 0;j<this._columns;j++) {
				_matrix[i][j] = r.nextInt(11);
			}
		}	
	}
}
