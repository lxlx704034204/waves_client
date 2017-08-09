package com.ziyjy.web.dubbo;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ziyjy.dubborpc.service.UploadTravelService;
import com.zyjy.base.BaseController;


/**
 *@author wanghjbuf
 *@date 2017年4月22日下午6:09:41
 *@version 1.0
 *@parameter 
*/
@Controller("com.les.web.dubbo.ConnectionTestAction")
public class ConnectionTestAction  extends BaseController{

	@Autowired
	UploadTravelService uploadTravelService;
	
    /**
     * 测试demo
     * @param request
     * @param user
     * @return
     * @throws Exception
     */
	@RequestMapping("/dubbo/test.htm")
	public ModelAndView test(HttpServletRequest request) throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("webpage/json/json.html");
		modelAndView.addObject("test", "测试成功");
		
		uploadTravelService.Creat();
		
		logger.info(">>>>>>>>>>>>>测试成功！");
		return modelAndView;
	}
}
