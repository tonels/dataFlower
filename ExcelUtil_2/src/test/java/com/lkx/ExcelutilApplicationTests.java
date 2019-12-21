package com.lkx;

import java.util.ArrayList;
import java.util.List;

import com.lkx.util.Excel;
import org.junit.Test;
import com.lkx.model.PhoneModel;
import com.lkx.util.ExcelParam;
import com.lkx.util.ExcelUtil;


public class ExcelutilApplicationTests {

	@Test
	public void contextLoads() throws Exception {
		
		String keyValue ="手机名称:phoneName,颜色:color,售价:price,时间:sj"; 
		ExcelParam param = new ExcelParam();
		param.setFilePath("D://testsss.xlsx");
		param.setMap(ExcelUtil.getMap(keyValue));
		param.setClazz(PhoneModel.class);
		//param.setSheetIndex(2);
		
		List<PhoneModel> list =  ExcelUtil.getResult(param);
		
	     for (PhoneModel phoneModel : list) {
			System.out.println(phoneModel);
		}

	     
	}

	/**
	 * 测试注解读取Excel
	 * @throws Exception
	 */
	@Test
	public void testAnnotationReadXls() throws Exception
	{

		List<PhoneModel> list =  ExcelUtil.readXls("D://testxxx.xls",PhoneModel.class);
		for (PhoneModel phoneModel : list) {
			System.out.println(phoneModel);
		}
	}
	
	/**
	 * 测试读取Excel,需要定义的字段和Excel中的表头完全匹配
	 * @throws Exception
	 */
	@Test
	public void testReadXls() throws Exception
	{
		String keyValue ="手机名称:phoneName,颜色:color,售价:price,时间:sj"; 
	     List<PhoneModel> list =  ExcelUtil.readXls("D://testsss.xlsx",ExcelUtil.getMap(keyValue),PhoneModel.class);
	     for (PhoneModel phoneModel : list) {
			System.out.println(phoneModel);
		}
	}
	
	/**
	 * 测试读取Excel,需要定义的字段
	 * @throws Exception
	 */
	@Test
	public void readXlsPart() throws Exception
	{
		String keyValue ="手机名称:phoneName,颜色:color,售价:price,时间:sj"; 
	     List<PhoneModel> list =  ExcelUtil.readXls("D://testsss.xlsx",PhoneModel.class);
	     for (PhoneModel phoneModel : list) {
			System.out.println(phoneModel);
		}
	}
	
	/**
	 * 用List模拟一个数据源，导出到本地磁盘
	 * @throws Exception
	 */
	@Test
	public void testExportExcel() throws Exception{
		List<PhoneModel> list = new ArrayList<PhoneModel>();
		PhoneModel model = null;
		for(int i=0;i<5000;i++){
			model = new PhoneModel();
			model.setColor("金色"+i);
			model.setPhoneName("苹果"+i+"S");
			model.setPrice(i);
			list.add(model);
		}


		String keyValue ="手机名称:phoneName,颜色:color,售价:price"; 
		//ExcelUtil.exportExcel("d:/testsss.xlsx",keyValue,list,PhoneModel.class);
		ExcelUtil.exportExcel("d:/testsss.xlsx",list,PhoneModel.class);
	}

}
