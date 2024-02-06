package ex21_2_select_statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
class JDBCExample3_Oracle2_Scanner {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.print("검색어를 입력하세요>");
		String mul = sc.nextLine();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs =null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    String url = "jdbc:oracle:thin:@localhost:1521:xe";
		    conn = DriverManager.getConnection(url, "scott", "tiger");
		    stmt = conn.createStatement();
		    
		    
		    String sel_where_sql = 
		    		"select code, name, price, maker "
		    		+ "from goodsinfo "
		    		+ "where name = '"
		    		+ mul + "'";
		    
		    rs=stmt.executeQuery(sel_where_sql+"\n");
		    
		    System.out.println("번호\t부서번호\t부서명\t\t지역");
		    System.out.println("-------------------------------------------------------------------------------------");
		    
		    /*
		    ResultSet 객체로부터 select문의 실행 결과를 얻기 위해서는 먼저
		    next() 메소드를 호출해야 합니다.
		    rs.next() - 다음 행 위치로 이동하는 메소드
		    					리턴값은 boolean으로 실제로 행을 읽어 왔는지의 여부
		     */
		    
		    int i = 0;
		    while (rs.next()) { // 더 이상 읽을 데이터가 없을 때까지 반복
		    	int deptno = rs.getInt(1);			// 조회시 첫번째 칼럼의 값을 가져옴
		    	String dname = rs.getString(2);		// 조회시 첫번째 칼럼의 값을 가져옴
		    	String loc = rs.getString(3);		// 조회시 첫번째 칼럼의 값을 가져옴
		    	
		    	System.out.printf("%5d\t%5d\t%-15s%s\n",
		    						++i,deptno,dname,loc);
		    	
		    }											//sdf.format(hiredate)
		    
		} catch (ClassNotFoundException classNotFoundException) {
		    System.out.println("해당 클래스를 찾을 수 없습니다." + classNotFoundException.getMessage());
		} catch (SQLException sqlException) {
		    System.out.println(sqlException.getMessage());
		} finally {
		    if (rs != null)
		        try {
		            rs.close(); 
		        } catch (SQLException sqlException) {
		            System.out.println(sqlException.getMessage());
		        }
		    
		    if (stmt != null)
		        try {
		            stmt.close(); 
		        } catch (SQLException sqlException) {
		            System.out.println(sqlException.getMessage());
		        }
		    
		    if (conn != null)
		        try {
		            conn.close(); //4단계 : DB연결을 끊는다.
		        } catch (SQLException sqlException) {
		            System.out.println(sqlException.getMessage());
		        }
		}
	}
}
