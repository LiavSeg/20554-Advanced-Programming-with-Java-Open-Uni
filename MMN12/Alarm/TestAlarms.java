/*
 *This class uses the Alarm and it's subclasses operations 
 */
import java.util.ArrayList;
public class TestAlarms {
	public static void process (ArrayList<Alarm> alarms) throws BadAlarm {
		int smokeDetector=0;//counts every smoke alarm triggered - **fire alarm is a smoke alarm also
		
		for(Alarm element: alarms) {
			if(element instanceof Elevator) {
				((Elevator)element).action();
				((Elevator)element).setFloor();
				System.out.println("\n");
			}
			else if(element instanceof Fire) {
				((Fire)element).action();
				smokeDetector++;
				System.out.println("Total smoke alarms triggered: " +smokeDetector+"\n\n");
			}
			else {
				((Smoke)element).action();
				smokeDetector++;
				System.out.println("Total smoke alarms triggered: " +smokeDetector+"\n\n");
			}
		}
	}
	
	public static void main(String[]args){
		ArrayList<Alarm> alarmList = new ArrayList<Alarm>() ;	
		try {
			Alarm e1 = new Elevator("Kaplan 12",4);
			Alarm e2 = new Elevator("Kaplan 12",21);
			Alarm e3 = new Elevator("Derech Yavne 11",12);
			Alarm e4 = new Elevator("Namir 39",5);
			Alarm e5 = new Elevator("Ben Yehuda 82",4);
			Alarm e6 = new Elevator("Kaplan 12",21);
			Alarm e7 = new Elevator("Hertzel 234",329);
			Alarm e8 = new Elevator("Hertzel 314",5);
			Alarm f1 = new Fire("d","Dan");
			Alarm f2 = new Fire("Carmel 32","Dana");
			Alarm f3 = new Fire("Rothchild","Hisbulla");
			Alarm f4 = new Fire("Kaplan 12","DEKEL");
			Alarm s1 = new Smoke("Kaplan 12","Dan");
			Alarm s2 = new Smoke("Namir 29","Dana");
			Alarm s3 = new Smoke("Derech Yavne 52","George Cantor");
			// remove the following comment and its addition to the list to get a BadAlarm exception message 
			//Alarm s4 = new Smoke(null,"Ragnar");
			Alarm s5 = new Smoke("Diagon Alley","Minerva");
			alarmList.add(e1);
			alarmList.add(e2);
			alarmList.add(e3);
			alarmList.add(e4);
			alarmList.add(f1);
			alarmList.add(f2);
			alarmList.add(f3);
			alarmList.add(f4);
			alarmList.add(f1);
			alarmList.add(f2);
			alarmList.add(f3);
			alarmList.add(f4);
			//alarmList.add(s4);
			alarmList.add(s1);
			alarmList.add(s2);
			alarmList.add(s2);
			alarmList.add(s5);
			alarmList.add(s3);
			alarmList.add(e5);
			alarmList.add(e6);
			alarmList.add(e7);
			alarmList.add(e8);
			process(alarmList);
		}
		catch(BadAlarm e) {
			System.out.println("Address can not be NULL please recheck this address");
		}
	}

}
