package cn.edu.zucc.personplan.comtrol.example;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.personplan.util.DBUtil;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.DbException;
import cn.edu.zucc.personplan.util.BusinessException;
import cn.edu.zucc.personplan.itf.IUserManager;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.util.BaseException;

public class ExampleUserManager implements IUserManager {

	@Override
	public BeanUser reg(String userid, String pwd,String pwd2) throws BaseException {
		if(userid==null|| "".equals(userid)) {
			throw new BusinessException("用户名不能为空，请重新输入！");
		}
		if(pwd==null||pwd2==null|| "".equals(pwd)|| "".equals(pwd2)) {
			throw new BusinessException("用户密码不能为空，请重新输入！");
		}
		if(!pwd.equals(pwd2)) {
			throw new BusinessException("两次密码输入不一致，请重新输入！");
		}
		
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql ="select * from tbl_user where user_id = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,userid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("登陆账号已经存在");
			rs.close();
			pst.close();
			sql="insert into tbl_user(user_id,user_pwd,register_time) values(?,?,?)";
			pst=conn.prepareStatement(sql);
			BeanUser u=new BeanUser();
			u.setUserid(userid);
			u.setUserpwd(pwd);
			u.setRegister_time(new java.sql.Timestamp(System.currentTimeMillis()));
			pst.setString(1,userid);
			pst.setString(2,pwd);
			pst.setTimestamp(3,new java.sql.Timestamp(System.currentTimeMillis()));
			pst.execute();
			pst.close();
			return u;
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	
	@Override
	public BeanUser login(String userid, String pwd) throws BaseException {
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select user_id,user_pwd from BeanUser where user_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,userid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("登陆账号不存在");
			if(!pwd.equals(rs.getString(2)));
			BeanUser u = new BeanUser();
			u.setUserid(rs.getString(1));
			u.setUserpwd(rs.getString(2));
			u.setRegister_time(rs.getDate(3));
			rs.close();
			pst.close();
			return u;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}


	@Override
	public void changePwd(BeanUser user, String oldPwd, String newPwd,String newPwd2) throws BaseException
	{
		if(oldPwd==null) 
			throw new BusinessException("原始密码不能为空!");
		if(newPwd==null || "".equals(newPwd)) 
			throw new BusinessException("新密码不能为空！");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select pwd from tbl_user where user_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,user.getUserid());
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("登陆账号不存在");
			if(!oldPwd.equals(rs.getString(1))) throw new BusinessException("原始密码错误");
			rs.close();
			pst.close();
			sql="update tbl_user set user_pwd=? where user_id=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, newPwd);
			pst.setString(2, user.getUserid());
			pst.execute();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}
