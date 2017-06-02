package com.goldskyer.gmxx.survey.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name="part_paper")
public class PartPaper {
	@Id
	@Column(name = "ID", length = 40)
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	private String id;
	private String partName; //第几部分的标题，默认第{x}部分
	
//	@ManyToMany(targetEntity=Question.class,cascade=CascadeType.ALL,fetch=FetchType.EAGER)
//    @JoinTable(name="part_paper_to_question",  
//        joinColumns={@JoinColumn(name="partPaperId")},   
//        inverseJoinColumns={@JoinColumn(name="questionId")}) 

	
	private double weight=1000d;
	
	@ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="paperId") 
	private Paper paper;
	
	@OrderBy("weight") 
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "partPaper",fetch=FetchType.EAGER )
	private List<Question> questions = new ArrayList<Question>();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Paper getPaper() {
		return paper;
	}
	public void setPaper(Paper paper) {
		this.paper = paper;
	}
	public String getPartName() {
		return partName;
	}
	public void setPartName(String partName) {
		this.partName = partName;
	}
	
	
	
	public List<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	
	
}
