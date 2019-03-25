package com.osf.test.dao.Impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.osf.test.dao.PBoardDAO;
import com.osf.test.db.DBCon;

public class PBoardDAOImpl implements PBoardDAO {
	private static final String INSERT_PBOARD = " insert into photo_board (pb_num, pb_title, pb_content,"
			+ " pb_credat, pb_cretim, pb_file_path, pb_real_path)"
			+ " values(seq_pb_num.nextval,?,?,to_char(sysdate, 'YYYYMMDD'),to_char(sysdate,'HH24MISS'),?,?)";
	private static final String SELECT_PBOARD_LIST = " select * from photo_board";
	private static final String SELECT_PBOARD = "select pb_title, pb_content, pb_credat, pb_cretim, pb_file_path, "
			+ " pb_real_path from photo_board";
	public int insertPBoard(Map<String, String> pBoard) {
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(INSERT_PBOARD);
			ps.setString(1, pBoard.get("pb_title"));
			ps.setString(2, pBoard.get("pb_content"));
			ps.setString(3, pBoard.get("pb_file_path"));
			ps.setString(4, pBoard.get("pb_real_path"));
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCon.close();
		}
		return 0;
	}
	public List<Map<String, String>> selectPBoardList() {
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(SELECT_PBOARD_LIST);
			List<Map<String,String>> pBoardList = new ArrayList();
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Map<String,String> pBoard = new HashMap<>();
				pBoard.put("pb_num", rs.getString("pb_num"));
				pBoard.put("pb_title", rs.getString("pb_title"));
				pBoard.put("pb_content", rs.getString("pb_content"));
				pBoard.put("pb_credat", rs.getString("pb_credat"));
				pBoard.put("pb_cretim", rs.getString("pb_cretim"));
				pBoard.put("pb_real_path", rs.getString("pb_real_path"));
				pBoard.put("pb_file_path", rs.getString("pb_file_path"));
				pBoardList.add(pBoard);
			}
			return pBoardList;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCon.close();
		}
		return null;
	}
	public static void main(String[] args) {
		PBoardDAO pbdao = new PBoardDAOImpl();
		System.out.println(pbdao.selectPBoardList());
	}

	public Map<String, String> selectPBoard(int pbNum) {
		String where = " where pb_num=?";
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(SELECT_PBOARD+where);
			ps.setInt(1, pbNum);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Map<String,String> p = new HashMap<>();
				p.put("pb_title", rs.getString("pb_title"));
				p.put("pb_content", rs.getString("pb_content"));
				p.put("pb_credat", rs.getString("pb_credat"));
				p.put("pb_cretim", rs.getString("pb_cretim"));
				p.put("pb_real_path", rs.getString("pb_real_path"));
				p.put("pb_file_path", rs.getString("pb_file_path"));
				return p;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCon.close();
		}
		
		return null;
	}
}
