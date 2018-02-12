package appperfect.ashutosh;

import java.io.PrintWriter;

public class ErrorDisplayer {
private static String errorMessage;

public static String getErrorMessage() {
	return errorMessage;
}

public static void findError(Exception e) {
	//String error=e.toString();

	setErrorMessage(e.getMessage());
	return;
	/*if(error.contains("ClassNotFound"))
	{
		setErrorMessage(e.getMessage());
		return;
	
	}
	if(error.contains("No suitable driver found"))
	{
		setErrorMessage("Something wrong with url, system cannot access it with current driver");
		return;
	}
	if(error.contains("Access denied for user"))
	{
		setErrorMessage("Something wrong with username or password, Enter correct username, password");
		return;
	}
	if(error.contains("MySQLSyntaxErrorException"))
	{
		setErrorMessage("Something wrong with your Query check syntax and enter correct query");
		return;
	}
	if(error.contains("No tables used"))
	{
		setErrorMessage("Mention tables in your query ");
		return;
	}
	else
	{
		setErrorMessage(e.toString());
	}
	e.printStackTrace();
*/
}


	
private static void setErrorMessage(String errorMessage) {
	// TODO Auto-generated method stub
	ErrorDisplayer.errorMessage=errorMessage;
}


public static boolean validateRequired(String driver,String url,String username,String password,String query,PrintWriter pw)
{
	if(driver==null||driver.isEmpty())
	{
		pw.println("driver name is required");
		return false;
	}
	if(url==null||url.isEmpty())
	{
		pw.println("url is required");
		return false;
	}
	if(username==null||username.isEmpty())
	{
		pw.println("username is required");
		return false;
	}
	if(password==null||password.isEmpty())
	{
		pw.println("password is required");
		return false;
	}
	if(query==null||query.isEmpty())
	{
		pw.println("query cannot be empty");
		return false;
	}
	return true;
	}
}
