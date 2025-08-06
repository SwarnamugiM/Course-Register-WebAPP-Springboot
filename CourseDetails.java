import java.sql.*;
import java.util.*;
public class CourseDetails 
{
	
	public void regCourse()
	{
		ValidateUserInput ui = new ValidateUserInput();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your name");
		String sname = sc.nextLine();
		if(!ui.validateName(sname)) {
			System.out.println("Name Shouldn't contain any digits & symbols");
			regCourse();
		}
		if(sname.length()<3)
		{
			System.out.println("Invalid Length for Name");
			regCourse();
		}
			
		
		System.out.println("Enter your UNIV.REG.NO");
		String regno = sc.next();
		boolean b = ui.validateRegNo(regno);
		while(!b)
		{
			System.out.println("Only Digits of length 13 allowed...");
			System.out.println();
			System.out.println("Enter your UNIV.REG.NO");
			regno = sc.next();
			b = ui.validateRegNo(regno);
		}
		
		
		long r = Long.parseLong(regno);
		
		System.out.println("Enter Your Gender (Male (or) Female)");
		String gender = sc.next();
		boolean d = ui.validateGender(gender);
		
		while(!d)
		{
			System.out.println("Only Male or Female Allowed.....");
			System.out.println();
			System.out.println("Enter Your Gender (Male (or) Female)");
			gender = sc.next();
			d = ui.validateGender(gender);
		}
		
		System.out.println("Enter your college email id");
		String mail = sc.next();
		
		boolean m = ui.validateEMail(mail);
		
		while(!m)
		{
			System.out.println("Invalid college mail id");
			System.out.println();
			System.out.println("Enter Your College mail id");
			mail = sc.next();
			m = ui.validateEMail(mail);
		}
		
		
		System.out.println("Enter the Course-ID");
		String cid = sc.next();
		
		boolean course = ui.validateCourseId(cid);
		
		while(!course)
		{
			System.out.println("Course-Id not available..");
			System.out.println();
			System.out.println("Enter the Course-Id");
			cid = sc.next();
			course = ui.validateCourseId(cid);
		}
		sc.close();
		String cname="";
		
		switch(cid)
		{
			case "cs18601":
				cname = "user interface";
				break;
			case "it18604":
				cname = "android app development";
				break;
			
			case "ch18603":
				cname = "water pollution";
				break;
				
			case "se18001":
				cname = "maths for ai and ml";
				break;
			
			case "se18005":
				cname = "corporate finance";
				break;
					
		}
		
		if(isAvailable(cid))
		{
			try 
			{
				ConnectJdbc connect = new ConnectJdbc();
				Connection con = connect.myConnection();
				String query = "insert into register values(?,?,?,?,?,?)";
				PreparedStatement st = con.prepareStatement(query);
				st.setString(1, cid);
				st.setString(2, cname);
				st.setString(3, sname);
				st.setLong(4,r);
				st.setString(5, gender);
				st.setString(6, mail);
				st.executeUpdate();
				
			
			}
			catch(Exception e)
			{
				System.err.println(e.getMessage());
			}
			
			System.out.println("Registered Successfully....");
		}
		
		
		else
		{
			System.out.println("Sorry , Course Intake was full");
		}
		
		
		
	}
	
	private boolean isAvailable(String cid) {
		String courseId = cid;
		int intake = 0;
		int registered = 0;
		
		
		try 
		{
			ConnectJdbc connect = new ConnectJdbc();
			Connection con = connect.myConnection();
			String q = "select intake,registered from course where cid = ?";
			PreparedStatement st = con.prepareStatement(q);
			st.setString(1, courseId);
			
			ResultSet res= st.executeQuery();
			
			while(res.next())
			{
				intake = res.getInt(1);
				registered = res.getInt(2);
			}
			
			if(registered<intake)
			{
				registered++;
				String query = "update course set registered = ? where cid = ?";
				PreparedStatement p = con.prepareStatement(query);
				p.setInt(1, registered);
				p.setString(2, courseId);
				p.executeUpdate();
				return true;
			}
		
		}
		catch(Exception e)
		{
			System.err.println("ERRROR"+e.getMessage());
		}
		
		
		return false;
	}

	
	
	public void availableCourses()
	{
			try 
			{
				ConnectJdbc connect = new ConnectJdbc();
				Connection con = connect.myConnection();
				Statement st = con.createStatement();
				ResultSet res = st.executeQuery("select cid,cname from course");
				while(res.next())
				{
					System.out.println(res.getString(1) + " - " + res.getString(2));
				}
			
			}
			catch(Exception e)
			{
				System.err.println(e.getMessage());
			}
			
	}
	
}
