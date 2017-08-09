package com.ziyjy.service.etrance.register;

import javax.servlet.http.HttpServletRequest;

import com.zyjy.exception.AppException;

/**
 * 登记信息服务
 * @author wanghjbuf
 * @date 2017年3月13日下午7:56:46
 * @version 1.0
 * @parameter 
*/
public interface RegisterInfoService {

	/**
	 * 执行登记服务
	 * 2017年3月14日
	 * @param whereMap
	 * @return
	 */
	public int doRegister(HttpServletRequest request) throws AppException;
}
