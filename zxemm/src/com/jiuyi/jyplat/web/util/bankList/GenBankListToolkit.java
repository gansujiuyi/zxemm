package com.jiuyi.jyplat.web.util.bankList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class GenBankListToolkit {
	Logger log = Logger.getLogger(GenBankListToolkit.class);
	
	final String templatesDir = "src/com/uxun/uxunplat/web/util/bankList/";
	final String templatesName = "bankDb.ftl";
	final String xlsName = "bankId.xls";
	final String emmJsPath = "WebRoot/js/order/bankDb.js";
	final String ehouseJsPath = "../ehouse/WebRoot/js/regulatorySeller/bankDb.js";
	private Map<String, Object> dat = new HashMap<String, Object>();
	
	public static void main(String[] args) throws InvalidFormatException, IOException {
		GenBankListToolkit gen = new GenBankListToolkit();
		gen.genData();
		gen.genJs();		
	}
	
	/**
	 * 生成对应的js文件。
	 * @param banks
	 * @throws IOException 
	 */
	public void genJs(){
		try{
			Configuration freeMakerCfg = new Configuration();
			freeMakerCfg.setDefaultEncoding("UTF-8");		
			freeMakerCfg.setDirectoryForTemplateLoading(new File(templatesDir));
			
			Template template = freeMakerCfg.getTemplate(templatesName);
			
			//生成emm中对应文件
			Writer out = new FileWriter(emmJsPath);
			template.process(dat, out);
			
			//生成ehouse中对应文件
			out = new FileWriter(ehouseJsPath);
			template.process(dat, out);
			
			out.flush();
			out.close();
		}catch (Exception e) {
			log.error("生成页面时出现错误：" + e.getMessage(), e);
		}
	}
	
	/**
	 * 读取xls。
	 * @return
	 * @throws IOException 
	 * @throws InvalidFormatException 
	 */
	public void genData() throws InvalidFormatException, IOException{
		List<BankEntity> banks = new ArrayList<BankEntity>();
		Workbook work = WorkbookFactory.create(new File(templatesDir + xlsName));
		Sheet sheet = work.getSheetAt(0);
		for (Row row : sheet) {
			BankEntity bank = new BankEntity();
			bank.setBankId(XlsReaderHelper.getStr(row, 0));
			bank.setBankName(XlsReaderHelper.getStr(row, 1));
			if(StringUtils.isNotBlank(bank.getBankId())&&StringUtils.isNotBlank(bank.getBankName())){
				banks.add(bank);
			}
		}
		dat.put("banks", banks);
	}

}
