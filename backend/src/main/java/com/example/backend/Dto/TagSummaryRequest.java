package com.example.backend.Dto;

import java.util.List;

public class TagSummaryRequest {
	private List<UserTag> users;
	
	public static class UserTag{
		private String id;
		private String name;
		private List<String>tags;
	}

}

