package ex21_2_select_statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample3_Oracle2_where_input {
    public static void main(String args[]) {
    	if (args.length != 1) {
            System.out.printf("상품명 입력하세요");
            return;
        }

    	// *********************************************
    	// run as>> arguments에 값 넣어줘야 정상동작한다.
    	// *********************************************
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            String driver = "oracle.jdbc.driver.OracleDriver";
            Class.forName(driver);
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            conn = DriverManager.getConnection(url, "scott", "tiger");
            stmt = conn.createStatement();

            String select_sql = "SELECT * " +
                    "FROM goodsinfo " +
                    "WHERE name = '"+args[0]+"'";
            rs = stmt.executeQuery(select_sql);

            System.out.println("번호\t\t상품코드\t\t상품명\t\t가격\t\t제조사");
            System.out.println("=================================================================================");

            /*
             * ResultSet 객체로부터 select문의 실행 결과를 얻기 위해서는 먼저 next() 메소드를 호출해야 합니다.
             * */

            int i = 0;
            while (rs.next()) {
                String code = rs.getString(1);
                String name = rs.getString(2);
                int price = rs.getInt(3);
                String maker = rs.getString(4);

                System.out.printf("%2d\t\t%5s\t\t%-6s\t\t%-9d\t%-4s\n", ++i, code, name, price, maker);
            }
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
                    conn.close();
                } catch (SQLException sqlException) {
                    System.out.println(sqlException.getMessage());
                }
        }
    }
}