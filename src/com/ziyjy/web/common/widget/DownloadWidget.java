package com.ziyjy.web.common.widget;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zyjy.base.BaseController;

import net.sf.json.JSONObject;

/**
 * 公共下载接口
 * @author wanghjbuf
 * @date 2017年3月11日下午2:21:30
 * @version 1.0
 * @parameter 
*/
@Controller("com.les.web.common.widget.DownloadWidget")
@RequestMapping("/download")
public class DownloadWidget extends BaseController{

	/**
	 * 下载操作
	 * 2017年3月11日
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/do.htm")
	public ModelAndView doDownload(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
		      response.addHeader("Content-Disposition", "attachment;filename=" + new String("测试导出".getBytes("gb2312"), "ISO8859-1") + ".json");
		      response.setContentType("octets/stream");
		      OutputStream out = response.getOutputStream();
		      out.write("测试文件".getBytes());
		      out.close();
		      
			resultMap.put("msg", "操作成功");
	        resultMap.put("success", true);
		} catch (Exception e) {
           logger.error("系统错误！",e);
           resultMap.put("msg", e.getMessage());
           resultMap.put("success", false);
		}
		
		modelAndView.setViewName("webpage/json/json.html");
		modelAndView.addObject("json", JSONObject.fromObject(resultMap));
		return modelAndView;
	}
	
}
