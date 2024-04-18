package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import vo.Emp;

public class EmpDAO {
	// 조인으로 Map을 사용하는 경루
	public static ArrayList<HashMap<String, Object>> selectEmpAndDeptList()throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<>();
		
		Connection conn = DBHelper.getConnection();
		String sql ="select emp.empno empNo, emp.ename ename, emp.deptno deptNo, dept.dname dname "
				+ " from emp inner join dept " // 첫번째테이블 : emp 두번째테이블 dept
				+ " ON emp.deptno = dept.deptno"; // 조건문 emp.deptno = dept.deptno
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<>();
			m.put("empNo",rs.getInt("empNo"));
			m.put("ename",rs.getString("ename"));
			m.put("deptNo",rs.getInt("deptNo"));
			m.put("dname",rs.getString("dname"));
			
			list.add(m);
		}
		
		return list;
	}
	// VO 사용
		public static ArrayList<Emp> selectEmpList() throws Exception {
			ArrayList<Emp> list = new ArrayList<>();
			
			Connection conn = DBHelper.getConnection();
			String sql = "select empno empNo, ename, sal "
					+ " from Emp";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Emp e = new Emp();
				e.empNo = rs.getInt("empNo");
				e.ename	= rs.getString("ename");
				e.sal = rs.getInt("sal");
				
				list.add(e);
			}
			
		return list;
	}
}
