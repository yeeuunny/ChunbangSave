package moduleTest;

import java.util.Arrays;

public class CashBookUniqueDTO {
	private static int currentBalance;
	private String registerDate[];//원래 String[]
	private int dayLimit;
	private int isCompliment;//원래enum
	
	public CashBookUniqueDTO() {
		super();
	}
	
	public CashBookUniqueDTO(String[] registerDate) {
		super();
		this.registerDate = registerDate;
	}
	
	public CashBookUniqueDTO(String registerDate[], int dayLimit) {
		super();
		this.registerDate = registerDate;
		this.dayLimit = dayLimit;
	}

	public CashBookUniqueDTO(String registerDate[], int dayLimit, int isCompliment) {
		super();
		this.registerDate = registerDate;
		this.dayLimit = dayLimit;
		this.isCompliment = isCompliment;
	}

	public static int getCurrentBalance() {
		return currentBalance;
	}

	public static void setCurrentBalance(int currentBalance) {
		CashBookUniqueDTO.currentBalance = currentBalance;
	}

	public String[] getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String[] registerDate) {
		this.registerDate = registerDate;
	}

	public int getDayLimit() {
		return dayLimit;
	}

	public void setDayLimit(int dayLimit) {
		this.dayLimit = dayLimit;
	}

	public int getIsCompliment() {
		return isCompliment;
	}

	public void setIsCompliment(int isCompliment) {
		this.isCompliment = isCompliment;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dayLimit;
		result = prime * result + isCompliment;
		result = prime * result
				+ ((registerDate == null) ? 0 : registerDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CashBookUniqueDTO other = (CashBookUniqueDTO) obj;
		if (dayLimit != other.dayLimit)
			return false;
		if (isCompliment != other.isCompliment)
			return false;
		if (registerDate == null) {
			if (other.registerDate != null)
				return false;
		} else if (!registerDate.equals(other.registerDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CashBookUniqueDTO [registerDate="
				+ Arrays.toString(registerDate) + ", dayLimit=" + dayLimit
				+ ", isCompliment=" + isCompliment + "]";
	}

	
	
	
}
