package boardaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import myboarddao.MyBoardDAO;
import myboardvo.MyBoardVO;


public class BoardReplyAction implements Action {
	
	String path;
	public BoardReplyAction(String path) {
		this.path=path;
	}

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) {
		//hidden에 있는값
				int board_num=Integer.parseInt(request.getParameter("board_num"));
				int board_re_ref=Integer.parseInt(request.getParameter("board_re_ref"));
				int board_re_lev=Integer.parseInt(request.getParameter("board_re_lev"));
				int board_rs_seq=Integer.parseInt(request.getParameter("board_rs_seq"));
				String page=request.getParameter("page");
				//form으로 받아온 값
				String board_subject=request.getParameter("board_subject");
				String user_name=request.getParameter("board_name");
				String board_content=request.getParameter("board_content");
				String board_pass=request.getParameter("board_pass");
				String id=request.getParameter("id");
				int prcode=Integer.parseInt(request.getParameter("prcode"));
				
				MyBoardVO vo=new MyBoardVO(prcode, id, board_num, user_name, board_pass, 
						board_subject, board_content, board_re_ref, 
						board_re_lev, board_rs_seq);
						
				MyBoardDAO dao=new MyBoardDAO();
				int result=dao.boardReply(vo);
				
				if(result==0)
					path="error_page.jsp?code=작성실패"; //잘안될경우..
				else {
					path+="?page="+page;
				}
				
				return new ActionForward(path, true);
	}

}
