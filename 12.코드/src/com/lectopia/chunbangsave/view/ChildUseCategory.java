package com.lectopia.chunbangsave.view;

public enum ChildUseCategory {
	SCHOOL("학용품"),SNACK("간식"),HOBBY("취미"),GAME("오락"),EXTRA("기타");
	private final String childUseCategory;
	private ChildUseCategory(String childUseCategory) {
		this.childUseCategory = childUseCategory;
	}
	public String value(){return this.childUseCategory;}
}
