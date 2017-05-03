package cn.scdd.jxc.service.user;

import java.util.List;

import cn.scdd.jxc.entity.ScddUser;

public interface UserService {
	public void saveUser(ScddUser user);
	
	public List<ScddUser> searchByUser(ScddUser user);
	
	public ScddUser searchUserById(int id);
	
	public boolean checkUserExists(ScddUser record);
}
