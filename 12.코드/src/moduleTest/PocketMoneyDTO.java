package moduleTest;

import java.util.Arrays;

public class PocketMoneyDTO {
	private String registerDate;
	private String category;
	private int amount;
	private String content;
	private int isReceived;
	private String registerCode;
	
	public PocketMoneyDTO() {
		super();
	}
	public PocketMoneyDTO(String registerCode,String registerDate,String category,int amount,
			String content, int isReceived) {
		super();
		this.registerDate = registerDate;
		this.category = category;
		this.amount = amount;
		this.content = content;
		this.isReceived = isReceived;
		this.registerCode = registerCode;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
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
	public int getIsReceived() {
		return isReceived;
	}
	public void setIsReceived(int isReceived) {
		this.isReceived = isReceived;
	}
	public String getRegisterCode() {
		return registerCode;
	}
	public void setRegisterCode(String registerCode) {
		this.registerCode = registerCode;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PocketMoneyDTO other = (PocketMoneyDTO) obj;
		if (amount != other.amount)
			return false;
		if (category != other.category)
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (isReceived != other.isReceived)
			return false;
		if (registerCode == null) {
			if (other.registerCode != null)
				return false;
		} else if (!registerCode.equals(other.registerCode))
			return false;
	
		return true;
	}
	@Override
	public String toString() {
		return "PocketMoneyDTO [registerDate=" +registerDate
				+ ", category=" + category + ", amount=" + amount
				+ ", content=" + content + ", isReceived=" + isReceived
				+ ", registerCode=" + registerCode + "]";
	}
	
}
