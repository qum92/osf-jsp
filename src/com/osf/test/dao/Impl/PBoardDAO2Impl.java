package com.osf.test.dao.Impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.osf.test.dao.PBoardDAO2;
import com.osf.test.db.DBCon;
import com.osf.test.vo.PhotoBoardVO;

public class PBoardDAO2Impl implements PBoardDAO2 {

	private static final String SELECT_PBOARD_LIST = "select pb_num, pb_title, pb_content, pb_credat, pb_cretim, pb_file_path, "
			+ " pb_real_path from photo_board";
	private static final String SELECT_PBOARD = "select pb_num, pb_title, pb_content, pb_credat, pb_cretim, pb_file_path, "
			+ " pb_real_path from photo_board";
	private static final String INSERT_PBOARD = "insert into photo_board (pb_num, pb_title, pb_content, pb_credat, pb_cretim, "
			+ " pb_file_path, pb_real_path"
			+ " values(seq_pb_num.nextval, ?,?,to_char(sysdate,'YYYYMMDD'),to_char(sysdate,'HH24MISS'),?,?)";
	public List<PhotoBoardVO> selectPBoardList() {
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(SELECT_PBOARD_LIST);
			List<PhotoBoardVO> pList = new ArrayList();
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				PhotoBoardVO pb = new PhotoBoardVO();
				pb.setPbNum(rs.getInt("pb_num"));
				pb.setPbTitle(rs.getString("pb_title"));
				pb.setPbContent(rs.getString("pb_content"));
				pb.setPbCredat(rs.getString("pb_credat"));
				pb.setPbCretim(rs.getString("pb_cretim"));
				pb.setPbFilePath(rs.getString("pb_file_path"));
				pb.setPbRealPath(rs.getString("pb_real_path"));
				pList.add(pb);
			}
			return pList;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCon.close();
		}
		return null;
	}


	public PhotoBoardVO selectBoard(int pbNum) {
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(SELECT_PBOARD);
			ps.setInt(1, pbNum);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				PhotoBoardVO pb = new PhotoBoardVO();
				pb.setPbTitle(rs.getString("pb_title"));
				pb.setPbContent(rs.getString("pb_content"));
				pb.setPbCredat(rs.getString("pb_credat"));
				pb.setPbCretim(rs.getString("pb_cretim"));
				pb.setPbFilePath(rs.getString("pb_file_path"));
				pb.setPbRealPath(rs.getString("pb_real_path"));
				return pb;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCon.close();
		}
		return null;
	}


	public int insertPBoard(PhotoBoardVO pBoard) {
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(INSERT_PBOARD);
			ps.setString(1, pBoard.getPbTitle());
			ps.setString(2, pBoard.getPbContent());
			ps.setString(3, pBoard.getPbFilePath());
			ps.setString(4, pBoard.getPbRealPath());
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCon.close();
		}
		return 0;
	}

}
