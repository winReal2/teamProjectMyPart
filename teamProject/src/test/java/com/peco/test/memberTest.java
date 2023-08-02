package com.peco.test;

import static org.junit.Assert.assertEquals;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.peco.Mapper.FUploadMapper;
import com.peco.Mapper.MemberMapper;
import com.peco.Mapper.PensionMapper;
import com.peco.VO.FUploadVO;
import com.peco.VO.MemberVO;
import com.peco.VO.PensionVO;
import com.peco.test.memberTest;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class memberTest {
	
	@Autowired
	MemberMapper memberMapper;
	
	@Autowired
	PensionMapper pensionMapper;
	
	@Autowired
	FUploadMapper fUploadMapper;

//[Member Mapper test]=================================================================
	
	
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

//[Pension  Mapper test]====================================================================
	
	@Test
	public void getOne_P() {
		PensionVO vo = pensionMapper.getOne_P("m001");
		System.out.println("=====================" + vo);
		log.info(vo);
	}
	
	@Test
	public void update_P() {
		String m_id = "m001";
		String p_id = "p001";
		
		PensionVO vo = new PensionVO();
		
		vo.setP_id(p_id);
		vo.setM_id(m_id);
		vo.setPName("중앙별장");
		vo.setAddr("강원도 춘천시 남산면 중앙1길 56");
		vo.setOpenHour("15:00~11:00");
		vo.setParkYN("Y");
		vo.setCheckYN("Y");
		
		log.info(vo);

		int res = pensionMapper.update_P(vo);
		
		assertEquals(res, 1);
	}
	
//[FUpload  Mapper test]====================================================================
	
	@Test
	public void insert() {
		FUploadVO vo = new FUploadVO();
		
		vo.setUploadpath("uploadpath");
		vo.setFileName("fileName");
		vo.setFileType("fileType");
		vo.setM_id("m001");
		UUID uuid = UUID.randomUUID();
		vo.setUuid(uuid.toString());
		
		System.out.println("vo : " + vo);
		int res = fUploadMapper.insert(vo);
		System.out.println("res : " + res);
		assertEquals(1, res);
	}
	
	@Test
	public void delete() {
		FUploadVO vo = new FUploadVO();
		log.info("delete()");
		int res = fUploadMapper.delete("m001", "8dd1cc93-8447-4223-83d1-8961c8858768");
		assertEquals(1,	res);
	}
	
}
