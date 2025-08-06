import java.util.Collection;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.sql.*;
public class ValidateUserInput {

	public boolean validateName(String name)
	{
		String sname = name;
		
		Pattern p = Pattern.compile("^[a-z A-Z]*$");
		boolean b = p.matcher(sname).matches();
		return b;
	}
	
	
	public boolean validateRegNo(String regno)
	{
		Collection<String> rno = new ArrayList<>();
		
		try 
		{
			ConnectJdbc connect = new ConnectJdbc();
			Connection con = connect.myConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select regno from register");
			while(rs.next())
			{
				rno.add(rs.getString(1));
			}
		}
		
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		boolean result = rno.contains(regno);
		if(result)
		{
			System.out.println("You are already registered....");
			System.out.println("Bye Bye!! Have a nice day");
			System.exit(0);
		}
		Pattern p = Pattern.compile("^[0-9]*$");
		boolean b = p.matcher(regno).matches();
		if(regno.length()!=13)
		{
			return false;
		}
		return b;
	}
	
	
	public boolean validateGender(String gender)
	{
		
		if(gender.equals("Male")|| gender.equals("Female"))
		{
			return true;
		}
		return false;
	}
	
	
	public boolean validateEMail(String mail)
	{
		
		Pattern p = Pattern.compile("^[a-z0-9]+@[a-z]+[.]+[a-z]{2,3}+[.]+[a-z]{2,3}$");
		boolean b = p.matcher(mail).matches();
		return b;
	}
	
	
	public boolean validateCourseId(String cid)
	{
		Collection<String> courses = new ArrayList<>();
		
		try 
		{
			ConnectJdbc connect = new ConnectJdbc();
			Connection con = connect.myConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select cid from course");
			while(rs.next())
			{
				courses.add(rs.getString(1));
			}
		}
		
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		boolean result = courses.contains(cid);
		return result;
	}



}
