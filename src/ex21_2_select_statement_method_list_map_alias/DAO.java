package ex21_2_select_statement_method_list_map_alias;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DAO {
    public List<Map<String, Object>> select() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            String driver = "oracle.jdbc.driver.OracleDriver";
            Class.forName(driver);

            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            conn = DriverManager.getConnection(url, "scott", "tiger");
            stmt = conn.createStatement();

            String select_sql = "select deptno,max(sal) as sal,count(*) "
            	    + "from emp "
            	    + "group by deptno";
            rs = stmt.executeQuery(select_sql);

            while (rs.next()) {
                Map<String, Object> map = new HashMap<>();

                map.put("deptno", rs.getInt("deptno"));
                map.put("sal", rs.getInt("sal"));
                map.put("cnt", rs.getInt("count(*)"));

                list.add(map);
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
        return list;
    }
}
