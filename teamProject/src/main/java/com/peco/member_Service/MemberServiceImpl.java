package com.peco.member_Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peco.Member_VO.MemberVO;
import com.peco.member_Mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	MemberMapper memberMapper;
	
	@Override
	public MemberVO getOne(String id) {
		return memberMapper.member_getOne(id);
	}
	
	@Override
	public int update(MemberVO vo) {
		return memberMapper.update(vo);
	}


}
