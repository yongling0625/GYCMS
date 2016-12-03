package lianxue.online.service;

import java.util.List;
import java.util.Map;

import lianxue.online.model.Role;
import lianxue.online.vo.Tree;

public interface RoleService {

	List<Long> findRoleIdListByUserId(Long id);

	List<Map<Long, String>> findRoleResourceListByRoleId(Long roleId);

	void addRole(Role role);

	void deleteRoleById(Long id);

	Role findRoleById(Long id);

	void updateRole(Role role);

	List<Long> findResourceIdListByRoleId(Long id);

	void updateRoleResource(Long id, String resourceIds);

	List<Tree> findTree();

	List<Role> findDataGrid();


}
