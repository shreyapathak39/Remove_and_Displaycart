package p1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	
	protected void doThings(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("----->> login servlet starts "+username+"  "+password);
		// DAO
		if(username.equals("ramesh")&&password.equals("123"))
		{
			// ---- create a session
			HttpSession session = request.getSession(true); // always create new session
			// session -> attributes -> null
			// the representation of HashMap.
			
			session.setAttribute("username",username);
			session.setAttribute("accountNumber",101);
			session.setAttribute("role", "customer");
			
			System.out.println("1. ------->> Login Servlet");
			request.getRequestDispatcher("UserPageServlet").forward(request, response);
			
			// move user to UserPageServlet
		}
		else
		{
			// for wrong users
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doThings(request, response);
	}//end doPost
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				doThings(request, response);
	}

}