package cn.lyk.ssm.crud.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.pagehelper.PageInfo;

import cn.lyk.ssm.crud.bean.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:app-context.xml", "file:src/main/webapp/WEB-INF/spring-mvc.xml" })
public class MvcTest {
	@Autowired
	WebApplicationContext context;

	MockMvc mockMvc;

	@Before
	public void initMockMvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void testPage() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pn", "2")).andReturn();
		PageInfo<Employee> pageInfo = (PageInfo<Employee>) result.getRequest().getAttribute("pageInfo");
		System.out.println("当前页码:" + pageInfo.getPageNum());
		System.out.println("总页码:" + pageInfo.getPages());
		System.out.println("总记录数:" + pageInfo.getTotal());
		System.out.print("连续显示的页码:");
		int[] nums = pageInfo.getNavigatepageNums();
		for (int i = 0; i < nums.length; i++)
			System.out.print(" " + nums[i]);
		System.out.println();

		List<Employee> list = pageInfo.getList();
		for (Employee employee : list) {
			System.out.println("ID:" + employee.getEmpId() + ", name:" + employee.getEmpName());
		}
	}
}
