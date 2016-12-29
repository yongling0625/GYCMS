package lianxue.online.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.PageHelper;
import lianxue.online.code.Result;
import lianxue.online.model.Problem;
import lianxue.online.service.ProblemService;

@Controller
@RequestMapping("/problem")
public class ProblemController {
	private static Logger LOGGER = LoggerFactory.getLogger(ProblemController.class);
	@Autowired
	private ProblemService problemService;
	
	@RequestMapping(value="/manager")
	public String manager(){
		return "problem/problem";
	}
	
	@RequestMapping(value="/dataGrid")
	@ResponseBody
	public List<Problem> dataGrid(Problem problem,Integer page,Integer rows){
		PageHelper.startPage(page, rows);
		List<Problem> problemList = problemService.selectProblemList(problem);
		return problemList;
	}
	
	 /**
     * 添加
     *
     * @return
     */
    @RequestMapping(value = "/problemPage", method = RequestMethod.GET)
    public String addPage() {
        return "problem/problemAdd";
    }
    
    
    @RequestMapping(value="/add",method=RequestMethod.POST)
    @ResponseBody
    public Result add(Problem problem){
    	Result result = new Result();
    	try {
    		problemService.addProblem(problem);
            result.setSuccess(true);
            result.setMsg("添加成功");
            return result;
        } catch (Exception e) {
            LOGGER.error("添加用户失败：{}", e);
            result.setMsg(e.getMessage());
            return result;
        }
    }
    
    
    @RequestMapping(value="/editPage",method=RequestMethod.GET)
    public String editPage(Long id,Model model){
    	Problem problem = problemService.selecProblem(id);
    	model.addAttribute("problem", problem);
    	return "problem/problemEdit";
    }
    
    
    @RequestMapping("/edit")
    @ResponseBody
    public Result edit(Problem problem){
    	Result result = new Result();
    	try {
    		problemService.editProblem(problem);
			result.setSuccess(true);
            result.setMsg("修改成功");
            return result;
		} catch (Exception e) {
			LOGGER.error("修改失败：{}", e);
            result.setMsg(e.getMessage());
            return result;
		}
    }
	
    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(Long id){
    	Result result = new Result();
    	try {
    		problemService.deleteProblem(id);
    		result.setSuccess(true);
    		result.setMsg("删除成功");
    		return result;
    	} catch (Exception e) {
    		LOGGER.error("删除失败：{}", e);
    		result.setMsg(e.getMessage());
    		return result;
    	}
    }
    

}
