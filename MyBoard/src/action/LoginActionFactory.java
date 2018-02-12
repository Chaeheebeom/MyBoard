package action;

import loginaction.LoginAction;
import loginaction.UserInsertAction;
import loginaction.UserLogoutAction;
import loginaction.getInfoAction;

public class LoginActionFactory {

	private static LoginActionFactory af;
	
	public static LoginActionFactory getInstance() {
		
		if(af==null)
			af=new LoginActionFactory();
		
		return af;
	}
		
	public Action execute(String cmd) {
		
		Action action=null;		
		
		switch(cmd) {
			case"/login.login":
				action=new LoginAction("view/mainBoard.jsp");
			break;
			case"/insert.login":
				action=new UserInsertAction("index.jsp");
			break;
			case"/logout.login":
				action=new UserLogoutAction("index.jsp");
			break;
		}
	
		return action;
	}
	
	
}
