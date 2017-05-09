package cn.scdd.jxc.service.member;

import java.util.List;

import cn.scdd.jxc.entity.ScddMember;

public interface MemberService {
	public void saveMember(ScddMember member);
	
	public List<ScddMember> searchByMember(ScddMember member);
	
	public ScddMember searchMemberById(int id);
	
	public boolean checkMemberExists(ScddMember record);
	
	/**
	 * 根据会员电话查找会员信息
	 * @param phone
	 * @return
	 */
	public ScddMember searchMemberByPhone(String phone);
}
