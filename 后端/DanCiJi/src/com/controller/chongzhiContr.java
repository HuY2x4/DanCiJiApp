package com.controller;

import java.sql.SQLException;

import com.model.ChenJi;
import com.util.DBUtil;

public class chongzhiContr {

	public String resPoDC(String UserAccount,int UserSelect){
		java.sql.Connection conn=null;
		try{
			conn=DBUtil.getConnection();
			if(UserSelect==4){
			String sql="delete from info_important_cet4 where UserAccount=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,UserAccount);
			pst.execute();
			return "success";
			}
			if(UserSelect==6){
				String sql="delete from info_important_cet6 where UserAccount=?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setString(1,UserAccount);
				pst.execute();
				return "success";
				}
		}catch(SQLException e){
			e.printStackTrace();
		}
		//重置重点单词
		return null;
	}
	
	public String resHasDC(){
		//�����ѱ����ʿ�ģ��
		return null;
	}
}
