package user.Data;

import java.util.ArrayList;
import java.sql.*;

public class DB {
	private DB(){};
	private static final DB py_transaction = new DB();
	public static DB getPY_transaction(){
		return py_transaction;
	}
	public ArrayList<String> DataBase_query(String sql_command){
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			}
		catch(ClassNotFoundException ex){
			System.out.println("找不到驱动程序类，加载驱动失败！");
			ex.printStackTrace();
			return null;
		}
		try{
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
			System.out.println("找不到驱动程序类，加载驱动失败！");
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
	public boolean DataBase_Update(String sqlCommand){
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			}
		catch(ClassNotFoundException ex){
			ex.printStackTrace();
			return false;
		}
		try{
			Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost;DatabaseName=PY_transaction","sa","123456");
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
