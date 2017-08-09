package com.ziyjy.web.common.widget;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zyjy.base.BaseController;


/**
 * 公共上传接口
 * @author wanghjbuf
 * @date 2017年3月11日上午9:54:19
 * @version 1.0
 * @parameter 
*/

@Controller("com.les.web.common.widget.UploadWidget")
@RequestMapping("/upload")
public class UploadWidget extends BaseController{

   /**
    * 上传demo
    * 2017年3月11日
    * @param request
    * @param user
    * @return
    * @throws Exception
    */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/do.htm")
	public ModelAndView doUplaod(HttpServletRequest request) throws Exception{
		ModelAndView modelAndView = new ModelAndView();
	    Map<String,Object> resultMap = new HashMap<String,Object>();
	    try {
	      boolean isMultipart = ServletFileUpload.isMultipartContent(request);
	      if (isMultipart) {
	        FileItemFactory factory = new DiskFileItemFactory();
	        ServletFileUpload upload = new ServletFileUpload(factory);
	        
        	for (Iterator iter = upload.parseRequest(request).iterator(); iter.hasNext();) {
        		FileItem item = (FileItem)iter.next();
        		InputStream inputStream = null;
        		String filename="";
        		
	            if (!(item.isFormField())){
	            	inputStream = item.getInputStream();
	            }
	          
	            if (inputStream == null)
			    	throw new Exception("未获取上传图片数据,操作失败！");
	            
	            filename = item.getName();
	           	            
	            String realPath = new StringBuffer(getRealPath(request)).append(File.separator).append("filepool").append(File.separator).toString();
		        logger.info("realPath="+realPath);
		
			    FileOutputStream fileOutputStream = new FileOutputStream(realPath + filename);
				byte buffer[] = new byte[1024];
				int len = 0;
				while((len=inputStream.read(buffer))>0){
					fileOutputStream.write(buffer, 0, len);
				}
				
				inputStream.close();
			    fileOutputStream.close();
            }
	      }

		  resultMap.put("msg", "操作成功");
		  resultMap.put("success", Boolean.valueOf(true));

	    }catch (Exception e) {
	        logger.error("系统错误！", e);	
	        resultMap.put("msg", e.getMessage());
	        resultMap.put("success", Boolean.valueOf(false));
	    }
		
		modelAndView.setViewName("webpage/json/json.html");
		modelAndView.addObject("json", resultMap);
		return modelAndView;
	}
	
	/**
	 * 获取webapps路径
	 * 2017年3月11日
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private String getRealPath(HttpServletRequest request) throws Exception{
		String webappsRoot = request.getSession().getServletContext().getRealPath("/");  
		
        String[] rootArry = webappsRoot.split("/");  
        StringBuilder webappsTemp = new StringBuilder();  
        int i = 1;  
        for(String nodePath : rootArry){  
            ++i;  
            if(i != rootArry.length){  
            	webappsTemp.append(nodePath);  
            	webappsTemp.append(File.separator);  
            }  
        }  
        String webappsPath = webappsTemp.toString();
        return webappsPath;
    }
}
