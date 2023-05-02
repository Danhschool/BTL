package Final;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class rankDAO implements DataAccessObject<Model> {
	
	public static rankDAO getInstance() {
		return new rankDAO();
	}
	
	@Override
	public int insert(Model t) {
		// TODO Auto-generated method stub
		int ketQua = 0;
		try {
			//B1: tạo kết nối
			Connection con = JDBCUtil.getConnection();
			
			//B2 tạo đối tượng statament
			Statement st = con.createStatement();
			
			//B3: thưc thi câu lệnh
			String sql = "INSERT INTO rank_game (rank_name,rank_score)"
					+ "VALUES "
					+ "	('" + t.getRank_name() +"',"+t.getRank_score()+")";
			
			ketQua = st.executeUpdate(sql);
			
			//B4: 
			System.out.println("Ban da duoc thuc thi: " + sql);
			System.out.println("co " + ketQua + "dong bi thay doi!");
			
			//B5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return ketQua;
	}

	@Override
	public int update(Model t) {
		// TODO Auto-generated method stub
		int ketQua = 0;
		try {
			//B1: tạo kết nối
			Connection con = JDBCUtil.getConnection();
			
			//B2 tạo đối tượng statament
			Statement st = con.createStatement();
			
			//B3: thưc thi câu lệnh
			String sql = "UPDATE rank_game "+
					 " SET " +
					 " rank_name='"+ t.getRank_name()+"'"+
					 ", rank_score="+ t.getRank_score()+
					 " WHERE rank_id='"+t.getRank_id()+"\'";
			ketQua = st.executeUpdate(sql);
			
			//B4: 
			System.out.println("Ban da duoc thuc thi: " + sql);
			System.out.println("co " + ketQua + "dong bi thay doi!");
			
			//B5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return ketQua;
	}

	@Override
	public int delete(Model t) {
		// TODO Auto-generated method stub
		int ketQua = 0;
		try {
			//B1: tạo kết nối
			Connection con = JDBCUtil.getConnection();
			
			//B2 tạo đối tượng statament
			Statement st = con.createStatement();
			
			//B3: thưc thi câu lệnh
			String sql = "DELETE from rank_game "+
					 " WHERE rank_id='"+t.getRank_id()+"'";
			ketQua = st.executeUpdate(sql);
			
			//B4: 
			System.out.println("Ban da duoc thuc thi: " + sql);
			System.out.println("co " + ketQua + "dong bi thay doi!");
			
			//B5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return ketQua;
	}

	@Override
	public ArrayList<Model> selectAll() {
		// TODO Auto-generated method stub
		ArrayList<Model> ketQua = new ArrayList<Model>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();
			
			// Bước 2: tạo ra đối tượng statement
			Statement st = con.createStatement();
			
			// Bước 3: thực thi câu lệnh SQL
			
			String sql = "SELECT * FROM rank_game";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			
			// Bước 4:
			while(rs.next()) {
				int rank_id = rs.getInt("rank_id");
				String rank_name = rs.getString("rank_name");
				int rank_score = rs.getInt("rank_score");
				
				Model rank = new Model(rank_id, rank_name, rank_score);
				ketQua.add(rank);
			}
			
			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ketQua;
	}

	@Override
	public Model selectById(Model t) {
		// TODO Auto-generated method stub
		Model ketQua = null;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();
			
			// Bước 2: tạo ra đối tượng statement
			Statement st = con.createStatement();
			
			// Bước 3: thực thi câu lệnh SQL
			
			String sql = "SELECT * FROM rank_game where rank_id ="+t.getRank_id();
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			
			// Bước 4:
			while(rs.next()) {
				int rank_id = rs.getInt("rank_id");
				String rank_name = rs.getString("rank_name");
				int rank_score = rs.getInt("rank_score");
				
				ketQua = new Model(rank_id, rank_name, rank_score);
				
			}
			
			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ketQua;
	}

	@Override
	public ArrayList<Model> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Model> selectSort() {
		// TODO Auto-generated method stub
		ArrayList<Model> ketQua = new ArrayList<Model>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();
			
			// Bước 2: tạo ra đối tượng statement
			Statement st = con.createStatement();
			
			// Bước 3: thực thi câu lệnh SQL
			
			String sql = "SELECT * FROM rank_game "
					+ "ORDER BY rank_score DESC";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			
			// Bước 4:
			while(rs.next()) {
				int rank_id = rs.getInt("rank_id");
				String rank_name = rs.getString("rank_name");
				int rank_score = rs.getInt("rank_score");
				
				Model rank = new Model(rank_id, rank_name, rank_score);
				ketQua.add(rank);
			}
			
			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ketQua;
	}

}
