package com.devkyoungyun.springdatajpa.team;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String username;

	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;
}
