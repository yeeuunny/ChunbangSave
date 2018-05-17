package com.lectopia.chunbangsave.vo;

public enum Weather {
	SUNNY("����"),CLOUDY("�帲"),RAINY("��"),SNOWY("��");
	final private String weather;
	private Weather(String weather) {
		this.weather = weather;
	}
	String value(){
		return this.weather;
	}
}
