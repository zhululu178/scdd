package cn.scdd.jxc.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class Context {
	public final static String DATE_FORMAT = "yyyy-MM-dd";
	
	public final static String SESSION_USER_NAME = "SESSION_USER_NAME";
	
	public final static String SESSION_USER_ID = "SESSION_USER_ID";
	
	/**
	 * 删除标记
	 */
	public enum DeleteFlagEnum {
		NO("0","未删除"),
		YES("1","删除");
		/**code.*/
		private final String code;
		/**title.*/
		private final String title;
		/**
		 * 构造方法
		  *@param code code
		  *@param title title
		 */
		DeleteFlagEnum(final String code,String title) {
			this.code = code;
			this.title=title;
		}
		
		/***
		 * @return  the code
		 */
		public String getCode() {
			return this.code;
		}

		/**
		 *@return  the title
		 */
		public String getTile() {
			return title;
		}
	}
	
	/**
	 * 会员级别
	 */
	public enum MemberLevelEnum {
		NORMAL("1","普通会员");
		/**code.*/
		private final String code;
		/**title.*/
		private final String title;
		/**
		 * ���췽��.
		  *@param code code
		  *@param title title
		 */
		MemberLevelEnum(final String code,String title) {
			this.code = code;
			this.title=title;
		}
		/**
		 * ��ݱ�������ص�ö������.
		 *
		 * @param code ����
		 * @return CityType
		 */
		public static MemberLevelEnum parse(String code) {
			for (MemberLevelEnum v : MemberLevelEnum.values()) {
				if (v.getCode().equals(code)) {
					return v;
				}
			}
			return null;
		}

		/**
		 * ��ü�ֵ��.
		 * @return Map<String , String>
		 */
		public static Map<String , String> getKeyValues() {
			Map<String, String> map =
				new LinkedHashMap<String, String>();
			for (MemberLevelEnum v : MemberLevelEnum.values()) {
				map.put(v.getCode(), v.getTile());
			}
			return map;
		}
		
		/***
		 * @return  the code
		 */
		public String getCode() {
			return this.code;
		}

		/**
		 *@return  the title
		 */
		public String getTile() {
			return title;
		}
	}
}
