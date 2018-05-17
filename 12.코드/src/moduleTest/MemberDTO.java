package moduleTest;

import java.util.Arrays;

public class MemberDTO 
{
	private String familyCode;	   //�����ڵ�
	private String registerCode;   //����ڵ� 
	private String id; 		 	   //ID 
	private String password; 	   //PW
	private String name; 		   //�̸�
	private String email; 		   //�̸��� 
	private String passwordQ; 	   //��й�ȣ ã�� ����
	private String passwordA; 	   //��й�ȣ ã�� �亯 
	private String[] birthDate;    //������� 
	private String[] registerDate; //����� 
	public String getId() {
		return id;
	}
	
	public MemberDTO() {
		super();
	}
	public MemberDTO(String familyCode, String registerCode, String id,
			String password, String name, String email, String passwordQ,
			String passwordA, String[] birthDate, String[] registerDate) 
	{
		super();
		this.familyCode = familyCode;
		this.registerCode = registerCode;
		this.id = id;
		this.password = password;
		this.name = name;
		this.email = email;
		this.passwordQ = passwordQ;
		this.passwordA = passwordA;
		this.birthDate = birthDate;
		this.registerDate = registerDate;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String[] getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String[] registerDate) {
		this.registerDate = registerDate;
	}
	public String getFamilyCode() {
		return familyCode;
	}
	public void setFamilyCode(String familyCode) {
		this.familyCode = familyCode;
	}
	public String getRegisterCode() {
		return registerCode;
	}
	public void setRegisterCode(String registerCode) {
		this.registerCode = registerCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String[] birthDate) {
		this.birthDate = birthDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPasswordQ() {
		return passwordQ;
	}
	public void setPasswordQ(String passwordQ) {
		this.passwordQ = passwordQ;
	}
	public String getPasswordA() {
		return passwordA;
	}
	public void setPasswordA(String passwordA) {
		this.passwordA = passwordA;
	}

	@Override
	public String toString() {
		return "MemberDTO [familyCode=" + familyCode + ", registerCode="
				+ registerCode + ", id=" + id + ", password=" + password
				+ ", name=" + name + ", email=" + email + ", passwordQ="
				+ passwordQ + ", passwordA=" + passwordA + ", birthDate="
				+ Arrays.toString(birthDate) + ", registerDate="
				+ Arrays.toString(registerDate) + "]";
	}
	
	
	
}