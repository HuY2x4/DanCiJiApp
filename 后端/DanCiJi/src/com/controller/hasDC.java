package com.controller;

import java.sql.SQLException;

import com.util.DBUtil;

public class hasDC {

	public void addHasDC(String name){
		java.sql.Connection conn = null;
		try {
			int gongneng=3;
			int siliuji=6;
			int xiaociku=1;
			int yanse=0;
			int i=0;
			int max=264;
			String sql;
			
				
			sql="insert into info_hasck(userid,gongneng,siliuji,xiaociku,yanse) values(?,?,?,?,?)";
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			for(gongneng=1;gongneng<=3;gongneng++){
				siliuji=4;
				max=150;
				for(xiaociku=1;xiaociku<=max;xiaociku++){
					pst.setString(1, name);
					pst.setInt(2, gongneng);
					pst.setInt(3, siliuji);
					pst.setInt(4, xiaociku);
					pst.setInt(5, yanse);
					pst.addBatch();
				}
				
			}
			
			for(gongneng=1;gongneng<=3;gongneng++){
				siliuji=6;
				max=264;
				for(xiaociku=1;xiaociku<=max;xiaociku++){
					pst.setString(1, name);
					pst.setInt(2, gongneng);
					pst.setInt(3, siliuji);
					pst.setInt(4, xiaociku);
					pst.setInt(5, yanse);
					pst.addBatch();
				}
				
			}
			pst.executeBatch();
			conn.commit();
			System.out.println("All down "); 
			conn.close();
			
			System.out.print("out6");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	public void resHasDC(String name){
		java.sql.Connection conn = null;
		try {
			int gongneng=3;
			int siliuji=6;
			int xiaociku=1;
			int yanse=0;
			int i=0;
			int max=264;
			String sql;
			
				
			sql="update  info_hasck set yanse=0 where userid=? and gongneng=? and siliuji=? and xiaociku=?";
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			for(gongneng=1;gongneng<=3;gongneng++){
				siliuji=4;
				max=150;
				for(xiaociku=1;xiaociku<=max;xiaociku++){
					pst.setString(1, name);
					pst.setInt(2, gongneng);
					pst.setInt(3, siliuji);
					pst.setInt(4, xiaociku);
					
					pst.addBatch();
				}
				
			}
			
			for(gongneng=1;gongneng<=3;gongneng++){
				siliuji=6;
				max=264;
				for(xiaociku=1;xiaociku<=max;xiaociku++){
					pst.setString(1, name);
					pst.setInt(2, gongneng);
					pst.setInt(3, siliuji);
					pst.setInt(4, xiaociku);
				
					pst.addBatch();
				}
				
			}
			pst.executeBatch();
			conn.commit();
			System.out.println("All down "); 
			conn.close();
			
			System.out.print("out6");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public int[] chaHasDC(String name,int gongneng,int siliuji){
		java.sql.Connection conn = null;
		int[] a=new int[300];
		try {
			
			int xiaociku=1;
			int yanse=0;
			int i=0;
			
			int max=264;
			String sql;
			if(siliuji==4){
				max=150;
			}
			else if(siliuji==6){
				max=264;
			}
			sql="select  yanse from info_hasck where userid=? and gongneng=? and siliuji=?";
			conn=DBUtil.getConnection();
			
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			
			pst.setString(1, name);
			pst.setInt(2, gongneng);
			pst.setInt(3, siliuji);
					
			java.sql.ResultSet rs=pst.executeQuery();
			
			while(rs.next()){
				a[i]=rs.getInt(1);
				
				i++;
			}
			System.out.println("!!"+a[0]+"!");
			System.out.println("All down "); 
			conn.close();
			
			System.out.print("out6");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return a;
	}
	
	
	
	public void updHasDC(String name,int gongneng,int siliuji,int xiaociku,int yanse){
		java.sql.Connection conn = null;
		try {						
			String sql;
			
			sql="update  info_hasck set yanse=? where userid=? and gongneng=? and siliuji=? and xiaociku=?";
			conn=DBUtil.getConnection();
			
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			
			pst.setInt(1, yanse);
			pst.setString(2, name);
			pst.setInt(3, gongneng);
			pst.setInt(4, siliuji);
			pst.setInt(5, xiaociku);
					
		    pst.execute();
			
			
			
			System.out.println("All down "); 
			conn.close();
			
			System.out.print("out6");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
	}
}
