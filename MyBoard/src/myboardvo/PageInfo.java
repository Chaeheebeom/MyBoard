package myboardvo;

public class PageInfo {
	//페이지 나누기를 위한 VO임.
	private int totalPage;
	private int startPage;
	private int endPage;
	private int page;
	private int totalRows;
	
	
	public PageInfo(int totalPage, int startPage, int endPage, int page, int totalRows) {
		super();
		this.totalPage = totalPage;
		this.startPage = startPage;
		this.endPage = endPage;
		this.page = page;
		this.totalRows = totalRows;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
	
	
}
