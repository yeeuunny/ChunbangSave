package com.lectopia.chunbangsave.vo;

import java.util.Arrays;

public class MemberVO 
{
	private int[] registerDate; //년, 월, 일(등록일)
	private String id;			//아이디 
	private String password;	//비밀번호 
	private String familyCode;	//가족코드
	private String registerCode;//등록코드 
	private String name;		//이름 
	private int[] birthDate;	//생년월일
	private String email;		//이메일 
	private String passwordQ;	//비밀번호 질문
	private String passwordA;	//비밀번호 답변 
	
	public MemberVO() {
		super();
	}

	public MemberVO(int[] registerDate, String id, String password,
			String familyCode, String registerCode, String name,
			int[] birthDate, String email, String passwordQ, String passwordA) {
		super();
		this.registerDate = registerDate;
		this.id = id;
		this.password = password;
		this.familyCode = familyCode;
		this.registerCode = registerCode;
		this.name = name;
		this.birthDate = birthDate;
		this.email = email;
		this.passwordQ = passwordQ;
		this.passwordA = passwordA;
	}

	public int[] getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(int[] registerDate) {
		this.registerDate = registerDate;
	}

	public String getId() {
		return id;
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

	public int[] getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(int[] birthDate) {
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
		return "MemberVO [registerDate=" + Arrays.toString(registerDate)
				+ ", id=" + id + ", password=" + password + ", familyCode="
				+ familyCode + ", registerCode=" + registerCode + ", name="
				+ name + ", birthDate=" + Arrays.toString(birthDate)
				+ ", email=" + email + ", passwordQ=" + passwordQ
				+ ", passwordA=" + passwordA + "]";
	}
	
	
}