package dao.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import bean.UserBean;
import dao.UserBeanDao;
import utils.C3P0Util;
import utils.DateUtil;
import utils.GlobalUtil;
import utils.GlobalUtil.UserType;

/*
 *  用户信息的持久层操作
 */
public class UserBeanDaoImpl implements UserBeanDao {

	private QueryRunner runner = new QueryRunner(C3P0Util.getDataSource());
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public String getSearchSql(Map<String, Object> searchMap) {
		StringBuilder whereSQL = new StringBuilder();
		whereSQL.setLength(0);

		String p_adminrealname = String.valueOf(searchMap.get("p_adminrealname"));
		String p_adminusername = String.valueOf(searchMap.get("p_adminusername"));
		String p_regusername = String.valueOf(searchMap.get("p_regusername"));
		String p_regrealname = String.valueOf(searchMap.get("p_regrealname"));
		if (GlobalUtil.isNotNull(p_adminusername)) {
			whereSQL.append(" and username = " + p_adminusername + "");
		}
		if (GlobalUtil.isNotNull(p_adminrealname)) {
			whereSQL.append(" and realname like '%" + p_adminrealname + "%'");
		}
		if (GlobalUtil.isNotNull(p_regusername)) {
			whereSQL.append(" and username = " + p_regusername + "");
		}
		if (GlobalUtil.isNotNull(p_regrealname)) {
			whereSQL.append(" and realname like '%" + p_regrealname + "%'");
		}
		return whereSQL.toString();
	}

