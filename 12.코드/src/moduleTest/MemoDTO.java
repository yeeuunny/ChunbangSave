package moduleTest;

import java.util.Arrays;

public class MemoDTO 
{
	private String registerDate[]; //����� 
	private String memoTitle;    	//�޸����� 
	private String memoContent;		//�޸𳻿�
	
	public MemoDTO() {
		super();
	}
	public MemoDTO(String[] registerDate, String memoTitle, String memoContent) {
		super();
		this.registerDate = registerDate;
		this.memoTitle = memoTitle;
		this.memoContent = memoContent;
	}
	public String[] getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String[] registerDate) {
		this.registerDate = registerDate;
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
	public String toString() {
		return "MemoDTO [registerDate=" + Arrays.toString(registerDate)
				+ ", memoTitle=" + memoTitle + ", memoContent=" + memoContent
				+ "]";
	}
	
	
}
