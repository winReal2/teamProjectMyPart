package com.peco.MemberDao;

import java.lang.reflect.Member;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.tomcat.jdbc.pool.ConnectionPool;

import com.peco.VO.MemberVO;

import oracle.net.ns.ConnectPacket;
import oracle.security.pki.ldap.ConnectionUtil;



public class MemberDao {

	public MemberVO login(MemberVO paramMember) {
		MemberVO member = null;
		
		String sql = 
				String.format("select * from P_member where id='%s'"
						, paramMember.getId());
		
		try (Connection conn = ConnectionUtil.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);){
			if(rs.next()) {
				String m_id = rs.getString(1);
				String id = rs.getString(2);
				String pw = rs.getString(3);
				String mName = rs.getString(4);
				String mPhone = rs.getString(5);
				String email = rs.getString(6);
				String nickname = rs.getString(7);
				String age = rs.getString(8);
				
				member = new MemberVO();
				member.setId(paramMember.getId());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return member;
	}
}
