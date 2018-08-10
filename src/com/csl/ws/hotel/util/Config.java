package com.csl.ws.hotel.util;

/**
 * 常量配置类
 * @author LIUCS
 *
 */
public class Config {

	    //照片的最大字节数
		public final static long MAX_PHOTO = 1024 * 30;
		//酒店客人的信息的最大字节数
		public final static long MAX_CONTENT = 1024 * 10;
		//附件总和最大字节数
		public final static long MAX_ATTACHMENT = 1024 * 40;

		//处理成功返回代码
		public final static String SUCCESS = "ok";

		//错误代码前缀
		public final static String ERROR_PREFIX = "error_";
		//末知错误
		public final static String ERROR_UNKNOWN = ERROR_PREFIX + "01";
		//处理数据文件出错
		public final static String ERROR_HANDLE_FILE = ERROR_PREFIX + "02";

		//错误的文件类型
		public final static String ERROR_FILE_TYPE = ERROR_PREFIX + "11";
		//数据文件提交不全
		public final static String ERROR_FILE_COUNT = ERROR_PREFIX + "12";
		//数据文件超过规定大小
		public final static String ERROR_FILE_KB = ERROR_PREFIX + "13";

		//旅客ID  旅客编码不能空，长度要有22位
		public final static String ERROR_ID = ERROR_PREFIX + "21";
		public final static String ERROR_ID_MSG = ":旅客ID不能为空且必须为22位数字";
		
		//旅客姓名   旅客姓名不能空
		public final static String ERROR_XM = ERROR_PREFIX + "22";
		//旅客性别  旅客性别不能空
		public final static String ERROR_XB = ERROR_PREFIX + "23";
		//旅客民旅   旅客民旅不能空
		public final static String ERROR_MZ = ERROR_PREFIX + "24";
		//旅客出生日期不能空
		public final static String ERROR_CSRQ = ERROR_PREFIX + "25";
		//证件种类不能为空
		public final static String ERROR_ZJZL = ERROR_PREFIX + "26";
		//证件号码不能为空
		public final static String ERROR_ZJHM = ERROR_PREFIX + "27";
		//行政区划不能为空
		public final static String ERROR_XZQH = ERROR_PREFIX + "28";
		//详细地址不能为空
		public final static String ERROR_XXDZ = ERROR_PREFIX + "29";
		//入住时间不能为空
		public final static String ERROR_RZSJ = ERROR_PREFIX + "20";
		//房间号码不能为空
		public final static String ERROR_FJHM = ERROR_PREFIX + "2A";
		//英文姓不能为空
		public final static String ERROR_YWX = ERROR_PREFIX + "JW1";
		//国籍地区不能为空
		public final static String ERROR_GJ = ERROR_PREFIX + "JW2";
		//签证种类不能为空
		public final static String ERROR_QZZL = ERROR_PREFIX + "JW3";
		//停留有效期不能为空
		public final static String ERROR_TLSJ = ERROR_PREFIX + "JW4";
		//入境日期不能为空
		public final static String ERROR_RJSJ = ERROR_PREFIX + "JW5";
		//签证种类不能为空
		public final static String ERROR_QZHM = ERROR_PREFIX + "JW6";
		//此房间号不存在
		public final static String ERROR_FJHMID = ERROR_PREFIX + "NO ROOM";
		//入境口岸不能为空
		public final static String ERROR_RJKA = ERROR_PREFIX + "RJKA";
		//停留事由不能为空
		public final static String ERROR_TLSY = ERROR_PREFIX + "TLSY";
		//签发地不能为空
		public final static String ERROR_QFD = ERROR_PREFIX + "QFD";

		//分局不能为空
		public final static String ERROR_FJ = ERROR_PREFIX + "FJ";
		//派出所不能为空
		public final static String ERROR_PCS = ERROR_PREFIX + "PCS";
		// ID 重复

		//一条信息不能何存两次
		public final static String ERROR_SAVE = ERROR_PREFIX + "2B";
		//系统到期，请继费，
		public final static String ERROR_DQ = ERROR_PREFIX + "AA";
		//系统到期时间为空
		public final static String ERROR_KWK = ERROR_PREFIX + "AC";
		//无权使用
		public final static String ERROR_NO_AUTH = "You have no right to use this code, please contact your administrator.";

		//2014 01 08
		//新增错误代码 从40开始
		/**
		 * 旅客ID中不能包含字母，必须为全数字
		 */
		public final static String ERROR_IDCODE = ERROR_PREFIX + "40";
		/**
		 * 境内旅客信息项必须为12项，即12行
		 */
		public final static String ERROR_CICOUNT = ERROR_PREFIX + "41";
		public static final String ERROR_NO_RYXX = "没有找到上传的*旅客*信息,请检查上传文件内容是否完整";
		public static final String ERROR_NO_PHOTO = "没有找到上传的*旅客照片*信息,请检查上传文件内容是否完整";
}
