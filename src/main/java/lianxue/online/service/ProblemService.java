package lianxue.online.service;

import java.util.List;
import lianxue.online.model.Problem;

public interface ProblemService {

	List<Problem> selectProblemList(Problem problem);

	void addProblem(Problem problem);

	Problem selecProblem(Long id);

	void editProblem(Problem problem);

	void deleteProblem(Long id);

}
