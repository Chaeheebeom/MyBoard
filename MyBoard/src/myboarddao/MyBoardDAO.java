package myboarddao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import java.util.concurrent.ExecutionException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import board.vo.BoardVO;
import myboardvo.MyBoardVO;

public class MyBoardDAO {

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
	//전부다 가져오가
	public Vector<MyBoardVO> getList(){
		Vector<MyBoardVO> vec=new Vector<>();
		try {
			con=getConnection();
			String sql="select * from myboard order by board_re_ref desc, board_rs_seq asc";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
			int user_prcode=rs.getInt(1);
			String user_id=rs.getString(2);
			int board_num=rs.getInt(3); 
			String user_name=rs.getString(4); 
			String board_pass=rs.getString(5); 
			String board_subject=rs.getString(6); 
			String board_content=rs.getString(7); 
			String board_ori_file=rs.getString(8);
			String board_re_file=rs.getString(9);
			int board_re_ref=rs.getInt(10);
			int board_re_lev=rs.getInt(11); 
			int board_rs_seq=rs.getInt(12); 
			int board_readcount=rs.getInt(13); 
			Date board_date=rs.getDate(14);
			MyBoardVO vo=new MyBoardVO(user_prcode, user_id, board_num, user_name, 
					board_pass, board_subject, board_content, board_ori_file, board_re_file, 
					board_re_ref, board_re_lev, board_rs_seq, board_readcount, board_date);
				vec.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs, pstmt, con);
		}return vec;
	}
	//번호에 맞는거 전부다 가져오가
		public MyBoardVO getView(int num){
			MyBoardVO vo=null;
			try {
				con=getConnection();
				String sql="select * from myboard where board_num=? order by board_re_ref desc, board_rs_seq asc";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, num);
				rs=pstmt.executeQuery();
				
				if(rs.next()) {
				int user_prcode=rs.getInt(1);
				String user_id=rs.getString(2);
				int board_num=rs.getInt(3); 
				String user_name=rs.getString(4); 
				String board_pass=rs.getString(5); 
				String board_subject=rs.getString(6); 
				String board_content=rs.getString(7); 
				String board_ori_file=rs.getString(8);
				String board_re_file=rs.getString(9);
				int board_re_ref=rs.getInt(10);
				int board_re_lev=rs.getInt(11); 
				int board_rs_seq=rs.getInt(12); 
				int board_readcount=rs.getInt(13); 
				Date board_date=rs.getDate(14);
				vo=new MyBoardVO(user_prcode, user_id, board_num, user_name, 
						board_pass, board_subject, board_content, board_ori_file, board_re_file, 
						board_re_ref, board_re_lev, board_rs_seq, board_readcount, board_date);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				close(rs, pstmt, con);
			}return vo;
		}
	//삽입
		public int insertArticle(MyBoardVO vo) {
			int num;
			
			try {
				con=getConnection();
				con.setAutoCommit(false);
				
				String sql="select max(board_num) from myboard"; //가장 높은번호 가져오기
				pstmt=con.prepareStatement(sql);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					num=rs.getInt(1)+1;
				}else {
					num=1;
				}
				
				sql="insert into myboard values(?,?,?,?,?,"
						+ "?,?,?,?,?,?,?,?,now())"; //now()현재시각
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, vo.getUser_prcode()); 
				pstmt.setString(2, vo.getUser_id());
				pstmt.setInt(3,num);
				pstmt.setString(4, vo.getUser_name());
				pstmt.setString(5, vo.getBoard_pass());
				pstmt.setString(6, vo.getBoard_subject());
				pstmt.setString(7, vo.getBoard_content());
				pstmt.setString(8,vo.getBoard_ori_file());
				pstmt.setString(9,vo.getBoard_re_file());
				pstmt.setInt(10, 0);
				pstmt.setInt(11, 0); 
				pstmt.setInt(12, 0); 
				pstmt.setInt(13, 0); 
				//pstmt.setDate(12, vo.getBoard_date());
				result=pstmt.executeUpdate();
				if(result>0){
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
		//조회수 증가
		public int addCount(int board_num) {
			try {
				con=getConnection();
				con.setAutoCommit(false);
				String sql="update myboard set board_readcount=board_readcount+1 where board_num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, board_num);
				result=pstmt.executeUpdate();
				if(result>0)
					con.commit();
			}catch(Exception e) {
				e.printStackTrace();
				try {
					con.rollback();
				}catch(Exception e2) {
					e2.printStackTrace();
				}
			}finally {
				close(rs, pstmt, con);
			}return result;
		}
		//삭제  
		public int delete(int board_num) {
			try {
				con=getConnection();
				con.setAutoCommit(false);
				String sql="delete from myboard where board_num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, board_num);
				result=pstmt.executeUpdate();
				if(result>0)
					con.commit();
			}catch(Exception e) {
				e.printStackTrace();
				try {
				con.rollback();
				}catch(Exception e2) {
					e2.printStackTrace();
				}
			}finally {
				close(pstmt, con);
			}
			return result;
		}
		
}
