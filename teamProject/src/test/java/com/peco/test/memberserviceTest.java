package com.peco.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.peco.Member_VO.MemberVO;
import com.peco.member_Mapper.MemberMapper;
import com.peco.member_Service.MemberService;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class memberserviceTest {
	
	@Autowired
	MemberService memberService;
	
	@Test
	public void getOne() {
		MemberVO vo = memberService.getOne("user01");
		System.out.println("=====================" + vo);
		log.info(vo);
	}
}
