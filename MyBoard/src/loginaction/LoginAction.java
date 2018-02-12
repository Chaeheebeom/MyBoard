package loginaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import myboarddao.LoginDAO;
import myboardvo.UsertblVO;

public class LoginAction implements Action {

	private String path;
	
	public LoginAction(String path) {
		super();
		this.path = path;
	}

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) {
		
		String id=request.getParameter("id");
		String passwd=request.getParameter("passwd");
		
		LoginDAO dao=new LoginDAO();
		UsertblVO vo=dao.login(id, passwd);
		
		if(vo!=null) {
			HttpSession session=request.getSession();
			session.setMaxInactiveInterval(36000);
			session.setAttribute("Info", vo);
			path+="?code=mainPage";
		}else
			path="error_page?code=로그인오류";
		
		return new ActionForward(path, true);
	}

}
