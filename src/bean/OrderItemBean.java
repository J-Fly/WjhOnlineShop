package bean;

import java.math.BigDecimal;

/** Title: UserType 
 * Description: 订单明细表实体类  
 * @author wjh
 * @date 2020年7月28日
*/
public class OrderItemBean {
	private Integer nid;
	private Integer orderid;
	private Integer itemid;
	private String itemname;
	private String itemdescription;
	private String itemimg;
	private Integer count;
	private BigDecimal price;
	private BigDecimal totalprice;

	public Integer getNid() {
		return nid;
	}

	public Integer getOrderid() {
		return orderid;
	}

	public Integer getItemid() {
		return itemid;
	}

	public String getItemname() {
		return itemname;
	}

	public String getItemdescription() {
		return itemdescription;
	}

	public String getItemimg() {
		return itemimg;
	}

	public Integer getCount() {
		return count;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public BigDecimal getTotalprice() {
		return totalprice;
	}

	public void setNid(Integer nid) {
		this.nid = nid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public void setItemdescription(String itemdescription) {
		this.itemdescription = itemdescription;
	}

	public void setItemimg(String itemimg) {
		this.itemimg = itemimg;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setTotalprice(BigDecimal totalprice) {
		this.totalprice = totalprice;
	}

	@Override
	public String toString() {
		return "OrderItemBean [nid=" + nid + ", orderid=" + orderid + ", itemid=" + itemid + ", itemname=" + itemname
				+ ", itemdescription=" + itemdescription + ", itemimg=" + itemimg + ", count=" + count + ", price="
				+ price + ", totalprice=" + totalprice + "]";
	}

}
