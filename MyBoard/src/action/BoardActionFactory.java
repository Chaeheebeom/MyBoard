package action;

import boardaction.BoardDeleteAction;
import boardaction.BoardInsertAction;
import boardaction.BoardListAction;
import boardaction.BoardShowDeleteAction;
import boardaction.BoardViewAction;
import boardaction.BoardWriteViewAction;
import boardaction.MyBoardHitUpdateAction;
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
				action=new BoardWriteViewAction("view/mainBoard.jsp");
			break;
			case"/qWrite.do":
				action=new BoardInsertAction("list.do");
			case"/view.do":
				action= new BoardViewAction("templet/myboard_view.jsp");
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
		}
		
		return action;
	}
	
	
}
