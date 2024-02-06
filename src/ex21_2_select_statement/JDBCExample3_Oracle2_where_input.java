package ex21_2_select_statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample3_Oracle2_where_input {
    public static void main(String args[]) {
        if (args.length != 1) {
            System.out.println("상품명 입력하세요");
            return;
        }
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            conn = DriverManager.getConnection(url, "scott", "tiger");
            stmt = conn.createStatement();

            String sel_where_sql =
                    "select code, name, price, maker "
                            + "from goodsinfo "
                            + "where name = '"
                            + args[0] + "'";

            rs = stmt.executeQuery(sel_where_sql);
            System.out.println(sel_where_sql);

            // Execute the query

            System.out.println("번호\t상품코드\t상품명\t가격\t제조사");
            System.out.println("-------------------------------------------------------------------------------------");

            int i = 0;
            while (rs.next()) {
                int code = rs.getInt("code");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                String maker = rs.getString("maker");

                System.out.printf("%5d\t%5d\t%-15s%d\t%s\n", ++i, code, name, price, maker);
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
                    conn.close(); //4단계 : DB연결을 끊는다.
                } catch (SQLException sqlException) {
                    System.out.println(sqlException.getMessage());
                }
        }
    }
}
