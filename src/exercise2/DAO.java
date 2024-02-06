package exercise2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            	Emp g = new Emp();
                int empno = rs.getInt("empno");
                g.setEmpno(empno);
                String ename = rs.getString("ename");
                g.setEname(ename);
                String job = rs.getString("job");
                g.setJob(job);
                int mgr = rs.getInt("mgr");
                g.setMgr(mgr);
                java.sql.Date hiredate = rs.getDate("hiredate");
                g.setHiredate(hiredate);
                int sal = rs.getInt("sal");
                g.setSal(sal);
                int comm = rs.getInt("comm");
                g.setComm(comm);
                int deptno = rs.getInt("deptno");
                g.setDeptno(deptno);

                list.add(g);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
