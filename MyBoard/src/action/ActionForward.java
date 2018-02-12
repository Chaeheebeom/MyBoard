package action;

public class ActionForward {

	private String path;
	private boolean isDirect;
	
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
