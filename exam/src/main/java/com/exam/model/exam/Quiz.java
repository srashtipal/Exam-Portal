package com.exam.model.exam;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "quiz")
public class Quiz {
	
	
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private Long qId;
	
	private String title;
	
	private String description;
	
    private String maxMarks;
    
    private String numberofQuestions;
    
    private boolean active=false;
    
    @ManyToOne(fetch = FetchType.EAGER)
	private Category category;
    
    @OneToMany(mappedBy = "quiz",fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Question> quetion = new HashSet<>();

    
    public Quiz() {
    	
    }
    
    

	public Quiz(Long qId, String title, String description, String maxMarks, String numberofQuestions, boolean active,
			Category category, Set<Question> quetion) {
		super();
		this.qId = qId;
		this.title = title;
		this.description = description;
		this.maxMarks = maxMarks;
		this.numberofQuestions = numberofQuestions;
		this.active = active;
		this.category = category;
		this.quetion = quetion;
	}



	public Long getqId() {
		return qId;
	}

	public void setqId(Long qId) {
		this.qId = qId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMaxMarks() {
		return maxMarks;
	}

	public void setMaxMarks(String maxMarks) {
		this.maxMarks = maxMarks;
	}

	public String getNumberofQuestions() {
		return numberofQuestions;
	}

	public void setNumberofQuestions(String numberofQuestions) {
		this.numberofQuestions = numberofQuestions;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}



	public Category getCategory() {
		return category;
	}



	public void setCategory(Category category) {
		this.category = category;
	}



	public Set<Question> getQuetion() {
		return quetion;
	}



	public void setQuetion(Set<Question> quetion) {
		this.quetion = quetion;
	}
    
    
    
}
