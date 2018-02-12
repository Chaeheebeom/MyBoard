package boardaction;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import action.ActionForward;
import myboarddao.MyBoardDAO;
import myboardvo.MyBoardVO;



public class BoardInsertAction implements Action {

	String path;
	
	public BoardInsertAction(String path) {
		this.path=path;
	}
	
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) {
		String name="";
		String subject="";
		String content="";
		String pass="";
		String file="";
		String id="";
		int prcode;
		String uploadpath=request.getServletContext().getRealPath("/upload");
		
		int size=10*1024*1024;		
		try {
			MultipartRequest multi=
					new MultipartRequest
					(request, uploadpath,size,"UTF-8",new DefaultFileRenamePolicy());
			name=multi.getParameter("name");
			subject=multi.getParameter("subject");
			content=multi.getParameter("content");
			pass=multi.getParameter("passwd");
			id=multi.getParameter("id");
			prcode=Integer.parseInt(multi.getParameter("prcode"));
			
			Enumeration files=multi.getFileNames();
			
						
			MyBoardVO vo=new MyBoardVO();
			vo.setUser_name(name);
			vo.setBoard_subject(subject);
			vo.setBoard_pass(pass);
			vo.setBoard_content(content);
			vo.setUser_id(id);
			vo.setUser_prcode(prcode);
			String file1=(String)files.nextElement();
			vo.setBoard_re_file(multi.getFilesystemName(file1));
			vo.setBoard_ori_file(multi.getOriginalFileName(file1));
			
			
			
			MyBoardDAO dao=new MyBoardDAO();
			int result=dao.insertArticle(vo);
			if(result>0) {
				request.setAttribute("vo",vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ActionForward(path, true);
	}

}
