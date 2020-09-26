package bean;

import java.io.Serializable;
import java.util.Date;

public class UserBean implements Serializable{
	private Integer uid;
	private String username;
	private String password;
	private String sex;
	private String realname;
	private Date birthday;
	private String email;
	private String phone;
	private String address;
	private String postcode;
	private Integer level;// 权限级别：1超级管理员 5系统管理员 9注册用户
	private Date regdate;// 注册日期
	private Integer islock;// 是否冻结 1冻结 0解冻
	private Date lastlogin;// 最近登录日期
	private Integer logtime;// 登录次数

	public Integer getUid() {
		return uid;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getSex() {
		return sex;
	}

	public String getRealname() {
		return realname;
	}

	public Date getBirthday() {
		return birthday;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}

	public String getPostcode() {
		return postcode;
	}

	public Integer getLevel() {
		return level;
	}

	public Date getRegdate() {
		return regdate;
	}

	public Integer getislock() {
		return islock;
	}

	public Date getLastlogin() {
		return lastlogin;
	}

	public Integer getLogtime() {
		return logtime;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public void setislock(Integer islock) {
		this.islock = islock;
	}

	public void setLastlogin(Date lastlogin) {
		this.lastlogin = lastlogin;
	}

	public void setLogtime(Integer logtime) {
		this.logtime = logtime;
	}

	@Override
	public String toString() {
		return "UserBean [uid=" + uid + ", username=" + username + ", password=" + password + ", sex=" + sex
				+ ", realname=" + realname + ", birthday=" + birthday + ", email=" + email + ", phone=" + phone
				+ ", address=" + address + ", postcode=" + postcode + ", level=" + level + ", regdate=" + regdate
				+ ", islock=" + islock + ", lastlogin=" + lastlogin + ", logtime=" + logtime + "]";
	}
}
