package vo;

import java.sql.Date;

public class Employees {
	private int employee_id;
	private String first_name;
	private String last_name;
	private String email;
	private String phone_number;
	private Date hire_date;
	private String job_id;
	private int salary;
	private double commission_pct;
	private int manager_id;
	private int department_id;
	
	public int getEmployee_id() {
		return employee_id;
	}
	
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	
	public String getFirst_name() {
		return first_name;
	}
	
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	
	public String getLast_name() {
		return last_name;
	}
	
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone_number() {
		return phone_number;
	}
	
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	
	public Date getHire_date() {
		return hire_date;
	}
	
	public void setHire_date(Date hire_date) {
		this.hire_date = hire_date;
	}
	
	public String getJob_id() {
		return job_id;
	}
	
	public void setJob_id(String job_id) {
		this.job_id = job_id;
	}
	
	public int getSalary() {
		return salary;
	}
	
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	public double getCommission_pct() {
		return commission_pct;
	}

	public void setCommission_pct(double commission_pct) {
		this.commission_pct = commission_pct;
	}

	public int getManager_id() {
		return manager_id;
	}
	
	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}
	
	public int getDepartment_id() {
		return department_id;
	}
	
	public void setDepartment_id(int dapartment_id) {
		this.department_id = dapartment_id;
	}

	@Override
	public String toString() {
		return "ID: " + employee_id + " - " + first_name + " " + last_name;
	}
	
}
