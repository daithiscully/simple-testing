package com.david.simple_testing.models;

import java.util.ArrayList;

public class InisTest {

	private int id;
	private String name;
	private String description;
	private ArrayList<Step> steps;
	
	public InisTest(){
		
	}
	
	public InisTest(int id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.steps = new ArrayList<Step>();
	}

	public InisTest(int id, String name, String description, ArrayList<Step> steps) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.steps = steps;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<Step> getSteps() {
		return steps;
	}

	public void setSteps(ArrayList<Step> steps) {
		this.steps = steps;
	}

	@Override
	public String toString() {
		return "\nInisTest [id=" + id + ", name=" + name + ", description=" + description + ", steps=" + steps + "]\n";
	}

}
