package boardaction;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import myboarddao.MyBoardDAO;
import myboardvo.MyBoardVO;


public class BoardListAction implements Action {

	String path;
	
	public BoardListAction(String path) {
		this.path=path;
	}
	
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) {
		MyBoardDAO dao=new MyBoardDAO();
		Vector<MyBoardVO> vec=dao.getList();
		
		request.setAttribute("list", vec);
		
		return new ActionForward(path, false);
	}

}
