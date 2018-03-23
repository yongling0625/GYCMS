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
import lianxue.online.model.News;
import lianxue.online.service.NewsService;
@Controller
@RequestMapping("/news")
public class NewsController {
	private static Logger LOGGER = LoggerFactory.getLogger(NewsController.class);
	@Autowired
	private NewsService newsService;
	
	@RequestMapping(value="/manager")
	public String manager(){
		return "news/news";
	}
	
	@RequestMapping(value="/dataGrid")
	@ResponseBody
	public List<News> dataGrid(Integer page,Integer rows){
		PageHelper.startPage(page, rows);
		List<News> newsList = newsService.selectNewsList();
		return newsList;
	}
	
	 /**
     * 添加产品页
     *
     * @return
     */
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addPage() {
        return "news/newsAdd";
    }
    
    
    @RequestMapping(value="/add",method=RequestMethod.POST)
    @ResponseBody
    public Result add(News news){
    	Result result = new Result();
    	try {
    		newsService.addNews(news);
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
    	News news = newsService.selectNews(id);
    	model.addAttribute("news", news);
    	return "news/newsEdit";
    }
    
    
    @RequestMapping("/edit")
    @ResponseBody
    public Result edit(News news){
    	Result result = new Result();
    	try {
			newsService.editNews(news);
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
    		newsService.deleteNews(id);
    		result.setSuccess(true);
    		result.setMsg("删除成功");
    		return result;
    	} catch (Exception e) {
    		LOGGER.error("删除失败：{}", e.getMessage());
    		result.setMsg(e.getMessage());
    		return result;
    	}
    }
    

}
