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
 * 定时结算服务
 * @author wanghjbuf
 * @date 2017年3月13日下午8:10:34
 * @version 1.0
 * @parameter 
*/
@Component("settlementCronService")
public class SettlementCronService extends BaseService{

	@Autowired
	CarDealInfoBeanMapper carDealInfoBeanMapper;
	
	/**
	 * 定时更新汽车订单状态(每天凌晨一点执行)
	 * 2017年3月14日
	 */
	@Scheduled(cron = "0 1 0 * * ?")  
    public void updateStatusForDeal() {  
		try {
			CarDealInfoBeanExample carDealInfoBeanExample = new CarDealInfoBeanExample();
			carDealInfoBeanExample.createCriteria().andDealIdNotEqualTo(-1).andCreateDateLessThan(DateUtil.getDateyyyyMMddHHmmss(DateUtil.getDateExpiredDay(new Date(),60)));
			List<CarDealInfoBean> list = carDealInfoBeanMapper.selectByExample(carDealInfoBeanExample);
            
			String qrCodeAddress = GlobalUtil.getPropertiesMap().get("user_query_addr").toString();
			if(list.size()>0){
				for(CarDealInfoBean carDealInfoBean : list){
					NameValuePair token = new NameValuePair("token",carDealInfoBean.getToken());
		            NameValuePair id = new NameValuePair("id",""+carDealInfoBean.getDealId());
		            NameValuePair[] arry = new NameValuePair[]{token,id};
		            
					String resultString = HttpClientBrowser.doHttpPost(qrCodeAddress, arry);	
					
					JSONObject resultObject = JSONObject.fromObject(resultString);
                    if(resultObject.optString("status_code", "-1").equals("1")){
                    	carDealInfoBean.setStatus(resultObject.optJSONObject("data").getInt("car_status_code"));  
                    	carDealInfoBean.setStatusDes(resultObject.optJSONObject("data").getString("car_status_msg"));
                    	
                    	carDealInfoBean.setUpdateDate(DateUtil.getDateyyyyMMddHHmmss(new Date()));
                    	carDealInfoBean.setUpdateName("system");
                    	
                    	carDealInfoBeanMapper.updateByPrimaryKeySelective(carDealInfoBean);
                    	
                    	//TODO 记录变更
                    }
                    logger.info(">>>settlment_log_record:id="+carDealInfoBean.getInfoId()+";deal_id="+carDealInfoBean.getDealId());
				}
			}
			
			
		} catch (Exception e) {
			logger.error("系统错误", e);
        }
    } 
}
