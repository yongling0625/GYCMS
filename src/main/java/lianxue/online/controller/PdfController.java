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
import lianxue.online.model.PdfInfo;
import lianxue.online.service.PdfService;
import lianxue.online.util.Config;
import lianxue.online.util.FileUtil;

@Controller
@RequestMapping("/pdf")
public class PdfController {
	private static Logger LOGGER = LoggerFactory.getLogger(PdfController.class);
	@Autowired
	private PdfService pdfService;
	
	@RequestMapping(value="/manager")
	public String manager(){
		return "pdf/pdf";
	}
	
	@RequestMapping(value="/dataGrid")
	@ResponseBody
	public List<PdfInfo> dataGrid(PdfInfo pdfInfo,Integer page,Integer rows){
		PageHelper.startPage(page, rows);
		List<PdfInfo> pdfList = pdfService.selectPdfList(pdfInfo);
		return pdfList;
	}
	
	 /**
     * 添加
     *
     * @return
     */
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addPage() {
        return "pdf/pdfAdd";
    }
    
    
    @RequestMapping(value="/add",method=RequestMethod.POST)
    @ResponseBody
    public Result add(HttpServletRequest request,PdfInfo pdfInfo){
    	Result result = new Result();
    	try {
    		pdfInfo = FileUtil.uploadPdf(Config.PDFIMAGEPATH,Config.PDFIMAGESHOWPATH,Config.PDFPATH,Config.PDFSHOWPATH,pdfInfo,request);
    		pdfService.addPdf(pdfInfo);
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
    	PdfInfo pdfInfo = pdfService.selecPdf(id);
    	model.addAttribute("pdfInfo", pdfInfo);
    	return "pdf/pdfEdit";
    }
    
    
    @RequestMapping("/edit")
    @ResponseBody
    public Result edit(HttpServletRequest request,PdfInfo pdfInfo){
    	Result result = new Result();
    	try {
    		try{
    			pdfInfo = FileUtil.uploadPdf(Config.PDFIMAGEPATH,Config.PDFIMAGESHOWPATH,Config.PDFPATH,Config.PDFSHOWPATH,pdfInfo,request);
    		}catch (Exception e) {
    		}
    		pdfService.editPdf(pdfInfo);
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
    		pdfService.deletePdf(id);
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
