
/***********************************
 * Student Name : Thomas Khalil	   *		
 * ASSIGNMENT 2                    *
 ***********************************/

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Toy {


	public String toyNum;
	public String memNum;
	public String desc;
	public int age;
	public char stat;
	public double rate;
	public String hiringDate;

	public Toy (String toyID, String description, int minAge, double dailyRate,
			char status, String memberID, String hireDate)
	{
		this.toyNum		= toyID;
		this.memNum		= memberID;
		this.desc		= description;
		this.age		= minAge;
		this.stat		= status;
		this.rate		= dailyRate;
		this.hiringDate	= hireDate;
		this.stat ='U';
	}

	public Toy (String toyID, String description, int minAge, double dailyRate)
	{
		this.toyNum 	= toyID;
		this.desc		= description;
		this.age		= minAge;
		this.rate		= dailyRate;
		this.stat ='A';
	}

	public String getToyID()
	{
		return toyNum;
	}
	public String getMemNum()
	{

		return memNum;
	}
	public String getDesc()
	{
		return desc;
	}
	public int getAge()
	{
		return age;
	}
	public char getStatus()
	{
		return stat;
	}
	public double getDailyrate()
	{
		return rate;
	}
	public String getHireDate()

	{
		return hiringDate;
	}

	public  boolean borrowToy (String memberID, String childDOB)
	{
		String DOB = childDOB ;
		String memID=memberID;

		String todayDate ;
		int YEAR,MONTH,DAY;
		boolean Indicator = false ;



		String s[] = DOB.split("/");;
		String s1 = s[0];
		String s2 = s[1];
		String s3 = s[2];

		YEAR=Integer.parseInt(s3);
		MONTH=Integer.parseInt(s2);
		DAY=Integer.parseInt(s1);



		Calendar dob = Calendar.getInstance();  
		dob.set(YEAR,MONTH,DAY);  

		Calendar today = Calendar.getInstance();  
		int old = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);  

		if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR))
		{
			old--;
		}



		DateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");

		Date now = Calendar.getInstance().getTime();        



		if (old<=this.age) 
		{
			Indicator = false;
		} 
		else if (old>=this.age) 
		{
			this.stat ='U';
			this.memNum = memID;
			todayDate = dateF.format(now);
			this.hiringDate =todayDate;
			Indicator = true;
		}

		return Indicator;


	}


	public double returnToy(String returnDate)
	{
		String returnD = returnDate ;
		String leaseDate = this.hiringDate;

		int YEARr,MONTHr,DAYr,YEARh,MONTHh,DAYh;
		double fee=0,dailyFee=this.rate,day1,day2,time ;


		String s[] = returnD.split("/");;
		String s1 = s[0];
		String s2 = s[1];
		String s3 = s[2];

		String p[] = leaseDate.split("/");;
		String p1 = p[0];
		String p2 = p[1];
		String p3 = p[2];


		YEARr=Integer.parseInt(s3);
		MONTHr=Integer.parseInt(s2);
		DAYr=Integer.parseInt(s1);

		YEARh=Integer.parseInt(p3);
		MONTHh=Integer.parseInt(p2);
		DAYh=Integer.parseInt(p1);


		Calendar leaseCalendar = Calendar.getInstance();  
		leaseCalendar.set(YEARh,MONTHh,DAYh);  


		Calendar returnCalendar = Calendar.getInstance();
		returnCalendar.set(YEARr,MONTHr,DAYr); 

	
		day1 = leaseCalendar.getTimeInMillis();
		day2 = returnCalendar.getTimeInMillis();
		time = day2 - day1;

		System.out.println("time is "+time);

		double numOfDays = time / (24 * 60 * 60 * 1000);

		fee = dailyFee * numOfDays;
		this.stat='A';
		this.hiringDate=null;
		this.memNum=null;

		return fee;
	}


	public void print()
	{

		if (this.stat=='U') {
			System.out.printf("%-9s",this.getToyID());
			System.out.printf("%-20s",this.getDesc());
			System.out.printf("%4s",this.getAge()+"yo");
			System.out.printf("%2s","$");
			System.out.printf("%.2f",this.getDailyrate());
			System.out.printf("%3s",this.getStatus());
			System.out.printf("%8s",this.getMemNum());
			System.out.printf("%12s",this.getHireDate()+"\n");
		} else {
			System.out.printf("%-9s",this.getToyID());
			System.out.printf("%-20s",this.getDesc());
			System.out.printf("%4s",this.getAge()+"yo");
			System.out.printf("%2s","$");
			System.out.printf("%.2f",+this.getDailyrate());
			System.out.printf("%4s",this.getStatus()+"\n");
		}


	}

}