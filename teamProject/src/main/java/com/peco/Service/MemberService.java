package com.peco.Service;

import org.springframework.stereotype.Service;

import com.peco.VO.MemberVO;

@Service
public interface MemberService {
	
	public MemberVO getOne(String m_id);
	
	public int update(MemberVO vo);



	
	
}
