package com.osf.test.service.impl;

import java.util.List;

import com.osf.test.dao.PBoardDAO2;
import com.osf.test.dao.Impl.PBoardDAO2Impl;
import com.osf.test.service.PBoardService2;
import com.osf.test.vo.PhotoBoardVO;

public class PBoardServiceImpl2 implements PBoardService2 {
	PBoardDAO2 pdao = new PBoardDAO2Impl();

	public List<PhotoBoardVO> selectPBoardList() {
		return pdao.selectPBoardList();
	}


	public PhotoBoardVO selectBoard(int pbNum) {

		return pdao.selectBoard(pbNum);
	}


	public int insertPBoard(PhotoBoardVO pBoard) {

		return pdao.insertPBoard(pBoard);
	}

}
