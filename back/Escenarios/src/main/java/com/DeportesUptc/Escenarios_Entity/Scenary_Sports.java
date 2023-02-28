package com.DeportesUptc.Escenarios_Entity;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Escenarios")
public class Scenary_Sports implements Serializable {

	
	
	@Column(length = 100 )
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@Column(name="Escenary",nullable=false,length=20,unique=true)
	private String name_scenarySport;

	private String description;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) { 
		this.id = id;
	}
	
	public String getName_scenarySport() {
		return name_scenarySport;
	}
	public void setName_scenarySport(String name_scenarySport) {
		this.name_scenarySport = name_scenarySport;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
