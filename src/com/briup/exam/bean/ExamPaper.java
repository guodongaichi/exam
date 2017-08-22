package com.briup.exam.bean;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * �Ծ�
 * */
@Entity
@Table(name="tbl_exam_paper")
public class ExamPaper implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	//�Ծ�����
	private String type;
	
	//�Ծ�����
	private String title;
	//�Ծ�����
	private String description;
	//�ܷ�
	private Integer totalPoints;
	//����ʱ��
	private Date createTime;
	//����ʱ��,СʱΪ��λ
	private Double answerQuestionTime;
	
	//��������
	private Department department;
	//������
	private User user;
	//��Ŀ
	private Set<PaperSubject> paperSubjects = new HashSet<PaperSubject>();
	
	@Id
	@GeneratedValue(strategy = SEQUENCE)
	@Column(name = "id", unique = true)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="department_id")
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getTotalPoints() {
		return totalPoints;
	}
	public void setTotalPoints(Integer totalPoints) {
		this.totalPoints = totalPoints;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Double getAnswerQuestionTime() {
		return answerQuestionTime;
	}
	public void setAnswerQuestionTime(Double answerQuestionTime) {
		this.answerQuestionTime = answerQuestionTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * һ�Զ�
	 * */
	@OneToMany
	@JoinColumn(name="examPaper_id")
	@Cascade({CascadeType.REMOVE})
	public Set<PaperSubject> getPaperSubjects() {
		return paperSubjects;
	}
	public void setPaperSubjects(Set<PaperSubject> paperSubjects) {
		this.paperSubjects = paperSubjects;
	}}
