package lianxue.online.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lianxue.online.mapper.PdfInfoMapper;
import lianxue.online.model.PdfInfo;
import lianxue.online.service.PdfService;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class PdfServiceImpl implements PdfService {

	@Autowired
	private PdfInfoMapper pdfInfoMapper;

	@Override
	public List<PdfInfo> selectPdfList(PdfInfo pdfInfo) {
		Example example = new Example(PdfInfo.class);
		Criteria c = example.createCriteria();
		if(StringUtils.isNotEmpty(pdfInfo.getTitle())){
			c.andLike("title", "%"+pdfInfo.getTitle()+"%");
		}
		if(pdfInfo.getType() != null && !"".equals(pdfInfo.getType())){
			c.andEqualTo("type", pdfInfo.getType());
		}
		return pdfInfoMapper.selectByExample(example);
	}

	@Override
	public void editPdf(PdfInfo pdfInfo) {
		int edit = pdfInfoMapper.updateByPrimaryKey(pdfInfo);
		if(edit != 1){
			throw new RuntimeException("修改失败");
		}
	}

	@Override
	public PdfInfo selecPdf(Long id) {
		return pdfInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public void deletePdf(Long id) {
		pdfInfoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void addPdf(PdfInfo pdfInfo) {
		int add = pdfInfoMapper.insert(pdfInfo);
		if(add != 1){
			throw new RuntimeException("增加失败");
		}
	}
}
