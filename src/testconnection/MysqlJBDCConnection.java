package testconnection;
import java.sql.*;

public class MysqlJBDCConnection {
	public static void main(String args[])
	{
		Connection con=null;
		PreparedStatement ps1 = null;//for Insert data into database
		PreparedStatement ps2 = null;//for Retrive data from database
		PreparedStatement ps3 =null;//for Delete Record from database
		PreparedStatement ps4=null;//for update
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Loaded"); 
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dbjdbc","root","");
			System.out.println("Connection Established");
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace(); 
		}
		catch(SQLException e)
		{
			System.out.println("SQLException" + e.getMessage());
		}
		String sqlQuery1="insert into register(username,pwd) values(?,?)";
		String sqlQuery2="select * from register";
		String sqlQuery3="delete from register where pwd='jacck123'";
		String sqlQuery4="update register set pwd='joy123' where username='joy'";
		try {
			ps1=con.prepareStatement(sqlQuery1);
			ps2=con.prepareStatement(sqlQuery2);
			ps3=con.prepareStatement(sqlQuery3);
			ps4=con.prepareStatement(sqlQuery4);
			System.out.println("sqlQueries Compiled");
		
			ps1.setString(1,"tony");
			ps1.setString(2,"tony123");
			System.out.println("Data Set");			
			ps1.execute();
			System.out.println("data Inserted");
						
			ResultSet rs =ps2.executeQuery(sqlQuery2);
			System.out.println("Username"+" "+"Password");
			
			while(rs.next())
			{
			String username=rs.getString("username");
			String pwd=rs.getString("pwd");
				System.out.format("%s  %s\n",username,pwd);
			}
			ps3.execute();
			System.out.println("Record Delted Successfully");
			
			ps4.executeUpdate();
			System.out.println("Data Updated");

			con.close();
			System.out.println("Connection Closed");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}
	

}
