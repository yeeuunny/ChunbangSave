package com.lectopia.chunbangsave.vo;

public class PocketMoneyVO {
	private String category;
	private String amount;
	private String content;
	private String isReceived;
	
	/**
	 * default생성자.
	 */
	public PocketMoneyVO() {
		this("","","","");
	}
	/**
	 * overloaded생성자
	 * @param category 카테고리
	 * @param amount 금액(부모: 지급금액)
	 * @param content 내용(부모: 지급 or 미지급)
	 * @param isReceived 지급여부
	 */
	public PocketMoneyVO(String category, String amount, String content,
			String isReceived) {
		super();
		this.category = category;
		this.amount = amount;
		this.content = content;
		this.isReceived = isReceived;
	}
	/**
	 * 카테고리 정보 get 메소드
	 * @return 카테고리정보
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * 전달인자로 받은 카테고리 정보로 카테고리를 set하는 메소드
	 * @param category 카테고리
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * 금액 정보 get메소드
	 * @return 금액 정보
	 */
	public String getAmount() {
		return amount;
	}
	/**
	 * 전달인자로 받은 금액 정보로 금액을 set하는 메소드
	 * @param amount 금액
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}
	/**
	 * 내용 정보 get메소드
	 * @return 내용 정보 
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 내용  정보 set메소드
	 * @param content 내용
	 */
	public void setContent(String content) {
		this.content = content;
	}
  	/**
	 * 지급여부를 get하는 메소드
	 * @return 지금 여부 정보
	 */
	public String getIsReceived() {
		return isReceived;
	}
	/**
	 * 전달인자로 받은 지급여부 정보로 set하는 메소드
	 * @param isReceived
	 */
	public void setIsReceived(String isReceived) {
		this.isReceived = isReceived;
	}
	@Override
	public String toString() {
		return "PocketMoneyVO [category=" + category + ", amount=" + amount
				+ ", content=" + content + ", isReceived=" + isReceived + "]";
	}
	
	
	
}
