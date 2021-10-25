package service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import dao.DepartmentsDAO;
import dao.EmployeesDAO;
import dao.Job_historyDAO;
import dao.JobsDAO;
import vo.Employees;

public class HRService {

	EmployeesDAO edao; 
	DepartmentsDAO ddao;
	Job_historyDAO jhdao;
	JobsDAO jdao;

	public HRService() {
		this.edao = new EmployeesDAO();
		this.ddao = new DepartmentsDAO();
		this.jhdao = new Job_historyDAO();
		this.jdao = new JobsDAO();
	}
	// 1. 주어진 기간의 입사자 목록찾기 
	public List<Employees> getEmpListByHireDate(Date a,Date b){
		List<Employees> result = null;
		try {
			Predicate<Employees> bt= m->{
				Date x = m.getHire_date();
				return (x.after(a) && x.before(b))
						|| x.equals(a) || x.equals(b);
			};
			result = (List<Employees>) edao.selectAll().stream().filter(bt).collect(Collectors.toList());
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	// 2. 근무부서별 직원 목록 
	public List<Employees> getEmpListByDep(int depId){
		List<Employees> result = null;
		try {
			Predicate<Employees> bt= m->{
				return m.getDepartment_id()==depId;
			};
			result = (List<Employees>) edao.selectAll().stream().filter(bt).collect(Collectors.toList());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	// 3. 많이 받는 급여순으로 본 직원 목록
	public List<Employees> getEmpListOrderbyPay(){
		List<Employees> result = null;
		try {
			result = (List<Employees>) edao.selectAll().stream().sorted(
					(em,em2) ->em2.getSalary() - em.getSalary()
					).collect(Collectors.toList());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
				
	// 4. 커미션율이 높은 순으로 본 직원 목록 
	public List<Employees> getEmpListOrderbyCommission(){
		List<Employees> result = null;
		
		try {
			result = (List<Employees>) edao.selectAll().stream().sorted(
					(comm, comm2) -> (int)((comm2.getCommission_pct())*100) - (int)((comm.getCommission_pct())*100)
					).collect(Collectors.toList());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 5. 직업id별로 본 직원 목록 
	public List<Employees> getEmpListByJobid(String jobId){
		List<Employees> result = null;
		
		try {
			Predicate<Employees> bt= m->{
				return m.getJob_id().equals(jobId);
			};
			result = (List<Employees>) edao.selectAll().stream().filter(bt).collect(Collectors.toList());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 6. 성으로 직원들 찾기( 
	public List<Employees> getEmpListByLast_name(String last_name){
		List<Employees> result = null;
		
		try {
			Predicate<Employees> bt= m->{
				return m.getLast_name().equalsIgnoreCase(last_name);
			};
			result = (List<Employees>) edao.selectAll().stream().filter(bt).collect(Collectors.toList());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}	
	
	// 7. 전화번호로 직원 찾기
	public List<Employees> getEmpListByPhone(String phone_num){
		List<Employees> result = null;
		
		try {
			Predicate<Employees> bt = m ->{
				return m.getPhone_number().equals(phone_num);
			};
			result = (List<Employees>) edao.selectAll().stream().filter(bt).collect(Collectors.toList());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 8. 이메일로 직원 찾기 
	public List<Employees> getEmpListByEmail(String email){
		List<Employees> result = null;
		
		try {
			Predicate<Employees> bt = m ->{
				return m.getEmail().equalsIgnoreCase(email);
			};
			result = (List<Employees>) edao.selectAll().stream().filter(bt).collect(Collectors.toList());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

}
