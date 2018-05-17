package com.lectopia.chunbangsave.controller;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Set;

import moduleTest.CashBookDAO;
import moduleTest.CashBookUniqueDAO;
import moduleTest.CashBookUniqueDTO;
import moduleTest.DiaryDAO;
import moduleTest.MemoDAO;
import moduleTest.PocketMoneyDAO;
import moduleTest.SavingGoalDAO;
import moduleTest.YearlyTotalDAO;

import com.lectopia.chunbangsave.vo.CashBookDayVO;
import com.lectopia.chunbangsave.vo.MonthCashBookVO;

public class DataSaveManager {
	/**
	 * ���� ����� ����
	 */
	private CashBookUniqueDAO cashBookUniqueList;
	/**
	 * ����� ����
	 */
	private CashBookDAO cashBookList;
	/**
	 * �޸� ����
	 */
	private MemoDAO memoList;
	/**
	 * ������ ���
	 */
	private YearlyTotalDAO yearlyList;
	/**
	 * �ϱ� ����
	 */
	private DiaryDAO diaryLIst;
	/**
	 * �뵷 ��û ����
	 */
	private PocketMoneyDAO pocketList;
	/**
	 * ���� ��ǥ ����
	 */
	private SavingGoalDAO goalInfo;
	public DataSaveManager(){
		this.cashBookList = new CashBookDAO();
		this.cashBookUniqueList = new CashBookUniqueDAO();
		this.memoList = new MemoDAO();
		this.yearlyList = new YearlyTotalDAO();
		this.diaryLIst = new DiaryDAO();
		this.pocketList = new PocketMoneyDAO();
		this.goalInfo = new SavingGoalDAO();
	}
	
	public CashBookUniqueDAO getCashBookUniqueList() {
		return cashBookUniqueList;
	}

	public void setCashBookUniqueList(CashBookUniqueDAO cashBookUniqueList) {
		this.cashBookUniqueList = cashBookUniqueList;
	}

	public void saveOneMonthCBUnique(String familyCode,String registerCode,int month,ArrayList<MonthCashBookVO> monthCashBookVO){
		GregorianCalendar calendar = new GregorianCalendar();
		int quarter = (int)Math.ceil(month / 3.0);
		int quarterIndex = month+2 - 3*quarter;
		if(monthCashBookVO == null)
			System.out.println("������ ���� �����ϴ�!");
		System.out.println("saveOneMonth : "+quarterIndex);
		MonthCashBookVO tempVoList = monthCashBookVO.get(quarterIndex);
		tempVoList.getMonthCBMap().keySet();
		
		ArrayList<CashBookUniqueDTO> saveData = new ArrayList<CashBookUniqueDTO>();
		for( Integer key : tempVoList.getMonthCBMap().keySet() ){
			switch(tempVoList.get(key).getCompliment()){
			case "1": saveData.add(new CashBookUniqueDTO(new String[]{Integer.toString(tempVoList.get(key).getRegisterDate()[0]),
																			Integer.toString(tempVoList.get(key).getRegisterDate()[1]),
																				Integer.toString(tempVoList.get(key).getRegisterDate()[2])},
																					Integer.parseInt(tempVoList.get(key).getDayLimit()),1)); 
						System.out.println("Datasave���� �� :"+tempVoList.get(key).getRegisterDate()[1]);
						break;
			case "2" : saveData.add(new CashBookUniqueDTO(new String[]{Integer.toString(tempVoList.get(key).getRegisterDate()[0]),
																			Integer.toString(tempVoList.get(key).getRegisterDate()[1]),
																				Integer.toString(tempVoList.get(key).getRegisterDate()[2])},
																					Integer.parseInt(tempVoList.get(key).getDayLimit()),2)); 
						break;
			default : saveData.add(new CashBookUniqueDTO(new String[]{Integer.toString(tempVoList.get(key).getRegisterDate()[0]),
																			Integer.toString(tempVoList.get(key).getRegisterDate()[1]),
																				Integer.toString(tempVoList.get(key).getRegisterDate()[2])},
																					Integer.parseInt(tempVoList.get(key).getDayLimit()),0)); 
						break;
			}
		}
		for(int i = 0; i < saveData.size();++i)
			System.out.println(saveData.get(i));
		this.cashBookUniqueList.put(registerCode, month, saveData);
		this.cashBookUniqueList.saveCashBookUniqueFile("F0001_U_p1_16_11_16_1115.txt", registerCode);
	}
}
