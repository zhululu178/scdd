package cn.scdd.jxc.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class Context {
	public final static String DATE_FORMAT = "yyyy-MM-dd";
	
	/**
	 * 会员等级
	 */
	public enum MemberLevelEnum {
		NORMAL("1","普通会员");
		/**code.*/
		private final String code;
		/**title.*/
		private final String title;
		/**
		 * 构造方法.
		  *@param code code
		  *@param title title
		 */
		MemberLevelEnum(final String code,String title) {
			this.code = code;
			this.title=title;
		}
		/**
		 * 根据编码获得相关的枚举类型.
		 *
		 * @param code 编码
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
		 * 获得键值对.
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
