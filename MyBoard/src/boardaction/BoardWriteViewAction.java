package boardaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import myboardvo.UsertblVO;

public class BoardWriteViewAction implements Action {

	private String path;
	
	
	public BoardWriteViewAction(String path) {
		super();
		this.path = path;
	}


	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) {
		
		path+="?code=write";
		
		return new ActionForward(path, true);
	}

}
