package data;

import java.time.LocalDateTime;

public enum Freq {
	P1(100,25,2,5,1);
	/*P2("P2",1),
	P3("P3",2),
	P4("P4",3),
	P5("P5",4),
	P6("P6",5),
	P7("P7",6),
	I1("I1",7),
	I2("I2",8),
	I3("I3",9),
	I4("I4",10);*/
	
	int f1,f2,f3,f4,f5;
	LocalDateTime begin;
	LocalDateTime end;
	Freq(int f1, int f2, int f3, int f4, int f5)
	{
		this.f1 = f1;
		this.f2 = f2;
		this.f3 = f3;
		this.f4 = f4;
		this.f5 = f5;
	}
	
	public int valueTime(LocalDateTime t)
	{
		return 0;
	}
	
	
}
