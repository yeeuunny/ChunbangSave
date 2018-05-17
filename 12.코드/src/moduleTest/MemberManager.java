package moduleTest;

import java.util.ArrayList;
import java.util.HashMap;

import com.lectopia.chunbangsave.vo.MemberVO;

public class MemberManager 
{
	private HashMap<String, MemberVO> members; //키:아이디 
	private MemberDAO memberDAO;
	
	public MemberManager() 
	{
		this(new HashMap<String, MemberVO>(), new MemberDAO());
	}

	public MemberManager(HashMap<String, MemberVO> members, MemberDAO memberDAO)
	{
		super();
		this.members = members;
		this.memberDAO = memberDAO;
		
		memberDAO.loadMemberInfoFile("info.txt");
	}

	/* access methods */
	public HashMap<String, MemberVO> getMembers()
	{
		return members;
	}
	public void setMembers(HashMap<String, MemberVO> members) 
	{
		this.members = members;
	}
	public MemberDAO getMemberDAO() 
	{
		return memberDAO;
	}
	public void setMemberDAO(MemberDAO memberDAO) 
	{
		this.memberDAO = memberDAO;
	}
	
	/* custom methods */
	/* 로그인 시 하나의 회원정보 put */
	public boolean put(String id, MemberVO member)
	{
		this.members.put(id, member);
		return true;
	}
	/* id 중복 검사 */
	public boolean checkId(String id)
	{
		if(memberDAO.getRegisterCode(id) == null)
			return false;
		System.out.println("test");
		return true;
	}
	/* 부모님의 id 검사 - 존재 여부 & 부모가 맞는지 */
	public boolean checkParentID(String id)
	{
		String parentCode = memberDAO.getRegisterCode(id);
		/**** 존재하는지, 부모가 맞는지의 유효성 검사 ***/
		if(parentCode != null && parentCode.charAt(0) == 'P')
			return true;
		return false;
	}
	/* 회원가입 시 회원 정보를 저장 */ /************/
	public boolean save(String id, MemberVO memberVO)
	{
		/*** DTO -> VO ***/
		
		MemberDTO memberDTO = new MemberDTO(memberVO.getFamilyCode(), memberVO.getRegisterCode(), 
				memberVO.getId(), memberVO.getPassword(), memberVO.getName(), memberVO.getEmail(), 
				memberVO.getPassword(), memberVO.getPasswordA(),
				new String[] { Integer.toString(memberVO.getBirthDate()[0]),
							   Integer.toString(memberVO.getBirthDate()[1]), 
							   Integer.toString(memberVO.getBirthDate()[2]) 
							 },
				new String[] { Integer.toString(memberVO.getRegisterDate()[0]), 
							   Integer.toString(memberVO.getRegisterDate()[1]), 
							   Integer.toString(memberVO.getRegisterDate()[2])
							 });
		memberDAO.put(id, memberDTO);
		memberDAO.saveMemberInfoFile("info.txt");
		return true;
	}
	/* id 로 DAO에서 회원 정보 get */
	public MemberVO get(String id)
	{
		return this.members.get(id);
	}
	/* id의 가족목록 get - 등록코드, 이름 */
	public String[][] getFamilyInfo(String id)
	{
		//DAO에서 해당 id의 가족코드와 동일한 회원 정보를 모두 가져온다.
		ArrayList<MemberDTO> list =
				memberDAO.getMemberInfo(members.get(id).getFamilyCode());
		
		String family[][] = new String[list.size()][];
		
		for(int i = 0; i < list.size(); i++)
		{
			family[i][0] = list.get(i).getRegisterCode();
			family[i][1] = list.get(i).getName();
		}
		return family;
	}
	/* id의 등록 코드 get */
	public String getRegisterCode(String id)
	{
		if(members.get(id) == null)
			return null;
		return this.members.get(id).getRegisterCode();
	}
	/* 로그인 시 아이디, 비밀번호 확인 & 회원 정보 찾아 매니저 맵에 저장  */
	public boolean checkLogin(String id, String password)
	{
		//아이디, 비밀번호가 존재 & 회원정보와 일치할 경우 
		 if(memberDAO.checkMemberInfo(id, password) == true)
		 {
			 MemberDTO memberDTO = memberDAO.get(id);
			 MemberVO  memberVO  = new MemberVO(
						new int[] { Integer.parseInt(memberDTO.getRegisterDate()[0]),
								Integer.parseInt(memberDTO.getRegisterDate()[1]),
								Integer.parseInt(memberDTO.getRegisterDate()[2]) },
								id, memberDTO.getPassword(), memberDTO.getFamilyCode(), 
								memberDTO.getRegisterCode(), memberDTO.getName(), 
								new int[] {  Integer.parseInt(memberDTO.getBirthDate()[0]),
							Integer.parseInt(memberDTO.getBirthDate()[0]),
							Integer.parseInt(memberDTO.getBirthDate()[1]),
							Integer.parseInt(memberDTO.getBirthDate()[2]) },
							memberDTO.getEmail(), memberDTO.getPasswordQ(), memberDTO.getPasswordA());
							
			 if(put(id, memberVO) == true)
					 return true;
		 }
		 return false;
	}
	
	public String toString() 
	{
		return "MemberManager [members=" + members + ", memberDAO=" + memberDAO
				+ "]";
	}
	
	
}