package ActivitiServiceClasses;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javaclasses.ConnectionManager;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class PatientDetails implements JavaDelegate{
	
	static Connection currentCon1 = null; 
	static ResultSet rs1 = null;
	
	public void execute(DelegateExecution execution) throws Exception {
	
	String pid = (String) execution.getVariable("patientID");
	
	Statement stmt1 = null; 
	String searchQuery1 = "select * from patient where patientID ='" + pid + "';";
	System.out.println(searchQuery1);

	try { 
		//connect to DB 
		currentCon1 = ConnectionManager.getConnection(); 
		stmt1 = currentCon1.createStatement();
		rs1 = stmt1.executeQuery(searchQuery1);
		
		if (rs1.next()){
			execution.setVariable("firstname",rs1.getString("firstname"));
			execution.setVariable("lastname",rs1.getString("lastname"));
			execution.setVariable("dob",rs1.getString("dob"));
			execution.setVariable("age",rs1.getString("age"));
			execution.setVariable("email",rs1.getString("email"));
			execution.setVariable("phoneno",rs1.getString("phoneno"));
			execution.setVariable("address",rs1.getString("address"));
			execution.setVariable("category",rs1.getString("category"));
			execution.setVariable("gender",rs1.getString("gender"));
		
		}

}
	catch (Exception ex) { 
		System.out.println("Log In failed: An Exception has occurred! " + ex); 
		} 
	//some exception handling 
	finally { 
		if (rs1 != null) { 
			try {
		rs1.close(); 
		} 
			
			catch (Exception e) {} 
			rs1 = null; 
		} 
		if (stmt1 != null) { 
			try { 
				stmt1.close(); 
				} 
			catch (Exception e) {} 
			stmt1 = null; 
			} 
		if (currentCon1 != null) { 
			try { 
				currentCon1.close(); 
			} 
			catch (Exception e) { } 
			currentCon1 = null; 
			} 
		}
	}
}
