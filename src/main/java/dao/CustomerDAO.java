package dao;
	//디버깅용 메인 메서드
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class CustomerDAO {

	public static void main(String[] args) {
		// 메일 체크 메서드 디버깅
				//System.out.println(CustomerDAO.checkMail("a@goodee.com")); // false
				 
				//System.out.println(CustomerDAO.insertCustomer(
				//		"z@goodee.com","1234","zzz","1999/09/09","여")); // 1
				
				// System.out.println(CustomerDAO.login("a@goodee.com", "1234")); // 성공...
				// System.out.println(CustomerDAO.deleteCustomer("a@goodee.com", "1234")); 
				
				// System.out.println(CustomerDAO.selectCustomerListByPage(10, 10));
			}
			
			// 관리자 페이지 전체 회원정보(pw제외)
			// 호출 : /admin/customerList.jsp
			// param : void
			// return : Customer배열(리스트) -> ArrayList<HashMap<String, Object>>
			public static ArrayList<HashMap<String, Object>> selectCustomerListByPage(
					int startRow, int rowPerPage) throws Exception {
				// currentPage + rowPerPage -> startRow를 구하는 알고리즘 코드구현 액션에서...
				// startRow를 구하는 알고리즘 코드구현을 DAO에 하지 않도록...
				
				ArrayList<HashMap<String, Object>> list =
						new ArrayList<HashMap<String, Object>>();
				
				Connection conn = DBHeloper.getConnection();
				String sql = "select "
						+ "mail,name,birth,gender,update_date updateDate,create_date createDate "
						+ " from customer"
						+ " order by mail"
						+ " offset ? rows fetch next ? rows only";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, startRow);
				stmt.setInt(2, rowPerPage);
				ResultSet rs = stmt.executeQuery();
				
				// JDBC Resulst(자바에서 일반적이지 않은 자료구조) 
				//  -> (자바에서 평이한 자료구조) Collections API 타입 -> List, Set, Map 
				while(rs.next()) {
					HashMap<String, Object> m = new HashMap<String, Object>();
					m.put("mail", rs.getString("mail"));
					m.put("name", rs.getString("name"));
					list.add(m);
				}
				
				conn.close();
				
				return list;
			}
		}
	