package com.lectopia.chunbangsave.vo;

import java.util.HashMap;

public class MonthMemoVO {
	/**
	 * Key - 일
	 * Value - 하루치 메모 정보
	 */
	private HashMap<Integer,MemoVO> memos;
	/**
	 * default생성자
	 */
	public MonthMemoVO() {
		super();
		memos = new HashMap<Integer, MemoVO>();
		

	}
	/**
	 * OverRoaded생성자
	 * @param memos 한달치 메모 정보 hashMap
	 */
	public MonthMemoVO(HashMap<Integer, MemoVO> memos) {
		super();
		this.memos = memos;
	}
	/**
	 * day에 해당하는 데이터 put 메소드
	 * @param day 해당 날짜
	 * @param addDate 추가MemosData
	 */
	public void put(Integer day, MemoVO addData){
		this.memos.put(day, addData);
	}
	/**
	 * day에 해당하는 데이터 replace 메소드
	 * @param day 해당 날짜
	 * @param replaceData replace할 data
	 * @return replace성공 여부
	 */
	public boolean replace(Integer day,MemoVO replaceData){
		//if(this.memos.get(day)==null)this.memos.put(day,new MemoVO());
		if(this.memos.replace(day, replaceData)==null){
			return false;
		}
		return true;
	}
	/**
	 * day에 해당하는 데이터 get 메소드
	 * @param day 해당 날짜
	 * @return 해당 날짜의 MemoVO
	 */
	public MemoVO get(Integer day){
		return this.memos.get(day);
	}
	public HashMap<Integer, MemoVO> getMemos() {
		return memos;
	}
	public void setMemos(HashMap<Integer, MemoVO> memos) {
		this.memos = memos;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MonthMemoVO other = (MonthMemoVO) obj;
		if (memos == null) {
			if (other.memos != null)
				return false;
		} else if (!memos.equals(other.memos))
			return false;
		return true;
	}
	public String toString() {
		return "MonthMemoVO [memos=" + memos + "]";
	}
	
	
}
