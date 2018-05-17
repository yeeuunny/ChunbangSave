package com.lectopia.chunbangsave.vo;

import java.util.HashMap;
/**
 * 
 * @author ����
 *	- �Ѵ� ġ �ϱ� ����
 */
public class MonthDiaryVO {
	/**
	 * Key - day
	 * Value - �Ϸ� �ϱ� ����
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
	 * @return �Ѵ� ����� ����
	 */
	public HashMap<Integer, DiaryVO> getDiarys() {
		return diarys;
	}
	/**
	 * 
	 * @param diarys �Ѵ� ����� ����
	 */
	public void setDiarys(HashMap<Integer, DiaryVO> diarys) {
		this.diarys = diarys;
	}
	/**
	 * 
	 * @param day �߰��� ������
	 * @param addData �߰� �ϱ� ����
	 * @return ���� �߰� ����
	 */
	public boolean put(int day,DiaryVO addData){
		if(this.diarys.put(day, addData)!=null)
			return true;
		return false;
	}
	/**
	 * 
	 * @param day ������ ������
	 * @param reviseData ���� �ϱ� ����
	 * @return ���� ���� ����
	 */
	public boolean replace(int day,DiaryVO reviseData){
		if(this.diarys.replace(day, reviseData)!=null)
			return true;
		return false;
	}
	/**
	 * 
	 * @param day ������ ������
	 * @return �Ͽ� �ش��ϴ� �ϱ�����
	 */
	public DiaryVO get(int day){
		return this.diarys.get(day);
	}
}
