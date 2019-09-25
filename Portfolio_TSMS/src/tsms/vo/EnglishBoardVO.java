package tsms.vo;

import java.util.Date;

public class EnglishBoardVO {
	protected int student_id;
	protected String subject;
	protected String content;
	protected Date createddate;
	protected String photo;
	protected String name;
	protected int readcount;
	protected Date updatedate;
	protected int bno;
	
	public int getStudent_id() {
		return student_id;
	}
	public EnglishBoardVO setStudent_id(int student_id) {
		this.student_id = student_id;
		return this;
	}
	public String getSubject() {
		return subject;
	}
	public EnglishBoardVO setSubject(String subject) {
		this.subject = subject;
		return this;
	}
	public String getContent() {
		return content;
	}
	public EnglishBoardVO setContent(String content) {
		this.content = content;
		return this;
	}
	public Date getCreateddate() {
		return createddate;
	}
	public EnglishBoardVO setCreateddate(Date createddate) {
		this.createddate = createddate;
		return this;
	}
	public String getPhoto() {
		return photo;
	}
	public EnglishBoardVO setPhoto(String photo) {
		this.photo = photo;
		return this;
	}
	public String getName() {
		return name;
	}
	public EnglishBoardVO setName(String name) {
		this.name = name;
		return this;
	}
	public int getReadcount() {
		return readcount;
	}
	public EnglishBoardVO setReadcount(int readcount) {
		this.readcount = readcount;
		return this;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public EnglishBoardVO setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
		return this;
	}
	public int getBno() {
		return bno;
	}
	public EnglishBoardVO setBno(int bno) {
		this.bno = bno;
		return this;
	}
	
	
}
