package com.lectopia.chunbangsave.vo;

import java.util.HashMap;

public class MonthMemoVO {
	/**
	 * Key - ��
	 * Value - �Ϸ�ġ �޸� ����
	 */
	private HashMap<Integer,MemoVO> memos;
	/**
	 * default������
	 */
	public MonthMemoVO() {
		super();
		memos = new HashMap<Integer, MemoVO>();
		

	}
	/**
	 * OverRoaded������
	 * @param memos �Ѵ�ġ �޸� ���� hashMap
	 */
	public MonthMemoVO(HashMap<Integer, MemoVO> memos) {
		super();
		this.memos = memos;
	}
	/**
	 * day�� �ش��ϴ� ������ put �޼ҵ�
	 * @param day �ش� ��¥
	 * @param addDate �߰�MemosData
	 */
	public void put(Integer day, MemoVO addData){
		this.memos.put(day, addData);
	}
	/**
	 * day�� �ش��ϴ� ������ replace �޼ҵ�
	 * @param day �ش� ��¥
	 * @param replaceData replace�� data
	 * @return replace���� ����
	 */
	public boolean replace(Integer day,MemoVO replaceData){
		//if(this.memos.get(day)==null)this.memos.put(day,new MemoVO());
		if(this.memos.replace(day, replaceData)==null){
			return false;
		}
		return true;
	}
	/**
	 * day�� �ش��ϴ� ������ get �޼ҵ�
	 * @param day �ش� ��¥
	 * @return �ش� ��¥�� MemoVO
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
