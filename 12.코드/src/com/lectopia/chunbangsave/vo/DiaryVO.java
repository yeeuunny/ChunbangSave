package com.lectopia.chunbangsave.vo;

import java.util.Arrays;
/**
 * 
 * @author ����
 *	�ϱ� ����
 */
public class DiaryVO {
	/**
	 * ��� ����(��,��,��)
	 */
	private int[]registerDate;
	/**
	 * �ϱ� ����
	 */
	private String diaryTitle;
	/**
	 * �ϱ� ����
	 */
	private String diaryContent;
	/**
	 * ����
	 */
	private Weather weather;
	/**
	 * default constructor
	 */
	public DiaryVO() {
		super();
	}
	/**
	 * 
	 * @param registerDate ��� ��¥ ( ��,��,�� )
	 * @param diaryTitle �ϱ� ����
	 * @param diaryContent �ϱ� ����
	 * @param weather ����
	 */
	public DiaryVO(int[] registerDate, String diaryTitle,
			String diaryContent, Weather weather) {
		super();
		this.registerDate = registerDate;
		this.diaryTitle = diaryTitle;
		this.diaryContent = diaryContent;
		this.weather = weather;
	}
	/**
	 * 
	 * @return ��� ��¥
	 */
	public int[] getRegisterDate() {
		return registerDate;
	}
	/**
	 * 
	 * @param registerDate ���ο� ��� ��¥
	 */
	public void setRegisterDate(int[] registerDate) {
		this.registerDate = registerDate;
	}
	/**
	 * 
	 * @return �ϱ� ����
	 */
	public String getDiaryTitle() {
		return diaryTitle;
	}
	/**
	 * 
	 * @param diaryTitle ���ο� �ϱ� ����
	 */
	public void setDiaryTitle(String diaryTitle) {
		this.diaryTitle = diaryTitle;
	}
	/**
	 * 
	 * @return �ϱ� ����
	 */
	public String getDiaryContent() {
		return diaryContent;
	}
	/**
	 * 
	 * @param diaryContent ���ο� �ϱ� ����
	 */
	public void setDiaryContent(String diaryContent) {
		this.diaryContent = diaryContent;
	}
	/**
	 * 
	 * @return ���� ���� ��ȯ
	 */
	public Weather getWeather() {
		return weather;
	}
	/**
	 * 
	 * @param weather ���ο� ���� ����
	 */
	public void setWeather(Weather weather) {
		this.weather = weather;
	}
	@Override
	public String toString() {
		return "DiaryVO [registerDate=" + Arrays.toString(registerDate)
				+ ", diaryTitle=" + diaryTitle + ", diaryContent="
				+ diaryContent + ", weather=" + weather + "]";
	}
	
}
