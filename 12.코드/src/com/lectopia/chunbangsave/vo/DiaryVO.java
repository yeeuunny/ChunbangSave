package com.lectopia.chunbangsave.vo;

import java.util.Arrays;
/**
 * 
 * @author 동익
 *	일기 정보
 */
public class DiaryVO {
	/**
	 * 등록 일자(년,월,일)
	 */
	private int[]registerDate;
	/**
	 * 일기 제목
	 */
	private String diaryTitle;
	/**
	 * 일기 내용
	 */
	private String diaryContent;
	/**
	 * 날씨
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
	 * @param registerDate 등록 날짜 ( 년,월,일 )
	 * @param diaryTitle 일기 제목
	 * @param diaryContent 일기 내용
	 * @param weather 날씨
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
	 * @return 등록 날짜
	 */
	public int[] getRegisterDate() {
		return registerDate;
	}
	/**
	 * 
	 * @param registerDate 새로운 등록 날짜
	 */
	public void setRegisterDate(int[] registerDate) {
		this.registerDate = registerDate;
	}
	/**
	 * 
	 * @return 일기 제목
	 */
	public String getDiaryTitle() {
		return diaryTitle;
	}
	/**
	 * 
	 * @param diaryTitle 새로운 일기 제목
	 */
	public void setDiaryTitle(String diaryTitle) {
		this.diaryTitle = diaryTitle;
	}
	/**
	 * 
	 * @return 일기 내용
	 */
	public String getDiaryContent() {
		return diaryContent;
	}
	/**
	 * 
	 * @param diaryContent 새로운 일기 내용
	 */
	public void setDiaryContent(String diaryContent) {
		this.diaryContent = diaryContent;
	}
	/**
	 * 
	 * @return 날씨 정보 반환
	 */
	public Weather getWeather() {
		return weather;
	}
	/**
	 * 
	 * @param weather 새로운 날씨 정보
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
