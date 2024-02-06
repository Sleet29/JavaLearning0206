// 데이터베이스의 테이블에 데이터를 추가하는 프로그램
// executeUpdate 메서드 사용
package ex21_3_insert_delete_update;
import java.sql.*;
class JDBCExample4_Oracle_insert_input2_변수화 {
    public static void main(String args[]) {
    	// "A2000" "모니터" 200 "삼성"
    	if (args.length !=4) {
    		System.out.println(
    				"상품코드 상품명 가격 제조사를 입력하세요");
    		return;
    	}
    	Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            conn = DriverManager.getConnection(url, "scott", "tiger");
            stmt = conn.createStatement();

            /*
             * 1. 직접 데이터를 입력하는 경우
             * insert into goodsinfo
             * values('A1000', '고급 핸드폰', 100, 'LG')
             * 
             */
            String single = "'";
            String sql = "insert into goodsinfo " +
                    "values"
                    + "(" + single + args[0] + single + ", "
                    + 		single + args[1] + single + ", "
                    + 				 args[2] + 		    ", "
                    + 		single + args[3] + single + ")";
            
            System.out.println(sql+"\n");
            int rowNum = stmt.executeUpdate(sql);
            System.out.println(rowNum + "행이 추가되었습니다.");
        } catch (ClassNotFoundException classNotFoundException) {
            System.out.println("해당 클래스를 찾을 수 없습니다." + classNotFoundException.getMessage());
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlException) {
                    System.out.println(sqlException.getMessage());
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException sqlException) {
                    System.out.println(sqlException.getMessage());
                }
            }
        }
    }
}
/*
import java.sql.*;

class JDBCExample4_Oracle_insert_direct {
    public static void main(String args[]) {
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            conn = DriverManager.getConnection(url, "scott", "tiger");
            stmt = conn.createStatement();

            String sql = "insert into goodsinfo " +
                         "values('A1000', '고급 핸드폰', 100, 'LG')";

            stmt.executeUpdate(sql);
            System.out.println("데이터가 성공적으로 삽입되었습니다.");
        } catch (ClassNotFoundException classNotFoundException) {
            System.out.println("해당 클래스를 찾을 수 없습니다." + classNotFoundException.getMessage());
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlException) {
                    System.out.println(sqlException.getMessage());
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException sqlException) {
                    System.out.println(sqlException.getMessage());
                }
            }
        }
    }
}
*/