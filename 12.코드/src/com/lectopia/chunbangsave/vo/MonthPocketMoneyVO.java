package com.lectopia.chunbangsave.vo;

import java.util.HashMap;

public class MonthPocketMoneyVO {
	/**
	 * Key : ��
	 * Value : �Ѱ��� �뵷 ����
	 */
	private HashMap<String, PocketMoneyVO> pocketInfos;
	/**
	 * default������
	 */
	public MonthPocketMoneyVO() {
		this(new HashMap<String, PocketMoneyVO>());
	}
	/**
	 * overloaded������
	 * @param pocketInfos �Ѵ��� �뵷 ���� ����
	 */
	public MonthPocketMoneyVO(HashMap<String, PocketMoneyVO> pocketInfos) {
		super();
		this.pocketInfos = pocketInfos;
	}
	/**
	 * �Ϸ��� �뵷������ put�ϴ� �޼ҵ�
	 * @param day ��¥
	 * @param addData �Ѱ��� �뵷 ����
	 */
	public void put(String day,PocketMoneyVO addData){
		pocketInfos.put(day, addData);
	}
	/**
	 * ��¥�� ��ġ�ϴ� �Ѱ��� �뵷������ get�ϴ� �޼ҵ�
	 * @param day ��¥
	 * @return �Ѱ��� �뵷����
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
