package cn.scdd.jxc.service.user;

import java.util.List;

import cn.scdd.jxc.entity.ScddUser;

public interface UserService {
	public void saveUser(ScddUser user);
	
	public List<ScddUser> searchByUser(ScddUser user);
	
	public ScddUser searchUserById(int id);
	
	public boolean checkUserExists(ScddUser record);
	
	/**
	 * 根据用户编码获得用户信息
	 * @param code
	 * @return
	 */
	public ScddUser searchByCode(String code);
	
	/**
	 * 根据用户手机号和密码登陆
	 * @param phone
	 * @param password
	 * @return
	 */
	public ScddUser loginUser(String phone, String password);
}
