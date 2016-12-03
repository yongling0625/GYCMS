package lianxue.online.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lianxue.online.mapper.OrganizationMapper;
import lianxue.online.model.Organization;
import lianxue.online.service.OrganizationService;
import lianxue.online.vo.Tree;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    private OrganizationMapper organizationMapper;

    @Override
    public List<Tree> findTree() {
        List<Tree> trees = new ArrayList<Tree>();

        List<Organization> organizationFather = organizationMapper.findOrganizationAllByPidNull();

        if (organizationFather != null) {
            for (Organization organizationOne : organizationFather) {
                Tree treeOne = new Tree();

                treeOne.setId(organizationOne.getId());
                treeOne.setText(organizationOne.getName());
                treeOne.setIconCls(organizationOne.getIcon());

                List<Organization> organizationSon = organizationMapper.findOrganizationAllByPid(organizationOne.getId());

                if (organizationSon != null) {
                    List<Tree> tree = new ArrayList<Tree>();
                    for (Organization organizationTwo : organizationSon) {
                        Tree treeTwo = new Tree();
                        treeTwo.setId(organizationTwo.getId());
                        treeTwo.setText(organizationTwo.getName());
                        treeTwo.setIconCls(organizationTwo.getIcon());
                        tree.add(treeTwo);
                    }
                    treeOne.setChildren(tree);
                } else {
                    treeOne.setState("closed");
                }
                trees.add(treeOne);
            }
        }
        return trees;
    }

    @Override
    public List<Organization> findTreeGrid() {
        return organizationMapper.selectAll();
    }

    @Override
    public void addOrganization(Organization organization) {
    	organization.setCreatedate(new Date());
        organizationMapper.insert(organization);
    }

    @Override
    public Organization findOrganizationById(Long id) {
        return organizationMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateOrganization(Organization organization) {
        organizationMapper.updateByPrimaryKey(organization);
    }

    @Override
    public void deleteOrganizationById(Long id) {
        organizationMapper.deleteByPrimaryKey(id);
    }

}
