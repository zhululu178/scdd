package cn.scdd.jxc.entity;

import java.util.List;

public class ScddPaymentType {
    private Integer id;

    private String name;

    private Integer parentId;

    private List<ScddPaymentType> children;
    
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

	public List<ScddPaymentType> getChildren() {
		return children;
	}

	public void setChildren(List<ScddPaymentType> children) {
		this.children = children;
	}
}