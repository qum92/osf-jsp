package com.osf.test.dao;

import java.util.List;

import com.osf.test.vo.PhotoBoardVO;

public interface PBoardDAO2 {
	
	public List<PhotoBoardVO> selectPBoardList();
	public PhotoBoardVO selectBoard(int pbNum);
	public int insertPBoard(PhotoBoardVO pBoard);
	
}
