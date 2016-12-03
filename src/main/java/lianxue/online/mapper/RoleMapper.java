package lianxue.online.mapper;

import java.util.List;
import java.util.Map;

import lianxue.online.model.Resource;
import lianxue.online.model.Role;
import tk.mybatis.mapper.common.Mapper;

public interface RoleMapper extends Mapper<Role> {

	List<Map<Long, String>> findRoleResourceListByRoleId(Long roleId);

	List<Resource> findResourceIdListByRoleIdAndType(Long i);

	List<Long> findResourceIdListByRoleId(Long id);

	List<Long> findRoleResourceIdListByRoleId(Long id);
}