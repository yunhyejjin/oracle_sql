<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*"%>  
<%@ page import = "dao.*" %> 
<%
	ArrayList<Integer> list = EmpDAO.selectDeptnoList();
%>
<%
	ArrayList<HashMap<String, Integer>> cntList = EmpDAO.selectDeptnoCntList();
%>
<%
	ArrayList<HashMap<String, String>> JobList = EmpDAO.selectJobCaseList();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<div>
	<h1>DISTINCT 사용 예제</h1>

	<select name ="deptNo">
		<option value="">:::선택:::</option>
		<%
			for(Integer i: list) {
		%>
			<option value="<%=i%>"><%=i%></option>
		<%		
			}
		%>
	</select>
	</div>
	
	<div>
	<h1>DISTINCT 사용이 아닌 GROUP BY 사용예제</h1>
	<table border ="1">
		<tr>
			<th>deptNo</th>
			<th>cnt</th>
		</tr>
		<%
			for(HashMap m : cntList) {
		%>
				<tr>
					<th><%=(Integer)(m.get("deptno"))%></th>
					<th><%=(Integer)(m.get("cnt"))%></th>
				</tr>
		<%		
			}
		%>
	</div>	
	
	<div>
	<h1>JobLIst</h1>		
	<table border ="1">
		<tr>
			<th>ENAME</th>
			<th>JOB</th>
			<th>COLOR</th>
		</tr>
		<%
			for(HashMap m : JobList) {
		%>
				<tr>
					<th><%=(String)(m.get("ename"))%></th>
					<th><%=(String)(m.get("job"))%></th>
					<th><%=(String)(m.get("color"))%></th>
				</tr>
		<%		
			}
		%>
	</div>
</body>
</html>