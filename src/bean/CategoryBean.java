package bean;

import java.util.ArrayList;
import java.util.List;

public class CategoryBean {
	private Integer cid;
	private String classname;
	private Integer parentid;
	private List<CategoryBean> childCategory = new ArrayList<>();

	public CategoryBean() {

	}

	public CategoryBean(Integer cid, String classname, Integer parentid) {
		this.cid = cid;
		this.classname = classname;
		this.parentid = parentid;
	}

	public List<CategoryBean> getChildCategory() {
		return childCategory;
	}

	public void setChildCategory(List<CategoryBean> childCategory) {
		this.childCategory = childCategory;
	}

	public Integer getCid() {
		return cid;
	}

	public String getClassname() {
		return classname;
	}

	public Integer getParentid() {
		return parentid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	@Override
	public String toString() {
		return "CategoryBean [cid=" + cid + ", classname=" + classname + ", parentid=" + parentid + "]";
	}

}
