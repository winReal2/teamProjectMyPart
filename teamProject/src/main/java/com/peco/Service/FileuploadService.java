package com.peco.Service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.peco.VO.FileuploadVO;

@Service
public interface FileuploadService {

	public int insert_Img(FileuploadVO vo);
	
	public int delete_Img(@Param("m_id")String m_id, @Param("uuid") String uuid);
	
	int Profileupload(List<MultipartFile> files, String m_id) throws Exception;
}
