
package boardaction;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import action.ActionForward;
import myboarddao.MyBoardDAO;
import myboardvo.MyBoardVO;


public class BoardModifyAction implements Action{

	private String path;
	
	public BoardModifyAction(String path) {
		super();
		this.path = path;
	}

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) {
		MyBoardVO vo=new MyBoardVO();
		int board_num=0;
		String page=null;
		try {
			MultipartRequest multi=
					new MultipartRequest(request, request.getServletContext().getRealPath("/upload"), 
							10*1024*1024, "UTF-8", new DefaultFileRenamePolicy());
			
			MyBoardDAO dao=new MyBoardDAO();
			
			board_num=Integer.parseInt(multi.getParameter("board_num"));//
			
			String board_pass=multi.getParameter("board_pass");
			String board_subject=multi.getParameter("board_subject");
			String board_content=multi.getParameter("board_content");
			
			page=multi.getParameter("page");
			Enumeration file=multi.getFileNames();
			if(file.hasMoreElements()) {
				String file1=(String) file.nextElement(); //없으면 suchmoreexception을 날린다.
				String board_re_file=multi.getFilesystemName(file1);//올라간 파일이름
				String board_ori_file=multi.getOriginalFileName(file1);//원본파일
				vo.setBoard_ori_file(board_ori_file);
				vo.setBoard_re_file(board_re_file);
			}
			vo.setBoard_num(board_num);
			vo.setBoard_pass(board_pass);
			vo.setBoard_subject(board_subject);
			vo.setBoard_content(board_content);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		MyBoardDAO dao=new MyBoardDAO();
		int result=dao.modify(vo);
		
		if(result>0){
			path="?board_num="+board_num+"&page="+page;
		}else {
			path="error_page.jsp?code=수정실패";//에러페이지
		}
		
		return new ActionForward(path, true);
	}

}
