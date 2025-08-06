import java.util.*;
public class CourseReg {

	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("		------Course Registration------");
		int opt = 1;
		int choice = -1;
		while(opt!=3) 
		{
			System.out.println();
			System.out.println("Enter 1 to register , Enter 2 to show courses available , Enter 3 - Exit");
			
			boolean valid = false;
			
			while(valid==false) 
			{
				String msg = "";
				try 
				{
					msg = sc.next();
					choice = Integer.parseInt(msg);
					valid = true;
					
				}
						
				catch(Exception e) 
				{
					System.out.println("Alert : Enter only numbers");
					System.out.println();
					System.out.println("Enter 1 to register , Enter 2 to show courses available , Enter 3 - Exit");

				}
			}
		
			
			
			
			CourseDetails c = new CourseDetails();
			
			switch(choice)
			{
				case 1:
					c.regCourse();
					break;
				case 2:
					System.out.println("Here are the courses we offering");
					System.out.println("");
					c.availableCourses();
					break;
				case 3:
					System.out.println("Bye Bye!! Have a nice day");
					System.exit(0);
					break;
				default:
					System.out.println("Invalid Choice");
			}
		}

		sc.close();
		
	}

	

}
