package entify;

public class ScancodePushButton extends AbstractButton{
	
	private String type = "scancode_push";
	private String key;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public ScancodePushButton(String name,String key) {
		super(name);
		this.key = key;
	}
}
