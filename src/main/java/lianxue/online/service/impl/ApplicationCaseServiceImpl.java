package lianxue.online.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lianxue.online.mapper.ApplicationCaseMapper;
import lianxue.online.model.ApplicationCase;
import lianxue.online.service.ApplicationCaseService;
@Service
public class ApplicationCaseServiceImpl implements ApplicationCaseService {
	@Autowired
	private ApplicationCaseMapper applicationCaseMapper;

	@Override
	public void addApplicationCase(ApplicationCase applicationCase) {
		applicationCaseMapper.insertSelective(applicationCase);
	}

	@Override
	public List<ApplicationCase> selectApplicationCaseList(ApplicationCase applicationCase) {
		return applicationCaseMapper.select(applicationCase);
	}

	@Override
	public void editApplicationCase(ApplicationCase applicationCase) {
		applicationCaseMapper.updateByPrimaryKeySelective(applicationCase);
		
	}

	@Override
	public void deleteApplicationCaseById(Long id) {
		applicationCaseMapper.deleteByPrimaryKey(id);
	}

	@Override
	public ApplicationCase selectApplicationCaseById(Long id) {
		return applicationCaseMapper.selectByPrimaryKey(id);
	}

}
