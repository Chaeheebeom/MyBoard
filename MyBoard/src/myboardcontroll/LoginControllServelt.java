package myboardcontroll;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import action.LoginActionFactory;

@WebServlet("*.login")
public class LoginControllServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		
		String uri=request.getRequestURI();
		String context=request.getContextPath();
		String cmd=uri.substring(context.length());
		
		//객체생성
		LoginActionFactory laf=LoginActionFactory.getInstance();
		
		Action action=laf.execute(cmd);
		
		ActionForward forward=action.excute(request, response);
		
		if(forward.isDirect()) {
			response.sendRedirect(forward.getPath());
		}else {
			RequestDispatcher dis=request.getRequestDispatcher(forward.getPath());
			dis.forward(request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
