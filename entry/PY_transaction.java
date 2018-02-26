package entry;
import java.util.ArrayList;
import java.sql.*;

public class PY_transaction {
    private PY_transaction(){};
    private static final PY_transaction py_transaction = new PY_transaction();
    public static PY_transaction getPY_transaction(){
        return py_transaction;
    }
    public ArrayList<String> dbQuery(String sql_command){
        try{
            try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            }catch(ClassNotFoundException ex){
                ex.printStackTrace();
            }
            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost;DatabaseName=PY_transaction","sa","123456");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql_command);
            ArrayList<String> sqlResult = new ArrayList<String>();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            while(rs.next()){
                for(int i=1;i<columnCount+1;i++){
                    sqlResult.add(rs.getString(i));
                }
            }
            rs.close();
            stmt.close();
            con.close();
            return sqlResult;
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }
    public ArrayList<String> DataBase_list(String sql_command){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }
        catch(ClassNotFoundException ex){
            ex.printStackTrace();
            return null;
        }
        try{
            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost;DatabaseName=PY_transaction","sa","123456");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql_command);
            ArrayList<String> sqlResult = new ArrayList<String>();
            while(rs.next()){
                sqlResult.add(rs.getString(2));
                sqlResult.add(rs.getString(3));
                sqlResult.add(rs.getString(4));
                sqlResult.add(rs.getString(5));
            }
            rs.close();
            stmt.close();
            con.close();
            return sqlResult;
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }
    public boolean dbUpdate(String sqlCommand){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }
        catch(ClassNotFoundException ex){
            ex.printStackTrace();
            return false;
        }
        try{
            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost;DatabaseName=PY_transaction","sa","Shift123");
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sqlCommand);
            stmt.close();
            con.close();
            return true;
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
}

