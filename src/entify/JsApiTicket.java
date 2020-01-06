package entify;

public class JsApiTicket {
	private String ticket;
	private long expireTime;
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public long getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(long expireTime) {
		this.expireTime = expireTime;
	}
	
	public JsApiTicket(String ticket, String expireIn) {
		super();
		this.ticket = ticket;
		expireTime = System.currentTimeMillis()+Integer.parseInt(expireIn)*1000;
	}
	/**
	 * 判断Ticket是否过期
	 * @return
	 */
	public boolean isExpired() {
		return System.currentTimeMillis()>expireTime;
	}
}
