package bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Title: ItemBean Description: 商品的实体类对象
 * 
 * @author wjh
 * @date 2020年7月20日
 */
public class ItemBean implements Serializable {
	private int id;// 编号
	private String name;// 名称
	private String description;// 描述
	private BigDecimal price;// 价格
	private String filepath = "";// 图片路径
	private String filename = "";// 图片名称
	private int isdel;// 是否删除（缺货）
	private Date dcdate;// 添加日期
	private int maxid;// 大类id
	private int minid;// 小类id
	private String maxname = "";// 大类名称
	private String minname = "";// 小类名称
	private int singleCount;
	private BigDecimal singlePrice;

	public int getSingleCount() {
		return singleCount;
	}

	public BigDecimal getSinglePrice() {
		return singlePrice;
	}

	public void setSingleCount(int singleCount) {
		this.singleCount = singleCount;
	}

	public void setSinglePrice(BigDecimal singlePrice) {
		this.singlePrice = singlePrice;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getMaxname() {
		return maxname;
	}

	public String getMinname() {
		return minname;
	}

	public void setMaxname(String maxname) {
		this.maxname = maxname;
	}

	public void setMinname(String minname) {
		this.minname = minname;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public String getFilepath() {
		return filepath;
	}

	public int getIsdel() {
		return isdel;
	}

	public Date getDcdate() {
		return dcdate;
	}

	public int getMaxid() {
		return maxid;
	}

	public int getMinid() {
		return minid;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public void setIsdel(int isdel) {
		this.isdel = isdel;
	}

	public void setDcdate(Date dcdate) {
		this.dcdate = dcdate;
	}

	public void setMaxid(int maxid) {
		this.maxid = maxid;
	}

	public void setMinid(int minid) {
		this.minid = minid;
	}

	@Override
	public String toString() {
		return "ItemBean [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", filepath=" + filepath + ", filename=" + filename + ", isdel=" + isdel + ", dcdate=" + dcdate
				+ ", maxid=" + maxid + ", minid=" + minid + ", maxname=" + maxname + ", minname=" + minname
				+ ", singleCount=" + singleCount + ", singlePrice=" + singlePrice + "]";
	}

}
