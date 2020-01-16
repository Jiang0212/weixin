package domain;


public class Goods {
	private String id;//商品编号
	private String name;//商品名称
	private String size;//商品规格
	private String unit;//商品单位
	private String day;//剩余天数
	private String validTime;//有效期

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

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getValidTime() {
		return validTime;
	}

	public void setValidTime(String validTime) {
		this.validTime = validTime;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	@Override
	public String toString() {
		return "Goods [id=" + id + ", name=" + name + ", size=" + size + ", unit=" + unit + ", day=" + day
				+ ", validTime=" + validTime + "]";
	}
	
}
