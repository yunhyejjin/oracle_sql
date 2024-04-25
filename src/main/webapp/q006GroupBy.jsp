<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*"%>  
<%@ page import = "dao.*" %> 

<!-- Controller -->
<%
	ArrayList<HashMap<String, Integer>> list = EmpDAO.selectEmpSalStats();
%>
<!-- view -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>등급별 Sal통계</h1>
	<table border="1">
		<tr>
			<th>GRADE</th>
			<th>COUNT</th>
			<th>SUM</th>
			<th>AVG</th>
			<th>MAX</th>
			<th>MIN</th>
		</tr>
		<%
			for(HashMap<String, Integer> m : list) {
		%>
				<tr>
					<td><%=(Integer)(m.get("grade"))%></td>
					<td>
						<%
							for(int i=0; i<m.get("count"); i= i+1) {
						%>
								&#9734;
						<%	
							}
						%>
					</td>
					<td><%=(Integer)(m.get("sum"))%></td>
					<td><%=(Integer)(m.get("avg"))%></td>
					<td><%=(Integer)(m.get("max"))%></td>
					<td><%=(Integer)(m.get("min"))%></td>
				</tr>
		<%		
			}
		%>
	</table>

</body>
</html>