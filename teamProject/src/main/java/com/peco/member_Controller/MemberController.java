package com.peco.member_Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.peco.Member_VO.MemberVO;
import com.peco.member_Service.MemberService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/member/*")
@Log4j
public class MemberController {

	@Autowired
	MemberService service;
	
	@GetMapping("profile")
	public String getOne(Model model, MemberVO vo) {
		try {
			log.info("======================= id" + vo);
			if(vo.getId().equals("") ) {
				// TODO 메세지 처리
				return "";
			}
			MemberVO member = service.getOne(vo.getId());
			model.addAttribute("member", member);
			
			return "/member/profile";
			
		} catch (Exception e) {
			//TODO 메세지 처리하기
			return "";
		}
	}
	
	@GetMapping("css")
	public String css(){
		return "member/css";
	}
}
