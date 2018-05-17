package com.lectopia.chunbangsave.view;

public enum ParentUseCategory {
	FOOD("식비"),INSUARANCE("보험"),EVENT("경조사"),TAX("세금"),CARD("카드대금"),EXTRA("기타");
	private final String parentUseCategory;
	private ParentUseCategory(String parentUseCategory) {
		this.parentUseCategory = parentUseCategory;
	}
	public String value(){return this.parentUseCategory;}
}
