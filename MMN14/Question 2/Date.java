import java.io.Serializable;
import java.util.Objects;

public class Date implements Serializable {
	private int _day;
	private int _month;
	private int _year;
	
	public Date(int day,int month,int year)  {
		_day = day;
		_month = month;
		_year = year;
	}
	
	public int getDay() {
		return _day;
	}

	public void setDay(int _day) {
		this._day = _day;
	}

	public int getMonth() {
		return _month;
	}

	public void setMonth(int _month) {
		this._month = _month;
	}

	public int getYear() {
		return _year;
	}

	public void setYear(int _year) {
		this._year = _year;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_day, _month, _year);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Date){
			Date other = (Date) obj;
			return _day == other._day && _month == other._month && _year == other._year;	
			}
		return false;
	}


}
