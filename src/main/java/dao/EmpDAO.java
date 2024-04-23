package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import vo.Emp;

public class EmpDAO {
	// q004WereIn.jsp
	public static ArrayList<Emp> selectEmpListByGrade(ArrayList<Integer> ckList) throws Exception{
		ArrayList<Emp> list = new ArrayList<>();
		
		Connection conn = DBHeloper.getConnection();
		String sql = "select ename, grade"
				+ " from emp"
				+ " where grade in "; // 조건절 grade가 어떤 값이야~
		PreparedStatement stmt = null;
		
		if(ckList.size() == 1) {
			sql = sql + "(?)"; // "select ename, grade from emp where grade in(?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,ckList.get(0));
		} else if(ckList.size() == 2) {
			sql = sql + "(?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,ckList.get(0));
			stmt.setInt(2,ckList.get(1));
		} else if(ckList.size() == 3) {
			sql = sql + "(?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,ckList.get(0));
			stmt.setInt(2,ckList.get(1));
			stmt.setInt(3,ckList.get(2));
		} else if(ckList.size() == 4) {
			sql = sql + "(?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,ckList.get(0));
			stmt.setInt(2,ckList.get(1));
			stmt.setInt(3,ckList.get(2));
			stmt.setInt(4,ckList.get(3));
		} else if(ckList.size() == 5) {
			sql = sql + "(?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,ckList.get(0));
			stmt.setInt(2,ckList.get(1));
			stmt.setInt(3,ckList.get(2));
			stmt.setInt(4,ckList.get(3));
			stmt.setInt(5,ckList.get(4));
		}
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) { // Getter and Setter 이용
			Emp e = new Emp();
			e.setEname(rs.getString("ename"));
			e.setGrade(rs.getInt("grade"));
			list.add(e);
		}
		
		conn.close();
		return list;
	}
	
	public static ArrayList<HashMap<String, String>> selectJobCaseList() throws Exception{
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		
		Connection conn = DBHeloper.getConnection();
		String sql = "select ename,"
				+ " job,"
				+ " CASE"
				+ " WHEN job = 'PRESIDENT' THEN '빨강'"
				+ " WHEN job = 'MANAGER' THEN '주황'"
				+ " WHEN job = 'ANALYST' THEN '노랑'"
				+ " WHEN job = 'CLERK' THEN '초록'"
				+ " ELSE '파랑' END color"
				+ " from emp"
				+ " order by (CASE"
				+ " WHEN color = '빨강' THEN 1"
				+ " WHEN color = '주황' THEN 2"
				+ " WHEN color = '노랑' THEN 3"
				+ " WHEN color = '초록' THEN 4"
				+ " ELSE 5 END) ASC";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String, String> m = new HashMap<>();
			m.put("ename", rs.getString("ename"));
			m.put("job", rs.getString("job"));
			m.put("color", rs.getString("color"));
			
			list.add(m);
		}
		
		conn.close();
		return list;
	}
	// DEPTNO 뒤에 부서별 인원 같이 조회하는 메서드
	public static ArrayList<HashMap<String, Integer>> selectDeptnoCntList() throws Exception {
		ArrayList<HashMap<String, Integer>> list = new ArrayList<>();
		
		Connection conn = DBHeloper.getConnection();
		// CONUT(*) 의 * 은 모든열이 아니고 rowid를 가르킨다
		String sql = "select deptno, count(*) cnt"
				+ " from emp"
				+ " where deptno is not null"
				+ " group by deptno"
				+ " order by deptno asc";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Integer> m = new HashMap<>();
			m.put("deptno", rs.getInt("deptno"));
			m.put("cnt", rs.getInt("cnt"));
			
			list.add(m);
		}
		
		conn.close();
		return list; // 구현 후 수정/ return 수정해야함
	}
	
	// DEPTNO 목록을 출력하는 메서드
	public static ArrayList<Integer> selectDeptnoList() throws Exception {
		ArrayList<Integer> list = new ArrayList<>();
		
		Connection conn = DBHeloper.getConnection();
		String sql = "select distinct deptno deptNo"
				+ " from emp"
				+ " where deptno is not null"
				+ " order by deptno asc";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Integer i = rs.getInt("deptNo");// 랩퍼타입과 기본타입간에 Auto Boxing		
			list.add(i);
		}
		conn.close();
		
		
		return list;
	}
	
	// 조인으로 Map을 사용하는 경루
	public static ArrayList<HashMap<String, Object>> selectEmpAndDeptList()throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<>();
		
		Connection conn = DBHeloper.getConnection();
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
		
		conn.close();
		return list;
	}
	// VO 사용
		public static ArrayList<Emp> selectEmpList() throws Exception {
			ArrayList<Emp> list = new ArrayList<>();
			
			Connection conn = DBHeloper.getConnection();
			String sql = "select empno empNo, ename, sal "
					+ " from Emp";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Emp e = new Emp();
				e.setEmpNo(rs.getInt("empNo"));
				e.setEname(rs.getString("ename"));
				e.setSal(rs.getInt("sal"));
				
				list.add(e);
			}
		
		conn.close();	
		return list;
	}
}
