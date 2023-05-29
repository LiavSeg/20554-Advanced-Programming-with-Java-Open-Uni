import java.util.Iterator;
import java.util.Random;

public class SortedStudents {

	public static void main(String[]args) {
		final int PASSED = 60;
		SortedGroup<Student> studentsGroup = new SortedGroup<Student>();
		SortedGroup<Student> passedTheTest;
		Student gradeLimit = new Student("",0,PASSED);
		
		System.out.println("The following list contains personal and academic information of students in our class");
		addStudents(studentsGroup);
		printStudentGroup(studentsGroup);
		
		System.out.println("The following list contains personal and academc information of students who passed the test");
		passedTheTest =  SubSortedGroup.reduce(studentsGroup,gradeLimit);
		printStudentGroup(passedTheTest);
		
		System.out.println("During this semester a new group of students joined our class\n");
		System.out.println("The following list contains the updated information");
		addStudents(studentsGroup);
		printStudentGroup(studentsGroup);
		
		System.out.println("This semester the university decided to eliminate random group of students \n");
		removeRandomGroup(studentsGroup);
		printStudentGroup(studentsGroup);
		
		System.out.println("The following list contains students that have passed the test");
		passedTheTest =  SubSortedGroup.reduce(studentsGroup,gradeLimit);
		printStudentGroup(passedTheTest);
		
		System.out.println("Another group of students decided to join our class ");
		System.out.println("The following list contains personal and academc information of students");
		addStudents(studentsGroup);
		printStudentGroup(studentsGroup);
		
		System.out.println("As mentioned before the university decided to eliminate random students \n");
		System.out.println("For now,this is the last elimination, students wont get eliminated or added to our class\n");
		removeRandomGroup(studentsGroup);
		System.out.println("The following list contains the final updated information");
		printStudentGroup(studentsGroup);
	}

	 
	private static void removeRandomGroup(SortedGroup<Student> sGroup) {
		Iterator<Student> it;
		SortedGroup<Student> eliminatedGroup = new SortedGroup<Student>();
		int count = 0;
		addStudents(eliminatedGroup);
		addStudents(eliminatedGroup);
		System.out.println("                Eliminated students:");
		it = eliminatedGroup.iterator();
		while(it.hasNext()) {
			Student removed = it.next();
			count = sGroup.remove(removed);
			if(count > 0)
				System.out.println("              "+count+" students with grade: " +removed.getGrade());
		}
		System.out.println("");
	}
	
	private static void addStudents(SortedGroup<Student> sGroup) {
		final  int NAMES = 13;
		final  int ID_LIMIT = 900000000;
		final  int ID_OFFSET = 100000000;
		final  int GRADES_LIMIT = 101;
		Random rand = new Random();
		String namesArr[] = {"Roy","Omer","Alon","Maya","Linoy","Dan","Daniel",
							"Ron","Amir","Leibnitz","Hezy","Laflesian","Eddie"};
		for(int i = 0 ; i<NAMES ; i++) {
			int grade = rand.nextInt(GRADES_LIMIT);
			int id = rand.nextInt(ID_LIMIT)+ID_OFFSET;
			int name  = rand.nextInt(NAMES);
			Student s =  new Student(namesArr[name],id,grade);
			sGroup.add(s);
		}	
	}
	private static void printStudentGroup(SortedGroup<Student> sGroup)	{
		Iterator<Student> it = sGroup.iterator();
		Student st;
		System.out.print("                  ");
		System.out.print("  \n--------------------------------------------------------------------\n");
		while(it.hasNext()) {
			st = it.next();
			System.out.println(st);
		}
		System.out.println("--------------------------------------------------------------------\n"); 
	}

}
