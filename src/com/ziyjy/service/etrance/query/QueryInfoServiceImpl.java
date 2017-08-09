package com.ziyjy.service.etrance.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyjy.base.BaseService;
import com.zyjy.dao.CarDealInfoBeanMapper;
import com.zyjy.entity.CarDealInfoBean;
import com.zyjy.entity.CarDealInfoBeanExample;
import com.zyjy.exception.AppException;


/**
 * 查询服务
 * @author wanghjbuf
 * @date 2017年3月13日下午8:00:12
 * @version 1.0
 * @parameter 
*/
@Service("com.les.service.entrance.query.QueryInfoServiceImpl")
public class QueryInfoServiceImpl extends BaseService implements QueryInfoService{

	@Autowired
	CarDealInfoBeanMapper carDealInfoBeanMapper;
	
	public CarDealInfoBean searchCarDetailBeanByInfoid(int info_id) throws AppException{
		try{
			CarDealInfoBeanExample carDealInfoBeanExample = new CarDealInfoBeanExample();
			carDealInfoBeanExample.createCriteria().andInfoIdEqualTo(info_id);
			
			List<CarDealInfoBean> list = carDealInfoBeanMapper.selectByExample(carDealInfoBeanExample);
			if(list.size()==1){
				return list.get(0); 
			}else{
				throw new AppException("错误的info_id,操作失败");
			}
		}catch(AppException appException){
			throw appException;
		} catch (Exception e) {
			throw new AppException("服务层错误",e);
		}
	}
}
