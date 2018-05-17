package com.lectopia.chunbangsave.vo;

import java.util.HashMap;
import java.util.LinkedList;
/**
 * 
 * @author ����
 *	�Ϸ� ����� ���� ���
 */
public class MonthCashBookDetailVO {
	/**
	 * Key - day
	 * Value - day�� ����� ���� ���� ���
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
	 * @param day �����͸� ���� �� ����
	 * @param addData �߰��� ����� ���� ����
	 */
	public boolean put(int day,CashBookDetailVO addData){
		if(this.monthCBDMap.get(day)==null){
			LinkedList<CashBookDetailVO> inData = new LinkedList<CashBookDetailVO>();
			this.monthCBDMap.put(day,inData);		
			System.out.println(day+"�� "+inData);
		}
		System.out.println("MonthCashBookDetailVO - put : "+addData);
		return this.monthCBDMap.get(day).add(addData);
	}
	/**
	 * 
	 * @param day ������ �����Ͱ� �����ϴ� �� ����
	 * @param registerTime �Ͻ� ����
	 * @return ���� ���� �� �� ��ȯ
	 */
	public boolean remove(int day,int[]registerTime){
		if(this.monthCBDMap.get(day)==null)
			return false;
		/*
		 * �Ͻ� ã��
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
	 * @param day ������ �����Ͱ� �����ϴ� �� ����
	 * @param registerTime �Ͻ� ����
	 * @param replaceData ������ ������
	 * @return ���� ���� �� �� ��ȯ
	 */
	public boolean replace(int day, int[]registerTime, CashBookDetailVO replaceData){
		System.out.println("MonthCashbookDetailVO-replace:"+replaceData);
		if(this.monthCBDMap.get(day)==null)
			return false;
		/*
		 * �Ͻ� ã��
		 */
		int index = this.checkRegisterTime(day, registerTime);
		if(index!=-1)
			 if(this.monthCBDMap.get(day).set(index,replaceData)!=null)
				 return true;
		return false;
	}
	/**
	 * 
	 * @param day ������ ������ �� ����
	 * @param registerTime ������ �������� �Ͻ� ����
	 * @return ã�� �������� ����� ���� ����
	 */
	public CashBookDetailVO get(int day, int[]registerTime){
		if(this.monthCBDMap.get(day)==null)
			return null;
		/*
		 * �Ͻ� ã��
		 */
		int index = this.checkRegisterTime(day, registerTime);
		if(index!=-1)
			 return this.monthCBDMap.get(day).get(index);
		return null;
	}
	/**
	 * 
	 * @param day ������ ������ �� ����
	 * @return �Ͽ� �ش��ϴ� ��� ����� ���� ����
	 */
	public LinkedList<CashBookDetailVO>get(int day){
		return this.monthCBDMap.get(day);
	}
	/**
	 * 
	 * @param day �� ���� 
	 * @param registerTime �Ͻ� ����
	 * @return �����Ѵٸ� LinkedList index ����, -1
	 */
	public int checkRegisterTime(int day,int[]registerTime){
		if(this.monthCBDMap.get(day)==null)
			return -1;
		if(registerTime.length!=3)
			return -1;
		System.out.println("MonthCashBookDetailVO-checkRegisterTime : �������� ���");
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
