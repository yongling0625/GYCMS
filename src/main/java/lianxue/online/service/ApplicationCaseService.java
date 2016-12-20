package lianxue.online.service;

import java.util.List;

import lianxue.online.model.ApplicationCase;

public interface ApplicationCaseService {

	void addApplicationCase(ApplicationCase applicationCase);

	List<ApplicationCase> selectApplicationCaseList(ApplicationCase applicationCase);

	void editApplicationCase(ApplicationCase applicationCase);

	void deleteApplicationCaseById(Long id);

	ApplicationCase selectApplicationCaseById(Long id);

}
