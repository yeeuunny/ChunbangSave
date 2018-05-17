package com.lectopia.chunbangsave.vo;

import java.util.Arrays;

import com.lectopia.chunbangsave.view.RegisterCategory;
/**
 * 
 * @author 동익
 *	- 한개 가계부 내역
 */
public class CashBookDetailVO {
	private int[] registerDate;
	private RegisterCategory registerCategory;
	private String detailCategory;
	private String amount;
	private String content;
	
	public CashBookDetailVO() {
		super();
	}

	public CashBookDetailVO(int[] registerDate, RegisterCategory registerCategory,
			String amount, String content) {
		super();
		this.registerDate = registerDate;
		this.registerCategory = registerCategory;
		this.amount = amount;
		this.content = content;
	}

	public CashBookDetailVO(int[] registerDate, RegisterCategory registerCategory,
			String detailCategory, String amount, String content) {
		super();
		this.registerDate = registerDate;
		this.registerCategory = registerCategory;
		this.detailCategory = detailCategory;
		this.amount = amount;
		this.content = content;
	}

	public int[] getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(int[] registerDate) {
		this.registerDate = registerDate;
	}

	public RegisterCategory getRegisterCategory() {
		return registerCategory;
	}

	public void setRegisterCategory(RegisterCategory registerCategory) {
		this.registerCategory = registerCategory;
	}

	public String getDetailCategory() {
		return detailCategory;
	}

	public void setDetailCategory(String detailCategory) {
		this.detailCategory = detailCategory;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 
	 * @param registerTime 등록 일시
	 * @return 존재하는 날짜 일 경우 참반환
	 */
	public boolean equals(int[]registerTime){
		if(this.registerDate.length-3!= registerTime.length)
			return false;
		for(int i = 0; i < registerTime.length; ++i)
			if(this.registerDate[i+3]!=registerTime[i])
				return false;
		return true;
	}

	@Override
	public String toString() {
		return "CashBookDetailVO [registerDate="
				+ Arrays.toString(registerDate) + ", registerCategory="
				+ registerCategory + ", detailCategory=" + detailCategory
				+ ", amount=" + amount + ", content=" + content + "]";
	}
	
}
