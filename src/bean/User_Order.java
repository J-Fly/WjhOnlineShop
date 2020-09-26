package bean;

import java.util.Date;

public class User_Order {
	private String ouser;// 订单用户名
	private String odate;// 下单日期
	private String getname;// 收货人姓名
	private Integer pay_count;
	private Integer nopay_count;
	private Integer send_count;
	private Integer nosend_count;

	public String getOuser() {
		return ouser;
	}

	public String getOdate() {
		return odate;
	}

	public String getGetname() {
		return getname;
	}

	public Integer getPay_count() {
		return pay_count;
	}

	public Integer getNopay_count() {
		return nopay_count;
	}

	public Integer getSend_count() {
		return send_count;
	}

	public Integer getNosend_count() {
		return nosend_count;
	}

	public void setOuser(String ouser) {
		this.ouser = ouser;
	}

	public void setOdate(String odate) {
		this.odate = odate;
	}

	public void setGetname(String getname) {
		this.getname = getname;
	}

	public void setPay_count(Integer pay_count) {
		this.pay_count = pay_count;
	}

	public void setNopay_count(Integer nopay_count) {
		this.nopay_count = nopay_count;
	}

	public void setSend_count(Integer send_count) {
		this.send_count = send_count;
	}

	public void setNosend_count(Integer nosend_count) {
		this.nosend_count = nosend_count;
	}

	@Override
	public String toString() {
		return "User_Order [ouser=" + ouser + ", odate=" + odate + ", getname=" + getname + ", pay_count=" + pay_count
				+ ", nopay_count=" + nopay_count + ", send_count=" + send_count + ", nosend_count=" + nosend_count
				+ "]";
	}

}
