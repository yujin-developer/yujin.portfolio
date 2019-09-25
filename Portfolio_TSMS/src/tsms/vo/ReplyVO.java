package tsms.vo;

import java.util.Date;

public class ReplyVO {
	protected int rno;
	protected int bno;
	protected String content;
	protected String replyer;
	protected Date replydate;
	protected Date updatedate;
	
	public int getRno() {
		return rno;
	}
	public ReplyVO setRno(int rno) {
		this.rno = rno;
		return this;
	}
	public int getBno() {
		return bno;
	}
	public ReplyVO setBno(int bno) {
		this.bno = bno;
		return this;
	}
	public String getContent() {
		return content;
	}
	public ReplyVO setContent(String content) {
		this.content = content;
		return this;
	}
	public String getReplyer() {
		return replyer;
	}
	public ReplyVO setReplyer(String replyer) {
		this.replyer = replyer;
		return this;
	}
	public Date getReplydate() {
		return replydate;
	}
	public ReplyVO setReplydate(Date replydate) {
		this.replydate = replydate;
		return this;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public ReplyVO setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
		return this;
	}
	
	
}
