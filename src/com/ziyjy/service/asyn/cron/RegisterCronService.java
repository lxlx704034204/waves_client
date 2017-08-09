package com.ziyjy.service.asyn.cron;

import java.util.Date;
import java.util.List;

import org.apache.commons.httpclient.NameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zyjy.base.BaseService;
import com.zyjy.dao.CarDealInfoBeanMapper;
import com.zyjy.entity.CarDealInfoBean;
import com.zyjy.entity.CarDealInfoBeanExample;
import com.zyjy.util.DateUtil;
import com.zyjy.util.GlobalUtil;

import net.sf.json.JSONObject;

/**
 * 定时登记服务
 * @author wanghjbuf
 * @date 2017年3月13日下午8:11:05
 * @version 1.0
 * @parameter 
*/
@Component("registerCronService")
public class RegisterCronService extends BaseService{
	
	@Autowired
	CarDealInfoBeanMapper carDealInfoBeanMapper;
	
	/**
	 * 定时注册信息汽车订单状态(每5分钟执行一次操作)
	 * 2017年3月14日
	 */
	@Scheduled(cron = "0 0/5 * * * ?")  
    public void registerDealForOther() {  
		try {
			
			CarDealInfoBeanExample carDealInfoBeanExample = new CarDealInfoBeanExample(); 
			carDealInfoBeanExample.createCriteria().andIsRegisterNotEqualTo(1);
			List<CarDealInfoBean> list = carDealInfoBeanMapper.selectByExample(carDealInfoBeanExample);
			
			if(list.size()>0){
	            String qrCodeAddress = GlobalUtil.getPropertiesMap().get("user_regiter_addr").toString();
				for(CarDealInfoBean carDealInfoBean : list){
				    NameValuePair token = new NameValuePair("token",carDealInfoBean.getToken());
		            NameValuePair phone = new NameValuePair("phone",carDealInfoBean.getPhone());
		            NameValuePair contacts = new NameValuePair("contacts", carDealInfoBean.getContacts());
		            NameValuePair brand = new NameValuePair("brand", carDealInfoBean.getBrand());
		            NameValuePair series = new NameValuePair("series", carDealInfoBean.getSeries());
		            NameValuePair model = new NameValuePair("model", carDealInfoBean.getModel());
		            NameValuePair car_area = new NameValuePair("car_area", carDealInfoBean.getCarArea());
		            NameValuePair plat_number = new NameValuePair("plat_number", carDealInfoBean.getPlateNumber());
		            NameValuePair mileage = new NameValuePair("mileage", carDealInfoBean.getMlieage());
		            NameValuePair note = new NameValuePair("note", carDealInfoBean.getNote());
		            NameValuePair[] arry = new NameValuePair[]{token,phone,contacts,brand,series,model,car_area,plat_number,mileage,note};
		           
					String resultString = HttpClientBrowser.doHttpPost(qrCodeAddress, arry);
					
					JSONObject resultObject = JSONObject.fromObject(resultString);
                    if(resultObject.optString("status_code", "-1").equals("1")){
                    	carDealInfoBean.setDealId(resultObject.optJSONObject("data").getInt("id"));  
                    	carDealInfoBean.setIsRegister(1);//标识符修改
                    	carDealInfoBean.setUpdateDate(DateUtil.getDateyyyyMMddHHmmss(new Date()));
                    	carDealInfoBean.setUpdateName("system");
                    	
                    	carDealInfoBeanMapper.updateByPrimaryKeySelective(carDealInfoBean);
                    }
                    
                    //TODO 访问日志记录
                    logger.info(">>>register_log_record:id="+carDealInfoBean.getInfoId()+";deal_id="+carDealInfoBean.getDealId());
				}
			}
			
		} catch (Exception e) {
			logger.error("系统错误", e);
        }
    } 
}
