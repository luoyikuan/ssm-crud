package cn.lyk.ssm.crud.bean;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

public class Employee {
	private Integer empId;

	@Pattern(regexp = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})")
	private String empName;

	private String gander;

	@Email
	private String email;

	private Integer dId;

	private Department department;

	public Employee() {
		super();
	}

	public Employee(Integer empId, String empName, String gander, String email, Integer dId) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.gander = gander;
		this.email = email;
		this.dId = dId;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getGander() {
		return gander;
	}

	public void setGander(String gander) {
		this.gander = gander;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getdId() {
		return dId;
	}

	public void setdId(Integer dId) {
		this.dId = dId;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
}