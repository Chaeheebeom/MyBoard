package boardaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;

public class BoardShowDeleteAction implements Action {

	private String path;
	
	public BoardShowDeleteAction(String path) {
		super();
		this.path = path;
	}

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) {
		String num=request.getParameter("num");
		
		
		return null;
	}

}
