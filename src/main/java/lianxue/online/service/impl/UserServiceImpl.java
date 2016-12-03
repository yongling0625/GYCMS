package lianxue.online.service.impl;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lianxue.online.mapper.UserMapper;
import lianxue.online.mapper.UserRoleMapper;
import lianxue.online.model.User;
import lianxue.online.model.UserRole;
import lianxue.online.service.UserService;
import lianxue.online.vo.UserVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

	private static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;

	@Override
	public User findUserByLoginName(String username) {
		User user = new User();
		user.setLoginname(username);
		return userMapper.selectOne(user);
	}

	@Override
	public User findUserById(Long id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<UserVo> findDataGrid(UserVo userVo) {
		Map<String, Object> condition = new HashMap<String,Object>();
        if (StringUtils.isNoneBlank(userVo.getName())) {
            condition.put("name", userVo.getName());
        }
        if (userVo.getOrganizationId() != null) {
            condition.put("organizationId", userVo.getOrganizationId());
        }
        if (userVo.getCreatedateStart() != null) {
            condition.put("startTime", userVo.getCreatedateStart());
        }
        if (userVo.getCreatedateEnd() != null) {
            condition.put("endTime", userVo.getCreatedateEnd());
        }
		return userMapper.findUserPageCondition(condition);
	}

	@Override
	public void addUser(UserVo userVo) {
		User user = new User();
		try {
			PropertyUtils.copyProperties(user, userVo);
		} catch (Exception e) {
			LOGGER.error("类转换异常：{}", e);
			throw new RuntimeException("类型转换异常：{}", e);
		}
		userMapper.insert(user);

		Long id = user.getId();
		String[] roles = userVo.getRoleIds().split(",");
		UserRole userRole = new UserRole();

		for (String string : roles) {
			userRole.setUserId(id);
			userRole.setRoleId(Long.valueOf(string));
			userRoleMapper.insert(userRole);
		}
	}

	@Override
	public UserVo findUserVoById(Long id) {
		return userMapper.findUserVoById(id);
	}

	@Override
	public void updateUser(UserVo userVo) {
		User user = new User();
		try {
			PropertyUtils.copyProperties(user, userVo);
		} catch (Exception e) {
			LOGGER.error("类转换异常：{}", e);
			throw new RuntimeException("类型转换异常：{}", e);
		}
		userMapper.updateByPrimaryKey(user);
		Long id = userVo.getId();
		List<UserRole> userRoles = userRoleMapper.findUserRoleByUserId(id);
		if (userRoles != null && (!userRoles.isEmpty())) {
			for (UserRole userRole : userRoles) {
				userRoleMapper.deleteByPrimaryKey(userRole.getId());
			}
		}

		String[] roles = userVo.getRoleIds().split(",");
		UserRole userRole = new UserRole();
		for (String string : roles) {
			userRole.setUserId(id);
			userRole.setRoleId(Long.valueOf(string));
			userRoleMapper.insert(userRole);
		}
	}

	@Override
	public void updateUserPwdById(Long userId, String md5Hex) {
		User user = new User();
		user.setId(userId);
		user.setPassword(md5Hex);
		int update = userMapper.updateByPrimaryKeySelective(user);
		if (update != 1) {
			throw new RuntimeException("更新失败");
		}
	}

	@Override
	public void deleteUserById(Long id) {
		int delete = userMapper.deleteByPrimaryKey(id);
		if (delete != 1) {
			throw new RuntimeException("删除失败");
		}
	}

}
