package com.costa.entities;

import java.util.List;

public class House {

	private int id;
	private String name;
	private String action;
	private String description;

	public House() {

	}

	public House(int id, String name, String action, String description) {
		super();
		this.id = id;
		this.name = name;
		this.action = action;
		this.description = description;
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

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
