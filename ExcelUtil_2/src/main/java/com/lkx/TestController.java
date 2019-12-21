package com.lkx;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lkx.model.PhoneModel;
import com.lkx.util.ExcelUtil;

@Controller
public class TestController {
	
	@GetMapping(value = "/")
	public String ss1(){
		return "index";
	}
	
	@PostMapping(value = "/test")
	@ResponseBody
	public List<PhoneModel> testImport(MultipartFile file) throws Exception{

		String keyValue ="手机名称:phoneName,颜色:color,售价:price";
		List<PhoneModel> list = ExcelUtil.readXls(file.getBytes(), ExcelUtil.getMap(keyValue), PhoneModel.class);
		return list;
	}

	/**
	 * @Function 注解测试
	 * @author   likaixuan
	 * @Date     2019-10-10 18:31
	 * @param     * @param null
	 * @return
	 */
	@PostMapping(value = "/test1")
	@ResponseBody
	public List<PhoneModel> testImport1(MultipartFile file) throws Exception{
		List<PhoneModel> list = ExcelUtil.readXls(file.getBytes(), PhoneModel.class);
		return list;
	}

	@GetMapping(value = "/export")
	public void testExport(HttpServletResponse response) throws Exception{
		 
		List<PhoneModel> list = new ArrayList<>();
		for(int i=0;i<100;i++){
			PhoneModel model = new PhoneModel();
			model.setColor("金色"+i);
			model.setPhoneName("苹果"+i+"S");
			model.setPrice(i);
			list.add(model);
		}
		String keyValue ="手机名称:phoneName,颜色:color,售价:price,时间:sj"; 
		ExcelUtil.exportExcelOutputStream(response,list,PhoneModel.class,"测试Excel导出");
	}
	
	@GetMapping(value = "/json")
	@ResponseBody
	public BizResult<PhoneModel> ss(){
		
		List<PhoneModel> list = new ArrayList<>();
		
		
		PhoneModel model = new PhoneModel();
		model.setColor("土豪金");
		model.setPhoneName("ipone X");
		model.setSj(new Date());
		
		list.add(model);
		
		BizResult<PhoneModel> result = new BizResult<>();
		result.setCode("0");
		result.setData(model);
		result.setList(list);
		
		return result;
	}
	
}
