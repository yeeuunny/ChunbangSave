package com.lectopia.chunbangsave.vo;

import java.util.HashMap;
/**
 * 
 * @author 동익
 *	- 한달 치 일기 정보
 */
public class MonthDiaryVO {
	/**
	 * Key - day
	 * Value - 하루 일기 정보
	 */
	HashMap<Integer, DiaryVO> diarys;
	/**
	 * default constructor
	 */
	public MonthDiaryVO() {
		super();
		this.diarys = new HashMap<Integer, DiaryVO>();
	}
	/**
	 * 
	 * @param diarys
	 */
	public MonthDiaryVO(HashMap<Integer, DiaryVO> diarys) {
		super();
		this.diarys = diarys;
	}
	/**
	 * 
	 * @return 한달 가계부 정보
	 */
	public HashMap<Integer, DiaryVO> getDiarys() {
		return diarys;
	}
	/**
	 * 
	 * @param diarys 한달 가계부 정보
	 */
	public void setDiarys(HashMap<Integer, DiaryVO> diarys) {
		this.diarys = diarys;
	}
	/**
	 * 
	 * @param day 추가할 일정보
	 * @param addData 추가 일기 정보
	 * @return 정상 추가 여부
	 */
	public boolean put(int day,DiaryVO addData){
		if(this.diarys.put(day, addData)!=null)
			return true;
		return false;
	}
	/**
	 * 
	 * @param day 수정할 일정보
	 * @param reviseData 수정 일기 정보
	 * @return 정상 수정 여부
	 */
	public boolean replace(int day,DiaryVO reviseData){
		if(this.diarys.replace(day, reviseData)!=null)
			return true;
		return false;
	}
	/**
	 * 
	 * @param day 가져올 일정보
	 * @return 일에 해당하는 일기정보
	 */
	public DiaryVO get(int day){
		return this.diarys.get(day);
	}
}
