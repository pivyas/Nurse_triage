package Rules;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;


public class CreateRuleObjectDelegate implements JavaDelegate {

  public void execute(DelegateExecution execution) throws Exception {
    RuleInput ruleInput = new RuleInput();
    ruleInput.setOper((String) execution.getVariable("age"));
    
    System.out.println("Setting ruleInput " + ruleInput);
    execution.setVariable("ruleInput", ruleInput);
  }

}
