package Servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

/**
 * Servlet implementation class GenericFormFieldFetchServlet
 */
@WebServlet(description = "populates the data of fields for the form", urlPatterns = { "/LoadFormValues" })
public class LoadFormValues extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadFormValues() {
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
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		HttpSession session = request.getSession(true);
		
		try{
			String formname = (String) session.getAttribute("formname");
			String taskid = (String) session.getAttribute("task_id");
			String processid = (String) session.getAttribute("process_id");
			
			TaskService taskService = processEngine.getTaskService();
			List<Task> taskList = taskService.createTaskQuery().taskId(taskid).list();
			Task task = taskList.get(0);
			
			RuntimeService runtimeService =	processEngine.getRuntimeService();
			//HashMap hm = new HashMap();            /*	temporarily  */
			Map<String, Object> exec_var = runtimeService.getVariables(task.getExecutionId());
			
			// add form name as parameter if not exists
			// add taskid as parameter if not exists
			// add process id as paramemter if not exists
			// send list to next page
			
			session.setAttribute("preformdata",exec_var);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/UserFormGenerator.jsp");
			rd.forward(request, response);
			
		}
		catch(Exception e){
			System.out.println("Exception is ;"+e);
		}
	}

}
