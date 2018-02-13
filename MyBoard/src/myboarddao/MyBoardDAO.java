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

import myboardvo.MyBoardVO;
import myboardvo.UsertblVO;

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
				pstmt.setInt(10, num);
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
		public int delete(int board_num,String board_pass) {
			try {
				con=getConnection();
				con.setAutoCommit(false);
				String sql="delete from myboard where board_num=? and board_pass=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, board_num);
				pstmt.setString(2, board_pass);
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
		//수정하기
		public int modify(MyBoardVO vo) {
			
			try {
				con=getConnection();
				con.setAutoCommit(false);
				if(vo.getBoard_ori_file()==null) {
					String sql="update myboard set board_subject=?, board_content=? "
							+ "where board_num=? AND "
							+ "board_pass=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1, vo.getBoard_subject());
					pstmt.setString(2, vo.getBoard_content());			
					pstmt.setInt(3, vo.getBoard_num());
					pstmt.setString(4, vo.getBoard_pass());
				}else {
				String sql="update myboard set board_subject=?, board_content=?, "
						+ "board_ori_file=?, board_re_file=? "
						+ "where board_num=? AND "
						+ "board_pass=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, vo.getBoard_subject());
				pstmt.setString(2, vo.getBoard_content());
				pstmt.setString(3, vo.getBoard_ori_file());
				pstmt.setString(4, vo.getBoard_re_file());			
				pstmt.setInt(5, vo.getBoard_num());
				pstmt.setString(6, vo.getBoard_pass());
				}
				result=pstmt.executeUpdate();
				if(result>0) {
					con.commit();
				}
			}catch(Exception e) {
				e.printStackTrace();
				try {
					con.rollback();
				}catch(Exception ew) {
					ew.printStackTrace();
				}
			}finally {
				close(pstmt, con);
			}
			return result;
		}
		//답변글 달기.
		public int boardReply(MyBoardVO vo) {
			
			int re_ref=vo.getBoard_re_ref();
			int re_lev=vo.getBoard_re_lev();
			int rs_seq=vo.getBoard_rs_seq();
			
			//최신글번호 가져오기
			String sql_num="select max(board_num) from myboard";
			int num=0;
			try {
				con=getConnection();
				pstmt=con.prepareStatement(sql_num);
				rs=pstmt.executeQuery();
				if(rs.next())
					num=rs.getInt(1)+1;
				else
					num=1;
				//번호가져오기 끝.
				//업데이트하기
				String sql="update myboard set board_rs_seq=board_rs_seq+1 "
						+" where board_re_ref=? and board_rs_seq>?";  //원본글seq보다 크고 ref랑 같은거 
							//seq없데이트
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, re_ref);
				pstmt.setInt(2, rs_seq);
				pstmt.executeUpdate();
				rs_seq++; //원본글에서 1씩 증가시켜 답변글에 삽입.
				re_lev++;
				//답변글 삽입하기.

				String ins_sql="insert into myboard(board_num,user_name,board_pass,board_content,"
						+ "board_subject, "
						+ " board_re_ref,board_re_lev,board_rs_seq,board_readcount,user_prcode,user_id,board_date)"
						+ " values(?,?,?,?,?,?,?,?,?,?,?,now())";
				pstmt=con.prepareStatement(ins_sql);
				pstmt.setInt(1, num);
				pstmt.setString(2, vo.getUser_name());
				pstmt.setString(3, vo.getBoard_pass());
				pstmt.setString(4, vo.getBoard_content());
				pstmt.setString(5, vo.getBoard_subject());
				pstmt.setInt(6, re_ref);
				pstmt.setInt(7, re_lev);
				pstmt.setInt(8, rs_seq);
				pstmt.setInt(9, 0);
				pstmt.setInt(10, vo.getUser_prcode());
				pstmt.setString(11, vo.getUser_id());
				result=pstmt.executeUpdate();
				
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {close(rs, pstmt, con);}
			return result;	
		}
		public int getRows() {
			int total_rows=0;
			try {
				con=getConnection();
				String sql="select count(*) from myboard";
				pstmt=con.prepareStatement(sql);
				rs=pstmt.executeQuery();
				if(rs.next())
					total_rows=rs.getInt(1);
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				close(rs, pstmt, con);
			}return total_rows;
		}
		public int getRows(UsertblVO vo) {
			int total_rows=0;
			try {
				con=getConnection();
				String sql="select count(*) from myboard where user_prcode=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, vo.getPrcode());
				rs=pstmt.executeQuery();
				if(rs.next())
					total_rows=rs.getInt(1);
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				close(rs, pstmt, con);
			}return total_rows;
		}
		public int getRows(String criti,String text) {
			int total_rows=0;
			try {
				con=getConnection();
				String sql="select count(*) from myboard where "+criti+" like ?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, "%"+text+"%");
				rs=pstmt.executeQuery();
				if(rs.next())
					total_rows=rs.getInt(1);
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				close(rs, pstmt, con);
			}return total_rows;
		}
		//번호 제목 작성자 날짜 조회수 가져오기.
		public Vector<MyBoardVO> getList(int page,int limit){
			//시작레코드번호구해야함.
			
			int start=(page-1)*10;
					
			Vector<MyBoardVO> list=new Vector<>();
			
			try {
				con=getConnection();
				String sql="select board_num, board_subject, user_name, board_date, board_readcount, "
						+"board_re_ref, board_re_lev, board_rs_seq, user_prcode, user_id"
						+ " from myboard order by board_re_ref desc, board_rs_seq asc"//desc오름차순 asc내림차순
						+ " limit ?,?";//limit [시작부터],[몇개까지]
				pstmt=con.prepareStatement(sql);
					pstmt.setInt(1, start);
					pstmt.setInt(2, limit);
				rs=pstmt.executeQuery();
				while(rs.next()) {
					MyBoardVO vo=new MyBoardVO();
					vo.setBoard_num(rs.getInt(1));
					vo.setBoard_subject(rs.getString(2));
					vo.setUser_name(rs.getString(3));
					vo.setBoard_date(rs.getDate(4));
					vo.setBoard_readcount(rs.getInt(5));
					vo.setBoard_re_ref(rs.getInt(6));
					vo.setBoard_re_lev(rs.getInt(7));
					vo.setBoard_rs_seq(rs.getInt(8));
					vo.setUser_prcode(rs.getInt(9));
					vo.setUser_id(rs.getString(10));
					list.add(vo);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				close(rs, pstmt, con);
			}
			return list;
		}
		//선택한 거 검색하기..
		public Vector<MyBoardVO> getSearch(int page,int limit,String criti,String text){
			Vector<MyBoardVO> list=new Vector<>();
			
			int start=(page-1)*10; 
			
			try {
				con=getConnection();
				String sql="select board_num, board_subject, user_name, board_date, board_readcount"
						+ " ,board_re_lev, user_prcode, user_id from myboard where "+criti+" like ? "
						+ "order by board_re_ref desc, board_rs_seq asc"
						+ " limit ?,? ";
				//속성이름 like %값%   값이 포함하는 것을 다 검색. 
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, "%"+text+"%");
				pstmt.setInt(2, start);
				pstmt.setInt(3, limit);
				rs=pstmt.executeQuery();
				while(rs.next()) {
					MyBoardVO vo=new MyBoardVO();
					vo.setBoard_num(rs.getInt(1));
					vo.setBoard_subject(rs.getString(2));
					vo.setUser_name(rs.getString(3));
					vo.setBoard_date(rs.getDate(4));
					vo.setBoard_readcount(rs.getInt(5));
					vo.setBoard_re_lev(rs.getInt(6));
					vo.setUser_prcode(rs.getInt(7));
					vo.setUser_id(rs.getString(8));
					list.add(vo);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				close(rs, pstmt, con);
			}		
			return list;
		}
		
		//전부다 가져오가
		public Vector<MyBoardVO> getMyboard(UsertblVO vo,int page,int limit){
			Vector<MyBoardVO> vec=new Vector<>();
			int start=(page-1)*10; 
			try {
				con=getConnection();
				String sql="select * from myboard where user_prcode=? order by board_re_ref desc, board_rs_seq asc"
						+ " limit ?,?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, vo.getPrcode());
				pstmt.setInt(2, start);
				pstmt.setInt(3, limit);
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
				MyBoardVO vo2=new MyBoardVO(user_prcode, user_id, board_num, user_name, 
						board_pass, board_subject, board_content, board_ori_file, board_re_file, 
						board_re_ref, board_re_lev, board_rs_seq, board_readcount, board_date);
					vec.add(vo2);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				close(rs, pstmt, con);
			}return vec;
		}
		
}
