package com.osf.test.service.impl;

import java.util.List;

import com.osf.test.dao.CommonCodeDAO;
import com.osf.test.dao.Impl.CommonCodeDAOImpl;
import com.osf.test.service.CommonCodeService;
import com.osf.test.vo.CommonCodeVO;

public class CommonCodeServiceImpl implements CommonCodeService {
	private CommonCodeDAO ccdao = new CommonCodeDAOImpl();
	
	public List<CommonCodeVO> selectCommonCodeList(String ccGroup) {
		return ccdao.selectCommonCodeList(ccGroup);
	}

}
