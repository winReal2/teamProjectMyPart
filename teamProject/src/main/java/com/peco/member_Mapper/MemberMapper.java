package com.peco.member_Mapper;

import com.peco.Member_VO.MemberVO;

public interface MemberMapper{
	

	
	public MemberVO member_getOne(String id);
	
	public int update(MemberVO vo);

}
