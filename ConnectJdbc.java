import java.sql.*;

public class ConnectJdbc 
{
	
	
	public Connection myConnection() throws Exception
	{
		
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root1234");
		
	}
}
