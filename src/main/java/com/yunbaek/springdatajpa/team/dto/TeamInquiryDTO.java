package com.yunbaek.springdatajpa.team.dto;

import lombok.Data;

@Data
public class TeamInquiryDTO {
	private long teamId;
	private String teamName;
	private long memberId;
	private String userName;

	public TeamInquiryDTO(long teamId, String teamName, long memberId, String userName) {
		this.teamId = teamId;
		this.teamName = teamName;
		this.memberId = memberId;
		this.userName = userName;
	}
}
