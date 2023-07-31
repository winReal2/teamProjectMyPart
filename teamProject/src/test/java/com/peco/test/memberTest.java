package com.peco.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.peco.Member_VO.MemberVO;
import com.peco.member_Mapper.MemberMapper;
import com.peco.test.memberTest;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class memberTest {
	
	@Autowired
	MemberMapper memberMapper;

	@Test
	public void getOne() {
		MemberVO vo = memberMapper.member_getOne("user01");
		System.out.println("=====================" + vo);
		log.info(vo);
	}
	
	@Test
	public void update() {
		String m_id = "m001";
		
		MemberVO vo = new MemberVO();
		
		vo.setM_id(m_id);
		vo.setPw("1234");
		vo.setMName("수정맨");
		vo.setMPhone("010-123-4567");
		vo.setEmail("update@naver.com");
		vo.setNickname("updateKim");
		vo.setAge(30);
		
		log.info(vo);

		int res = memberMapper.update(vo);
		
		assertEquals(res, 1);
	}
	
}
