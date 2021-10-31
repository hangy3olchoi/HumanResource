package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common_tool.ConnectionFactory;
import vo.Jobs;

public class JobsDAO implements IDao<Jobs, String>{

	@Override
	public int insert(Jobs vo) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String key) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Jobs vo) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Jobs select(String key) throws SQLException {
		
		Connection conn = ConnectionFactory.create();
		
		String sql = "select * from jobs where job_id = '" + key + "'";
		
		Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery(sql);
		
		Jobs vo = new Jobs();
		
		while(rs.next()) {
			
			vo.setJob_id(rs.getString("JOB_ID"));
			vo.setJob_title(rs.getString("JOB_TITLE"));
			vo.setMin_salary(rs.getInt("MIN_SALARY"));
			vo.setMax_salary(rs.getInt("MAX_SALARY"));
			
		}
		conn.close();
		return vo;
	}

	@Override
	public List<Jobs> selectAll() throws SQLException {
		
		Connection conn = ConnectionFactory.create();
		
		String sql = "select * from jobs ";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		
		List<Jobs> data = new ArrayList<>();
		
		while(rs.next()) {
			Jobs vo = new Jobs();
			
			vo.setJob_id(rs.getString("JOB_ID"));
			vo.setJob_title(rs.getString("JOB_TITLE"));
			vo.setMin_salary(rs.getInt("MIN_SALARY"));
			vo.setMax_salary(rs.getInt("MAX_SALARY"));
			
			data.add(vo);
		}
		conn.close();
		return data;
		
	}

	@Override
	public List<Jobs> selectByConditions(String conditions) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}



}
