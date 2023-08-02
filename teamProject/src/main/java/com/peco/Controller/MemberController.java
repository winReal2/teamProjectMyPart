package com.peco.Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.peco.Service.FUploadService;
import com.peco.Service.MemberService;
import com.peco.Service.PensionService;
import com.peco.VO.FUploadVO;
import com.peco.VO.MemberVO;
import com.peco.VO.PensionVO;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnails;

@Controller
@RequestMapping("/member/*")
@Log4j
public class MemberController {

	@Autowired
	MemberService service;
	
	@Autowired
	PensionService pensionService;

	@Autowired
	FUploadService fUploadService;
	
	
	@GetMapping("profile")
	public String getOne(HttpSession session, MemberVO vo) {
		try {
			log.info("======================= m_id" + vo);
			if(vo.getM_id().equals("") || vo.getM_id() == null ) {
				System.out.println("msg");
				return "msg";
			}
			
			MemberVO member = service.getOne(vo.getM_id());
			session.setAttribute("member", member);
			
			return "/member/profile";
			
		} catch (Exception e) {
			//TODO 메세지 처리하기
			return "";
		}
	}
	

	//수정페이지 이동
	@PostMapping("profile_Update")
	public String updatePage(Model model, HttpSession session) {
		
		MemberVO member = (MemberVO) session.getAttribute("member");
		    model.addAttribute("member", member);
  
	    return "/member/profile_Update";
	}
	
	
	//수정페이지에서 데이터 입력 후 저장
	@PostMapping("profile")
	public String updateSave(Model model, MemberVO vo) {
		
		//회원정보 업데이트
		service.update(vo);
		
		//업데이트된 회원정보 다시 조회
		MemberVO updateMember = service.getOne(vo.getM_id());
		
		//모델에 업데이트된 회원 정보 추가
		model.addAttribute("member", updateMember);
		
		return "/member/profile";
	}
	// int member = service.update(vo);
	// model.addAttribute("member", member);
	

	/*
	//TODO : 펜션매퍼,서비스, 컨트롤러  > 펜션을 등록한 회원 m_id와  펜션등록 테이블에 있는 m_id가 일치하면 펜션정보 화면에 출력할 수 있는 컨트롤러 작성
	//웹브라우저에서 원활하게 실행되려면 수정해야함 (삭제하니까 잘돌아감 - 세션이 연결안되서 그런것 같기도..)
	
	@PostMapping("/phProfile/{m_id}")
	public String phProfile(@PathVariable("m_id") String m_id, Model model) {
		
		//Member테이블에서 m_id기준으로 회원정보 조회
		MemberVO member = service.getOne(m_id);
		
		if(member==null||"".equals(member)) {
			//TODO : 회원정보가 존재하지 않을 경우 예외처리
			return ""; // 로그인 페이지로 이동
		}
		
	
		//Pension테이블에서 m_id기준으로 펜션정보 조회
		PensionVO pension = pensionService.getOne_P(m_id);
		if(pension==null||"".equals(pension)) {
			//TODO : 펜션정보가 존재하지 않을 경우 예외처리
			return ""; // 로그인 페이지로 이동			
		}
			
		//조회한 정보를 모델에 담아 뷰로 전달
		model.addAttribute("member", member);
		model.addAttribute("pension", pension);
		
		//pension 정보를 보여줄 jsp페이지
	    return "/member/phProfile"; 
	}
	
	*/
	
	//하나의 펜션 조회
	@GetMapping("phProfile")
	public String getOne_P(HttpSession session, PensionVO vo) {
		try {
			if(vo.getM_id()==null || vo.getM_id().equals("")) {
				//메세지 처리
				System.out.println("msg");
				return "";  
			}
			
			PensionVO pension = pensionService.getOne_P(vo.getM_id());
			session.setAttribute("pension", pension);
			
			return "/member/phProfile";
			
		} catch (Exception e) {
			// TODO: 메세지 처리
			return "";
		}
	}
	
