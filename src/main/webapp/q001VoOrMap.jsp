<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*"%>    
<%@ page import = "vo.*" %>    
<%@ page import = "dao.*" %>    

<!-- Controller(컨트롤러) -->
<%
	// DAO 호출(모델호출)
	// 모델을 뷰한테 넘겨준다.
	ArrayList<Dept> DeptList = DeptDAO.selectDeptList();
	ArrayList<Emp> EmpList = EmpDAO.selectEmpList();
	ArrayList<HashMap<String, Object>> DeptOnOffList 
		= DeptDAO.selectDeptOnOffList();
	ArrayList<HashMap<String, Object>> innerJoinList 
		= EmpDAO.selectEmpAndDeptList();
%> 

<!-- View(뷰) -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>Dept List</h1>
	<table border ="1">
		<tr>
			<th>deptNo</th>
			<th>dname</th>
			<th>loc</th>
		</tr>
		
		<%
			for(Dept d : DeptList){
		%>
			<tr>
				<td><%=d.getDeptNo()%></td>
				<td><%=d.getDname()%></td>
				<td><%=d.getLoc()%></td>
			</tr>
		<%
			}
		%>  
		
	</table>
	
	<h1>Emp List</h1>
	
	<table border ="1">
		<tr>
			<th>empNo</th>
			<th>ename</th>
			<th>sal</th>
		</tr>
		
		<%
			for(Emp e: EmpList) {
		%>
			<tr>
				<td><%=e.getEmpNo()%></td>
				<td><%=e.getEname()%></td>
				<td><%=e.getSal()%></td>
			</tr>
		<%		
			}
		%>
		
	
	<h1>Dept OnOff List</h1>
	
	<table border ="1">
		<tr>
			<th>deptNo</th>
			<th>dname</th>
			<th>loc</th>
			<th>OnOff</th>
		</tr>
		<%
			// map단점 : 형변환이 필요 할 수도 있고 IDE자동완성기능을 사용할 수 없다
			for(HashMap<String, Object> onoff : DeptOnOffList) {
		%>
			<tr>
				<td><%=(Integer)(onoff.get("deptNo"))%></td>
				<td><%=(String)(onoff.get("dname"))%></td>
				<td><%=(String)(onoff.get("loc"))%></td>
				<td><%=(String)(onoff.get("onOff"))%></td>
			</tr>
		<%	
			}
		%>
	
	<h1>EmpAndDeptList</h1>
	
	<table border ="1">
		<tr>
			<th>empNo</th>
			<th>ename</th>
			<th>deptNo</th>
			<th>dname</th>
			</tr>
		<%
			for(HashMap<String, Object> join : innerJoinList ) {
		%>
			<tr>
				<td><%=(Integer)(join.get("empNo"))%></td>
				<td><%=(String)(join.get("ename"))%><</td>
				<td><%=(Integer)(join.get("deptNo"))%><</td>
				<td><%=(String)(join.get("dname"))%><</td>
			</tr>
		<%		
			}
		%>
	
</body>
</html>