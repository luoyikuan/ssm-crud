package cn.lyk.ssm.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lyk.ssm.crud.bean.Department;
import cn.lyk.ssm.crud.dao.DepartmentMapper;

@Service
public class DepartmentService {
	@Autowired
	DepartmentMapper departmentMapper;

	public List<Department> getDepts() {
		return departmentMapper.selectByExample(null);
	}

}
