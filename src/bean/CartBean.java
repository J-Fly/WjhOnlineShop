package bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CartBean implements Serializable {
	private int totalCount;
	private BigDecimal totalPrice;
	private String totalPrice_format;
	private List<ItemBean> itemList = new ArrayList<ItemBean>();

	public int getTotalCount() {
		return totalCount;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public String getTotalPrice_format() {
		return totalPrice_format;
	}

	public List<ItemBean> getItemList() {
		return itemList;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void setTotalPrice_format(String totalPrice_format) {
		this.totalPrice_format = totalPrice_format;
	}

	public void setItemList(List<ItemBean> itemList) {
		this.itemList = itemList;
	}

}
