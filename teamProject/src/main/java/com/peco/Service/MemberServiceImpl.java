package com.peco.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peco.Mapper.MemberMapper;
import com.peco.VO.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	MemberMapper memberMapper;
	
	@Override
	public MemberVO getOne(String m_id) {
		return memberMapper.member_getOne(m_id);
	}
	
	@Override
	public int update(MemberVO vo) {
		return memberMapper.update(vo);
	}


}
