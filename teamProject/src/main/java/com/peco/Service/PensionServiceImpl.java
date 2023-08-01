package com.peco.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peco.Mapper.PensionMapper;
import com.peco.VO.PensionVO;

@Service
public class PensionServiceImpl implements PensionService{

	@Autowired
	PensionMapper pensionMapper;

	@Override
	public PensionVO getOne_P(String m_id) {
		return pensionMapper.getOne_P(m_id);
	}

	@Override
	public int update_P(PensionVO vo) {
		return pensionMapper.update_P(vo);
	}
	


}
