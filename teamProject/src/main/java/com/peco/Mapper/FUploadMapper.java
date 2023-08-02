package com.peco.Mapper;

import org.apache.ibatis.annotations.Param;

import com.peco.VO.FUploadVO;

public interface FUploadMapper {

	public int insert(FUploadVO vo);
	
	public int delete(@Param("m_id")String m_id, @Param("uuid") String uuid);
	
	
}
