 /**************************************************************************
 * Copyright (c) 2006-2010 ZheJiang Electronic Port, Inc.
 * All rights reserved.
 * 
 * 项目名称： 义乌国际贸易
 * 版权说明：本软件属浙江电子口岸有限公司所有，在未获得浙江电子口岸有限公司正式授权
 *           情况下，任何企业和个人，不能获取、阅读、安装、传播本软件涉及的任何受知
 *           识产权保护的内容。                            
 ***************************************************************************/
package cn.scdd.jxc.util;


import java.lang.reflect.Method;

/**
 * 导表（XML形式）
 * @ClassName: MethodTool 
 * @Description: TODO(这里用一句话描述这个类的作用)
 *
 */
public class MethodTool {

	/**
	 * 反转一个有返回值的无参方法
	 * 
	 * @param object object
	 * @param methodName methodName
	 * @return Object
	 * @throws Exception Exception
	 */
	@SuppressWarnings("rawtypes")
	public static Object excuteMethod(Object object, String methodName)
			throws Exception {
		Class c = object.getClass();
		Method m = c.getMethod(methodName);
		return m.invoke(object);
	}

	/**
	 * 反转一个没有返回值的有一个参数的方法
	 * 
	 * @param object object
	 * @param methodName methodName
	 * @param parameter parameter
	 * @throws Exception Exception
	 */
	@SuppressWarnings("rawtypes")
	public static void excuteMethod(Object object, String methodName,
			Object parameter) throws Exception {
		Class c = object.getClass();
		Method m = c.getDeclaredMethod(methodName, parameter.getClass());
		m.invoke(object, parameter);
	}

	/**
	 * 执行一个参数为boolean类型的方法
	 * 
	 * @param object object
	 * @param methodName methodName
	 * @param parameter parameter
	 * @throws Exception Exception
	 */
	@SuppressWarnings("rawtypes")
	public static void excuteBoolMethod(Object object, String methodName,
			boolean parameter) throws Exception {
		Class c = object.getClass();
		Method m = c.getDeclaredMethod(methodName, boolean.class);
		m.invoke(object, parameter);

	}

	/**
	 * 获得一个属性的set方法名
	 * 
	 * @param property property
	 * @return String
	 */
	public static String returnSetMethodName(String property) {
		return "set" + Character.toUpperCase(property.charAt(0))
				+ property.substring(1);
	}

	/**
	 * 获得一个属性的get方法名
	 * 
	 * @param property property
	 * @return String
	 */
	public static String returnGetMethodName(String property) {
		return "get" + Character.toUpperCase(property.charAt(0))
				+ property.substring(1);
	}
	
}
