package lianxue.online.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import lianxue.online.model.PdfInfo;

public class FileUtil {
	
	public static String upload(String path,String showPath,HttpServletRequest request) throws IOException {
		StringBuffer sb = new StringBuffer();
		//创建一个通用的多部分解析器  
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
        //判断 request 是否有文件上传,即多部分请求  
        if(multipartResolver.isMultipart(request)){  
            //转换成多部分request    
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
            //取得request中的所有文件名  
            Iterator<String> iter = multiRequest.getFileNames(); 
            while(iter.hasNext()){  
                //记录上传过程起始时的时间，用来计算上传时间  
                int pre = (int) System.currentTimeMillis();  
                //取得上传文件  
                MultipartFile file = multiRequest.getFile(iter.next());  
                if(file != null){  
                    //取得当前上传文件的文件名称  
                    String fileName = file.getOriginalFilename();
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在
                    if(fileName.trim() !=""){
                        //检查扩展名
                        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                        String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
                        //定义上传路径
                        String paths = path + newFileName;
                        String showPaths = showPath + newFileName;
                        File localFile = new File(paths);
                        file.transferTo(localFile);  
                        sb.append(showPaths).append(",");
                    }  
                }  
            }
        }  
        return sb.substring(0, sb.length()-1);
	}
	public static PdfInfo uploadPdf(String imagePath,String imageShowPath,String pdfPath,String pdfShowPath,PdfInfo pdfInfo,HttpServletRequest request) throws IOException {
		//创建一个通用的多部分解析器  
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
        //判断 request 是否有文件上传,即多部分请求  
        if(multipartResolver.isMultipart(request)){  
            //转换成多部分request    
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
            //取得request中的所有文件名  
            Iterator<String> iter = multiRequest.getFileNames(); 
            while(iter.hasNext()){  
                //记录上传过程起始时的时间，用来计算上传时间  
                int pre = (int) System.currentTimeMillis();  
                //取得上传文件  
                MultipartFile file = multiRequest.getFile(iter.next());  
                if(file != null){  
                    //取得当前上传文件的文件名称  
                    String myFileName = file.getOriginalFilename();  
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
                    if(myFileName.trim() !=""){  
                        System.out.println(myFileName);
                        if(myFileName.trim().toLowerCase().endsWith(".pdf")){
	                        //定义上传路径  
                        	pdfPath = pdfPath + myFileName;
                        	pdfShowPath = pdfShowPath + myFileName;
	                        File localFile = new File(pdfPath);  
	                        file.transferTo(localFile);  
	                        pdfInfo.setPdfAddress(pdfShowPath);
                        }else{
	                        //定义上传路径  
                        	imagePath = imagePath + myFileName;
                        	imageShowPath = imageShowPath + myFileName;
	                        File localFile = new File(imagePath);  
	                        file.transferTo(localFile);  
	                        pdfInfo.setPictureAddress(imageShowPath);
                        }
                    }  
                }  
                //记录上传该文件后的时间  
                int finaltime = (int) System.currentTimeMillis();  
                System.out.println(finaltime - pre);  
            }  
        }  
        return pdfInfo;
	}
}
