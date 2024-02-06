package exercise.onmyown;
import java.sql.*;
import java.util.ArrayList;

public class DAO {
    static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    static final String USER = "scott";
    static final String PASS = "tiger";

    public ArrayList<Emp> selectAll() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Emp> list = new ArrayList<>();

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            String sql = "SELECT * FROM emp";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Emp emp = new Emp(
                        rs.getInt("empno"),
                        rs.getString("ename"),
                        rs.getString("job"),
                        rs.getInt("mgr"),
                        rs.getString("hiredate").substring(0,10),
                        rs.getInt("sal"),
                        rs.getInt("comm"),
                        rs.getInt("deptno")
                );
                list.add(emp);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}

