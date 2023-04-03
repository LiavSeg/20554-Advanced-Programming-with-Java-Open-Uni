
import java.util.ArrayList;
import java.util.Random;
/*
 * this class provides data of monthly average temperatures 
 * of 5 years [unnecessarily consecutive] 
 * */
public  class DataBase {
	private  ArrayList<int[]> _temps;
	private final int TOTAL_DATA = 13;// 1-13 holds the average temperature 
	private final int YEARS = 5; // max temperature 
	private final int MAX_TEMP = 50; // max temperature 
	private final int YEAR_INDEX = 0;//0 holds the year info
	/*
	 * constructs a DataBase object 
	 * years 2016-2021
	 * randomly fills the average temperatures 
	 */
	public DataBase() {
		int year = 2016;
		_temps = new ArrayList<int[]>();
		Random r = new Random();
		for(int j= 0; j<YEARS;j++,year++){
			int[] tempData = new int[TOTAL_DATA];
			for(int i = 1; i<TOTAL_DATA;i++){
				tempData[i]=r.nextInt(MAX_TEMP);
				}
			tempData[YEAR_INDEX] = year;
			_temps.add(tempData);
			}
		
			
		}
	//gets the i-th year's data 
	public int[] getData(int i){
	    return _temps.get(i);

	}
	
       
    }
