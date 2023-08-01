package com.peco.Mapper;

import com.peco.VO.PensionVO;

public interface PensionMapper {
	
	public PensionVO getOne_P(String m_id);
	
	public int update_P(PensionVO vo);

}
