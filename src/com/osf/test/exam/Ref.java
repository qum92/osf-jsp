package com.osf.test.exam;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.osf.test.db.DBCon;

public class Ref {

	public static void main(String[] args) {
		String sql = "select * from photo_board";
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			List<Map<String,String>> colList = new ArrayList();
			for(int i=1;i<=rsmd.getColumnCount();i++) {
				String colName = rsmd.getColumnLabel(i);
				Map<String,String> col = new HashMap();
				colName = colName.toLowerCase();
				int idx = colName.indexOf("_");
				int idxs = colName.indexOf("_",idx+1);
				if(idxs!=-1) {
					String str1 = colName.substring(0,idx);
					String str2 = colName.substring(idx+1,idxs);
					String str3 = colName.substring(idxs+1);
					System.out.println(str1);
					System.out.println(str2);
					System.out.println(str3);
				}
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
