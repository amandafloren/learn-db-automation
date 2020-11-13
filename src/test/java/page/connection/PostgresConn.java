package page.connection;

import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class PostgresConn extends CommonSetup {
    public Statement OpenPostgres () throws Throwable {
        //System.out.println(this.getPassDbPostgre());

        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://" + this.getUrlDbPostgre();
        con = DriverManager.getConnection(url, this.getUserNameDbPostgre(), this.getPassDbPostgre());
        stmt = con.createStatement();
        return stmt;
    }

    public String ExcuteQuery(String SqlPg) throws Throwable{
        rs =stmt.executeQuery(SqlPg);
        return String.valueOf(rs);
    }

    public Map<String, String> GetListData(Statement stmt, String SqlPg) throws Exception{
        Map<String, String> test = new HashMap<String, String>();

        try{
            if (rs.next()) {
                ResultSetMetaData rsMetaData = rs.getMetaData();
                Integer CountColumn = rsMetaData.getColumnCount();
                for (int i = 1; i <= CountColumn; i++) {
                    test.put(rsMetaData.getColumnName(i), rs.getString(i));
                    System.out.println(rs.getString(i));
                }
            }
        }
        finally {
            try {
                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return test;

    }
}
