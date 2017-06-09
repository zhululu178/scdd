package cn.scdd.jxc.entity;

import java.util.List;

public class ScddGoodsClass {
    private Integer id;

    private String name;

    private Integer parentId;
    
    private List<ScddGoodsClass> children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

	public List<ScddGoodsClass> getChildren() {
		return children;
	}

	public void setChildren(List<ScddGoodsClass> children) {
		this.children = children;
	}
	
	/**
	 * 是否为跟节点
	 * @return
	 */
	public boolean isParent() {
		if(this.children == null || this.children.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}