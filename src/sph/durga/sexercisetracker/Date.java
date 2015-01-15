package sph.durga.sexercisetracker;

public class Date 
{
	int _day;
	int _month;
	int _year;
	
	public Date(int day, int month, int year)
	{
		_day = day;
		_month = month;
		_year = year;
	}
	
	public int get_day() {
		return _day;
	}

	public void set_day(int _day) {
		this._day = _day;
	}

	public int get_month() {
		return _month;
	}

	public void set_month(int _month) {
		this._month = _month;
	}

	public int get_year() {
		return _year;
	}

	public void set_year(int _year) {
		this._year = _year;
	}
}
