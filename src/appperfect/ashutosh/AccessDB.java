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
	public static boolean connect2DBandShowResults(HttpServletRequest request,PrintWriter pw)
	{
		String username=request.getParameter("username").trim();
		String password=request.getParameter("password");
		String url=request.getParameter("url").trim();
		String driver=request.getParameter("driver").trim();
		String query=request.getParameter("query").trim();
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		if(ErrorDisplayer.validateRequired(driver, url, username, password, query, pw))
		{
			try {
				Class.forName(driver);
				conn=(Connection) DriverManager.getConnection(url,username,password);
				st=(Statement) conn.createStatement();
				rs=st.executeQuery(query);
				setRs(rs);
				showResults(pw);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				ErrorDisplayer.findError(e);
				e.printStackTrace();
				return false;
			}
			finally{
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return true;
		}
		return false;
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
