package cn.lyk.ssm.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.lyk.ssm.crud.bean.Department;
import cn.lyk.ssm.crud.bean.Msg;
import cn.lyk.ssm.crud.service.DepartmentService;

@Controller
public class DepartmentController {
	@Autowired
	DepartmentService departmentService;

	@RequestMapping("/depts")
	@ResponseBody
	public Object getDepts() {
		List<Department> depts = departmentService.getDepts();
		return Msg.success().add("depts", depts);
	}

}