	//펜션 수정페이지 이동
	@PostMapping("phProfile_Update")
	public String phProfileUpdatePage(Model model, HttpSession session) {
		
		PensionVO pension = (PensionVO) session.getAttribute("pension");
		    model.addAttribute("pension", pension);
  
	    return "/member/phProfile_Update";
	}
	
	// 펜션 수정페이지에서 데이터 입력 후 저장
	@PostMapping("phProfile")
	public String phProfileUpdateSave(Model model, PensionVO vo) {
		
		//펜션정보 업데이트
		pensionService.update_P(vo);
		
		//업데이트된 펜션정보 다시 조회
		PensionVO updatePension = pensionService.getOne_P(vo.getM_id());
		
		//모델에 업데이트된 회원 정보 추가
		model.addAttribute("pension", updatePension);
		
		return "/member/phProfile";
	}
	
	
//프로필사진 첨부 ==========================================================================	
	

	private static final String ATTACHES_DIR = "c:\\upload\\";
	/**
	 * 첨부파일 저장 및 데이터베이스에 등록
	 * @param files
	 * @param bno
	 * @return
	 */
	public int FUpload(List<MultipartFile> files, int bno) {
		
		int insertRes = 0;

		for(MultipartFile file : files) {
			
			//선택된 파일이 없는 경우 다음 파일로 이동
			if(file.isEmpty()) {
				continue;
			}
				log.info("oFileName : " + file.getOriginalFilename());
				log.info("name : " + file.getName());
				log.info("size : " + file.getSize());
			
				try {

					UUID uuid = UUID.randomUUID();
					String saveFileName =  
							uuid + "_" + file.getOriginalFilename();
					String uploadPath = getFolder();

					File sFile = new File(ATTACHES_DIR 
										+ uploadPath    //경로반환 (2023\07\18\) 
										+ saveFileName);
					
					//file(원본파일)을 sFile(저장할 대상 파일)에 저장  
					file.transferTo(sFile);
					
					FUploadVO vo = new FUploadVO();

					//★★★이미지 파일일 경우 썸네일
					// 주어진 파일의 Mime 유형을 확인
					String contentType = Files.probeContentType(sFile.toPath());
					String m_id = "m001";
					// Mime타입을 확인하여 이미지인 경우 썸네일을 생성
					if(contentType != null && contentType.startsWith("image")) {
						vo.setFileType("Img");
						
						//썸네일 생성 경로
						String thumbnail =  ATTACHES_DIR 
											+ uploadPath   
											+ "s_"
											+ saveFileName;
						//썸네일 생성
						//원본파일, 크기, 저장될 파일경로
						Thumbnails.of(sFile).size(100, 100).toFile(thumbnail);
					} else {
						vo.setFileType("Other");
					}
					
					vo.setM_id(m_id);
					vo.setFileName(file.getOriginalFilename());
					vo.setUploadpath(uploadPath);
					vo.setUuid(uuid.toString());
					
					int res = fUploadService.insert(vo);
					
					if(res>0) {
						insertRes++;
					}
					
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		return insertRes;
		}
	
	private String getFolder() {
		// TODO Auto-generated method stub
		return null;
	}


	//파일삭제
	@GetMapping("/profile/{uuid}/{m_id}")
	public @ResponseBody Map<String, Object> delete(
							@PathVariable("uuid") String uuid
							, @PathVariable("m_id") String m_id){
		Map<String, Object> response = new HashMap();
		
		int res = fUploadService.delete(m_id, uuid);
		
	    if (res > 0) {
	        response.put("success", true);
	        response.put("message", "프로필 사진이 삭제되었습니다.");
	    } else {
	        response.put("success", false);
	        response.put("message", "프로필 사진 삭제에 실패하였습니다.");
	    }

	    return response;

		}
	

	
	@GetMapping("css")
	public String css(){
		return "member/css";
	}
}
