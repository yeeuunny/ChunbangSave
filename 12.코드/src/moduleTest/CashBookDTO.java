package moduleTest;

import java.util.Arrays;

public class CashBookDTO 
{
	private String[] registerDate;
	private int registerCategory;
	private String detailCategory;
	private int amount;
	private String content;
	
	
	public CashBookDTO() {
		super();
	}

	public CashBookDTO(String[] registerDate, int registerCategory,
			String detailCategory, int amount, String content) {
		super();
		this.registerDate = registerDate;
		this.registerCategory = registerCategory;
		this.detailCategory = detailCategory;
		this.amount = amount;
		this.content = content;
	}

	
	public String[] getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String[] registerDate) {
		this.registerDate = registerDate;
	}

	public int getRegisterCategory() {
		return registerCategory;
	}

	public void setRegisterCategory(int registerCategory) {
		this.registerCategory = registerCategory;
	}

	public String getDetailCategory() {
		return detailCategory;
	}

	public void setDetailCategory(String detailCategory) {
		this.detailCategory = detailCategory;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "CashBookDTO [registerDate=" + Arrays.toString(registerDate)
				+ ", registerCategory=" + registerCategory
				+ ", detailCategory=" + detailCategory + ", amount=" + amount
				+ ", content=" + content + "]";
	}


	
}
