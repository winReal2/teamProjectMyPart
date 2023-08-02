package com.peco.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peco.Mapper.FUploadMapper;
import com.peco.VO.FUploadVO;

@Service
public class FUploadServiceImpl implements FUploadService{

	@Autowired
	FUploadMapper fUploadMapper;
	
	@Override
	public int insert(FUploadVO vo) {
		return fUploadMapper.insert(vo);
	}

	@Override
	public int delete(String m_id, String uuid) {
		return fUploadMapper.delete(m_id, uuid);
	}

	
}
