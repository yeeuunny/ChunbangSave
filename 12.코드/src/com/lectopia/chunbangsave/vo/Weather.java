package com.lectopia.chunbangsave.vo;

public enum Weather {
	SUNNY("¸¼À½"),CLOUDY("Èå¸²"),RAINY("ºñ"),SNOWY("´«");
	final private String weather;
	private Weather(String weather) {
		this.weather = weather;
	}
	String value(){
		return this.weather;
	}
}
