package myboardvo;

public class UsertblVO {

	private String prcode;
	private String id;
	private String name;
	private String passwd;
	private String phonnum;
	
	public UsertblVO(){
		super();
	}
	
	public UsertblVO(String prcode, String id, String name, String passwd, String phonnum) {
		super();
		this.prcode = prcode;
		this.id = id;
		this.name = name;
		this.passwd = passwd;
		this.phonnum = phonnum;
	}

	public String getPrcode() {
		return prcode;
	}

	public void setPrcode(String prcode) {
		this.prcode = prcode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getPhonnum() {
		return phonnum;
	}

	public void setPhonnum(String phonnum) {
		this.phonnum = phonnum;
	}
	
	
	
}
