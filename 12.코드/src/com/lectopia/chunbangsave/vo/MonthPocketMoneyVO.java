package com.lectopia.chunbangsave.vo;

import java.util.HashMap;

public class MonthPocketMoneyVO {
	/**
	 * Key : 일
	 * Value : 한개의 용돈 정보
	 */
	private HashMap<String, PocketMoneyVO> pocketInfos;
	/**
	 * default생성자
	 */
	public MonthPocketMoneyVO() {
		this(new HashMap<String, PocketMoneyVO>());
	}
	/**
	 * overloaded생성자
	 * @param pocketInfos 한달의 용돈 내역 정보
	 */
	public MonthPocketMoneyVO(HashMap<String, PocketMoneyVO> pocketInfos) {
		super();
		this.pocketInfos = pocketInfos;
	}
	/**
	 * 하루의 용돈정보를 put하는 메소드
	 * @param day 날짜
	 * @param addData 한개의 용돈 정보
	 */
	public void put(String day,PocketMoneyVO addData){
		pocketInfos.put(day, addData);
	}
	/**
	 * 날짜에 일치하는 한개의 용돈정보를 get하는 메소드
	 * @param day 날짜
	 * @return 한개의 용돈정보
	 */
	public PocketMoneyVO get(String day){
		return this.pocketInfos.get(day);
	}
	public HashMap<String, PocketMoneyVO> getPocketInfos() {
		return pocketInfos;
	}
	public void setPocketInfos(HashMap<String, PocketMoneyVO> pocketInfos) {
		this.pocketInfos = pocketInfos;
	}

	@Override
	public String toString() {
		return "MonthPocketMoneyVO [pocketInfos=" + pocketInfos + "]";
	}
	
	
}
