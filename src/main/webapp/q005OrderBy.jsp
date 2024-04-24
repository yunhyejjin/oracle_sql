<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "vo.*" %>
<%@ page import = "dao.*" %>

<!-- Controller -->
<%
	// 어떤 컬럼으로 정렬
	String col = request.getParameter("col");
	System.out.println("col(무슨컬럼) : " + col);
	// asc / desc (분류하겠다~)
	String sort = request.getParameter("sort");
	System.out.println("sort(정렬) : " + sort);
	
	// DAO(모델) 호출 -> 모델 반환
	ArrayList<Emp> list = EmpDAO.selectEmpListSort(col, sort);
	System.out.println("q005OrderBy.jsp Emplist 개수 : " + list.size());
%>

<!-- view -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>
				empno
				<a href="./q005OrderBy.jsp?col=empno&sort=asc">오름</a>
				<a href="./q005OrderBy.jsp?col=empno&sort=desc">내림</a>
			</th>
			<th>
				ename
				<a href="./q005OrderBy.jsp?col=ename&sort=asc">오름</a>
				<a href="./q005OrderBy.jsp?col=ename&sort=desc">내림</a>
			</th>
		</tr>
		
		<%
			for(Emp e : list){
		%>
				<tr>
					<td><%=e.getEmpNo()%></td>
					<td><%=e.getEname()%></td>
				</tr>
		<%		
			}
		%>		
	</table>
</body>
</html>