package bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** Title: UserType 
 * Description: 订单实体类
 * @author wjh
 * @date 2020年7月28日
*/
public class OrderBean implements Serializable {
	private Integer orderid;
	private String ouser;// 订单用户名
	private Date odate;// 下单日期
	private String paytype;// 付款方式
	private String sendtype;// 发货方式
	private Integer mctypesize;// 商品种类数
	private Integer mcsize;// 商品总数量
	private BigDecimal totalprice;// 总价格
	private Integer status;// 审核状态
	private String msg;
	private String auser;// 审核人
	private Date adate;// 审核日期
	private String getname;// 收货人
	private String getaddress;// 收获地址
	private String getpostcode;// 收获邮编
	private String getphone;// 收货人电话
	private String getemail;// 收货人邮箱

	public Integer getOrderid() {
		return orderid;
	}

	public String getOuser() {
		return ouser;
	}

	public Date getOdate() {
		return odate;
	}

	public String getPaytype() {
		return paytype;
	}

	public String getSendtype() {
		return sendtype;
	}

	public Integer getMctypesize() {
		return mctypesize;
	}

	public Integer getMcsize() {
		return mcsize;
	}

	public BigDecimal getTotalprice() {
		return totalprice;
	}

	public Integer getStatus() {
		return status;
	}

	public String getMsg() {
		return msg;
	}

	public String getAuser() {
		return auser;
	}

	public Date getAdate() {
		return adate;
	}

	public String getGetname() {
		return getname;
	}

	public String getGetaddress() {
		return getaddress;
	}

	public String getGetpostcode() {
		return getpostcode;
	}

	public String getGetphone() {
		return getphone;
	}

	public String getGetemail() {
		return getemail;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	public void setOuser(String ouser) {
		this.ouser = ouser;
	}

	public void setOdate(Date odate) {
		this.odate = odate;
	}

	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}

	public void setSendtype(String sendtype) {
		this.sendtype = sendtype;
	}

	public void setMctypesize(Integer mctypesize) {
		this.mctypesize = mctypesize;
	}

	public void setMcsize(Integer mcsize) {
		this.mcsize = mcsize;
	}

	public void setTotalprice(BigDecimal totalprice) {
		this.totalprice = totalprice;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setAuser(String auser) {
		this.auser = auser;
	}

	public void setAdate(Date adate) {
		this.adate = adate;
	}

	public void setGetname(String getname) {
		this.getname = getname;
	}

	public void setGetaddress(String getaddress) {
		this.getaddress = getaddress;
	}

	public void setGetpostcode(String getpostcode) {
		this.getpostcode = getpostcode;
	}

	public void setGetphone(String getphone) {
		this.getphone = getphone;
	}

	public void setGetemail(String getemail) {
		this.getemail = getemail;
	}

	@Override
	public String toString() {
		return "OrderBean [orderid=" + orderid + ", ouser=" + ouser + ", odate=" + odate + ", paytype=" + paytype
				+ ", sendtype=" + sendtype + ", mctypesize=" + mctypesize + ", mcsize=" + mcsize + ", totalprice="
				+ totalprice + ", status=" + status + ", msg=" + msg + ", auser=" + auser + ", adate=" + adate
				+ ", getname=" + getname + ", getaddress=" + getaddress + ", getpostcode=" + getpostcode + ", getphone="
				+ getphone + ", getemail=" + getemail + "]";
	}

}
