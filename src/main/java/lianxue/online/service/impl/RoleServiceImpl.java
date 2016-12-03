package lianxue.online.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lianxue.online.mapper.RoleMapper;
import lianxue.online.mapper.RoleResourceMapper;
import lianxue.online.mapper.UserRoleMapper;
import lianxue.online.model.Role;
import lianxue.online.model.RoleResource;
import lianxue.online.service.RoleService;
import lianxue.online.vo.Tree;

@Service
public class RoleServiceImpl implements RoleService {
	private static Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
    private RoleResourceMapper roleResourceMapper;

	public List<Long> findRoleIdListByUserId(Long userId) {
		return userRoleMapper.findRoleIdListByUserId(userId);
	}

	public List<Map<Long, String>> findRoleResourceListByRoleId(Long roleId) {
		return roleMapper.findRoleResourceListByRoleId(roleId);
	}

	@Override
	public void addRole(Role role) {
		int insert = roleMapper.insert(role);
		if (insert != 1) {
			logger.warn("插入失败，参数：{}", role.toString());
			throw new RuntimeException("插入失败");
		}
	}

	@Override
	public void deleteRoleById(Long id) {
		int delete = roleMapper.deleteByPrimaryKey(id);
		if (delete != 1) {
			logger.warn("删除失败，参数：{}", id);
			throw new RuntimeException("删除失败");
		}
	}

	@Override
	public Role findRoleById(Long id) {
		return roleMapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateRole(Role role) {
		int update = roleMapper.updateByPrimaryKeySelective(role);
		if (update != 1) {
			logger.warn("更新失败，参数：{}", role.toString());
			throw new RuntimeException("更新失败");
		}
	}

	@Override
	public List<Long> findResourceIdListByRoleId(Long id) {
		return roleMapper.findResourceIdListByRoleId(id);
	}

	@Override
	public void updateRoleResource(Long id, String resourceIds) {
		// 先删除后添加,有点爆力
		List<Long> roleResourceIdList = roleMapper.findRoleResourceIdListByRoleId(id);
		if (roleResourceIdList != null && (!roleResourceIdList.isEmpty())) {
			for (Long roleResourceId : roleResourceIdList) {
				roleResourceMapper.deleteByPrimaryKey(roleResourceId);
			}
//			Example example = new Example(RoleResource.class);
//			Criteria c = example.createCriteria();
//			c.andIn("id", roleResourceIdList);
//			roleResourceMapper.deleteByExample(example);
		}
		String[] resources = resourceIds.split(",");
		RoleResource roleResource = new RoleResource();
		for (String string : resources) {
			roleResource.setRoleId(id);
			roleResource.setResourceId(Long.parseLong(string));
			roleResourceMapper.insert(roleResource);
		}
	}

	@Override
	public List<Tree> findTree() {
		List<Tree> trees = new ArrayList<Tree>();
        List<Role> roles = roleMapper.selectAll();
        for (Role role : roles) {
            Tree tree = new Tree();
            tree.setId(role.getId());
            tree.setText(role.getName());

            trees.add(tree);
        }
        return trees;
	}

	@Override
	public List<Role> findDataGrid() {
		return roleMapper.selectAll();
	}

}
