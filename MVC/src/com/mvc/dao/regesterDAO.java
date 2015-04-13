package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mvc.dto.regesterDTO;

public class regesterDAO {

	//DB연결
		//CRUD 작업
		// method 공통 사용 ....
		// 초기자 { } static 초기자 : static{ }
			static DataSource ds;
			Connection conn;
			PreparedStatement pstmt;
			ResultSet rs;

			static {
				InitialContext ctx;
				try {
					ctx = new InitialContext();
					Context envCtx = (Context) ctx.lookup("java:comp/env");
					ds = (DataSource) envCtx.lookup("/jdbc/oracle");
					System.out.println("DataSource 객체 생성 성공 !");
				} catch (NamingException e) {
					System.out.println("lookup Fail : " + e.getMessage());
				}
			}
			//=======연결 작업 셋팅 끝=============
			
			//회원 가입 추가
			
			public int regesterok(regesterDTO dtoin) throws SQLException{
				
				try{
				conn = ds.getConnection();
				String sql = "insert into mvcregister(id,pwd,email) values(?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dtoin.getId());
				pstmt.setString(2, dtoin.getPwd());
				pstmt.setString(3, dtoin.getEmail());
				
				
				
				return pstmt.executeUpdate();
				
				}finally{
					if(pstmt != null) pstmt.close();
					if(conn != null) pstmt.close();
				}
				
			}
	
}
