package com.lectopia.chunbangsave.vo;
/**
 * ī�װ� VO
 * @author yeeun
 *
 */
public class CategoryTotalVO
{
	/**
	 * ��� ī�װ� 
	 * - ����, ����, ����, ��� 
	 */
	private String registerCategory;
	/**
	 * �� ī�װ�
	 * �θ� 
	 * - ���� : �ĺ�, ����, ������, ����, ī����, ��Ÿ 
	 * - ���� : ����, �����, ����, ���ʽ�, ��Ÿ 
	 * 
	 * �ڳ�
	 * - ���� : �п�ǰ, ����, ���, ����, ��Ÿ 
	 * - ���� : �뵷, ��Ÿ  
	 */
	private String detailCategory;
	/**
	 * �ش� ī�װ��� �Ѿ� 
	 */
	private String amount; 
	/**
	 * �ش� ī�װ��� �����ϴ� ���� (�ۼ�Ʈ)
	 */ 
	private String percent;
	
	/**
	 * ����Ʈ ������ 
	 */
	public CategoryTotalVO() 
	{
		super();
	}
	/**
	 * �����ε�� ������ 
	 * @param registerCategory ���ī�װ� 
	 * @param detailCategory ��ī�װ� 
	 * @param amount ī�װ� �Ѿ� 
	 * @param percent ī�װ� ���� 
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