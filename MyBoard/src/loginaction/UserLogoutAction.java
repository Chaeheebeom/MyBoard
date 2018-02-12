package loginaction;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;

public class UserLogoutAction implements Action {

	String path;	
	
	public UserLogoutAction(String path) {
		super();
		this.path = path;
	}

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) {
		//세션존재하는지 확인하고 해제
		HttpSession session=request.getSession(false);	//세션이 없으면 널로 가져옴, 있으면 가져옴

		if(session!=null)
			session.invalidate();//세션지우기
		return new ActionForward(path, true);
	}

}
