package appperfect.ashutosh;

import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class AccessDB {
	private static ResultSet rs;
	private static Connection conn;
	private static Statement st;
	public static void connect2DBandShowResults(String driver,String url,String username,String password,String query,PrintWriter pw) throws Exception
	{
				Class.forName(driver);
				conn=(Connection) DriverManager.getConnection(url,username,password);
				st=(Statement) conn.createStatement();
				rs=st.executeQuery(query);
				setRs(rs);
				if(rs==null||rs.next()==false){
					throw new Exception("result is null");
				}
				else
				{
					showResults(pw);
				}
	}
	public ResultSet getRs() {
		return rs;
	}
	public static void setRs(ResultSet rs) {
		AccessDB.rs = rs;
	}
	public static void showResults(PrintWriter pw) 
	{
		
		
		try {
			pw.print("<table>");
			System.out.println("size fetched is " + rs.getFetchSize());
			while (rs.next()) {
				ResultSetMetaData rsmd = rs.getMetaData();
				int colsNumber = rsmd.getColumnCount();
				pw.print("<tr>");
				for (int i = 1; i <= colsNumber; i++) {
						pw.print("<td>" + rs.getString(i) + "</td>");
						System.out.print(rs.getString(i) + " ");
				}
				System.out.println();
				pw.print("</tr>");
			}
			pw.print("</table>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ErrorDisplayer.findError(e);
			e.printStackTrace();
		}
		
	}
}
