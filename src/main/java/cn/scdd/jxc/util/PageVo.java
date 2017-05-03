package cn.scdd.jxc.util;

import java.util.List;

public class PageVo<T> {
	
	private List<T> list;
	
	/**总页数*/
	private int pageNum = 0;
	
	/**当前页*/
	private int pageCur = 1;
	
	/**页面内容*/
	private String contents;

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageCur() {
		return pageCur;
	}

	public void setPageCur(int pageCur) {
		this.pageCur = pageCur;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}
}
