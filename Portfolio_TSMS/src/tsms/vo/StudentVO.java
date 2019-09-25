package tsms.vo;

import java.util.Date;

public class StudentVO {
	protected int student_id;
	protected String id;
	protected String password;
	protected String name;
	protected String gender;
	protected String age;
	protected String email;
	protected String address;
	protected String subject;
	protected String grade;
	protected String day;
	protected String time;
	protected String introduce;
	protected Date createddate;
	protected int readcount;
	
	public int getStudent_id() {
		return student_id;
	}
	public StudentVO setStudent_id(int student_id) {
		this.student_id = student_id;
		return this;
	}
	public String getId() {
		return id;
	}
	public StudentVO setId(String id) {
		this.id = id;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public StudentVO setPassword(String password) {
		this.password = password;
		return this;
	}
	public String getName() {
		return name;
	}
	public StudentVO setName(String name) {
		this.name = name;
		return this;
	}
	public String getGender() {
		return gender;
	}
	public StudentVO setGender(String gender) {
		this.gender = gender;
		return this;
	}
	public String getAge() {
		return age;
	}
	public StudentVO setAge(String age) {
		this.age = age;
		return this;
	}
	public String getEmail() {
		return email;
	}
	public StudentVO setEmail(String email) {
		this.email = email;
		return this;
	}
	public String getAddress() {
		return address;
	}
	public StudentVO setAddress(String address) {
		this.address = address;
		return this;
	}
	public String getSubject() {
		return subject;
	}
	public StudentVO setSubject(String subject) {
		this.subject = subject;
		return this;
	}
	public String getGrade() {
		return grade;
	}
	public StudentVO setGrade(String grade) {
		this.grade = grade;
		return this;
	}
	public String getDay() {
		return day;
	}
	public StudentVO setDay(String day) {
		this.day = day;
		return this;
	}
	public String getTime() {
		return time;
	}
	public StudentVO setTime(String time) {
		this.time = time;
		return this;
	}
	public String getIntroduce() {
		return introduce;
	}
	public StudentVO setIntroduce(String introduce) {
		this.introduce = introduce;
		return this;
	}
	public Date getCreateddate() {
		return createddate;
	}
	public StudentVO setCreateddate(Date createddate) {
		this.createddate = createddate;
		return this;
	}
	public int getReadcount() {
		return readcount;
	}
	public StudentVO setReadcount(int readcount) {
		this.readcount = readcount;
		return this;
	}
}
