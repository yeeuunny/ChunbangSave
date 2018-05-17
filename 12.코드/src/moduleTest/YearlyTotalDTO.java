package moduleTest;

public class YearlyTotalDTO {
	private int registerCategory;
	private int detailCategory;
	private int amount;
	public YearlyTotalDTO(){
		this(-1,-1,0);
	}
	public YearlyTotalDTO(int registerCategory, int detailCategory, int amount) {
		super();
		this.registerCategory = registerCategory;
		this.detailCategory = detailCategory;
		this.amount = amount;
	}
	public int getRegisterCategory() {
		return registerCategory;
	}
	public void setRegisterCategory(int registerCategory) {
		this.registerCategory = registerCategory;
	}
	public int getDetailCategory() {
		return detailCategory;
	}
	public void setDetailCategory(int detailCategory) {
		this.detailCategory = detailCategory;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "YearlyTotalDTO [registerCategory=" + registerCategory
				+ ", detailCategory=" + detailCategory + ", amount=" + amount
				+ "]";
	}
	
}
