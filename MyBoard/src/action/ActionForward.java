package action;

public class ActionForward {

	String path;
	boolean isDirect;
	
	public ActionForward(String path, boolean isDirect) {
		super();
		this.path = path;
		this.isDirect = isDirect;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isDirect() {
		return isDirect;
	}
	public void setDirect(boolean isDirect) {
		this.isDirect = isDirect;
	}
	
	
}
