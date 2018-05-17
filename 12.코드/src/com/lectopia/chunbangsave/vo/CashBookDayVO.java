package com.lectopia.chunbangsave.vo;

import java.util.Arrays;
/**
 * 
 * @author ����
 *	- ���� ����� ����
 */
public class CashBookDayVO {
	/**
	 * ��� ��¥
	 */
	private int[] registerDate;
	/**
	 * ���� �ѵ�
	 */
	private String dayLimit;
	/**
	 * Ī�� ����
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
	 * @param registerDate ��ϳ�¥
	 * @param dayLimit �����ѵ�
	 * @param compliment Ī���ϱ�
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
	 * @return ��ϳ�¥
	 */
	public int[] getRegisterDate() {
		return registerDate;
	}
	/**
	 * 
	 * @param registerDate ���ο� ��ϳ�¥
	 */
	public void setRegisterDate(int[] registerDate) {
		this.registerDate = registerDate;
	}
	/**
	 * 
	 * @return �����ѵ�
	 */
	public String getDayLimit() {
		return dayLimit;
	}
	/**
	 * 
	 * @param dayLimit ���ο� �����ѵ�
	 */
	public void setDayLimit(String dayLimit) {
		this.dayLimit = dayLimit;
	}
	/**
	 * 
	 * @return Ī���ϱ� 
	 */
	public String getCompliment() {
		return compliment;
	}
	/**
	 * 
	 * @param compliment ���ο� Ī���ϱ� 
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
