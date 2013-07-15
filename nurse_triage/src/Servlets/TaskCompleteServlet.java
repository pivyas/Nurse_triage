package Servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javaclasses.FormFieldPOJO;
import javaclasses.LoginPojo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

/**
 * Servlet implementation class TaskCompleteServlet
 */
@WebServlet("/TaskCompleteServlet")
public class TaskCompleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskCompleteServlet() {
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

		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		
		HttpSession session = request.getSession();
		
		LoginPojo user = (LoginPojo) session.getAttribute("user");
		String loginuser = user.getUsername();
		String task_id = (String) session.getAttribute("task_id");
		String process_id = (String) session.getAttribute("process_id");
		List<FormFieldPOJO> fields = (List<FormFieldPOJO>) session.getAttribute("fieldList"); 
		
		
		Map<String, Object> taskVariables = new HashMap<String, Object>();
		
		int listLength = fields.size();		
		int i;
		String name = "";
		String vname = "";
		FormFieldPOJO field = new FormFieldPOJO();
		if(fields.isEmpty() == false){
		for(i=0;i < listLength ; i++){
			field = (FormFieldPOJO)fields.get(i);
			name = field.getName();
			vname = field.getVariableName();
			System.out.println(vname);
			System.out.println((String) request.getParameter(name));
			taskVariables.put(vname, (String) request.getParameter(name));
		}
		}
		taskVariables.put("loginuser",loginuser);
		System.out.println("variables put");
		TaskService taskService = processEngine.getTaskService();
		List<Task> taskList = taskService.createTaskQuery().taskId(task_id).list();
		Task task = taskList.get(0);
		System.out.println("now task is"+task.getId());
		
		if (session.getAttribute("group").equals("nurse"))
		{
			taskService.claim(task_id, loginuser); 
			System.out.println("Claimed");
		}
		
		System.out.println("before complete");
		taskService.complete(task_id, taskVariables); 
		
		System.out.println("after complete");
		request.setAttribute("sno", "3");
		request.setAttribute("process_id", process_id);
		System.out.println("after redirect");
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/StartWorkFlow");
		System.out.println("after redirect");
		rd.forward(request, response);
		System.out.println("after redirect");
	}

}
