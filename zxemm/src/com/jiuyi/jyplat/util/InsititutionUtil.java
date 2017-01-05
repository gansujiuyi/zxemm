/**
 * 说明：Excel导入类
 * @author Frank
 * @version v1.0
 * @since 2012-05-07
 */
package com.jiuyi.jyplat.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.jfree.util.Log;

import com.jiuyi.jyplat.entity.system.Institution;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class InsititutionUtil {
	
	static {
		try {
			qurInstsPyinList();
			System.out.println("***************************加载支行机构完成************************");
		} catch (Exception e) {
			System.out.println("查询所有机构失败.原因：" + e.getMessage());
		}
	}

	public static List<Map<String, String>> bankInstitution = null;
	//查询机构并转换机构名称为拼音
	public static void qurInstsPyinList() throws Exception{
		
		

		List<Institution> lists=new ArrayList<Institution>();
		
		try {
			String allInstSql = "SELECT INST.INSTITUTIONNO,INST.INSTITUTIONNAME,INST.PARENTINSTITUTIONID "
					+ " FROM INSTITUTION INST ORDER BY INST.INSTITUTIONID ";
			String[][] allInst = new DBAction().executeSearchAll(allInstSql);

			if (allInst == null)
				return;

			// 第一次循环取第一级机构(即分行)
			for (int i = 0; i < allInst.length; i++) {
				Institution institution=new Institution();
				institution.setInstitutionNo(allInst[i][0]);
				institution.setInstitutionName(allInst[i][1]);
				lists.add(institution);
			}
			List<Map<String, String>> pinyinLists=new ArrayList<Map<String, String>>();
			String institutionName="";
			String institutionNo="";
			for(int i=0;i<lists.size();i++){
				Map<String, String> map = new HashMap<String, String>();
				institutionName=lists.get(i).getInstitutionName();
				institutionNo=lists.get(i).getInstitutionNo();
				map.put("institutionNo", institutionNo);
				map.put("institutionName",institutionName);
				map.put("allPin",getPinYin(institutionName));
				map.put("simplePin",getPinYinHeadChar(institutionName));
				pinyinLists.add(map);
			}
			bankInstitution = pinyinLists;
		}catch(Exception e){
			throw new SQLException("查询所有机构失败");
		}
	} 
	
	private static String getPinYin(String src) {

		char[] t1 = null;

		t1 = src.toCharArray();

		// System.out.println(t1.length);

		String[] t2 = new String[t1.length];

		// System.out.println(t2.length);

		// 设置汉字拼音输出的格式

		HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();

		t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);

		t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

		t3.setVCharType(HanyuPinyinVCharType.WITH_V);

		String t4 = "";

		int t0 = t1.length;

		try {

			for (int i = 0; i < t0; i++) {

				// 判断是否为汉字字符

				// System.out.println(t1[i]);

				if (Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {

					t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);// 将汉字的几种全拼都存到t2数组中

					t4 += t2[0];// 取出该汉字全拼的第一种读音并连接到字符串t4后

				} else {

					// 如果不是汉字字符，直接取出字符并连接到字符串t4后

					t4 += Character.toString(t1[i]);

				}

			}

		} catch (BadHanyuPinyinOutputFormatCombination e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		return t4;

	}

	private static String getPinYinHeadChar(String str) {

		String convert = "";

		for (int j = 0; j < str.length(); j++) {

			char word = str.charAt(j);

			// 提取汉字的首字母

			String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);

			if (pinyinArray != null) {

				convert += pinyinArray[0].charAt(0);

			} else {

				convert += word;

			}

		}

		return convert;

	}
}
