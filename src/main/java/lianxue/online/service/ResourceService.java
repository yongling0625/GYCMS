package lianxue.online.service;

import lianxue.online.model.Resource;
import lianxue.online.model.User;
import lianxue.online.vo.Tree;

import java.util.List;

/**
 * @description：资源管理
 * @date：2015/10/1 14:51
 */
public interface ResourceService {

    /**
     * 根据用户查询树形菜单列表
     *
     * @param currentUser
     * @return
     */
    List<Tree> findTree(User currentUser);

	List<Resource> findResourceAll();

	void addResource(Resource resource);

	List<Tree> findAllTree();

	List<Tree> findAllTrees();

	Resource findResourceById(Long id);

	void updateResource(Resource resource);

	void deleteResourceById(Long id);

}