	@Override
	public void save(UserBean userBean) {
		// TODO Auto-generated method stub
		try {
			runner.update(
					"insert into t_user(username,password,sex,realname,birthday,email,phone,address,postcode,nlevel,dregdate)values(?,?,?,?,?,?,?,?,?,?,?)",
					userBean.getUsername(), userBean.getPassword(), userBean.getSex(), userBean.getRealname(),
					userBean.getBirthday(), userBean.getEmail(), userBean.getPhone(), userBean.getAddress(),
					userBean.getPostcode(), userBean.getLevel(), userBean.getRegdate());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public UserBean findByUserName(String userName) {
		// TODO Auto-generated method stub
		try {
			return (UserBean) runner.query("select * from t_user where username = ?",
					new BeanHandler<UserBean>(UserBean.class), userName);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<UserBean> findAll() {
		// TODO Auto-generated method stub
		try {
			return (List<UserBean>) runner.query("select * from t_user", new BeanListHandler<UserBean>(UserBean.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	@Override
	public UserBean findByUserNameAndPassword(String userName, String password, int level) {
		// TODO Auto-generated method stub
		try {
			return (UserBean) runner.query("select * from t_user where username = ? and password = ? and level = ?",
					new BeanHandler<UserBean>(UserBean.class), userName, password, level);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Title: getUserCount
	 * Description: 获取用户数量dao层接口实现类
	 * @param userType
	 * @return int
	 * @see dao.UserBeanDao#getUserCount(utils.GlobalUtil.UserType)
	 * @author wjh
	 * @date 2020年7月28日  
	*/
	@Override
	public int getUserCount(UserType userType, Map<String, Object> searchMap) {
		int usernum = 0;
		try {
			String sql = "select count(uid) from t_user where ";
			if (userType == UserType.sysAdmin) {
				sql = sql + "level = 5";
			} else if (userType == UserType.regUser) {
				sql = sql + "level = 9";
			}
			sql = sql + this.getSearchSql(searchMap);
			Long userCountLong = runner.query(sql, new ScalarHandler<Long>());
			// sql语句count(列名)返回的并不是int类型，而是long型值，调整代码进行类型转换
			String tString = userCountLong.toString();
			int userCount = Integer.parseInt(tString);
			if (userCount > 0) {
				usernum = userCount;
			} else {
				usernum = 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usernum;
	}

	/**
	 * Title: getUserList
	 * Description: 获取用户信息列表dao层接口实现方法 
	 * @param startIndex
	 * @param pageSize
	 * @param userType
	 * @return List<UserBean>
	 * @see dao.UserBeanDao#getUserList(int, int, utils.GlobalUtil.UserType)
	 * @author wjh
	 * @date 2020年7月28日  
	*/
	@Override
	public List<UserBean> getUserList(int startIndex, int pageSize, UserType userType, Map<String, Object> searchMap) {
		List<UserBean> userBeans = null;
		try {
			String sql = "select * from t_user where  ";
			if (userType == UserType.sysAdmin) {
				sql = sql + "level = 5";
			} else if (userType == UserType.regUser) {
				sql = sql + "level = 9";
			}
			sql = sql + this.getSearchSql(searchMap) + " order by uid asc limit " + startIndex + "," + pageSize + "";
			userBeans = runner.query(sql, new BeanListHandler<UserBean>(UserBean.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}

		return userBeans;
	}

	/**
	 * Title: getUserById
	 * Description: 根据uid查找用户信息dao层接口实现方法
	 * @param userid
	 * @return UserBean
	 * @see dao.UserBeanDao#getUserById(int)
	 * @author wjh
	 * @throws Exception 
	 * @date 2020年7月29日  
	*/
	@Override
	public UserBean getUserById(int userid) throws Exception {
		// TODO Auto-generated method stub
		UserBean userBean = null;
		String sql = "select * from t_user where uid = " + userid + "";
		try {
			userBean = (UserBean) runner.query(sql, new BeanHandler<UserBean>(UserBean.class));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		return userBean;
	}

	/**
	 * Title: insertSysAdminUser
	 * Description: 添加系统用户dao层接口实现方法
	 * @param userBean
	 * @see dao.UserBeanDao#insertSysAdminUser(bean.UserBean)
	 * @author wjh
	 * @date 2020年7月31日  
	*/
	@Override
	public void insertSysAdminUser(UserBean userBean) {
		// TODO Auto-generated method stub
		try {
			System.out.println(userBean);
			runner.update(
					"insert into t_user(username,password,sex,realname,birthday,email,phone,address,postcode,level,regdate,islock,logtime)values(?,?,?,?,?,?,?,?,?,?,?,?,?)",
					userBean.getUsername(), userBean.getPassword(), userBean.getSex(), userBean.getRealname(),
					DateUtil.getDateStr(userBean.getBirthday()), userBean.getEmail(), userBean.getPhone(),
					userBean.getAddress(), userBean.getPostcode(), userBean.getLevel(),
					DateUtil.getDateStr(userBean.getRegdate()), userBean.getislock(), userBean.getLogtime());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Title: updateSysAdminUser
	 * Description: 修改系统管理员用户信息dao层实现方法
	 * @param userBean
	 * @see dao.UserBeanDao#updateSysAdminUser(bean.UserBean)
	 * @author wjh
	 * @date 2020年7月31日  
	*/
	@Override
	public void updateSysAdminUser(UserBean userBean) {
		// TODO Auto-generated method stub
		try {
			// System.out.println(userBean);
			runner.update(
					"update t_user set username=?,sex=?,realname=?,birthday=?,email=?,phone=?,address=?,postcode=?,islock=? where uid = ?",
					userBean.getUsername(), userBean.getSex(), userBean.getRealname(),
					DateUtil.getDateStr(userBean.getBirthday()), userBean.getEmail(), userBean.getPhone(),
					userBean.getAddress(), userBean.getPostcode(), userBean.getislock(), userBean.getUid());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Title: deleteUser
	 * Description: 根据uid删除用户信息dao层接口实现方法
	 * @param uid
	 * @see dao.UserBeanDao#deleteUser(java.lang.Integer)
	 * @author wjh
	 * @date 2020年8月1日  
	*/
	@Override
	public void deleteUser(Integer uid) {
		// TODO Auto-generated method stub
		String sql = "delete from t_user where uid=" + uid + "";
		// System.out.println(sql);
		try {
			runner.update(sql, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Title: insertRegUser
	 * Description: 添加注册用户dao层接口实现方法 
	 * @param userBean
	 * @see dao.UserBeanDao#insertRegUser(bean.UserBean)
	 * @author wjh
	 * @date 2020年8月2日  
	*/
	@Override
	public void insertRegUser(UserBean userBean) {
		// TODO Auto-generated method stub
		try {
			System.out.println(userBean);
			runner.update(
					"insert into t_user(username,password,sex,realname,birthday,email,phone,address,postcode,level,regdate,islock,logtime)values(?,?,?,?,?,?,?,?,?,?,?,?,?)",
					userBean.getUsername(), userBean.getPassword(), userBean.getSex(), userBean.getRealname(),
					DateUtil.getDateStr(userBean.getBirthday()), userBean.getEmail(), userBean.getPhone(),
					userBean.getAddress(), userBean.getPostcode(), userBean.getLevel(),
					DateUtil.getDateStr(userBean.getRegdate()), userBean.getislock(), userBean.getLogtime());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * Title: changePwd
	 * Description: 修改登录用户密码dao层接口实现方法 
	 * @param uid
	 * @param newpwd
	 * @see dao.UserBeanDao#changePwd(java.lang.String, java.lang.String)
	 * @author wjh
	 * @date 2020年8月2日  
	*/
	@Override
	public void changePwd(String uid, String newpwd) {
		// TODO Auto-generated method stub
		try {
			// System.out.println(userBean);
			runner.update("update t_user set password=? where uid = ?", newpwd, uid);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Title: updateRegUser
	 * Description: 注册用户修改个人信息dao层接口实现方法
	 * @param userBean
	 * @see dao.UserBeanDao#updateRegUser(bean.UserBean)
	 * @author wjh
	 * @date 2020年8月6日  
	*/
	@Override
	public void updateRegUser(UserBean userBean) {
		// TODO Auto-generated method stub
		try {
			System.out.println(userBean);
			runner.update(
					"update t_user set sex=?,realname=?,birthday=?,email=?,phone=?,address=?,postcode=? where uid = ?",
					userBean.getSex(), userBean.getRealname(), DateUtil.getDateStr(userBean.getBirthday()),
					userBean.getEmail(), userBean.getPhone(), userBean.getAddress(), userBean.getPostcode(),
					userBean.getUid());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
