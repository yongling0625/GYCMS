package lianxue.online.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import lianxue.online.model.Resource;
import tk.mybatis.mapper.common.Mapper;

public interface ResourceMapper extends Mapper<Resource> {

	List<Resource> findResourceAllByTypeAndPidNull(Integer resourceType);

	List<Resource> findResourceAllByTypeAndPid(@Param("resourceType") Integer resourceType, @Param("pid") Long pid);
}