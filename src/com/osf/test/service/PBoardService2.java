package com.osf.test.service;

import java.util.List;

import com.osf.test.vo.PhotoBoardVO;

public interface PBoardService2 {
	
	public List<PhotoBoardVO> selectPBoardList();
	public PhotoBoardVO selectBoard(int pbNum);
	public int insertPBoard(PhotoBoardVO pBoard);

}
