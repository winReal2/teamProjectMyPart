package com.peco.Service;

import org.springframework.stereotype.Service;

import com.peco.VO.PensionVO;

@Service
public interface PensionService {

	public PensionVO getOne_P(String m_id);
	
	public int update_P(PensionVO vo);
}
