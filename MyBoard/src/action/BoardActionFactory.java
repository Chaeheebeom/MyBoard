package action;

import boardaction.BoardInsertAction;
import boardaction.BoardListAction;
import boardaction.BoardWriteViewAction;
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
				action=new BoardListAction("templet/center.jsp");
			break;
			case"/writerview.do":
				action=new BoardWriteViewAction("view/mainBoard.jsp");
			break;
			case"/qWrite.do":
				action=new BoardInsertAction("list.do");
			case"/view.do":
				
			break;
		}
		
		return action;
	}
	
	
}
