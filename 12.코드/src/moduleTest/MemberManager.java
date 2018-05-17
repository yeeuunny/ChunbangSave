package moduleTest;

import java.util.ArrayList;
import java.util.HashMap;

import com.lectopia.chunbangsave.vo.MemberVO;

public class MemberManager 
{
	private HashMap<String, MemberVO> members; //Ű:���̵� 
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
	/* �α��� �� �ϳ��� ȸ������ put */
	public boolean put(String id, MemberVO member)
	{
		this.members.put(id, member);
		return true;
	}
	/* id �ߺ� �˻� */
	public boolean checkId(String id)
	{
		if(memberDAO.getRegisterCode(id) == null)
			return false;
		System.out.println("test");
		return true;
	}
	/* �θ���� id �˻� - ���� ���� & �θ� �´��� */
	public boolean checkParentID(String id)
	{
		String parentCode = memberDAO.getRegisterCode(id);
		/**** �����ϴ���, �θ� �´����� ��ȿ�� �˻� ***/
		if(parentCode != null && parentCode.charAt(0) == 'P')
			return true;
		return false;
	}
	/* ȸ������ �� ȸ�� ������ ���� */ /************/
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
	/* id �� DAO���� ȸ�� ���� get */
	public MemberVO get(String id)
	{
		return this.members.get(id);
	}
	/* id�� ������� get - ����ڵ�, �̸� */
	public String[][] getFamilyInfo(String id)
	{
		//DAO���� �ش� id�� �����ڵ�� ������ ȸ�� ������ ��� �����´�.
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
	/* id�� ��� �ڵ� get */
	public String getRegisterCode(String id)
	{
		if(members.get(id) == null)
			return null;
		return this.members.get(id).getRegisterCode();
	}
	/* �α��� �� ���̵�, ��й�ȣ Ȯ�� & ȸ�� ���� ã�� �Ŵ��� �ʿ� ����  */
	public boolean checkLogin(String id, String password)
	{
		//���̵�, ��й�ȣ�� ���� & ȸ�������� ��ġ�� ��� 
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