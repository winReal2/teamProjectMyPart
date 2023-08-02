package com.peco.Service;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.peco.VO.FUploadVO;

@Service
public interface FUploadService {

	public int insert(FUploadVO vo);
	
	public int delete(@Param("m_id")String m_id, @Param("uuid") String uuid);
}
