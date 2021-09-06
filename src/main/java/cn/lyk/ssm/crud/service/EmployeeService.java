package cn.lyk.ssm.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lyk.ssm.crud.bean.Employee;
import cn.lyk.ssm.crud.bean.EmployeeExample;
import cn.lyk.ssm.crud.bean.EmployeeExample.Criteria;
import cn.lyk.ssm.crud.dao.EmployeeMapper;

@Service
public class EmployeeService {
	@Autowired
	EmployeeMapper employeeMapper;

	public List<Employee> getAll() {
		return employeeMapper.selectByExampleWithDept(null);
	}

	public void saveEmployee(Employee employee) {
		employeeMapper.insertSelective(employee);
	}

	public boolean checkUser(String empName) {
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andEmpNameEqualTo(empName);
		long count = employeeMapper.countByExample(example);
		return count == 0;
	}

	public Employee getEmp(Integer id) {
		return employeeMapper.selectByPrimaryKey(id);
	}

	public void updateEmp(Employee employee) {
		employeeMapper.updateByPrimaryKeySelective(employee);
	}

	public void deleteEmp(Integer id) {
		employeeMapper.deleteByPrimaryKey(id);
	}

	public void deleteBatch(List<Integer> ids) {
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andEmpIdIn(ids);
		employeeMapper.deleteByExample(example);
	}

}
