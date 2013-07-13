package Servlets;

import java.io.IOException;
import java.util.List;

import javaclasses.LoginPojo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

/**
 * Servlet implementation class RetrieveList
 */
@WebServlet("/RetrieveList")
public class RetrieveList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetrieveList() {
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
		
		HttpSession session = request.getSession();
		LoginPojo user = (LoginPojo) session.getAttribute("user");
		String userId = user.getUsername();
		String group = (String) session.getAttribute("group");
		System.out.println(group);
	
		ProcessEngine processEngine = ProcessEngineConfiguration
				.createStandaloneProcessEngineConfiguration()
				.setJdbcUrl("jdbc:mysql://localhost:3306/activiti_standalone?autoReconnect=true")
				.setJdbcDriver("com.mysql.jdbc.Driver")
				.setJdbcUsername("root")
				.setJdbcPassword("mysql")
				.setDatabaseSchemaUpdate("true") 
				.setJobExecutorActivate(true)
				.buildProcessEngine();
		
		TaskService taskService = processEngine.getTaskService();
		List<Task> tasklist = taskService.createTaskQuery().taskCandidateGroup(group).list();
		if (tasklist.isEmpty())
		{
			System.out.println("empty hai");
		}
	   
	   request.setAttribute("tasklist", tasklist);
	   RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
	   rd.forward(request,response);	
	}

}
