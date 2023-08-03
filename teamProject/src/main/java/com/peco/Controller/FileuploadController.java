package com.peco.Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.peco.Service.FileuploadService;
import com.peco.Controller.FileuploadController;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/peco/*")
@Log4j
public class FileuploadController extends CommonRestController {

	
	
	//프로필사진 ==========================================================================	
		
	@Autowired
	FileuploadService service;
	

	public static final String ATTACHES_DIR = "c:\\upload\\";

	@GetMapping("/display")
		// 이미지를 화면에 보여줍니다
		public ResponseEntity<byte[]> display(String fileName) {
			log.info("=====fileName : " + fileName);
			
			try {
				// 파일 객체를 생성
				File file = new File(ATTACHES_DIR+fileName);
				HttpHeaders headers = new HttpHeaders();
				
				// 이미지 파일이 존재하면 파일을 이미지를 다운로드
				if(file.exists()) {
					// Mime타입을 설정
					headers.add("Content-Type",Files.probeContentType(file.toPath()));
					return new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
				}else {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				
			} catch (IOException e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		}

	
	//파일등록
	@PostMapping("/ProfileloadActionFetch")
	public @ResponseBody Map<String, Object> fileuploadActionFetch(List<MultipartFile> files, String m_id, RedirectAttributes rttr) throws Exception {
		log.info("fileuploadActionFetch");
		int insertRes = service.Profileupload(files, m_id);
		log.info("insertRes : " + insertRes);
		return responseMap("success", "프로필 사진 업로드되었습니다");
	}
	
		//파일삭제
		@GetMapping("/profile/{uuid}/{m_id}")
		public @ResponseBody Map<String, Object> delete(
								@PathVariable("uuid") String uuid
								, @PathVariable("m_id") String m_id){
			Map<String, Object> response = new HashMap();
			
			int res = service.delete_Img(m_id, uuid);
			
		    if (res > 0) {
		        response.put("success", true);
		        response.put("message", "프로필 사진이 삭제되었습니다.");
		    } else {
		        response.put("success", false);
		        response.put("message", "프로필 사진 삭제에 실패하였습니다.");
		    }

		    return response;

			}
}
