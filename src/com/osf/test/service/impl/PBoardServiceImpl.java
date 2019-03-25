package com.osf.test.service.impl;

import java.util.List;
import java.util.Map;

import com.osf.test.dao.PBoardDAO;
import com.osf.test.dao.Impl.PBoardDAOImpl;
import com.osf.test.service.PBoardService;

public class PBoardServiceImpl implements PBoardService {
	private PBoardDAO pbdao = new PBoardDAOImpl();
	
	public int insertPBoard(Map<String, String> pBoard) {
		return pbdao.insertPBoard(pBoard);
	}

	public List<Map<String, String>> selectPBoardList() {
		return pbdao.selectPBoardList();
	}


	public Map<String, String> selectPBoard(int pbNum) {
		return pbdao.selectPBoard(pbNum);
	}

}
