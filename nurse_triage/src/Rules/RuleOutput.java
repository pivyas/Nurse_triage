package Rules;

import java.io.Serializable;

public class RuleOutput implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String result;
	private String result2;
	public String getResult() {
		System.out.println("Getting result from output");
		return result;
	}
	
	public void setResult(String result) {
		System.out.println("Setting result to output");
		this.result = result;
	}
	public String getResult2() {
		System.out.println("Getting result from output");
		return result2;
	}
	
	public void setResult2(String result) {
		System.out.println("Setting result to output");
		this.result2 = result;
	}
	@Override
	
	public String toString() {
		return "RuleOutput [result=" + result + ", result2" +result2+ "]";
	}
}
