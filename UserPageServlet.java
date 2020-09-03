package p1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Product;
import db.MyDatabase;

/**
 * Servlet implementation class UserPageServlet
 */
public class UserPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doThings(request, response);
	}//end doPost
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				doThings(request, response);
	}
	
	protected void doThings(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		HttpSession session = request.getSession(false);
		// getSession(false)-> provide old session otherwise create new one

		PrintWriter out = response.getWriter();
		
		if(session != null)
		{
			String username = (String) session.getAttribute("username");
			String account = (String) session.getAttribute("account");
			String role = (String) session.getAttribute("role");

			System.out.println("2------>>"+username+" "+account+" "+role);
			
			// welcome Ramesh
			
			// Product List DAO 
			
			List<Product> listProducts = MyDatabase.getProductsByKeyword("Laptop");
			
			System.out.println("3-------->> "+listProducts);
			
			Iterator<Product> itr = listProducts.iterator();
			
			out.print("<HTML><BODY>");
			out.print("Welcome "+username+"<br/>");
			while(itr.hasNext())
			{
				Product p = itr.next();
				System.out.println("4---->>"+p);
				out.print(p.getProductName()+" - "+p.getCost()+"<a href='AddCartServlet?p="+p.getProductName()+"'> Add to Cart </a>");
				out.print("<br/>");
			}
			out.print("</BODY></HTML>");
			
		}
		else
		{
			response.sendRedirect("LoginPage.html");
		}
	}

}