<%@page import="org.apache.catalina.tribes.group.interceptors.TwoPhaseCommitInterceptor.MapEntry"%>
<%@page import="javaclasses.FormFieldPOJO"%>
<%@page import="java.io.Writer"%>
<%@page import="java.util.*"%>
<%@page import="java.io.PrintWriter"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<% 

/*get list*/ 
//List<FormFieldPOJO> flist = session.getAttribute("fieldList");
//list length is int listLenght = fieldList.lenght

List<FormFieldPOJO> fields = (List<FormFieldPOJO>) session.getAttribute("fieldList"); 
Map<String,Object> preformdata = (Map<String,Object>) session.getAttribute("preformdata");
//HttpSession session = request.getSession();
String formname = (String) session.getAttribute("formname");

int listLength = fields.size();		

Set set = preformdata.entrySet();

//while(it.hasNext()){
	//Map.Entry me = (Map.Entry)it.next();
	//System.out.print(me.getKey()+":");
	//System.out.println((String) me.getValue()+":");
//}

int i;
FormFieldPOJO field = new FormFieldPOJO();

/*get values if already set in
	lastValue= value
*/
int count=0;
String z="0";
	field = (FormFieldPOJO)fields.get(0);
out.print("<form action='TaskCompleteServlet?sno=3' method = 'POST'>");
/* Browse through all elements of list */
if(fields.isEmpty() == false){
for(i=0;i < listLength ; i++){
	
	
	field = (FormFieldPOJO)fields.get(i);
	
	if(field.getEditable() == false)
	{
	//	out.print("disabled ");
	}
	if(field.getType().equals("radio") || field.getType().equals("checkbox")){
		if(!field.getGroup().equals(z)){
		out.print(field.getName() + ":<br/>");
		out.print("");
		}
		//System.out.println(!(field.getGroup().equals(z)));
		
		
	}
		//System.out.println("Value of z is: "+z);
		
	if(field.getType().equals("radio") || field.getType().equals("checkbox"))
	{
		out.print(field.getValue());
	}
	else{
		out.print(field.getName());
	}
	out.print("<input ");
	out.print("type='"+field.getType()+"' ");
	out.print("name='"+field.getName()+"' ");
	if(field.getType().equals("radio"))
	{
		if(formname.equals("display") || formname.equals("nurse_triage")){
			//out.print("checked ");
		}
		out.print("value='"+field.getValue()+"' ");
	}
	else{
		if(formname.equals("display") || formname.equals("nurse_triage")){
				Iterator it = set.iterator();
				while(it.hasNext()){
					Map.Entry me = (Map.Entry)it.next();
					//System.out.println("field name is: "+ field.getName() + "mapValue is"+ me.getValue() + "Form is:" + formname);
					if(me.getKey().equals(field.getVariableName()))
					{
						out.print("value='"+ me.getValue() +"' ");
						//System.out.println(field.getName() + me.getValue());
					}
				//System.out.println((String) me.getValue()+":");
			//}}
			}
		}
	}
	
	if(field.getEditable() == true){
		if(field.getRequired() == true)
		{
			out.print("required ");
		}
	}
	out.print("><br/>");
	z = field.getGroup();	
}
}
else
{
	out.print("There are no parameters for this form");	
}
out.print("<input type='submit'>");
out.print("</form>");
%>

</body>
</html>
