package com.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.User;
import com.util.BusinessException;
import com.util.DBUtil;

public class userContr {
	public String chaUsermima(String UserAccount){
		java.sql.Connection conn=null;
		try{
			conn=DBUtil.getConnection();
			String sql="select * from info_user where UserAccount=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1,UserAccount);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next())           return "fail";//throw new BusinessException("不存在此账号");
		    return rs.getString(2);
	}catch(SQLException e){
		e.printStackTrace();
	}
		//查询用户密码模块
		return "fail";
	}
	
	
	public String addUser(String UserAccount, String pwd,String pwd2,String UserNumber,int UserSelect){
		java.sql.Connection conn=null;
		try{
			conn=DBUtil.getConnection();
			String sql="select * from info_user where UserAccount=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1,UserAccount);
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next())                 return "fail";           //账号已存在
			rs.close();
			pst.close();
			if(pwd.equals(pwd2)==false)                       return "fail"; //两次账号不一致
			if(UserAccount==null||"".equals(UserAccount))     return "fail"; //账号不得为空
			if(pwd==null||"".equals(pwd))                     return "fail"; // 密码不得为空
			sql="insert into info_user(UserAccount,UserPassword,UserNumber,UserSelect,ZongFen4_1,ZongFen4_2,ZongFen6_1,ZongFen6_2) values(?,?,?,?,0,0,0,0)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, UserAccount);
			pst.setString(2, pwd);
			pst.setString(3, UserNumber);
			pst.setInt(4, UserSelect);
			pst.execute();
		}catch(SQLException e){
				e.printStackTrace();
			}
		//注册账号
		hasDC hasdc=new hasDC();
		hasdc.addHasDC(UserAccount);
		chenjiContr chenji=new chenjiContr();
		chenji.addCJ(UserAccount);
		
		return "success";
	}
	
	
	public String updUser(String UserAccount,String UserNumber,int UserSelect){
		java.sql.Connection conn=null;
		try{
			conn=DBUtil.getConnection();
			String sql="update info_user set UserNumber=?,UserSelect=? where UserAccount=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst=conn.prepareStatement(sql);
			pst.setString(1,UserNumber);
			pst.setInt(2,UserSelect);
			pst.setString(3,UserAccount);
			pst.executeUpdate();
			return "success";
		}catch(SQLException e){
				e.printStackTrace();
			}
		//修改用户信息模块
		return "fail";
	}
	
	public String updUserPaw(String UserAccount,String NewPassword){
		java.sql.Connection conn=null;
		try{
		conn=DBUtil.getConnection();
		String sql="update info_user set UserPassword=? where UserAccount=?";
		java.sql.PreparedStatement pst=conn.prepareStatement(sql);
		pst=conn.prepareStatement(sql);
		pst.setString(1,NewPassword);
		pst.setString(2,UserAccount);
		pst.executeUpdate();
		return "success";
		}catch(SQLException e){
			e.printStackTrace();
		}
		//修改用户密码模块
		return "fail";
	}
	
	public User chaUser(String UserAccount){
		User user = new User();
		java.sql.Connection conn=null;
		try{
			conn=DBUtil.getConnection();
			String sql="select * from info_user where UserAccount=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,UserAccount);
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next()){
			user.setUserAccount(rs.getString(1));
			user.setUserPassword(rs.getString(2));
			user.setUserNumber(rs.getString(3));
			user.setUserSelect(rs.getInt(4));
			user.setZongFen4_1(rs.getInt(5));
			user.setZongFen4_2(rs.getInt(6));
			user.setZongFen6_1(rs.getInt(7));
			user.setZongFen6_2(rs.getInt(8));
			return user;
			}
			}catch(SQLException e){
				e.printStackTrace();
			}
		//查询用户信息模块
		return user;
	}
	
	
	public String updUserZF(String UserAccount,int UserSelect,int zf1,int zf2){
		java.sql.Connection conn=null;
		try{
			conn=DBUtil.getConnection();
			String sql="";
			if(UserSelect==4){
				 sql="update info_user set ZongFen4_1=?,ZongFen4_2=? where UserAccount=? ";
			}
			else if(UserSelect==6){
				 sql="update info_user set ZongFen6_1=?,ZongFen6_2=? where UserAccount=? ";
			}
			
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst=conn.prepareStatement(sql);
			pst.setInt(1,zf1);
			pst.setInt(2,zf2);
			pst.setString(3,UserAccount);
			pst.executeUpdate();
			return "success";
		}catch(SQLException e){
				e.printStackTrace();
			}
		//修改用户信息模块
		return "fail";
	}
	
	
	
}




//package cn.edu.zucc.personplan.comtrol.example;
//
//import cn.edu.zucc.personplan.itf.IUserManager;
//import cn.edu.zucc.personplan.model.BeanUser;
//import cn.edu.zucc.personplan.util.BaseException;
//
//public class ExampleUserManager implements IUserManager {
//
//	@Override
//	public BeanUser reg(String userid, String pwd,String pwd2) throws BaseException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	
//	@Override
//	public BeanUser login(String userid, String pwd) throws BaseException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//	@Override
//	public void changePwd(BeanUser user, String oldPwd, String newPwd,
//			String newPwd2) throws BaseException {
//		// TODO Auto-generated method stub
//		
//	}
//
//}

