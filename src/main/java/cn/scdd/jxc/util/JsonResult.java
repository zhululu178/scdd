package cn.scdd.jxc.util;

public class JsonResult {
	//代码 1:成功，2:失败
	private Integer code;
	//消息内容
	private String msg;
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
