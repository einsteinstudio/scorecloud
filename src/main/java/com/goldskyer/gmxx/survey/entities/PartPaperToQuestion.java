package com.goldskyer.gmxx.survey.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="part_paper_to_question")
public class PartPaperToQuestion implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1355951322408869941L;
	@Id
	@Column(name = "ID", length = 40)
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	private String id;
	private String partPaperId;
	private String questionId;
	private double weight=1000d;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPartPaperId() {
		return partPaperId;
	}
	public void setPartPaperId(String partPaperId) {
		this.partPaperId = partPaperId;
	}
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
}
