package moduleTest;
import java.util.Arrays;


public class SavingGoalDTO 
{
	private String registerCode;     //����ڵ� 
	private String savingGoalTitle;  //��ǥ���� 
	private String savingGoalAmount; //��ǥ�ݾ� 
	private String[] startDate;      //������
	private String successRate;      //�޼���
	private String quarter;			 //�б�����
	
	public SavingGoalDTO() {
		super();
	}

	public SavingGoalDTO(String registerCode, String savingGoalTitle,
			String savingGoalAmount, String[] startDate, String successRate,
			String quarter) {
		super();
		this.registerCode = registerCode;
		this.savingGoalTitle = savingGoalTitle;
		this.savingGoalAmount = savingGoalAmount;
		this.startDate = startDate;
		this.successRate = successRate;
		this.quarter = quarter;
	}

	public String getSavingGoalTitle() {
		return savingGoalTitle;
	}

	public void setSavingGoalTitle(String savingGoalTitle) {
		this.savingGoalTitle = savingGoalTitle;
	}

	public String getSavingGoalAmount() {
		return savingGoalAmount;
	}

	public void setSavingGoalAmount(String savingGoalAmount) {
		this.savingGoalAmount = savingGoalAmount;
	}

	public String[] getStartDate() {
		return startDate;
	}

	public void setStartDate(String[] startDate) {
		this.startDate = startDate;
	}

	public String getSuccessRate() {
		return successRate;
	}

	public void setSuccessRate(String successRate) {
		this.successRate = successRate;
	}

	public String getRegisterCode() {
		return registerCode;
	}

	public void setRegisterCode(String registerCode) {
		this.registerCode = registerCode;
	}
	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}

	@Override
	public String toString() {
		return "SavingGoalDTO [registerCode=" + registerCode
				+ ", savingGoalTitle=" + savingGoalTitle
				+ ", savingGoalAmount=" + savingGoalAmount + ", startDate="
				+ Arrays.toString(startDate) + ", successRate=" + successRate
				+ ", quarter=" + quarter + "]";
	}
}
