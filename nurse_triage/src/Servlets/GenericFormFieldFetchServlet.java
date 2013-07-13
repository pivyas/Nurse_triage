package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javaclasses.FormFieldPOJO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class GenericFormFieldFetchServlet
 */
@WebServlet(description = "populates the data fields for the form", urlPatterns = { "/GenericFormFieldFetchServlet" })
public class GenericFormFieldFetchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenericFormFieldFetchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//String formname = request.getParameter("queryString");
				HttpSession session = request.getSession(true);
				String formname = (String) session.getAttribute("formname");
				try{
				String connectionURL = "jdbc:mysql://localhost:3306/activiti_standalone";
				Connection con;
				Class.forName("com.mysql.jdbc.Driver");
				// Get a Connection to the database
				con = DriverManager.getConnection(connectionURL, "root", "mysql"); 
				//Add the data into the database
				String sql = "SELECT * FROM form_info WHERE form='"+formname+"';";
				System.out.println(sql); //Debug Statement
				Statement stm = con.createStatement();
				stm.executeQuery(sql);
				ResultSet rs= stm.getResultSet();
				
				List<FormFieldPOJO> fieldList = new ArrayList<FormFieldPOJO>();
				
				System.out.println(sql);
				while (rs.next ()){
					
					FormFieldPOJO field = new FormFieldPOJO();
					field.setId(rs.getInt("id"));
					field.setName(rs.getString("name"));
					field.setType(rs.getString("type"));
					field.setGroup(rs.getString("unique_group"));
					if(field.getType().equals("radio")){
					field.setValue(rs.getString("value"));
					}
					else{
						field.setValue("");
					}
					field.setEditable(rs.getBoolean("editable"));
					field.setRequired(rs.getBoolean("required"));
					field.setVariableName(rs.getString("variable_name"));
					fieldList.add(field);
					
				}
				// add form name as parameter if not exists
				// add taskid as parameter if not exists
				// add process id as paramemter if not exists
				// send list to next page
				System.out.println(sql);
				
				System.out.println("yo");
				System.out.println(session.getAttribute("task_id"));
				//int i;
				session.setAttribute("fieldList", fieldList);
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/LoadFormValues");
				rd.forward(request, response);
				}
				catch(Exception e){
					System.out.println("Exception is ;"+e);
				}
	}

}
