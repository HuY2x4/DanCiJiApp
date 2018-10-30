package com.controller;

import java.sql.SQLException;

import com.model.Word;
import com.util.DBUtil;

public class PointwordContr {
	public String addPoDC(int WordSn,int Suozaiciku,String UserAccount){
		Word word = new Word();
		int i=0,m=0;
		java.sql.Connection conn=null;
		try{
			conn=DBUtil.getConnection();
			if(Suozaiciku==4){
				String sql="select * from info_cet4 where WordSn=?";
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
				sql = "select count(*) from info_important_cet4 where UserAccount=?";
				pst=conn.prepareStatement(sql);
				pst.setString(1,UserAccount);
				rs=pst.executeQuery();
				if(rs.next())   i = rs.getInt(1);
				rs.close();
				pst.close();
//				sql = "select count(*) from info_important_cet4";
//				pst=conn.prepareStatement(sql);
//				rs=pst.executeQuery();
//				if(rs.next())   m = rs.getInt(1);
//				rs.close();
//				pst.close();
				sql ="insert into info_important_cet4(WordId,WordCn,WordPA,WordMean,WordCategory,UserAccount,count) values(?,?,?,?,?,?,'0')";
				pst=conn.prepareStatement(sql);
				pst.setString(1,word.getWordId());
				pst.setInt(2,i+1);
				pst.setString(3,word.getWordPA());
				pst.setString(4,word.getWordMean());
				pst.setString(5,word.getWordCategory());
				pst.setString(6,UserAccount);
				
				pst.execute();
				pst.close();
				rs.close();
				return "success";
			}
			if(Suozaiciku==6){
				String sql="select * from info_cet6 where WordSn=?";
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
				sql = "select count(*) from info_important_cet6 where UserAccount=?";
				pst=conn.prepareStatement(sql);
				pst.setString(1,UserAccount);
				rs=pst.executeQuery();
				if(rs.next())   i = rs.getInt(1);
				rs.close();
				pst.close();
//				sql = "select count(*) from info_important_cet6";
//				pst=conn.prepareStatement(sql);
//				rs=pst.executeQuery();
//				if(rs.next())   m = rs.getInt(1);
//				rs.close();
//				pst.close();
				sql ="insert into info_important_cet6(WordId,WordCn,WordPA,WordMean,WordCategory,UserAccount,count) values(?,?,?,?,?,?,'0')";
				pst=conn.prepareStatement(sql);
			
				pst.setString(1,word.getWordId());
				pst.setInt(2,i+1);
				pst.setString(3,word.getWordPA());
				pst.setString(4,word.getWordMean());
				pst.setString(5,word.getWordCategory());
				pst.setString(6,UserAccount);
				pst.execute();
				pst.close();
				rs.close();
				return "success";
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		//添加重点单词
		return null;
	}

	
	public String delPoDC(String WordId,int Suozaiciku,String UserAccount){
		int i=0;
		java.sql.Connection conn=null;
		try{
			conn=DBUtil.getConnection();
			if(Suozaiciku==4){
				String sql = "select * from info_important_cet4 where WordId=? and UserAccount=?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setString(1,WordId);
				pst.setString(2,UserAccount);
				java.sql.ResultSet rs=pst.executeQuery();
				if(!rs.next())   return "fail";    //单词库没有此单词
				else {
//					m=rs.getInt(1);
					i=rs.getInt(3);
				}
				rs.close();
				pst.close();
				sql = "delete from info_important_cet4 where WordId=? and UserAccount=?";
				pst=conn.prepareStatement(sql);
				pst.setString(1,WordId);
				pst.setString(2,UserAccount);
				pst.execute();
				rs.close();
				pst.close();
//				sql = "update info_important_cet4 set WordSn=WordSn-1 where WordSn>?";
//				pst=conn.prepareStatement(sql);
//				pst.setInt(1,m);
//				pst.executeUpdate();
//				rs.close();
//				pst.close();
				sql = "update info_important_cet4 set WordCn=WordCn-1 where WordCn>?";
				pst=conn.prepareStatement(sql);
				pst.setInt(1,i);
				pst.executeUpdate();
				return "success";
			}
			if(Suozaiciku==6){
				String sql = "select * from info_important_cet6 where WordId=? and UserAccount=?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setString(1,WordId);
				pst.setString(2,UserAccount);
				java.sql.ResultSet rs=pst.executeQuery();
				if(!rs.next())   return "fail";    //单词库没有此单词
				else {
//					m=rs.getInt(1);
					i=rs.getInt(3);
				}
				rs.close();
				pst.close();
				sql = "delete from info_important_cet6 where WordId=? and UserAccount=?";
				pst=conn.prepareStatement(sql);
				pst.setString(1,WordId);
				pst.setString(2,UserAccount);
				pst.execute();
				rs.close();
				pst.close();
//				sql = "update info_important_cet6 set WordSn=WordSn-1 where WordSn>?";
//				pst=conn.prepareStatement(sql);
//				pst.setInt(1,m);
//				pst.executeUpdate();
//				rs.close();
//				pst.close();
				sql = "update info_important_cet6 set WordCn=WordCn-1 where WordCn>?";
				pst=conn.prepareStatement(sql);
				pst.setInt(1,i);
				pst.executeUpdate();
				return "success";
			}
			}catch(SQLException e){
				e.printStackTrace();
			}
		
		//删除重点单词
		return null;
	}
	public Word chaPoDC(int WordCn,int Suozaiciku,String UserAccount){
		int i=0;
		Word word = new Word();
		java.sql.Connection conn=null;
		try{
			conn=DBUtil.getConnection();
			if(Suozaiciku==4){
				String sql ="select * from info_important_cet4 where WordCn=? and UserAccount=?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setInt(1,WordCn);
				pst.setString(2,UserAccount);
				java.sql.ResultSet rs=pst.executeQuery();
				if(rs.next()){
					word.setWordSn(rs.getInt(1));
					word.setWordId(rs.getString(2));
					word.setWordPA(rs.getString(4));
					word.setWordMean(rs.getString(5));
					word.setWordCategory(rs.getString(6));
					pst.close();
				    rs.close();
				}
				else return word;
				sql ="select WordSn from info_cet4 where WordId=?";
				pst=conn.prepareStatement(sql);
				pst.setString(1,word.getWordId());
				rs=pst.executeQuery();
				if(rs.next())   
				i=rs.getInt(1);    //i为在四级库中的WordSn
				pst.close();
				rs.close();
				sql ="select * from info_cet4 where WordSn=?";
				pst=conn.prepareStatement(sql);
				if(i>2000){
					pst.setInt(1,i-10);
				}
				else{
					pst.setInt(1,i+10);
				}
				rs=pst.executeQuery();
				if(rs.next()){
					word.setWord1_Id(rs.getString(2));
					word.setWord1_Mean(rs.getString(4));
					pst.close();
				rs.close();
				}
				else return word;
				sql ="select * from info_cet4 where WordSn=?";
				pst=conn.prepareStatement(sql);
				if(i>2000){
					pst.setInt(1,i-20);
				}
				else{
					pst.setInt(1,i+20);
				}
				rs=pst.executeQuery();
				if(rs.next()){
					word.setWord2_Id(rs.getString(2));
					word.setWord2_Mean(rs.getString(4));
					pst.close();
				rs.close();
				}
				else return word;
				sql ="select * from info_cet4 where WordSn=?";
				pst=conn.prepareStatement(sql);
				if(i>2000){
					pst.setInt(1,i-30);
				}
				else{
					pst.setInt(1,i+30);
				}
				rs=pst.executeQuery();
				if(rs.next()){
					word.setWord3_Id(rs.getString(2));
					word.setWord3_Mean(rs.getString(4));
					pst.close();
				rs.close();
				}
				else return word;
				return word;
			}
			if(Suozaiciku==6){
				String sql ="select * from info_important_cet6 where WordCn=? and UserAccount=?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setInt(1,WordCn);
				pst.setString(2,UserAccount);
				java.sql.ResultSet rs=pst.executeQuery();
				if(rs.next()){
					word.setWordSn(rs.getInt(1));
					word.setWordId(rs.getString(2));
					word.setWordPA(rs.getString(4));
					word.setWordMean(rs.getString(5));
					word.setWordCategory(rs.getString(6));
					pst.close();
				    rs.close();
				}
				else return word;
				sql ="select WordSn from info_cet6 where WordId=?";
				pst=conn.prepareStatement(sql);
				pst.setString(1,word.getWordId());
				rs=pst.executeQuery();
				if(rs.next())   
				i=rs.getInt(1);    //i为在四级库中的WordSn
				pst.close();
				rs.close();
				sql ="select * from info_cet6 where WordSn=?";
				pst=conn.prepareStatement(sql);
				if(i>2000){
					pst.setInt(1,i-10);
				}
				else{
					pst.setInt(1,i+10);
				}
				rs=pst.executeQuery();
				if(rs.next()){
					word.setWord1_Id(rs.getString(2));
					word.setWord1_Mean(rs.getString(4));
					pst.close();
				rs.close();
				}
				else return word;
				sql ="select * from info_cet6 where WordSn=?";
				pst=conn.prepareStatement(sql);
				if(i>2000){
					pst.setInt(1,i-20);
				}
				else{
					pst.setInt(1,i+20);
				}
				rs=pst.executeQuery();
				if(rs.next()){
					word.setWord2_Id(rs.getString(2));
					word.setWord2_Mean(rs.getString(4));
					pst.close();
				rs.close();
				}
				else return word;
				sql ="select * from info_cet6 where WordSn=?";
				pst=conn.prepareStatement(sql);
				if(i>2000){
					pst.setInt(1,i-30);
				}
				else{
					pst.setInt(1,i+30);
				}
				rs=pst.executeQuery();
				if(rs.next()){
					word.setWord3_Id(rs.getString(2));
					word.setWord3_Mean(rs.getString(4));
					pst.close();
				rs.close();
				}
				else return word;
				return word;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		//查询重点单词
		return null;
	}
	
	public String hasPoDC(String UserAccount,String dc,int Suozaiciku){
		String result="fail";
		java.sql.Connection conn=null;
		try{
			conn=DBUtil.getConnection();
			if(Suozaiciku==4){
				String sql="select WordId from info_important_cet4 where WordId=? and UserAccount=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,dc);
			pst.setString(2,UserAccount);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()){
				result="success";
			}
			
			rs.close();
			pst.close();
			}
			else if (Suozaiciku==6){
				String sql="select WordId from info_important_cet6 where WordId=? and UserAccount=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,dc);
			pst.setString(2,UserAccount);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()){
				result="success";
			}
			
			rs.close();
			pst.close();
			}
			
			
		
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		//添加重点单词
		return result;
	}
	
	
	public int count(String UserAccount,int Suozaiciku){                   //显示有几条未读
		int i=0;
		java.sql.Connection conn=null;
		try{
			conn=DBUtil.getConnection();
			String sql;
			if(Suozaiciku==4){
				sql = "select count(*) from info_important_cet4 where count=0 and UserAccount=?";
			}
			else{
				sql = "select count(*) from info_important_cet6 where count=0 and UserAccount=?";
			}
			 
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, UserAccount);
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
	public String change(String UserAccount,int Suozaiciku){                    //点击查看后，所有反馈都变为已读
		java.sql.Connection conn=null;
		try{
			conn=DBUtil.getConnection();
			String sql;
			if(Suozaiciku==4){
			  sql = "update info_important_cet4 set count=1 and UserAccount=?";
			}
			else{
			  sql = "update info_important_cet6 set count=1 and UserAccount=?";
			}
			
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, UserAccount);
			pst.executeUpdate();
			pst.close();
			return "success";
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	

}
