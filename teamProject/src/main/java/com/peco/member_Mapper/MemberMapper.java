package com.peco.member_Mapper;

import com.peco.Member_VO.MemberVO;

public interface MemberMapper{
	
	public MemberVO member_Profile(MemberVO vo);
	
	public int member_update(MemberVO vo);
}