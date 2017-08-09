package com.ziyjy.service.etrance.register;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyjy.base.BaseService;
import com.zyjy.cons.StatusConfigCons;
import com.zyjy.dao.CarDealInfoBeanMapper;
import com.zyjy.entity.CarDealInfoBean;
import com.zyjy.entity.CarDealInfoBeanExample;
import com.zyjy.exception.AppException;
import com.zyjy.util.DateUtil;

/**
 * 登记信息service
 * @author wanghjbuf
 * @date 2017年3月13日下午7:57:11
 * @version 1.0
 * @parameter 
*/
@Service("com.les.service.entrance.regiter.RegisterInfoServiceImpl")
public class RegisterInfoServiceImpl extends BaseService implements RegisterInfoService{

	@Autowired
	CarDealInfoBeanMapper carDealInfoBeanMapper;
	
	public int doRegister(HttpServletRequest request) throws AppException{
		try {
			String phone = request.getParameter("phone");
			CarDealInfoBeanExample carDealInfoBeanExample = new CarDealInfoBeanExample();
			carDealInfoBeanExample.createCriteria().andPhoneEqualTo(phone);
			List<CarDealInfoBean> list = carDealInfoBeanMapper.selectByExample(carDealInfoBeanExample);
			if(list.size() > 0){
				throw new AppException("车主电话号码不能重复,操作失败");
			}
			
			CarDealInfoBean carDealInfoBean = new CarDealInfoBean();
			carDealInfoBean.setToken(request.getParameter("token"));
			carDealInfoBean.setPhone(phone);
			
			carDealInfoBean.setContacts(request.getParameter("contacts"));
			carDealInfoBean.setBrand(request.getParameter("brand"));
			carDealInfoBean.setSeries(request.getParameter("series"));
			carDealInfoBean.setModel(request.getParameter("model"));
			carDealInfoBean.setCarArea(request.getParameter("car_area"));
			carDealInfoBean.setPlateNumber(request.getParameter("plat_number"));
			carDealInfoBean.setMlieage(request.getParameter("mileage"));
			carDealInfoBean.setRegDate(request.getParameter("reg_date"));
			carDealInfoBean.setNote(request.getParameter("note"));
			
			carDealInfoBean.setCreateDate(DateUtil.getDateyyyyMMddHHmmss(new Date()));
			carDealInfoBean.setCreateName("system");
			carDealInfoBean.setUpdateDate(DateUtil.getDateyyyyMMddHHmmss(new Date()));
			carDealInfoBean.setUpdateName("system");
			
			carDealInfoBean.setStatus(Integer.parseInt(StatusConfigCons.NO_DATA_TO_SELL_CARS));
			carDealInfoBean.setStatusDes(StatusConfigCons.getValue(StatusConfigCons.NO_DATA_TO_SELL_CARS));
			
			carDealInfoBeanMapper.insertSelective(carDealInfoBean);
			
			//TODO sequence实现
			CarDealInfoBeanExample selectExample = new CarDealInfoBeanExample();
			carDealInfoBeanExample.createCriteria().andPhoneEqualTo(phone);
			List<CarDealInfoBean> selectList = carDealInfoBeanMapper.selectByExample(selectExample);
			if(selectList.size() > 0){
				return selectList.get(0).getInfoId();
			}else{
				throw new AppException("插入操作存在问题,操作失败");
			}	
			
		} catch (AppException e) {
			throw e;	
		} catch (Exception e) {
			throw new AppException("服务层错误",e);
		}		
	}
}
