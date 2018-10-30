package com.controller;

import java.sql.SQLException;

import com.util.DBUtil;

public class zongfenPHB {

	public String[] chaPM(String leibie){
		String[] PM=new String[100];
		java.sql.Connection conn=null;
		try{
			conn=DBUtil.getConnection();
			String sql="";
			if(leibie.equals("四级看词选意")){
				 sql="SELECT UserAccount,ZongFen4_1  FROM info_user  ORDER BY ZongFen4_1 desc ";
			}
			else if(leibie.equals("四级看意选词")){
				 sql="SELECT UserAccount,ZongFen4_2  FROM info_user  ORDER BY ZongFen4_2 desc ";
			}
			else if(leibie.equals("六级看词选意")){
				 sql="SELECT UserAccount,ZongFen6_1  FROM info_user  ORDER BY ZongFen6_1 desc ";
			}
			else if(leibie.equals("六级看意选词")){
				 sql="SELECT UserAccount,ZongFen6_2  FROM info_user  ORDER BY ZongFen6_2 desc ";
			}
			System.out.println("Msql:"+sql);
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			System.out.println("M2:");
			pst=conn.prepareStatement(sql);
			System.out.println("M3:");
			java.sql.ResultSet rs=pst.executeQuery();
			System.out.println("M4:");
			int i=0;
			while(rs.next()){
				System.out.println("M1:"+rs.getString(1)+"M2M"+rs.getInt(2));
				PM[i]=rs.getString(1)+";"+Integer.toString(rs.getInt(2));
				System.out.println("M12:"+rs.getString(1)+"M2M"+Integer.toString(rs.getInt(2)));
				System.out.println("M50:"+PM[0]);
				i++;
			}
			System.out.println("M5:"+PM[0]);
			rs.close();
			pst.close();
			conn.close();
			
			return PM;
		}catch(SQLException e){
				e.printStackTrace();
			}
		
		
		return PM;
	}
}
