
public class Student implements Comparable<Student>{
	private String _name;
	private int _id;
	private int _grade;

	public Student(String name,int id,int grade) {
		_name = name;
		_id = id;
		_grade = grade;
	}
	
	public int getGrade() {
		return this._grade;
	}
	public String getName() {
		return this._name;
	}
	public int getId() {
		return this._id;
	}
	@Override
	public int compareTo(Student o) {
	
		if(this.getGrade()>o.getGrade())
				return 1;
		else if(this.getGrade()<o.getGrade())
			return -1;

		return 0;
	}
	public boolean equals(Object o) {
		Student s = (Student)o;
		if(this.compareTo(s)!=0)
			return false;
		return true;
	}
	public String toString() {
		return "Student's name: "+_name+" Student's ID: "+_id +" Student's grade: "+_grade+"\n";
	}
}
