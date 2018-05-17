package com.lectopia.chunbangsave.vo;

import java.util.HashMap;
import java.util.LinkedList;
/**
 * 
 * @author 동익
 *	하루 가계부 정보 목록
 */
public class MonthCashBookDetailVO {
	/**
	 * Key - day
	 * Value - day의 가계부 내역 정보 목록
	 */
	private HashMap<Integer, LinkedList<CashBookDetailVO>> monthCBDMap;
	
	public MonthCashBookDetailVO() {
		super();
		this.monthCBDMap = new HashMap<Integer, LinkedList<CashBookDetailVO>>();
	}

	public HashMap<Integer, LinkedList<CashBookDetailVO>> getMonthCBDMap() {
		return monthCBDMap;
	}
	
	public void setMonthCBDMap(
			HashMap<Integer, LinkedList<CashBookDetailVO>> monthCBDMap) {
		this.monthCBDMap = monthCBDMap;
	}

	public MonthCashBookDetailVO(
			HashMap<Integer, LinkedList<CashBookDetailVO>> monthCBDMap) {
		super();
		this.monthCBDMap = monthCBDMap;
	}
	/**
	 * 
	 * @param day 데이터를 넣을 일 정보
	 * @param addData 추가할 가계부 내역 정보
	 */
	public boolean put(int day,CashBookDetailVO addData){
		if(this.monthCBDMap.get(day)==null){
			LinkedList<CashBookDetailVO> inData = new LinkedList<CashBookDetailVO>();
			this.monthCBDMap.put(day,inData);		
			System.out.println(day+"일 "+inData);
		}
		System.out.println("MonthCashBookDetailVO - put : "+addData);
		return this.monthCBDMap.get(day).add(addData);
	}
	/**
	 * 
	 * @param day 삭제할 데이터가 존재하는 일 정보
	 * @param registerTime 일시 정보
	 * @return 정상 삭제 시 참 반환
	 */
	public boolean remove(int day,int[]registerTime){
		if(this.monthCBDMap.get(day)==null)
			return false;
		/*
		 * 일시 찾기
		 */
		int index = this.checkRegisterTime(day, registerTime);
		System.out.println("MonthCashBookDetailVO-remove(index) : "+index);
		if(index!=-1)
			 if(this.monthCBDMap.get(day).remove(index)!=null)
				 return true;
		return false;
	}
	/**
	 * 
	 * @param day 수정할 데이터가 존재하는 일 정보
	 * @param registerTime 일시 정보
	 * @param replaceData 수정할 데이터
	 * @return 정상 수정 시 참 반환
	 */
	public boolean replace(int day, int[]registerTime, CashBookDetailVO replaceData){
		System.out.println("MonthCashbookDetailVO-replace:"+replaceData);
		if(this.monthCBDMap.get(day)==null)
			return false;
		/*
		 * 일시 찾기
		 */
		int index = this.checkRegisterTime(day, registerTime);
		if(index!=-1)
			 if(this.monthCBDMap.get(day).set(index,replaceData)!=null)
				 return true;
		return false;
	}
	/**
	 * 
	 * @param day 가져올 데이터 일 정보
	 * @param registerTime 가져올 데이터의 일시 정보
	 * @return 찾은 데이터의 가계부 내역 정보
	 */
	public CashBookDetailVO get(int day, int[]registerTime){
		if(this.monthCBDMap.get(day)==null)
			return null;
		/*
		 * 일시 찾기
		 */
		int index = this.checkRegisterTime(day, registerTime);
		if(index!=-1)
			 return this.monthCBDMap.get(day).get(index);
		return null;
	}
	/**
	 * 
	 * @param day 가져올 데이터 일 정보
	 * @return 일에 해당하는 모든 가계부 내역 정보
	 */
	public LinkedList<CashBookDetailVO>get(int day){
		return this.monthCBDMap.get(day);
	}
	/**
	 * 
	 * @param day 일 정보 
	 * @param registerTime 일시 정보
	 * @return 존재한다면 LinkedList index 리턴, -1
	 */
	public int checkRegisterTime(int day,int[]registerTime){
		if(this.monthCBDMap.get(day)==null)
			return -1;
		if(registerTime.length!=3)
			return -1;
		System.out.println("MonthCashBookDetailVO-checkRegisterTime : 전제조건 통과");
		for(int i = 0 ; i < this.monthCBDMap.get(day).size();++i)
			if(this.monthCBDMap.get(day).get(i).equals(registerTime))
				return i;
		return -1;
	}

	@Override
	public String toString() {
		return "MonthCashBookDetailVO [monthCBDMap=" + monthCBDMap + "]";
	}
	
}
