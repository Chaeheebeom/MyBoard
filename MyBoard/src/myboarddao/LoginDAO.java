package myboarddao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import myboardvo.UsertblVO;



public class LoginDAO {
	
	Connection con=null;
	DataSource ds=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	int result=0;
	
	public Connection getConnection() {
		
		try {
			Context ctx=new InitialContext();
			ds=(DataSource) ctx.lookup("java:comp/env/jdbc/MySQL");
			con=ds.getConnection();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public void close(ResultSet rs,PreparedStatement pstmt, Connection con) {
		try {
		if(rs!=null)
			rs.close();
		if(pstmt!=null)
			pstmt.close();
		if(con!=null)
			con.close();
		}catch(Exception e) {e.printStackTrace();}
	}
	
	public void close(PreparedStatement pstmt, Connection con) {
		try {
		if(pstmt!=null)
			pstmt.close();
		if(con!=null)
			con.close();
		}catch(Exception e) {e.printStackTrace();}
	}
	//로그인하기
	public UsertblVO login(String id,String passwd) {
		UsertblVO vo=new UsertblVO();
		try {
			con=getConnection();
			String sql="select * from userstbl where id=? and passwd=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setString(2, passwd);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				vo.setPrcode(rs.getInt(1));
				vo.setId(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setPasswd(rs.getString(4));
				vo.setPhonnum(rs.getString(5));
			}			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs, pstmt, con);
		}return vo;
	}
	//계정추가
	public int userInsert(UsertblVO vo) {
		try {
			con=getConnection();
			con.setAutoCommit(false);
			String sql="insert into userstbl(id,name,passwd,phonnum) values(?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getPasswd());
			pstmt.setString(4, vo.getPhonnum());
			result=pstmt.executeUpdate();
			if(result>0) {
				con.commit();
			}
		}catch(Exception e) {
			e.printStackTrace();
			try {
			con.rollback();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}finally {
			close(pstmt, con);
		}return result;
	}
	//정보다가져오기
	
	
}
