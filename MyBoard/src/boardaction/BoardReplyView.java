package boardaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import myboarddao.MyBoardDAO;
import myboardvo.MyBoardVO;


public class BoardReplyView implements Action {
	String path;
	
	public BoardReplyView(String path) {
		this.path=path;
	}
	
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) {
		int board_num=Integer.parseInt(request.getParameter("num"));
		String page=request.getParameter("page");
		MyBoardDAO dao=new MyBoardDAO();
		MyBoardVO vo=dao.getView(board_num);
		
		if(vo!=null) {
			request.setAttribute("vo", vo);
			request.setAttribute("page", page);
		}
		
		return new ActionForward(path, false);
	}

}
