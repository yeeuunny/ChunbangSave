package com.lectopia.chunbangsave.view;

public enum ParentUseCategory {
	FOOD("�ĺ�"),INSUARANCE("����"),EVENT("������"),TAX("����"),CARD("ī����"),EXTRA("��Ÿ");
	private final String parentUseCategory;
	private ParentUseCategory(String parentUseCategory) {
		this.parentUseCategory = parentUseCategory;
	}
	public String value(){return this.parentUseCategory;}
}
