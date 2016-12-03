package lianxue.online.service;

import java.util.List;

import lianxue.online.model.User;
import lianxue.online.vo.UserVo;

public interface UserService {

	User findUserByLoginName(String username);

	User findUserById(Long id);

	void addUser(UserVo userVo);

	UserVo findUserVoById(Long id);

	void updateUser(UserVo userVo);

	void updateUserPwdById(Long userId, String md5Hex);

	void deleteUserById(Long id);

	List<UserVo> findDataGrid(UserVo userVo);


}
