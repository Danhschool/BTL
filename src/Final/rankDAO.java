package Final;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class rankDAO implements DataAccessObject<Model> {
	
	static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/game?serverTimezone=UTC";
	static final String USER = "root";
	static final String PASS = "";
	
	public static rankDAO getInstance() {
		return new rankDAO();
	}
	
	@Override
	public int insert(Model t) {
		Connection conn = null;
		Statement stmt = null;
		int ketQua = 0;
		// TODO Auto-generated method stub
		try {
			//B1: Register JDBC driver
			try {
				Class.forName(DRIVER_CLASS);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//B2 Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			//B3: thưc thi câu lệnh
			stmt = conn.createStatement();
			
			String sql = "INSERT INTO rank_game (rank_name,rank_score)"
					+ "VALUES "
					+ "	('" + t.getRank_name() +"',"+t.getRank_score()+")";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			//B4: 
			System.out.println("Ban da duoc thuc thi: " + sql);
			
			rs.close();
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
		//"STEP 5: Close connection");
		if (stmt != null)
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		
		return ketQua;
	}

	public ArrayList<Model> selectSort(){
		// TODO Auto-generated method stub
		
		Connection conn = null;
		Statement stmt = null;
		
		ArrayList<Model> ketQua = new ArrayList<Model>();
		try {
			//B1: Register JDBC driver
			try {
				Class.forName(DRIVER_CLASS);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//B2 Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			// Bước 3: thực thi câu lệnh SQL
			
			stmt = conn.createStatement();
			
			String sql = "SELECT * FROM rank_game "
					+ "ORDER BY rank_score DESC";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			// Bước 4:
			while(rs.next()) {
				int rank_id = rs.getInt("rank_id");
				String rank_name = rs.getString("rank_name");
				int rank_score = rs.getInt("rank_score");
				
				Model rank = new Model(rank_id, rank_name, rank_score);
				ketQua.add(rank);
			}
			
			// Bước 5:
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			System.out.println("STEP 5: Close connection");
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
		
		return ketQua;
	}

}
