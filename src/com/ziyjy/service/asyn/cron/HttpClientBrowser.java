package com.ziyjy.service.asyn.cron;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import com.zyjy.exception.AppException;



/**
 * 模拟浏览器功能
 * @author wanghjbuf
 * @date 2017年3月14日上午11:54:39
 * @version 1.0
 * @parameter 
*/
public final class HttpClientBrowser{

	 /**
	  * get方式访问
	  * 2017年3月14日
	  * @param qrCodeAddress
	  * @return
	  * @throws AppException
	  */
	 public static String  doHttpGet(String qrCodeAddress) throws AppException{
		  HttpClient client = new HttpClient(); 
		  if("".equals(qrCodeAddress)){
			  throw new AppException("网络地址不存在,操作失败!");
		  }
		  
	      GetMethod method=new GetMethod(qrCodeAddress);
	      try { 
	          Header accept = new Header("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
	          Header accept_encoding = new Header("Accept-Encoding","gzip, deflate, sdch");
	          Header accept_language = new Header("Accept-Language","zh-CN,zh;q=0.8");
	          Header connection = new Header("Connection","keep-alive");
//	          Header host = new Header("Host","open.weixin.qq.com");
	          Header insecure = new Header("Upgrade-Insecure-Requests","1");
	          Header user_agent = new Header("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");                 
//	          Header referer = new Header("Referer","https://mp.weixin.qq.com/");

	          method.setRequestHeader(accept);
	          method.setRequestHeader(accept_encoding);
	          method.setRequestHeader(accept_language);
	          method.setRequestHeader(connection);
	          method.setRequestHeader(insecure);
	          method.setRequestHeader(user_agent);
	      
	          client.executeMethod(method); 
	          
	          if (method.getStatusCode() == HttpStatus.SC_OK) {   
	        	return method.getResponseBodyAsString();
	          }else{
	          	throw new AppException("网络状态错误！");
	          }
	      } catch (Exception e) { 
	      	 throw new AppException("执行HTTP Get请求" + qrCodeAddress + "时，发生异常！",e);
	      }finally { 
	          method.releaseConnection(); 
	      } 
	  }
	 
	 /**
	  * post方式访问
	  * 2017年3月14日
	  * @param qrCodeAddress
	  * @param arr
	  * @return
	  * @throws AppException
	  */
	  public static String doHttpPost(String qrCodeAddress, NameValuePair[] arr) throws AppException {                
		   HttpClient client = new HttpClient();
	       if("".equals(qrCodeAddress)){
				throw new AppException("网络地址不存在,操作失败!");
		   }
	       PostMethod method=new PostMethod(qrCodeAddress);
	       try {  
                method.setRequestBody(arr);
      
                client.executeMethod(method); 
                if (method.getStatusCode() == HttpStatus.SC_OK) {   
                    return method.getResponseBodyAsString();	  
                }else{
                	throw new Exception("网络状态错误！");
                }
	       } catch (Exception e) { 
	        	throw new AppException("执行HTTP Post请求" + qrCodeAddress + "时，发生异常！",e);
	       } finally { 
	            method.releaseConnection(); 
	       } 
    }
}
