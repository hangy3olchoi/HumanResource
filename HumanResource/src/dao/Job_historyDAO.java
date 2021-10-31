package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common_tool.ConnectionFactory;
import vo.Job_history;

public class Job_historyDAO implements IDao<Job_history, String>{

	@Override
	public int insert(Job_history vo) throws SQLException {
		Connection conn = ConnectionFactory.create();
		
		String sql = "insert into job_history "
				+ "(employee_id, start_date, end_date, job_id, department_id) "
				+ "values(?,?,?,?,?) ";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, vo.getEmployee_id());
		pstmt.setDate(2, vo.getStart_date());
		pstmt.setDate(3, vo.getEnd_date());
		pstmt.setString(4, vo.getJob_id());
		pstmt.setInt(5, vo.getDepartment_id());
		
		int res = pstmt.executeUpdate();
		
		conn.close();
		
		return res;
	}
	public Map<String, Object> getKeyMap(String key){
		Map<String, Object> keyMap = new HashMap<>();
		
		String[] compositeKey = key.split(",");
		
		int id = Integer.parseInt(compositeKey[0]);
		
		String[] ymd = compositeKey[1].split("-");
		
		int year = Integer.parseInt(ymd[0])-1900;
		int month = Integer.parseInt(ymd[1])-1;
		int day = Integer.parseInt(ymd[2]);
		
		Date startDay = new Date(year, month, day);
		
		keyMap.put("employee_id", id);
		keyMap.put("start_date", startDay);
		
		return keyMap;
	}

	@Override
	public int delete(String key) throws SQLException {
		Map<String, Object> keyMap = getKeyMap(key);
		
		Connection conn = ConnectionFactory.create();
		
		String sql = "delete from employees where emplyee_id = ? and statr_date = ? ";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, (int) keyMap.get("employee_id"));
		pstmt.setDate(2, (Date) keyMap.get("start_date"));
		
		int res = pstmt.executeUpdate();
		
		conn.close();
		
		return 0;
	}

	@Override
	public int update(Job_history vo) throws SQLException {
		Connection conn = ConnectionFactory.create();
		
		String sql = "update job_history set end_date = ?, job_id = ?, department_id = ? "
				+ "where employee_id = ? and start_date = ? ";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setDate(1, vo.getEnd_date());
		pstmt.setString(2, vo.getJob_id());
		pstmt.setInt(3, vo.getDepartment_id());
		pstmt.setInt(4, vo.getEmployee_id());
		pstmt.setDate(5, vo.getStart_date());
		
		int res = pstmt.executeUpdate();
		
		conn.close();
		
		return res;
	}

	@Override
	public Job_history select(String key) throws SQLException {
		Map<String, Object> keyMap = getKeyMap(key);
		
		Connection conn = ConnectionFactory.create();
		
		String sql = "select * from job_histroy where employee_id = ? "
				+ "and start_date = ? ";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, (int) keyMap.get("employee_id"));
		pstmt.setDate(2, (Date) keyMap.get("start_date"));
		
		ResultSet rs = pstmt.executeQuery();
		
		Job_history vo = new Job_history();
		
		while(rs.next()) {
			vo.setEmployee_id(rs.getInt("employee_id"));
			vo.setStart_date(rs.getDate("start_date"));
			vo.setEnd_date(rs.getDate("end_date"));
			vo.setJob_id(rs.getString("job_id"));
			vo.setDepartment_id(rs.getInt("department_id"));
		
		}
		conn.close();
		return vo;
	}

	@Override
	public List<Job_history> selectAll() throws SQLException {
		Connection conn = ConnectionFactory.create();
		
		String sql = "select * from job_history ";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		
		List<Job_history> data = new ArrayList<>();
		
		while(rs.next()) {
			Job_history vo = new Job_history();
			
			vo.setEmployee_id(rs.getInt("employee_id"));
			vo.setStart_date(rs.getDate("start_date"));
			vo.setEnd_date(rs.getDate("end_date"));
			vo.setJob_id(rs.getString("job_id"));
			vo.setDepartment_id(rs.getInt("department_id"));
			
			data.add(vo);
		}
		conn.close();
		return data;
	}

	@Override
	public List<Job_history> selectByConditions(String conditions) throws SQLException {
		Connection conn = ConnectionFactory.create();
		
		String sql = "select * from job_history " + conditions;
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		
		List<Job_history> data = new ArrayList<>();
		
		while(rs.next()) {
			Job_history vo = new Job_history();
			
			vo.setEmployee_id(rs.getInt("employee_id"));
			vo.setStart_date(rs.getDate("start_date"));
			vo.setEnd_date(rs.getDate("end_date"));
			vo.setJob_id(rs.getString("job_id"));
			vo.setDepartment_id(rs.getInt("department_id"));
			
			data.add(vo);
		}
		conn.close();
		return data;
	}
	
	public int deleteOrphanRecord(int empId) throws SQLException{
		Connection conn = ConnectionFactory.create();
		
		String sql = "delete job_history where employee_id = ? ";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, empId);
		
		int res = pstmt.executeUpdate();
		
		conn.close();
		
		return res;
	}

}
