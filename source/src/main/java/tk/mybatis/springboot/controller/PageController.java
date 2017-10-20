package tk.mybatis.springboot.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/page")
public class PageController {
	
	/**
	 * 设置通用页面访问控制器，页面访问 /page/{pageName}，跳转到views下的pageName.jsp
	 * 
	 * @param pageName
	 * @return
	 */
	@RequestMapping("/{pageName}")
	public String toPage(@PathVariable("pageName")String pageName){
		return pageName;
	}
	
	
	
	
}
