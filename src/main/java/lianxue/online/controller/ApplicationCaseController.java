package lianxue.online.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import lianxue.online.model.ApplicationCase;
import lianxue.online.service.ApplicationCaseService;
import lianxue.online.util.Config;
import lianxue.online.util.FileUtil;
@Controller
@RequestMapping("/appCase")
public class ApplicationCaseController {
	private static Logger LOGGER = LoggerFactory.getLogger(ApplicationCaseController.class);
	@Autowired
	private ApplicationCaseService applicationCaseService;
	
	@RequestMapping(value="/manager",method=RequestMethod.GET)
	public String manager(){
		return "appCase/appCase";
	}
	
	@RequestMapping(value="/dataGrid")
	@ResponseBody
	public List<ApplicationCase> dataGrid(ApplicationCase applicationCase,Integer page,Integer rows){
		PageHelper.startPage(page, rows);
		List<ApplicationCase> applicationCaseList = applicationCaseService.selectApplicationCaseList(applicationCase);
		return applicationCaseList;
	}
	
	 /**
     * 添加产品页
     *
     * @return
     */
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addPage() {
        return "appCase/appCaseAdd";
    }
    
    @RequestMapping(value="/add",method=RequestMethod.POST)
    @ResponseBody
    public Result add(HttpServletRequest request,ApplicationCase applicationCase){
    	Result result = new Result();
    	try {
    		applicationCase.setApplicationCaseImages(FileUtil.upload(Config.APPCASEIMAGEPATH,request));
    		applicationCaseService.addApplicationCase(applicationCase);
            result.setSuccess(true);
            result.setMsg("添加成功");
            return result;
        } catch (Exception e) {
            LOGGER.error("添加失败：{}", e);
            result.setMsg(e.getMessage());
            return result;
        }
    }
    @RequestMapping(value="/editPage",method=RequestMethod.GET)
    public String editPage(Long id,Model model){
    	ApplicationCase applicationCase = applicationCaseService.selectApplicationCaseById(id);
    	model.addAttribute("appCase", applicationCase);
    	return "appCase/appCaseEdit";
    }
    
    
    @RequestMapping("/edit")
    @ResponseBody
    public Result edit(ApplicationCase applicationCase){
    	Result result = new Result();
    	try {
    		applicationCaseService.editApplicationCase(applicationCase);
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
    		applicationCaseService.deleteApplicationCaseById(id);
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
