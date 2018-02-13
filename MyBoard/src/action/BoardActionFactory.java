package action;

import boardaction.BoardDeleteAction;
import boardaction.BoardInsertAction;
import boardaction.BoardListAction;
import boardaction.BoardModifyAction;
import boardaction.BoardModifyView;
import boardaction.BoardReplyAction;
import boardaction.BoardReplyView;
import boardaction.BoardSearchAction;
import boardaction.BoardShowDeleteAction;
import boardaction.BoardViewAction;
import boardaction.BoardWriteViewAction;
import boardaction.MyBoardHitUpdateAction;
import boardaction.MyboardAction;
import loginaction.LoginAction;
import loginaction.UserInsertAction;

public class BoardActionFactory {

	private static BoardActionFactory af;
	
	public static BoardActionFactory getInstance() {
		
		if(af==null)
			af=new BoardActionFactory();
		
		return af;
	}
		
	public Action execute(String cmd) {
		
		Action action=null;		
		
		switch(cmd) {
			case"/list.do":
				action=new BoardListAction("templet/myboard_list.jsp");
			break;
			case"/writerview.do":
				action=new BoardWriteViewAction("view/myboard_write.jsp");
			break;
			case"/qWrite.do":
				action=new BoardInsertAction("list.do");
			break;	
			case"/view.do":
				action=new BoardViewAction("templet/myboard_view.jsp");
			break;
			case"/hitupdate.do":
				action=new MyBoardHitUpdateAction("view.do");
			break;
			case"/showdelete.do":
				action=new BoardShowDeleteAction("templet/myboard_delete.jsp");
			break;
			case"/delete.do":
				action=new BoardDeleteAction("list.do");
			break;
			case"/modifyview.do":
				action=new BoardModifyView("templet/myboard_modify.jsp");
			break;
			case"/qUpdate.do":
				action=new BoardModifyAction("view.do");
			break;
			case"/replyview.do":
				action=new BoardReplyView("templet/myboard_reply.jsp");
			break;				
			case"/reply.do":
				action=new BoardReplyAction("list.do");
			break;
			case"/qSearch.do":
				action=new BoardSearchAction("templet/myboard_list.jsp");
			break;
			
		}
		
		return action;
	}
	
	
}
