package com.peco.member_Service;

import org.springframework.stereotype.Service;

import com.peco.Member_VO.MemberVO;

@Service
public interface MemberService {
	
	public MemberVO getOne(String id);
	
	public int update(MemberVO vo);



	
	
}
