package com.lectopia.chunbangsave.view;

public enum ChildUseCategory {
	SCHOOL("�п�ǰ"),SNACK("����"),HOBBY("���"),GAME("����"),EXTRA("��Ÿ");
	private final String childUseCategory;
	private ChildUseCategory(String childUseCategory) {
		this.childUseCategory = childUseCategory;
	}
	public String value(){return this.childUseCategory;}
}
