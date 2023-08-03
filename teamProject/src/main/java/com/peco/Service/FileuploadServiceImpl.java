package com.peco.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.peco.Mapper.FileuploadMapper;
import com.peco.VO.FileuploadVO;
import com.peco.Controller.FileuploadController;
import com.peco.Service.FileuploadServiceImpl;
import com.peco.VO.FileuploadVO;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnails;

@Service
@Log4j
public class FileuploadServiceImpl implements FileuploadService{

	@Autowired
	FileuploadMapper fileuploadMapper;
	
	@Override
	public int insert_Img(FileuploadVO vo) {
		return fileuploadMapper.insert_Img(vo);
	}

	@Override
	public int delete_Img(String m_id, String uuid) {
		return fileuploadMapper.delete_Img(m_id, uuid);
	}
	
	
	/**
	 * 첨부파일 저장및 데이터 베이스에 등록
	 * @param files
	 * @param bno
	 * @return
	 * @throws Exception 
	 */
	public int Profileupload(List<MultipartFile> files,String m_id) throws Exception {
		int insertRes = 0;
		for(MultipartFile file : files) {
			if(file.isEmpty()) {
				continue;
			}
			log.info("=====================================");
			log.info("oFileName : "+file.getOriginalFilename());
			log.info("name : "+file.getName());
			log.info("size : "+file.getSize());
			
			
			try {
				
				String saveFileName = m_id+'_'+file.getOriginalFilename();
				String uploadPath = getProfile();
				
				File sFile = new File(FileuploadController.ATTACHES_DIR
						+uploadPath 
						+saveFileName);
				
				// file(원본파일)을 sFile(저장 대상 파일)에 저장
				file.transferTo(sFile);
				
				//주어진 파일의 Mine유형
				String contentType = Files.probeContentType(sFile.toPath());
				FileuploadVO vo = new FileuploadVO();
				
				// Mine타입을 확인하여 이미지인 경우 썸네일을 생성
				if(contentType.startsWith("image")) {
					vo.setFileType("I");
					
					// 썸네일 생성 경로
					String thmbnail = FileuploadController.ATTACHES_DIR
									+uploadPath 
									+"s_"
									+saveFileName;
					
					// 썸네일 생성
					// 원본파일.크기.저장될 경로
					Thumbnails.of(sFile).size(100, 100).toFile(thmbnail);
				}else {
					vo.setFileType("F");
				}
				
				vo.setM_id(m_id);
				vo.setFileName(file.getOriginalFilename());
				vo.setUploadpath(uploadPath);
				
				int res = insert_Img(vo);
				
				if(res>0) {
					insertRes++;
				}
			} catch (IllegalStateException e) {
				e.printStackTrace();
				throw new Exception("첨부파일 등록중 예외사항이 발생 하였습니다.(IllegalStateException)");
			} catch (IOException e) {
				e.printStackTrace();
				throw new Exception("첨부파일 등록중 예외사항이 발생 하였습니다.(IOException)");
			} catch(Exception e) {
				e.printStackTrace();
				throw new Exception("첨부파일 등록중 예외사항이 발생 하였습니다.(Exception)");
			}
		}
		return insertRes;
	}
	
	// 중복 방지용
	//		업로드 날짜를 폴더 이름으로 사용
	public String getProfile() {
		String uploadPath = "profile" + File.separator;
		log.info("경로 : " + uploadPath);
		
		File saveDir = new File(FileuploadController.ATTACHES_DIR + uploadPath);
		if(!saveDir.exists()) {
			if(saveDir.mkdirs()) {
				log.info("폴더 생성!!");
			}else {
				log.info("폴더 생성 실패!!");
			}
		}
		
		return uploadPath;
	}
	
}
