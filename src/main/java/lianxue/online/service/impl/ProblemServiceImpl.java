package lianxue.online.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lianxue.online.mapper.ProblemMapper;
import lianxue.online.model.Problem;
import lianxue.online.service.ProblemService;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class ProblemServiceImpl implements ProblemService {

	@Autowired
	private ProblemMapper problemMapper;
	
	@Override
	public List<Problem> selectProblemList(Problem problem) {
		Example example = new Example(Problem.class);
		Criteria c = example.createCriteria();
		if(StringUtils.isNotEmpty(problem.getCorporateName())){
			c.andLike("corporateName", "%"+problem.getCorporateName()+"%");
		}
		if(StringUtils.isNotEmpty(problem.getProblem())){
			c.andLike("problem", "%"+problem.getProblem()+"%");
		}
		return problemMapper.selectByExample(example);
	}

	@Override
	public void addProblem(Problem problem) {
		int add = problemMapper.insert(problem);
		if(add != 1){
			throw new RuntimeException("增加失败");
		}
	}

	@Override
	public Problem selecProblem(Long id) {
		return problemMapper.selectByPrimaryKey(id);
	}

	@Override
	public void editProblem(Problem problem) {
		int add = problemMapper.insert(problem);
		if(add != 1){
			throw new RuntimeException("增加失败");
		}
	}

	@Override
	public void deleteProblem(Long id) {
		problemMapper.deleteByPrimaryKey(id);
	}

}
