package com.lectopia.chunbangsave.view;

public enum RegisterCategory {
	IMPORT("+"),EXPORT("-"),SAVE("��"),DONATION("��");
	private final String howTo;
	private RegisterCategory(String howTo) {
		this.howTo = howTo;
	}
	public String value(){return this.howTo;}
}
