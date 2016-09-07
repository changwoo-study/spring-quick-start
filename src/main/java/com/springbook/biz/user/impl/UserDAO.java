package com.springbook.biz.user.impl;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.springbook.biz.common.JDBCUtil;
import com.springbook.biz.user.UserVO;

@Repository("userDAO")
public class UserDAO {

	private Connection       		conn = null;
	private PreparedStatement		stmt = null;
	private ResultSet						rs   = null;
	
	private final String USER_GET = "select * from users where id=? and password=?";
	
	public UserVO getUser(UserVO vo) {
		System.out.println("===> JDBC로 getUser() 기능 처리");
		UserVO user = null;
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_GET);
			stmt.setString(1, vo.getId());
			stmt.setString(2, vo.getPassword());
			rs = stmt.executeQuery();
			if(rs.next()) {
				user = new UserVO();
				user.setId(rs.getString("ID"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setName(rs.getString("NAME"));
				user.setRole(rs.getString("ROLE"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
		return user;
	}
}
