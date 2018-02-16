package appperfect.ashutosh;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SimpleServlet
 */
@WebServlet(description = "A simple servlet to access northwind database", urlPatterns = { "/SimpleServlet" })
public class SimpleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SimpleServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String username=request.getParameter("username").trim();
		String password=request.getParameter("password");
		String url=request.getParameter("url").trim();
		String driver=request.getParameter("driver").trim();
		String query=request.getParameter("query").trim();
		if(ErrorDisplayer.validateRequired(driver, url, username, password, query, pw))
		{
			try{
				AccessDB.connect2DBandShowResults(driver, url, username, password, query,pw);
			}
			catch(Exception e){
				ErrorDisplayer.findError(e);
				pw.println("please go back enter correct information the error describes itself as :-> ");
			}
			
		}
		else
		{
			pw.println("please go back enter correct information the error describes itself as :-> ");	
		}
			
			// TODO Auto-generated catch block
			pw.println(ErrorDisplayer.getErrorMessage());
		pw.println("<a href=\"http://localhost:8080/NorthWind/\">Back</a>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
