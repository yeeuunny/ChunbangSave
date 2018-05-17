package moduleTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;


public class MemberDAO 
{
	private HashMap<String, MemberDTO> memberInfo;
 
	public MemberDAO() 
	{
		this(new HashMap<String, MemberDTO>());
	}
	public MemberDAO(HashMap<String, MemberDTO> memberInfo) 
	{
		super();
		this.memberInfo = memberInfo;
	}
	public HashMap<String, MemberDTO> getMemberInfoMap() 
	{
		return memberInfo;
	}
	public void setMemberInfoMap(HashMap<String, MemberDTO> memberInfo) 
	{
		this.memberInfo = memberInfo;
	}
	
	public HashMap<String, MemberDTO> loadMemberInfoFile(String fileName)
	{
		BufferedReader memberInfoReader = null;
		try
		{
			memberInfoReader = new BufferedReader(new FileReader(fileName));

			String str = null;
			while((str = memberInfoReader.readLine()) != null)
			{
				StringTokenizer token = new StringTokenizer(str, "/");
				MemberDTO memberInfoDTO = new MemberDTO();
				while(token.hasMoreTokens())
				{
					memberInfoDTO.setFamilyCode(token.nextToken());
					memberInfoDTO.setRegisterCode(token.nextToken());
					memberInfoDTO.setId(token.nextToken());
					memberInfoDTO.setPassword(token.nextToken());
					memberInfoDTO.setName(token.nextToken());
					memberInfoDTO.setEmail(token.nextToken());
					memberInfoDTO.setPasswordQ(token.nextToken());
					memberInfoDTO.setPasswordA(token.nextToken());

					StringTokenizer dateToken = new StringTokenizer(token.nextToken(), "-");
					memberInfoDTO.setBirthDate(new String[]{ dateToken.nextToken(), dateToken.nextToken(), dateToken.nextToken() });
					StringTokenizer dateToken2 = new StringTokenizer(token.nextToken(), "-");
					memberInfoDTO.setRegisterDate(new String[]{ dateToken2.nextToken(), dateToken2.nextToken(), dateToken2.nextToken() });
				}
				put(memberInfoDTO.getId(), memberInfoDTO);
			}
		}
		catch(IOException e)
		{
			System.out.println("파일 입력 오류");
			e.getStackTrace();
		}
		finally
		{
			try { memberInfoReader.close(); }
			catch(Exception e) { e.getStackTrace(); }
		}
		return this.memberInfo;
	}
	public void saveMemberInfoFile(String fileName)
	{
		PrintWriter memberInfoWriter = null;
		String[] keys = (String[])(this.memberInfo.keySet().toArray(new String[this.memberInfo.size()]));
		try
		{
			memberInfoWriter = new PrintWriter(new FileWriter(fileName));
			int i = 0;
			while(i != memberInfo.size())
			{
				MemberDTO memberInfo = get(keys[i]);
				memberInfoWriter.println(memberInfo.getFamilyCode() +"/" + memberInfo.getRegisterCode() + "/"
						+ keys[i] + "/" + memberInfo.getPassword() + "/" 
						+ memberInfo.getName() + "/" + memberInfo.getEmail() + "/"
						+ memberInfo.getPasswordQ() + "/" + memberInfo.getPasswordA() + "/" 
						+ memberInfo.getBirthDate()[0] + "-" + memberInfo.getBirthDate()[1] + "-" + memberInfo.getBirthDate()[2] + "/"  
						+ memberInfo.getRegisterDate()[0] + "-" + memberInfo.getRegisterDate()[1] + "-" + memberInfo.getRegisterDate()[2]);
				i++;		
			}
		}
		catch(IOException e)
		{
			System.out.println("파일 출력 오류");
			e.getStackTrace();
		}
		finally
		{
			try { memberInfoWriter.close(); }
			catch(Exception e) { e.getStackTrace(); }
		}
	}
	/* id로 하나의 회원 정보 put */
	public MemberDTO put(String id, MemberDTO memberInfo)
	{
		return this.memberInfo.put(id, memberInfo);
	}
	/* id로 하나의 회원 정보 remove */
	public MemberDTO remove(String id)
	{
		return this.memberInfo.remove(id);
	}
	/* id에 해당하는 회원 정보 replace */
	public MemberDTO replace(String id, MemberDTO memberInfo)
	{
		return this.memberInfo.replace(id, memberInfo);
	}
	/* id로 회원 정보 get */
	public MemberDTO get(String id)
	{
		return this.memberInfo.get(id);
	}
	/* id로 등록 코드 get */
	public String getRegisterCode(String id)
	{
		MemberDTO memberDTO = memberInfo.get(id);
		if(memberDTO == null)
			return null;
	
		return memberDTO.getRegisterCode();
	}
	/* id로 가족 코드 get */
	public String getFamilyCode(String id)
	{
		return this.memberInfo.get(id).getFamilyCode();
	}
	/* id로 이름 get */
	public String getName(String id)
	{
		return this.memberInfo.get(id).getName();
	}
	/* 가족코드로 id 여러개 get  */
	public ArrayList<String> getIds(String familyCode)
	{
		ArrayList<String> list = new ArrayList<String>();
		String[] keys = (String[]) this.memberInfo.keySet().toArray(new String[this.memberInfo.size()]);
		
		for(String i : keys)
		{
			if(getFamilyCode(i).equals(familyCode))
				list.add(i);
		}
		return list;
	}
	/* 가족코드로 등록코드 여러개 get */
	public ArrayList<String> getRegisterCodes(String familyCode)
	{
		ArrayList<String> list = new ArrayList<String>();
		String[] keys = (String[]) this.memberInfo.keySet().toArray(new String[this.memberInfo.size()]);
		
		for(String i : keys)
		{
			if(getFamilyCode(i).equals(familyCode))
				list.add(getRegisterCode(i));
		}
		return list;
	}
	/* 가족코드로 회원 정보 여러개 get */
	public ArrayList<MemberDTO> getMemberInfo(String familyCode)
	{
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
		String[] keys = (String[]) this.memberInfo.keySet().toArray(new String[this.memberInfo.size()]);
		
		for(String i : keys)
		{
			if(getFamilyCode(i).equals(familyCode))
				list.add(get(i));
		}
		return list;
	}
	/* 로그인 시 아이디 비밀번호 유효성 검사 메소드 */
	public boolean checkMemberInfo(String id,String password) 
	{
		MemberDTO memberDTO = memberInfo.get(id);
		if(memberDTO == null)
			return false;
		
		if(memberDTO.getPassword().equals(password))
			return true;
		return false;
	}
	public String toString() 
	{
		return "MemberInfoDAO [memberInfo=" + memberInfo + "]";
	}
	
	
}