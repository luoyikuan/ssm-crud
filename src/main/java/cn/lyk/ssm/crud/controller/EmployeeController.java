package cn.lyk.ssm.crud.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.lyk.ssm.crud.bean.Employee;
import cn.lyk.ssm.crud.bean.Msg;
import cn.lyk.ssm.crud.service.EmployeeService;

@Controller
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;

	@RequestMapping(value = "/emp/{ids}", method = RequestMethod.DELETE)
	@ResponseBody
	public Msg deleteEmpById(@PathVariable("ids") String ids) {
		if (ids.contains("-")) {
			List<Integer> del_ids = new ArrayList<Integer>();
			String[] str_ids = ids.split("-");
			for (String id : str_ids) {
				del_ids.add(Integer.parseInt(id));
			}
			employeeService.deleteBatch(del_ids);
		} else {
			Integer id = Integer.parseInt(ids);
			employeeService.deleteEmp(id);
		}
		return Msg.success();
	}

	@RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Msg getEmp(@PathVariable("id") Integer id) {
		Employee employee = employeeService.getEmp(id);
		return Msg.success().add("emp", employee);
	}

	@RequestMapping(value = "/emp/{empId}", method = RequestMethod.PUT)
	@ResponseBody
	public Msg saveEmp(Employee employee) {
		employeeService.updateEmp(employee);
		return Msg.success();
	}

	@RequestMapping("/emps")
	public String getEmps(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
		PageHelper.startPage(pn, 5);
		List<Employee> emps = employeeService.getAll();
		PageInfo<Employee> pageInfo = new PageInfo<Employee>(emps, 5);
		model.addAttribute("pageInfo", pageInfo);
		return "list";
	}

	@RequestMapping(value = "/emp", method = RequestMethod.GET)
	@ResponseBody
	public Msg getEmpsWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
		PageHelper.startPage(pn, 5);
		List<Employee> emps = employeeService.getAll();
		PageInfo<Employee> pageInfo = new PageInfo<Employee>(emps, 5);
		return Msg.success().add("pageInfo", pageInfo);
	}

	@RequestMapping(value = "/emp", method = RequestMethod.POST)
	@ResponseBody
	public Msg saveEmp(@Valid Employee employee, BindingResult bindingResult) {

		if (bindingResult.hasFieldErrors()) {
			return Msg.fail().add("fieldErrors", bindingResult.getFieldErrors());
		} else {
			employeeService.saveEmployee(employee);
			return Msg.success();
		}

	}

	@RequestMapping(value = "/checkuser")
	@ResponseBody
	public Msg checkUser(@RequestParam("empName") String empName) {
		String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
		if (empName.matches(regx) == false) {
			return Msg.fail().add("va_msg", "");
		}

		boolean b = employeeService.checkUser(empName);
		if (b) {
			return Msg.success();
		} else {
			return Msg.fail();
		}
	}

}