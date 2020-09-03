package p1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Product;
import db.MyDatabase;


public class AddCartServlet extends HttpServlet {
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
		String productName = request.getParameter("pname");
		
		Product p = MyDatabase.getProductByName(productName);
		
		System.out.println(" ----->> p"+p);
		
		if(session != null)
		{
			
			List<Product> cartList = (List<Product>)session.getAttribute("cart");
			cartList.add(p);
			System.out.println(" ---->> Product "+p+" added in CartList ");
			response.sendRedirect("UserPageServlet");
			
		}
		else
		{
			response.sendRedirect("LoginPage.html");
		}
	}

}