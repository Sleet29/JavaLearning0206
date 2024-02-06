package exercise3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAO {
    static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    static final String USER = "scott";
    static final String PASS = "tiger";

    public ArrayList<Emp> selectAll() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<Emp> list = new ArrayList<>();

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql = "SELECT * FROM emp";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int empno = rs.getInt("empno");
                String ename = rs.getString("ename");
                String job = rs.getString("job");
                int mgr = rs.getInt("mgr");
                String hiredate = rs.getString("hiredate"); 
                int sal = rs.getInt("sal");
                int comm= rs.getInt("comm");
                int deptno = rs.getInt("deptno");
                
                Emp emp = new Emp(empno, ename, job, mgr, hiredate, sal, comm, deptno); 
                list.add(emp);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public ArrayList<Emp> search(int menu, String searchWord) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<Emp> list = new ArrayList<>();

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String columnName = "";
            switch (menu) {
                case 0:
                    columnName = "empno";
                    break;
                case 1:
                    columnName = "ename";
                    break;
                case 2:
                    columnName = "job";
                    break;
                case 3:
                    columnName = "mgr";
                    break;
                case 4:
                    columnName = "hiredate";
                    break;
                case 5:
                    columnName = "sal";
                    break;
                case 6:
                    columnName = "comm";
                    break;
                case 7:
                    columnName = "deptno";
                    break;
                default:
                    break;
            }

            String sql = "SELECT * FROM emp WHERE " + columnName + "=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, searchWord);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int empno = rs.getInt("empno");
                String ename = rs.getString("ename");
                String job = rs.getString("job");
                int mgr = rs.getInt("mgr");
                String hiredate = rs.getString("hiredate"); 
                int sal = rs.getInt("sal");
                int comm= rs.getInt("comm");
                int deptno = rs.getInt("deptno");
                
                Emp emp = new Emp(empno, ename, job, mgr, hiredate, sal, comm, deptno); 
                list.add(emp);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}

 */
