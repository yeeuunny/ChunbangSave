package com.lectopia.chunbangsave.vo;

public class PocketMoneyVO {
	private String category;
	private String amount;
	private String content;
	private String isReceived;
	
	/**
	 * default������.
	 */
	public PocketMoneyVO() {
		this("","","","");
	}
	/**
	 * overloaded������
	 * @param category ī�װ�
	 * @param amount �ݾ�(�θ�: ���ޱݾ�)
	 * @param content ����(�θ�: ���� or ������)
	 * @param isReceived ���޿���
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
	 * ī�װ� ���� get �޼ҵ�
	 * @return ī�װ�����
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * �������ڷ� ���� ī�װ� ������ ī�װ��� set�ϴ� �޼ҵ�
	 * @param category ī�װ�
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * �ݾ� ���� get�޼ҵ�
	 * @return �ݾ� ����
	 */
	public String getAmount() {
		return amount;
	}
	/**
	 * �������ڷ� ���� �ݾ� ������ �ݾ��� set�ϴ� �޼ҵ�
	 * @param amount �ݾ�
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}
	/**
	 * ���� ���� get�޼ҵ�
	 * @return ���� ���� 
	 */
	public String getContent() {
		return content;
	}
	/**
	 * ����  ���� set�޼ҵ�
	 * @param content ����
	 */
	public void setContent(String content) {
		this.content = content;
	}
  	/**
	 * ���޿��θ� get�ϴ� �޼ҵ�
	 * @return ���� ���� ����
	 */
	public String getIsReceived() {
		return isReceived;
	}
	/**
	 * �������ڷ� ���� ���޿��� ������ set�ϴ� �޼ҵ�
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
