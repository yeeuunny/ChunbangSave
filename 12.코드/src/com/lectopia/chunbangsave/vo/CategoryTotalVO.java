package com.lectopia.chunbangsave.vo;
/**
 * 카테고리 VO
 * @author yeeun
 *
 */
public class CategoryTotalVO
{
	/**
	 * 등록 카테고리 
	 * - 수입, 지출, 저축, 기부 
	 */
	private String registerCategory;
	/**
	 * 상세 카테고리
	 * 부모 
	 * - 지출 : 식비, 보험, 경조사, 세금, 카드대금, 기타 
	 * - 수입 : 월급, 보험금, 매출, 보너스, 기타 
	 * 
	 * 자녀
	 * - 지출 : 학용품, 간식, 취미, 오락, 기타 
	 * - 수입 : 용돈, 기타  
	 */
	private String detailCategory;
	/**
	 * 해당 카테고리의 총액 
	 */
	private String amount; 
	/**
	 * 해당 카테고리가 차지하는 비율 (퍼센트)
	 */ 
	private String percent;
	
	/**
	 * 디폴트 생성자 
	 */
	public CategoryTotalVO() 
	{
		super();
	}
	/**
	 * 오버로디드 생성자 
	 * @param registerCategory 등록카테고리 
	 * @param detailCategory 상세카테고리 
	 * @param amount 카테고리 총액 
	 * @param percent 카테고리 비율 
	 */
	public CategoryTotalVO(String registerCategory, String detailCategory,
			String amount, String percent) 
	{
		super();
		this.registerCategory = registerCategory;
		this.detailCategory = detailCategory;
		this.amount = amount;
		this.percent = percent;
	}
	public String getRegisterCategory() {
		return registerCategory;
	}
	public void setRegisterCategory(String registerCategory) {
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
	public String getPercent() {
		return percent;
	}
	public void setPercent(String percent) {
		this.percent = percent;
	}
	@Override
	public String toString() {
		return "CategoryTotalVO [registerCategory=" + registerCategory
				+ ", detailCategory=" + detailCategory + ", amount=" + amount
				+ ", percent=" + percent + "]";
	}
	
	
}