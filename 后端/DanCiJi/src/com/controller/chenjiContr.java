package com.controller;

import java.sql.SQLException;

import com.util.DBUtil;

public class chenjiContr {

	public void addCJ(String name){
		java.sql.Connection conn = null;
		try {
			int gongneng=1;
			int siliuji=4;
			int xiaociku=1;
			int fenshu=0;
			int i=0;
			int max=264;
			String sql;
			
				
			sql="insert into info_fenshu(name,gongneng,siliuji,xiaociku,fenshu) values(?,?,?,?,?)";
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			for(gongneng=1;gongneng<=2;gongneng++){
				siliuji=4;
				max=150;
				for(xiaociku=1;xiaociku<=max;xiaociku++){
					pst.setString(1, name);
					pst.setInt(2, gongneng);
					pst.setInt(3, siliuji);
					pst.setInt(4, xiaociku);
					pst.setInt(5, fenshu);
					pst.addBatch();
				}
				
			}
			
			for(gongneng=1;gongneng<=2;gongneng++){
				siliuji=6;
				max=264;
				for(xiaociku=1;xiaociku<=max;xiaociku++){
					pst.setString(1, name);
					pst.setInt(2, gongneng);
					pst.setInt(3, siliuji);
					pst.setInt(4, xiaociku);
					pst.setInt(5, fenshu);
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
	
	
	
	
	public void resCJ(String name){
		java.sql.Connection conn = null;
		try {
			int gongneng=3;
			int siliuji=6;
			int xiaociku=1;
			int fenshu=0;
			int i=0;
			int max=264;
			String sql;
			
				
			sql="update  info_fenshu set fenshu=0 where name=? and gongneng=? and siliuji=? and xiaociku=?";
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			for(gongneng=1;gongneng<=2;gongneng++){
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
			
			for(gongneng=1;gongneng<=2;gongneng++){
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
	
	
	
	public int[] chaCJ(String name,int gongneng,int siliuji){
		java.sql.Connection conn = null;
		int[] a=new int[300];
		try {
			
			int xiaociku=1;
			int fenshu=0;
			int i=0;
			
			int max=264;
			String sql;
			if(siliuji==4){
				max=150;
			}
			else if(siliuji==6){
				max=264;
			}
			sql="select  fenshu from info_fenshu where name=? and gongneng=? and siliuji=?";
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
			System.out.println("chenji  a[0]="+a[0]+"!");
			System.out.println("All down "); 
			conn.close();
			
			System.out.print("out6");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return a;
	}
	
	
	
	public void updCJ(String name,int gongneng,int siliuji,int xiaociku,int fenshu){
		java.sql.Connection conn = null;
		try {						
			String sql;
			
			sql="update  info_fenshu set fenshu=? where name=? and gongneng=? and siliuji=? and xiaociku=?";
			conn=DBUtil.getConnection();
			
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			
			pst.setInt(1, fenshu);
			pst.setString(2, name);
			pst.setInt(3, gongneng);
			pst.setInt(4, siliuji);
			pst.setInt(5, xiaociku);
					
		    pst.execute();
			
			
			
			System.out.println("mysql传入的分数："+fenshu); 
			conn.close();
			
			System.out.print("out6");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
	}
}
