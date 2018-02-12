package loginaction;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import myboarddao.LoginDAO;
import myboardvo.UsertblVO;

public class UserInsertAction implements Action {

	private String path;
	
	public UserInsertAction(String path) {
		super();
		this.path = path;
	}

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) {
		
		String id=request.getParameter("id");
		String passwd=request.getParameter("passwd");
		String name=request.getParameter("name");
		String phonnum=request.getParameter("phonnum");
		
		UsertblVO vo=new UsertblVO(0, id, name, passwd, phonnum);
		LoginDAO dao=new LoginDAO();
		int result=dao.userInsert(vo);
		
		
		if(result==0) {
			path="error_page?code=회원가입실패";
		}else {
			try {
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('회원가입완료');");
				out.println("</script>");
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		
		return new ActionForward(path, true);
	}

}
