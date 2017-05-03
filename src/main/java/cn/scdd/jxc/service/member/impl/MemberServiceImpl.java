package cn.scdd.jxc.service.member.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.scdd.jxc.dao.ScddMemberMapper;
import cn.scdd.jxc.entity.ScddMember;
import cn.scdd.jxc.entity.ScddMemberExample;
import cn.scdd.jxc.service.member.MemberService;
import cn.scdd.jxc.util.Context;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	@Autowired
	private ScddMemberMapper scddMemberMapper;
	
	public void saveMember(ScddMember member) {
		member.setModifyDate(new Date());
		if(member != null && member.getId() != null) {
			ScddMember memberT = this.scddMemberMapper.selectByPrimaryKey(member.getId());
			memberT.setAddress(member.getAddress());
			memberT.setQq(member.getQq());
			memberT.setGender(member.getGender());
			memberT.setWx(member.getWx());
			memberT.setPhone(member.getPhone());
			memberT.setName(member.getName());
			this.scddMemberMapper.updateByPrimaryKey(memberT);
		} else {
			member.setCreateDate(member.getModifyDate());
			this.scddMemberMapper.insert(member);	
		}
	}

	public List<ScddMember> searchByMember(ScddMember member) {
		ScddMemberExample example = new ScddMemberExample();
		example.setOrderByClause("modify_date desc");
		ScddMemberExample.Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(member.getName())) {
			criteria.andNameLike("%" + member.getName() + "%");
		}
		if(StringUtils.isNotBlank(member.getPhone())) {
			criteria.andPhoneLike("%" + member.getPhone() + "%");
		}
		List<ScddMember> list = this.scddMemberMapper.selectByExample(example);
		if(list != null && list.size() > 0) {
			for(ScddMember vo : list) {
				if(StringUtils.isNotBlank(vo.getLevel())) {
					vo.setLevel(Context.MemberLevelEnum.parse(vo.getLevel()).getTile());	
				}
			}
		}
		return list;
	}

	public ScddMember searchMemberById(int id) {
		return this.scddMemberMapper.selectByPrimaryKey(id);
	}

	public boolean checkMemberExists(ScddMember record) {
		// TODO Auto-generated method stub
		return false;
	}
}
