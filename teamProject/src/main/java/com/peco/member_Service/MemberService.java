package com.peco.member_Service;

import org.springframework.stereotype.Service;

import com.peco.Member_VO.MemberVO;

@Service
public interface MemberService {
	
	int update(MemberVO vo);
	
	
}
