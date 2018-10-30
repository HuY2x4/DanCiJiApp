package com.controller;

import java.sql.SQLException;

import com.model.Fkinf;
import com.model.User;
import com.util.DBUtil;

public class userFKContr {
	public String addUserFK(String inf,String name){
		//添加用户反馈信息
		java.sql.Connection conn = null;
		int i=0;
		try {
			conn=DBUtil.getConnection();
			String sql = "select count(*) from info_feedback";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next())   i = rs.getInt(1);
			pst.close();
			rs.close();
			sql="insert into info_feedback(id,Txt_feedback,name,Textcount) values(?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,i+1);
			pst.setString(2, inf);
			pst.setString(3, name);
			pst.setInt(4, 0);
			pst.execute();
			pst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}
	
	public Fkinf chaUserFK(int id){
		//查询用户反馈信息
		java.sql.Connection conn = null;
		Fkinf fkinf=new Fkinf();
		try {
			conn=DBUtil.getConnection();
			String sql="select * from info_feedback where id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, id);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()){
				fkinf.setId(rs.getInt(1));
				fkinf.setInf(rs.getString(2));
				fkinf.setName(rs.getString(3));
				fkinf.setTextcount(rs.getString(4));
			}
			pst.close();
			rs.close();
			return fkinf;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fkinf;
	}
	
	public String delUserFK(int id){
		int i=0;
		java.sql.Connection conn=null;
		try{
			conn=DBUtil.getConnection();
			String sql = "select * from info_feedback where id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, id);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next())    i=rs.getInt(1);
			rs.close();
			pst.close();
			sql = "delete from info_feedback where id=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,id);
			pst.execute();
			rs.close();
			pst.close();
			sql = "update info_feedback set id=id-1 where id>?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,id);
			pst.executeUpdate();
			rs.close();
			pst.close();
			return "success";
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	public int count(){                   //显示有几条未读
		int i=0;
		java.sql.Connection conn=null;
		try{
			conn=DBUtil.getConnection();
			String sql = "select count(*) from info_feedback where Textcount=0";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next())   i = rs.getInt(1);
			pst.close();
			rs.close();
			return i;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return 0;
	}
	public String change(){                    //点击查看后，所有反馈都变为已读
		java.sql.Connection conn=null;
		try{
			conn=DBUtil.getConnection();
			String sql = "update info_feedback set Textcount=1";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.executeUpdate();
			pst.close();
			return "success";
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
}
