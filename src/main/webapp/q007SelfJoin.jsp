<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*"%>  
<%@ page import = "dao.*" %> 

<!-- Controller -->
<%
	ArrayList<HashMap<String,Object>> list = EmpDAO.selectEmpMgrNameAndMgrGrade();
	System.out.println("호출확인 : " + list);
%>
<!-- view -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>Emp등급 And Mgr등급</h1>
	<table border="1">
		<tr>
			<th>EMPNO</th>
			<th>ENAME</th>
			<th>GRADE</th>
			<th>MGRNAME</th>
			<th>MGRGRADE</th>
		</tr>
		<%
			for(HashMap<String, Object> m : list) {
		%>
				<tr>
					<td><%=(Integer)(m.get("empno"))%></td>
					<td><%= m.get("ename")%></td>
					<td>
						<%
							for(int i=0; i<(Integer)(m.get("grade")); i= i+1){
						%>
								&#127812;
						<%		
							}
						%>
					</td>
					<td><%= m.get("mgrName")%></td>
					<td>
						<%
							for(int i=0; i<(Integer)(m.get("mgrGrade")); i= i+1){
						%>
								&#127812;
						<%		
							}
						%>
					</td>
				</tr>
		<%		
			}
		%>
		
	</table>
</body>
</html>