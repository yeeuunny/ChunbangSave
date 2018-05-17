package com.lectopia.chunbangsave.vo;

public enum QuarterNo {
	FIRST_QUARTER(1),SECOND_QUARTER(2),THIRD_QUARTER(3),FOURTH_QUARTER(4);
	final private int quarterNo;
	private QuarterNo(int quarterNo) {
		this.quarterNo = quarterNo;
	}
	public int value(){return this.quarterNo;}
}
