package com.kyoyu.seatlottery.model;

import java.io.Serializable;

public class Participant implements Serializable {

	private String name;
	private Integer seat;
	private static final long serialVersionUID = 1L;

	public Participant(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Integer getSeat() {
		return seat;
	}

	public void setSeat(Integer seat) {
		this.seat=seat;
	}
	
	public boolean isDrawn() {
		return seat !=null;
	
	
	}

}
