package boardaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import myboarddao.MyBoardDAO;
import myboardvo.MyBoardVO;
import myboardvo.UsertblVO;

public class BoardViewAction implements Action {
	private String path;
	
	public BoardViewAction(String path) {
		super();
		this.path = path;
	}

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) {
		int num=Integer.parseInt(request.getParameter("num"));
		String page=request.getParameter("page");
		MyBoardDAO dao=new MyBoardDAO();
		MyBoardVO vo=dao.getView(num);
		
		if(vo!=null) {
			request.setAttribute("vo", vo);
			request.setAttribute("page", page);
		}else {
			path="error_page.jsp?code=읽기실패";
		}
		return new ActionForward(path, false);
	}

}
