package action;

import action.imp.LoginAction;

public class ActionFactory {

	private static ActionFactory af;
	
	public static ActionFactory getInstance() {
		
		if(af==null)
			af=new ActionFactory();
		
		return af;
	}
	
	
	
	public Action execute(String cmd) {
		
		Action action=null;		
		
		switch(cmd) {
			case"":
				action=new LoginAction();
			break;
		}
		
		return action;
	}
	
	
}
