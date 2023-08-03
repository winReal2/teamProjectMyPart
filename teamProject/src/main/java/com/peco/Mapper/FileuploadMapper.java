package com.peco.Mapper;

import org.apache.ibatis.annotations.Param;

import com.peco.VO.FileuploadVO;

public interface FileuploadMapper {

	public int insert_Img(FileuploadVO vo);
	
	public int delete_Img(@Param("m_id")String m_id, @Param("uuid") String uuid);
	
	
	
}
