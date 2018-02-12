package myboardvo;

import java.sql.Date;
import java.util.Calendar;

public class MyBoardVO {

	private int user_prcode;
	private String user_id;
	private int board_num;//글번호
	private String user_name;//글쓴이
	private String board_pass;//비밀번호
	private String board_subject;//제목
	private String board_content;//내용
	private String board_ori_file;//원래파일(클라이언트)
	private String board_re_file;//바뀐 파일명(서버)
	private int board_re_ref;//답변글 달 때 참조 글 번호
	private int board_re_lev;//답변글 깊이
	private int board_rs_seq;//답변글 순서
	private int board_readcount;//조회수
	private Date board_date; //날짜
	
	
	public MyBoardVO(int user_prcode, String user_id, int board_num, String user_name, String board_pass,
			String board_subject, String board_content, String board_ori_file, String board_re_file, int board_re_ref,
			int board_re_lev, int board_rs_seq, int board_readcount, Date board_date) {
		super();
		this.user_prcode = user_prcode;
		this.user_id = user_id;
		this.board_num = board_num;
		this.user_name = user_name;
		this.board_pass = board_pass;
		this.board_subject = board_subject;
		this.board_content = board_content;
		this.board_ori_file = board_ori_file;
		this.board_re_file = board_re_file;
		this.board_re_ref = board_re_ref;
		this.board_re_lev = board_re_lev;
		this.board_rs_seq = board_rs_seq;
		this.board_readcount = board_readcount;
		this.board_date = board_date;
	}
	public MyBoardVO() {
		super();
	}
	
	public int getUser_prcode() {
		return user_prcode;
	}
	public void setUser_prcode(int user_prcode) {
		this.user_prcode = user_prcode;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getBoard_num() {
		return board_num;
	}
	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getBoard_pass() {
		return board_pass;
	}
	public void setBoard_pass(String board_pass) {
		this.board_pass = board_pass;
	}
	public String getBoard_subject() {
		return board_subject;
	}
	public void setBoard_subject(String board_subject) {
		this.board_subject = board_subject;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public String getBoard_ori_file() {
		return board_ori_file;
	}
	public void setBoard_ori_file(String board_ori_file) {
		this.board_ori_file = board_ori_file;
	}
	public String getBoard_re_file() {
		return board_re_file;
	}
	public void setBoard_re_file(String board_re_file) {
		this.board_re_file = board_re_file;
	}
	public int getBoard_re_ref() {
		return board_re_ref;
	}
	public void setBoard_re_ref(int board_re_ref) {
		this.board_re_ref = board_re_ref;
	}
	public int getBoard_re_lev() {
		return board_re_lev;
	}
	public void setBoard_re_lev(int board_re_lev) {
		this.board_re_lev = board_re_lev;
	}
	public int getBoard_rs_seq() {
		return board_rs_seq;
	}
	public void setBoard_rs_seq(int board_rs_seq) {
		this.board_rs_seq = board_rs_seq;
	}
	public int getBoard_readcount() {
		return board_readcount;
	}
	public void setBoard_readcount(int board_readcount) {
		this.board_readcount = board_readcount;
	}
	public Date getBoard_date() {
		return board_date;
	}
	public void setBoard_date(Date board_date) {
		this.board_date = board_date;
	}
	
	
	
}
