package com.ziyjy.service.etrance.query;

import com.zyjy.entity.CarDealInfoBean;
import com.zyjy.exception.AppException;

/**
 * 查询信息服务
 * @author wanghjbuf
 * @date 2017年3月13日下午7:59:58
 * @version 1.0
 * @parameter 
*/
public interface QueryInfoService {

	/**
	 * 根据info_id查询Cardealinfobean
	 * 2017年3月14日
	 * @param id
	 * @return
	 * @throws AppException
	 */
	public CarDealInfoBean searchCarDetailBeanByInfoid(int info_id) throws AppException;
}
