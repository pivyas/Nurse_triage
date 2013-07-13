package ActivitiServiceClasses;

import javaclasses.HibernateUtil;
import javaclasses.PatientPojo;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RegisterPatient implements JavaDelegate{
	
	public void execute(DelegateExecution execution) throws Exception {
		
	    	  
	try 
	{ 		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession(); 
		Transaction tx = session.beginTransaction(); 
		
		 PatientPojo bean1 = new PatientPojo();
		 bean1.setPatientid((String) execution.getVariable("patientID"));
		//bean1.setPatientid("5");
		 bean1.setFirstname((String) execution.getVariable("firstname"));
		 bean1.setLastname((String) execution.getVariable("lastname"));
		 bean1.setDob((String) execution.getVariable("dob"));
		 bean1.setAge((String) execution.getVariable("age"));
		 bean1.setEmail((String) execution.getVariable("email"));
		 bean1.setPhoneno((String) execution.getVariable("phoneno"));
		 bean1.setAddress((String) execution.getVariable("address"));
		 bean1.setCategory((String) execution.getVariable("category"));
		 bean1.setGender((String) execution.getVariable("gender"));
				 		 
		 session.save(bean1); 
		 tx.commit();
		 
	}
	catch (Throwable theException) 
	{ 
		System.out.println("In Register"+ theException); 
	}
	

}
}
