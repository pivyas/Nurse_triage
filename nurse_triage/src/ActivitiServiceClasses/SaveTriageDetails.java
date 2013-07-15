package ActivitiServiceClasses;

import javaclasses.HibernateUtil;
import javaclasses.PatientRemarksPOJO;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SaveTriageDetails implements JavaDelegate {

	public void execute(DelegateExecution execution) throws Exception {
		System.out.println("yo: we did it");
		
		try 
		{ 		
			System.out.println("yo: we did it again");
			Session session = HibernateUtil.getSessionFactory().getCurrentSession(); 
			Transaction tx = session.beginTransaction(); 
			
			 System.out.println((String) execution.getVariable("patientID"));
			 PatientRemarksPOJO bean1 = new PatientRemarksPOJO();
			 bean1.setPatientid((String) execution.getVariable("patientID"));
			 System.out.println((String) execution.getVariable("patientID"));
			 bean1.setNurseid((String) execution.getVariable("loginuser"));
			 System.out.println((String) execution.getVariable("loginuser"));
			 bean1.setRemarks((String) execution.getVariable("remarks"));
			 bean1.setSystolicBP((String) execution.getVariable("systolicBP"));
			 bean1.setDiastolicBP((String) execution.getVariable("diastolicBP"));
			 bean1.setPulse((String) execution.getVariable("pulse"));
			 bean1.setPulserhythm((String) execution.getVariable("pulserhythm"));
			 bean1.setHeight((String) execution.getVariable("height"));
			 bean1.setWeight((String) execution.getVariable("weight"));
			 bean1.setBmi((String) execution.getVariable("bmi"));
			 bean1.setWaistsize((String) execution.getVariable("waistsize"));
					 		 
			 session.save(bean1); 
			 tx.commit();
			 
		}
		catch (Throwable theException) 
		{ 
			System.out.println("In Saving Triage"+ theException); 
		}
	
	}
}
