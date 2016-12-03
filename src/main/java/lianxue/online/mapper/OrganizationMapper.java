package lianxue.online.mapper;

import java.util.List;

import lianxue.online.model.Organization;
import tk.mybatis.mapper.common.Mapper;

public interface OrganizationMapper extends Mapper<Organization> {

	List<Organization> findOrganizationAllByPidNull();

	List<Organization> findOrganizationAllByPid(Long id);

}