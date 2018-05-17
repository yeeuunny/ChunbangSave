package com.lectopia.chunbangsave.vo;

public class MemoVO {
	private String memoTitle;//메모제목
	private String memoContent;//메모내용
	/**
	 * default생성자
	 */
	public MemoVO() {
		super();
		this.memoContent="";
		this.memoTitle="";
	}
	/**
	 * OverRoaded생성자
	 * @param memoTitle 메모제목
	 * @param memoContent 메모내용
	 */
	public MemoVO(String memoTitle, String memoContent) {
		super();
		this.memoTitle = memoTitle;
		this.memoContent = memoContent;
	}
	public String getMemoTitle() {
		return memoTitle;
	}
	public void setMemoTitle(String memoTitle) {
		this.memoTitle = memoTitle;
	}
	public String getMemoContent() {
		return memoContent;
	}
	public void setMemoContent(String memoContent) {
		this.memoContent = memoContent;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemoVO other = (MemoVO) obj;
		if (memoContent == null) {
			if (other.memoContent != null)
				return false;
		} else if (!memoContent.equals(other.memoContent))
			return false;
		if (memoTitle == null) {
			if (other.memoTitle != null)
				return false;
		} else if (!memoTitle.equals(other.memoTitle))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "MemoVO [memoTitle=" + memoTitle + ", memoContent="
				+ memoContent + "]";
	}
	
	
	
}
