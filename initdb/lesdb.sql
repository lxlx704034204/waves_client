/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.7.17-log : Database - lesdb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`lesdb` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `lesdb`;

/*Table structure for table `tab_car_deal_info` */

DROP TABLE IF EXISTS `tab_car_deal_info`;

CREATE TABLE `tab_car_deal_info` (
  `info_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `deal_id` int(11) NOT NULL DEFAULT '-1' COMMENT '信息id',
  `token` varchar(50) NOT NULL DEFAULT '' COMMENT '口令',
  `phone` varchar(50) NOT NULL DEFAULT '' COMMENT '手机',
  `contacts_id` int(11) DEFAULT '-1' COMMENT '联系人id',
  `contacts` varchar(50) DEFAULT '' COMMENT '联系人',
  `brand_id` int(11) DEFAULT '-1' COMMENT '品牌id',
  `brand` varchar(50) DEFAULT '' COMMENT '车辆品牌',
  `series_id` int(11) DEFAULT '-1' COMMENT '车系id',
  `series` varchar(50) DEFAULT '' COMMENT '车系',
  `model_id` int(11) DEFAULT '-1' COMMENT '车型id',
  `model` varchar(50) DEFAULT '' COMMENT '车型',
  `car_area` varchar(100) DEFAULT '' COMMENT '车辆所在地',
  `plate_number` varchar(50) DEFAULT '' COMMENT '车牌号',
  `mlieage` varchar(50) DEFAULT '' COMMENT '里程数',
  `reg_date` varchar(30) DEFAULT '' COMMENT '上牌时间',
  `note` varchar(100) DEFAULT '' COMMENT '备注',
  `status` int(11) DEFAULT '-100' COMMENT '状态',
  `status_des` varchar(30) DEFAULT '' COMMENT '状态描述',
  `create_date` varchar(30) DEFAULT '' COMMENT '创建时间',
  `create_name` varchar(30) DEFAULT '' COMMENT '创建人',
  `update_date` varchar(30) DEFAULT '' COMMENT '更新时间',
  `update_name` varchar(30) DEFAULT '' COMMENT '更新人',
  `car_image` varchar(50) DEFAULT '' COMMENT '车辆展示图',
  `car_image_id` int(11) DEFAULT '-1' COMMENT '车辆展示图id',
  `is_register` int(11) DEFAULT '-1' COMMENT '是否注册完毕',
  `att1` int(11) DEFAULT '-1',
  `att2` int(11) DEFAULT '-1',
  `att3` int(11) DEFAULT '-1',
  `att4` int(11) DEFAULT '-1',
  `att5` int(11) DEFAULT '-1',
  `att6` int(11) DEFAULT '-1',
  `att7` int(11) DEFAULT '-1',
  `att8` int(11) DEFAULT '-1',
  `att9` int(11) DEFAULT '-1',
  `att10` int(11) DEFAULT '-1',
  `str1` varchar(50) DEFAULT '',
  `str2` varchar(50) DEFAULT '',
  `str3` varchar(50) DEFAULT '',
  `str4` varchar(50) DEFAULT '',
  `str5` varchar(50) DEFAULT '',
  `str6` varchar(50) DEFAULT '',
  `str7` varchar(50) DEFAULT '',
  `str8` varchar(50) DEFAULT '',
  `str9` varchar(50) DEFAULT '',
  `str10` varchar(50) DEFAULT '',
  `date1` varchar(30) DEFAULT '',
  `date2` varchar(30) DEFAULT '',
  `date3` varchar(30) DEFAULT '',
  `date4` varchar(30) DEFAULT '',
  `date5` varchar(30) DEFAULT '',
  PRIMARY KEY (`info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tab_car_deal_info` */

/*Table structure for table `tab_config_state` */

DROP TABLE IF EXISTS `tab_config_state`;

CREATE TABLE `tab_config_state` (
  `type_id` int(11) NOT NULL DEFAULT '-1' COMMENT '配置类型',
  `config_id` int(11) NOT NULL DEFAULT '-1' COMMENT '配置id',
  `descript` varchar(30) DEFAULT '' COMMENT '描述',
  `remark` varchar(30) DEFAULT '' COMMENT '备注',
  `create_date` varchar(30) DEFAULT '' COMMENT '创建时间',
  `create_name` varchar(30) DEFAULT '' COMMENT '创建人',
  `update_date` varchar(30) DEFAULT '' COMMENT '更新时间',
  `update_name` varchar(30) DEFAULT '' COMMENT '更新人',
  `att1` int(11) DEFAULT '-1',
  `att2` int(11) DEFAULT '-1',
  `att3` int(11) DEFAULT '-1',
  `att4` int(11) DEFAULT '-1',
  `att5` int(11) DEFAULT '-1',
  `att6` int(11) DEFAULT '-1',
  `att7` int(11) DEFAULT '-1',
  `att8` int(11) DEFAULT '-1',
  `att9` int(11) DEFAULT '-1',
  `att10` int(11) DEFAULT '-1',
  `str1` varchar(50) DEFAULT '',
  `str2` varchar(50) DEFAULT '',
  `str3` varchar(50) DEFAULT '',
  `str4` varchar(50) DEFAULT '',
  `str5` varchar(50) DEFAULT '',
  `str6` varchar(50) DEFAULT '',
  `str7` varchar(50) DEFAULT '',
  `str8` varchar(50) DEFAULT '',
  `str9` varchar(50) DEFAULT '',
  `str10` varchar(50) DEFAULT '',
  `date1` varchar(30) DEFAULT '',
  `date2` varchar(30) DEFAULT '',
  `date3` varchar(30) DEFAULT '',
  `date4` varchar(30) DEFAULT '',
  `date5` varchar(30) DEFAULT '',
  PRIMARY KEY (`config_id`,`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tab_config_state` */

/*Table structure for table `tab_deal_change_log` */

DROP TABLE IF EXISTS `tab_deal_change_log`;

CREATE TABLE `tab_deal_change_log` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `info_id` int(11) NOT NULL DEFAULT '-1' COMMENT '信息id',
  `pre_status` int(11) DEFAULT '-1' COMMENT '前状态',
  `crr_status` int(11) DEFAULT '-1' COMMENT '现状态',
  `create_date` varchar(30) DEFAULT '' COMMENT '创建时间',
  `create_name` varchar(30) DEFAULT '' COMMENT '创建人',
  `update_date` varchar(30) DEFAULT '' COMMENT '更新时间',
  `update_name` varchar(30) DEFAULT '' COMMENT '更新人',
  `att1` int(11) DEFAULT '-1',
  `att2` int(11) DEFAULT '-1',
  `att3` int(11) DEFAULT '-1',
  `att4` int(11) DEFAULT '-1',
  `att5` int(11) DEFAULT '-1',
  `att6` int(11) DEFAULT '-1',
  `att7` int(11) DEFAULT '-1',
  `att8` int(11) DEFAULT '-1',
  `att9` int(11) DEFAULT '-1',
  `att10` int(11) DEFAULT '-1',
  `str1` varchar(50) DEFAULT '',
  `str2` varchar(50) DEFAULT '',
  `str3` varchar(50) DEFAULT '',
  `str4` varchar(50) DEFAULT '',
  `str5` varchar(50) DEFAULT '',
  `str6` varchar(50) DEFAULT '',
  `str7` varchar(50) DEFAULT '',
  `str8` varchar(50) DEFAULT '',
  `str9` varchar(50) DEFAULT '',
  `str10` varchar(50) DEFAULT '',
  `date1` varchar(30) DEFAULT '',
  `date2` varchar(30) DEFAULT '',
  `date3` varchar(30) DEFAULT '',
  `date4` varchar(30) DEFAULT '',
  `date5` varchar(30) DEFAULT '',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tab_deal_change_log` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
