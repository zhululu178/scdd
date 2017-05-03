package cn.scdd.jxc.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class Context {
	public final static String DATE_FORMAT = "yyyy-MM-dd";
	
	/**
	 * ��Ա�ȼ�
	 */
	public enum MemberLevelEnum {
		NORMAL("1","��ͨ��Ա");
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
		 * ���ݱ�������ص�ö������.
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
