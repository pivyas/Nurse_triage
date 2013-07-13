package ActivitiServiceClasses;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class FalseClass implements JavaDelegate{
	
	public void execute(DelegateExecution execution) throws Exception {
		
		System.out.println("falsed");
	}

}
