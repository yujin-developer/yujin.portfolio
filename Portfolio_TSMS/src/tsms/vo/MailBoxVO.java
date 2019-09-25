package tsms.vo;

import java.util.Date;

public class MailBoxVO {
	protected String sender;
	protected String receiver;
	protected Date sendtime;
	protected String content;
	protected String readcheck;
	protected int id;
	protected String starcheck;
	
	public String getSender() {
		return sender;
	}
	public MailBoxVO setSender(String sender) {
		this.sender = sender;
		return this;
	}
	public String getReceiver() {
		return receiver;
	}
	public MailBoxVO setReceiver(String receiver) {
		this.receiver = receiver;
		return this;
	}
	public Date getSendtime() {
		return sendtime;
	}
	public MailBoxVO setSendtime(Date sendtime) {
		this.sendtime = sendtime;
		return this;
	}
	public String getContent() {
		return content;
	}
	public MailBoxVO setContent(String content) {
		this.content = content;
		return this;
	}
	public String getReadcheck() {
		return readcheck;
	}
	public MailBoxVO setReadcheck(String readcheck) {
		this.readcheck = readcheck;
		return this;
	}
	public int getId() {
		return id;
	}
	public MailBoxVO setId(int id) {
		this.id = id;
		return this;
	}
	public String getStarcheck() {
		return starcheck;
	}
	public MailBoxVO setStarcheck(String starcheck) {
		this.starcheck = starcheck;
		return this;
	}
	
	
}
