package com.lectopia.chunbangsave.vo;

import java.util.HashMap;
/**
 * 
 * @author 동익
 *	1개월 가계부 정보 목록
 */
public class MonthCashBookVO {
	/**
	 * Key - day
	 * Value - 일일 가계부 정보
	 */
	private HashMap<Integer, CashBookDayVO> monthCBMap;
	
	public MonthCashBookVO() {
		super();
		this.monthCBMap = new HashMap<Integer, CashBookDayVO>();
	}

	public MonthCashBookVO(HashMap<Integer, CashBookDayVO> monthCBMap) {
		super();
		this.monthCBMap = monthCBMap;
	}

	public HashMap<Integer, CashBookDayVO> getMonthCBMap() {
		return monthCBMap;
	}

	public void setMonthCBMap(HashMap<Integer, CashBookDayVO> monthCBMap) {
		this.monthCBMap = monthCBMap;
	}
	/**
	 * 
	 * @param day 추가할 일정보
	 * @param addData 추가할 일일 가계부 정보
	 * @return 정상 추가 여부
	 */
	public boolean put(int day,CashBookDayVO addData){
		if(this.monthCBMap.put(day, addData)!=null)
			return true;
		return false;
	}
	/**
	 * 
	 * @param day 삭제할 일정보
	 * @return 정상 삭제 여부
	 */
	public boolean remove(int day){
		if(this.monthCBMap.remove(day)!=null)
			return true;
		return false;
	}
	/**
	 * 
	 * @param day 수정할 일정보
	 * @param replaceData 수정할 일일 가계부 정보
	 * @return 정상 수정 여부
	 */
	public boolean replace(int day, CashBookDayVO replaceData){
		if(this.monthCBMap.replace(day, replaceData)!=null)
			return true;
		return false;
	}
	/**
	 * 
	 * @param day 검색할 일정보
	 * @return 일정보 검색 결과 일일 가계부 정보
	 */
	public CashBookDayVO get(int day){
		return this.monthCBMap.get(day);
	}
}
