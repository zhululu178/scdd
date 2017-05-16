package cn.scdd.jxc.service.user.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.scdd.jxc.dao.ScddUserMapper;
import cn.scdd.jxc.entity.ScddUser;
import cn.scdd.jxc.entity.ScddUserExample;
import cn.scdd.jxc.service.user.UserService;
import cn.scdd.jxc.util.Context.DeleteFlagEnum;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private ScddUserMapper scddUserMapper;
	
	public void saveUser(ScddUser user) {
		user.setModifyDate(new Date());
		if(user != null && user.getId() != null) {
			ScddUser userT = this.scddUserMapper.selectByPrimaryKey(user.getId());
			userT.setAddress(user.getAddress());
			userT.setAgentFlag(user.getAgentFlag());
			userT.setBirthday(user.getBirthday());
			userT.setGender(user.getGender());
			userT.setQq(user.getQq());
			userT.setWx(user.getWx());
			userT.setPhone(user.getPhone());
			userT.setName(user.getName());
			userT.setDeleteFlag(DeleteFlagEnum.NO.getCode());
			this.scddUserMapper.updateByPrimaryKey(userT);
		} else {
			user.setCreateDate(user.getModifyDate());
			this.scddUserMapper.insert(user);	
		}
	}

	public List<ScddUser> searchByUser(ScddUser user) {
		ScddUserExample example = new ScddUserExample();
		example.setOrderByClause("modify_date desc");
		ScddUserExample.Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(user.getName())) {
			criteria.andNameLike("%" + user.getName() + "%");
		}
		if(StringUtils.isNotBlank(user.getPhone())) {
			criteria.andPhoneLike("%" + user.getPhone() + "%");
		}
		if(StringUtils.isNotBlank(user.getCode())) {
			criteria.andCodeLike("%" + user.getCode() + "%");
		}
		return this.scddUserMapper.selectByExample(example);
	}

	public ScddUser searchUserById(int id) {
		return this.scddUserMapper.selectByPrimaryKey(id);
	}

	public boolean checkUserExists(ScddUser record) {
		int count =  this.scddUserMapper.checkUserExists(record);
		return count > 0?true:false;
	}

	public ScddUser searchByCode(String code) {
		ScddUserExample example = new ScddUserExample();
		ScddUserExample.Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(code)) {
			criteria.andCodeEqualTo(code);
		}
		List<ScddUser> users = this.scddUserMapper.selectByExample(example);
		if(users != null && users.size() != 1) {
			return null;
		} else {
			return users.get(0);
		}
	}
}
