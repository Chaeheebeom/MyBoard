package boardaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import myboarddao.MyBoardDAO;

public class BoardDeleteAction implements Action {

	private String path;
	
	public BoardDeleteAction(String path) {
		this.path=path;
	}
	
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) {
		int board_num=Integer.parseInt(request.getParameter("num"));
		String passwd=request.getParameter("passwd");
		
		MyBoardDAO dao=new MyBoardDAO();
		int result=dao.delete(board_num,passwd);
		if(result==0)
			path="error_page.jsp?code=삭제실패";
		return new ActionForward(path, true);
	}

}
