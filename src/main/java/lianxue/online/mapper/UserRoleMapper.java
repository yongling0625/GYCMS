package lianxue.online.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import lianxue.online.model.UserRole;
import tk.mybatis.mapper.common.Mapper;

public interface UserRoleMapper extends Mapper<UserRole> {

	List<Long> findRoleIdListByUserId(@Param("userId")Long userId);

	List<UserRole> findUserRoleByUserId(Long id);
}