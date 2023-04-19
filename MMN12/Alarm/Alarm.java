import java.util.Date;
//this class in an abstract class which represents 
public abstract class Alarm {
	protected Date _hour;//instance variable which holds time and date 
	protected String _time;//instance variable stores the time of alarm activation
	protected String _address;//instance variable stores the address of alram's location
	private final int START_OF_TIME = 3;//third gap in the Date object's toString is the start of the time data
	private final int END_OF_TIME = 4;//fourth gap in the Date object's toString is the end of the time data
	/*
	* This a constructor of Alarm class 
	* this is an abstract class therefore this constructor used within other constructors 
	* of other classes which inherits from this class
	* and extracts the the actual hour out of Date's toString returned value and returns it
	*/	
	public Alarm(String address)throws BadAlarm{
		if(address==null)
			throw new  BadAlarm("Invalid null address");
		_time = new String(timeExtraction());
	}
	/*
	* This is a toString method of the Alarm class 
	* uses timeExtraction as this returns a string which represents the alarm activation time
	*/	
	public String toString() {
		return timeExtraction();
	}
	
	public abstract void action() ;
		
	
	/*
	* This method is a private method which initializes the _hour field in each use
	* and extracts the the actual hour out of Date's toString returned value and returns it
	*/	
	private String timeExtraction() {
		int i,start,end,count;
		i = start = end = count = 0;
		String fullDate;
		_hour = new Date();//inits the date instance variable in each call of this function
		fullDate = _hour.toString();
		//extracting the relevant info out of Date's toString 
		while(count<END_OF_TIME) {
			if(fullDate.charAt(i)==' ') {
				count ++;
				if(count == START_OF_TIME)
					start = i;//keeps the position of the third gap
			}
			i++;
		}
		end = i;//keeps the end position of the fourth gap
		return fullDate.substring(start,end);
		}

}