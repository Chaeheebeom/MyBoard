package boardaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import myboarddao.MyBoardDAO;
import myboardvo.MyBoardVO;

public class MyBoardHitUpdateAction implements Action {

	String path;
	
	public MyBoardHitUpdateAction(String path) {
		this.path=path;
	}

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) {
		MyBoardDAO dao=new MyBoardDAO();
		int board_num=Integer.parseInt(request.getParameter("num"));
		MyBoardVO vo=dao.getView(board_num);
		int result=dao.addCount(board_num);
		
		String page=request.getParameter("page");
		if(result>0) //board_num을 넘겨주기
			path+="?num="+board_num+"&page="+page;
		else
			path="error_page?code=읽기실패"; //에러페이지 설정하기.
		
		return new ActionForward(path, true);
	}

}
