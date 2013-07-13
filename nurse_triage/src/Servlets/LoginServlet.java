package Servlets;

import java.io.IOException;

import javaclasses.CheckLogin;
import javaclasses.LoginPojo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			
		}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try 
		{ 
		LoginPojo user = new LoginPojo(); 
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password")); 
		String loginresult = CheckLogin.checklogin(user); 
		if (!loginresult.isEmpty()) 
		{ 
		HttpSession session = request.getSession(true);
		session.setAttribute("user",user); 
		session.setAttribute("group",loginresult); 
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/RetrieveList");
		   rd.forward(request,response);	
		} 
		else 
			response.sendRedirect("LoginPage.jsp"); //error page 
		} 
		
		catch (Throwable theException) 
		{ 
			System.out.println(theException); 
		}
	}

}
