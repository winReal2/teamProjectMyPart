package com.peco.Mapper;

import com.peco.VO.MemberVO;

public interface MemberMapper{
	

	
	public MemberVO member_getOne(String m_id);
	
	public int update(MemberVO vo);
	

}
