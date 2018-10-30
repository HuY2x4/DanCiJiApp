package com.controller;


import java.sql.SQLException;

import com.model.Word;
import com.util.DBUtil;

public class wordContr {
	public String addDC(String WordId,String WordMean,int Suozaiciku){
		int i=0;
		java.sql.Connection conn=null;
		if(WordId==null)  return "fail";  //单词名字不得为空
		if(WordMean==null)  return "fail";   //单词意思不得为空
		try{
			conn=DBUtil.getConnection();
			if(Suozaiciku==4){
			String sql = "select * from info_cet4 where WordId=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,WordId);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next())  return "fail";    //单词重复了
			pst.close();
			rs.close();
			sql = "select count(*) from info_cet4";
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			if(rs.next())   i = rs.getInt(1);
			pst.close();
			rs.close();
			sql = "insert into info_cet4 values(?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,i+1);
			pst.setString(2,WordId);
			pst.setString(3,"[ə'ɡri:mənt]");
			pst.setString(4,WordMean);
			pst.setString(5,"CET4-HARD");
			pst.execute();
			return "success";
			}
			if(Suozaiciku==6){
				String sql = "select * from info_cet6 where WordId=?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setString(1,WordId);
				java.sql.ResultSet rs=pst.executeQuery();
				if(rs.next())  return "fail";    //单词重复了
				pst.close();
				rs.close();
				sql = "select count(*) from info_cet6";
				pst=conn.prepareStatement(sql);
				rs=pst.executeQuery();
				if(rs.next())   i = rs.getInt(1);
				pst.close();
				rs.close();
				sql = "insert into info_cet6 values(?,?,?,?,?)";
				pst=conn.prepareStatement(sql);
				pst.setInt(1,i+1);
				pst.setString(2,WordId);
				pst.setString(3,"[ə'ɡri:mənt]");
				pst.setString(4,WordMean);
				pst.setString(5,"CET6-HARD");
				pst.execute();
				return "success";
				}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		//添加单词模块
		return "fail";
	}
	
	public String updDC(String WordId,String WordMean,int Suozaiciku){
		java.sql.Connection conn=null;
		try{
			conn=DBUtil.getConnection();
			if(Suozaiciku==4){
				String sql = "select * from info_cet4 where WordId=?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setString(1,WordId);
				java.sql.ResultSet rs=pst.executeQuery();
				if(!rs.next())   return "fail";  //单词库没有此单词
				rs.close();
				pst.close();
				sql = "update info_cet4 set WordMean=? where WordId=?";
				pst=conn.prepareStatement(sql);
				pst.setString(1,WordMean);
				pst.setString(2,WordId);
				pst.executeUpdate();
				return "success";
			}
			if(Suozaiciku==6){
				String sql = "select * from info_cet6 where WordId=?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setString(1,WordId);
				java.sql.ResultSet rs=pst.executeQuery();
				if(!rs.next())   return "fail";     //单词库没有此单词
				rs.close();
				pst.close();
				sql = "update info_cet6 set WordMean=? where WordId=?";
				pst=conn.prepareStatement(sql);
				pst.setString(1,WordMean);
				pst.setString(2,WordId);
				pst.executeUpdate();
				return "success";
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		//修改单词模块
		return null;
	}

	
	public String delDC(String WordId,int Suozaiciku){
		int i=0;
		java.sql.Connection conn=null;
		try{
			conn=DBUtil.getConnection();
			if(Suozaiciku==4){
				String sql = "select * from info_cet4 where WordId=?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setString(1,WordId);
				java.sql.ResultSet rs=pst.executeQuery();
				if(!rs.next())   return "fail";    //单词库没有此单词
				else i=rs.getInt(1);
				rs.close();
				pst.close();
				sql = "delete from info_cet4 where WordId=?";
				pst=conn.prepareStatement(sql);
				pst.setString(1,WordId);
				pst.execute();
				rs.close();
				pst.close();
				sql = "update info_cet4 set WordSn=WordSn-1 where WordSn>?";
				pst=conn.prepareStatement(sql);
				pst.setInt(1,i);
				pst.executeUpdate();
				return "success";
			}
			if(Suozaiciku==6){
				String sql = "select * from info_cet6 where WordId=?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setString(1,WordId);
				java.sql.ResultSet rs=pst.executeQuery();
				if(!rs.next())   return "fail";    //单词库没有此单词
				else i=rs.getInt(1);
				rs.close();
				pst.close();
				sql = "delete from info_cet6 where WordId=?";
				pst=conn.prepareStatement(sql);
				pst.setString(1,WordId);
				pst.execute();
				rs.close();
				pst.close();
				sql = "update info_cet6 set WordSn=WordSn-1 where WordSn>?";
				pst=conn.prepareStatement(sql);
				pst.setInt(1,i);
				pst.executeUpdate();
				return "success";
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		//删除单词模块
		return null;
	}
	
	public Word chaDC(int WordSn,int Suozaiciku){
		Word word = new Word();
		java.sql.Connection conn=null;
		try{
			conn=DBUtil.getConnection();
			if(Suozaiciku==4){
				String sql ="select * from info_cet4 where WordSn=?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setInt(1,WordSn);
				java.sql.ResultSet rs=pst.executeQuery();
				if(rs.next()){
					word.setWordSn(rs.getInt(1));
					word.setWordId(rs.getString(2));
					word.setWordPA(rs.getString(3));
					word.setWordMean(rs.getString(4));
					word.setWordCategory(rs.getString(5));
				}
				pst.close();
				rs.close();
				sql ="select * from info_cet4 where WordSn=?";
				pst=conn.prepareStatement(sql);
				if(WordSn>2000){
					pst.setInt(1,WordSn-10);
				}
				else{
					pst.setInt(1,WordSn+10);
				}
				
				rs=pst.executeQuery();
				if(rs.next()){
					word.setWord1_Id(rs.getString(2));
					word.setWord1_Mean(rs.getString(4));
				}
				pst.close();
				rs.close();
				sql ="select * from info_cet4 where WordSn=?";
				pst=conn.prepareStatement(sql);
				if(WordSn>2000){
					pst.setInt(1,WordSn-20);
				}
				else{
					pst.setInt(1,WordSn+20);
				}
				rs=pst.executeQuery();
				if(rs.next()){
					word.setWord2_Id(rs.getString(2));
					word.setWord2_Mean(rs.getString(4));
				}
				pst.close();
				rs.close();
				sql ="select * from info_cet4 where WordSn=?";
				pst=conn.prepareStatement(sql);
				if(WordSn>2000){
					pst.setInt(1,WordSn-30);
				}
				else{
					pst.setInt(1,WordSn+30);
				}
				rs=pst.executeQuery();
				if(rs.next()){
					word.setWord3_Id(rs.getString(2));
					word.setWord3_Mean(rs.getString(4));
				}
				return word;
			}
			if(Suozaiciku==6){
				String sql ="select * from info_cet6 where WordSn=?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setInt(1,WordSn);
				java.sql.ResultSet rs=pst.executeQuery();
				if(rs.next()){
					word.setWordSn(rs.getInt(1));
					word.setWordId(rs.getString(2));
					word.setWordPA(rs.getString(3));
					word.setWordMean(rs.getString(4));
					word.setWordCategory(rs.getString(5));
				}
				pst.close();
				rs.close();
				sql ="select * from info_cet6 where WordSn=?";
				pst=conn.prepareStatement(sql);
				if(WordSn>2000){
					pst.setInt(1,WordSn-10);
				}
				else{
					pst.setInt(1,WordSn+10);
				}
				rs=pst.executeQuery();
				if(rs.next()){
					word.setWord1_Id(rs.getString(2));
					word.setWord1_Mean(rs.getString(4));
				}
				pst.close();
				rs.close();
				sql ="select * from info_cet6 where WordSn=?";
				pst=conn.prepareStatement(sql);
				if(WordSn>2000){
					pst.setInt(1,WordSn-20);
				}
				else{
					pst.setInt(1,WordSn+20);
				}
				rs=pst.executeQuery();
				if(rs.next()){
					word.setWord2_Id(rs.getString(2));
					word.setWord2_Mean(rs.getString(4));
				}
				pst.close();
				rs.close();
				sql ="select * from info_cet6 where WordSn=?";
				pst=conn.prepareStatement(sql);
				if(WordSn>2000){
					pst.setInt(1,WordSn-30);
				}
				else{
					pst.setInt(1,WordSn+30);
				}
				rs=pst.executeQuery();
				if(rs.next()){
					word.setWord3_Id(rs.getString(2));
					word.setWord3_Mean(rs.getString(4));
				}
				return word;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		//查询单词模块
		return null;
	}
}
