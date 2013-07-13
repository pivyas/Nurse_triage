package Servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.util.ReflectUtil;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

/**
 * Servlet implementation class StartWorkFlow
 */
@WebServlet("/StartWorkFlow")
public class StartWorkFlow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StartWorkFlow() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("in Get");
	}
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		HttpSession session = request.getSession();
		
		LoginPojo user = (LoginPojo) session.getAttribute("user");
		String loginuser = user.getUsername();
		
		String process_id = "";
		String formname = "";
		
		if (((String) request.getParameter("sno")).equals("2"))
		{
		
		String pid = (String) request.getParameter("patientid");
		System.out.println(pid);
		
				RuntimeService runtimeService =	processEngine.getRuntimeService();
				
				RepositoryService repositoryService = processEngine.getRepositoryService();
				repositoryService.createDeployment().addInputStream("nurse_triage.bpmn20.xml", ReflectUtil.getResourceAsStream("nurse_triage.bpmn")).deploy();
				
				Map<String, Object> variableMap = new HashMap<String, Object>();
				
				pid = (String) request.getParameter("patientid");
				variableMap.put("patientID", pid);
				variableMap.put("loginuser", loginuser);
				
				ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("triageProcess",variableMap);
				process_id = processInstance.getProcessInstanceId();
		}
		
			System.out.println("else-one");
			
			if (!((String) request.getParameter("sno")).equals("2"))
			{	
				//System.out.println("not2");
				process_id = request.getParameter("process_id");
				//System.out.println(process_id);
			}
			
			session.setAttribute("process_id", process_id);
			
		    TaskService taskService = processEngine.getTaskService();
		    
		    
		    if (((String) request.getParameter("sno")).equals("1")){
		    	
		    	System.out.println("is1");
		    	String taskId = request.getParameter("task_id");
		    	session.setAttribute("task_id", taskId);
		    	System.out.println(taskId);
		    	formname = processEngine.getFormService().getTaskFormData(taskId).getFormKey();
		    	System.out.println(formname);
		    	session.setAttribute("formname", formname);
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/GenericFormFieldFetchServlet");
				rd.forward(request, response);
		    }
		    else{
		    	
		    	System.out.println("isnot1");
		    	List<Task> taskList = taskService.createTaskQuery().processInstanceId(process_id).taskAssignee(loginuser).list();
					
				if (taskList.isEmpty())
				{
					System.out.println("listempty");
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/RetrieveList");
					   rd.forward(request,response);
				}
				else
				{
					System.out.println("notempty");
					Task task = taskList.get(0);
					String taskId = task.getId();
					System.out.println(taskId);
					session.setAttribute("task_id", taskId);
					formname = processEngine.getFormService().getTaskFormData(taskId).getFormKey();
					System.out.println(formname);
					session.setAttribute("formname", formname);
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/GenericFormFieldFetchServlet");
					rd.forward(request, response);
				}
				
		    }
		    
	}

}
