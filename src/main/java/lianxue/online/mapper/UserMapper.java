package lianxue.online.mapper;

import java.util.List;
import java.util.Map;

import lianxue.online.model.User;
import lianxue.online.vo.UserVo;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {

	User findUserByLoginName(String username);

	UserVo findUserVoById(Long id);

	List<UserVo> findUserPageCondition(Map<String, Object> condition);
}