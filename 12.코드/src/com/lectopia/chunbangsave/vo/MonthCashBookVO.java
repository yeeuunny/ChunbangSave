package com.lectopia.chunbangsave.vo;

import java.util.HashMap;
/**
 * 
 * @author ����
 *	1���� ����� ���� ���
 */
public class MonthCashBookVO {
	/**
	 * Key - day
	 * Value - ���� ����� ����
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
	 * @param day �߰��� ������
	 * @param addData �߰��� ���� ����� ����
	 * @return ���� �߰� ����
	 */
	public boolean put(int day,CashBookDayVO addData){
		if(this.monthCBMap.put(day, addData)!=null)
			return true;
		return false;
	}
	/**
	 * 
	 * @param day ������ ������
	 * @return ���� ���� ����
	 */
	public boolean remove(int day){
		if(this.monthCBMap.remove(day)!=null)
			return true;
		return false;
	}
	/**
	 * 
	 * @param day ������ ������
	 * @param replaceData ������ ���� ����� ����
	 * @return ���� ���� ����
	 */
	public boolean replace(int day, CashBookDayVO replaceData){
		if(this.monthCBMap.replace(day, replaceData)!=null)
			return true;
		return false;
	}
	/**
	 * 
	 * @param day �˻��� ������
	 * @return ������ �˻� ��� ���� ����� ����
	 */
	public CashBookDayVO get(int day){
		return this.monthCBMap.get(day);
	}
}
