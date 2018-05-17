package com.lectopia.chunbangsave.vo;

import java.util.Arrays;
/**
 * 
 * @author 동익
 *	- 일일 가계부 정보
 */
public class CashBookDayVO {
	/**
	 * 등록 날짜
	 */
	private int[] registerDate;
	/**
	 * 일일 한도
	 */
	private String dayLimit;
	/**
	 * 칭찬 여부
	 */
	private String compliment;
	/**
	 * default constructor
	 */
	public CashBookDayVO() {
		super();
		this.registerDate = new int[3];
	}
	/**
	 * 
	 * @param registerDate 등록날짜
	 * @param dayLimit 일일한도
	 * @param compliment 칭찬하기
	 */
	public CashBookDayVO(int[] registerDate, String dayLimit,
			String compliment) {
		super();
		this.registerDate = registerDate;
		this.dayLimit = dayLimit;
		this.compliment = compliment;
	}
	/**
	 * 
	 * @return 등록날짜
	 */
	public int[] getRegisterDate() {
		return registerDate;
	}
	/**
	 * 
	 * @param registerDate 새로운 등록날짜
	 */
	public void setRegisterDate(int[] registerDate) {
		this.registerDate = registerDate;
	}
	/**
	 * 
	 * @return 일일한도
	 */
	public String getDayLimit() {
		return dayLimit;
	}
	/**
	 * 
	 * @param dayLimit 새로운 일일한도
	 */
	public void setDayLimit(String dayLimit) {
		this.dayLimit = dayLimit;
	}
	/**
	 * 
	 * @return 칭찬하기 
	 */
	public String getCompliment() {
		return compliment;
	}
	/**
	 * 
	 * @param compliment 새로운 칭찬하기 
	 */
	public void setCompliment(String compliment) {
		this.compliment = compliment;
	}

	@Override
	public String toString() {
		return "CashBookDayVO [registerDate=" + Arrays.toString(registerDate)
				+ ", dayLimit=" + dayLimit + ", compliment=" + compliment + "]";
	}
	
}
