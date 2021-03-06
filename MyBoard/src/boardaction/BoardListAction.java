package boardaction;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import myboarddao.MyBoardDAO;
import myboardvo.MyBoardVO;
import myboardvo.PageInfo;


public class BoardListAction implements Action {

	String path;
	
	public BoardListAction(String path) {
		this.path=path;
	}
	
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) {
		MyBoardDAO dao=new MyBoardDAO();
		
		int page=1;
		if(request.getParameter("page")!=null)
			page=Integer.parseInt(request.getParameter("page"));//사용자가 선택한페이지번호
		
		//전체 레코드 개수 구하기.
		int totalRows=dao.getRows();
		//한페이지에 보여줄 목록 갯수
		int limit=10;
		//총페이지 수  구하기.  300.00/10=30.0+0.95=30.95=>30    9.0/10=0.9+0.95=1.85=>1  
		//11.0/10=1.1+0.95=2.05=>2
		int totalPage=(int)((double)totalRows/limit+0.95);
		//사용자의 페이지번호에 맞춰 읽어올 레코드의 시작번호와 끝번호 저장
		//ex)1:0~9 2:10~19 3:20~29 ....
		// 
		int startPage=( ( (int)( (double)page/10+0.9) ) -1)*10+1;
		int endPage=startPage+10-1;
		
		if(endPage>totalPage)//맨마지막 페이지를 위한 것
			endPage=totalPage;
		//정보를 넘기기위해 pageInfo에 담는다.
		PageInfo pageInfo=new PageInfo(totalPage, startPage, endPage, page, totalRows);
		
		//전체리스트가아닌 사용자가 선택한 페이지에 대한 리스트를 가져옵니다.
		Vector<MyBoardVO> list=dao.getList(page,limit);
		//정렬하기.
				
		if(list!=null) {
			request.setAttribute("list", list);
			request.setAttribute("info", pageInfo);
		}
		
		return new ActionForward(path, false);
	}

}
