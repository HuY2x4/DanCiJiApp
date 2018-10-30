package com.controller;

import java.sql.SQLException;

import com.model.ChenJi;
import com.util.DBUtil;

public class gonggaoContr {
	public String updGG(String inf){
		//修改公告栏模块
		java.sql.Connection conn = null;
		try {
			String sql="update info_notice set Txt_notice=? where Id=?";
			conn=DBUtil.getConnection();
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, inf);
			pst.setString(2, "1");
			pst.execute();
			conn.close();
			pst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}
	
	public String chaGG(){
		//查询公告栏模块
		java.sql.Connection conn = null;
		String result=null;
		try {
			
			String sql="select Txt_notice from info_notice where Id=?";
			conn=DBUtil.getConnection();
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, 1);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()){
				result=rs.getString(1);
			}
			conn.close();
			pst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result);
		return result;
	}
}
