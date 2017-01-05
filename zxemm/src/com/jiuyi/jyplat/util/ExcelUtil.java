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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.jfree.util.Log;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelUtil {

	/**
	 * 读取Excel的第一行数据
	 * @param file Excel文件的路径
	 * @param clz  对应Excel模板的实体类
	 * @return  对应Excel模板的实体类数据
	 * @throws Exception  需要在调用此方法里面捕获异常
	 */
	public static List readFirst(File file, Class clz) throws Exception {
		return readExcel(clz, null, file, 0);
	}

	/**
	 * 导入Excel文件
	 * @param file 要导入的Excel文件
	 * @param clz 实体类的class
	 * @return
	 * @throws Exception
	 */
	public static List importExcel(File file, Class clz) throws Exception {
		Method[] set = reflectFunction(clz);
		return readExcel(clz, set, file, 1);
	}

	/**
	 * 读取Excel内容的值，并给目标实体类赋值
	 * @param object 目标实体类
	 * @param set 所以set方法的集合
	 * @param rwb
	 * @param sheetLength
	 * @param start
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws IOException 
	 * @throws BiffException 
	 * @throws InstantiationException 
	 */
	private static List readExcel(Class clz, Method[] set, File file, int start) throws IllegalAccessException,
			InvocationTargetException, BiffException, IOException, InstantiationException {
		List list = new ArrayList();
		// 将传入的File构造为FileInputStream;
		InputStream in = new FileInputStream(file);
		// jxl的Workbook得到这个输入流
		Workbook rwb = Workbook.getWorkbook(in);

		// Workbook得到第一个sheet
		Sheet[] sheets = rwb.getSheets();// 获得当前Excel表共有几个sheet
		int sheetLength = sheets.length;
		for (int w = 0; w < sheetLength; w++) { // 将每个sheet中的内容全部读取出来
			// 在从Excel中读取数据的时候，不需要知道每个sheet有几行，有多少列
			Sheet rs = rwb.getSheet(w);
			int cols = rs.getColumns();
			int rows = rs.getRows();
			switch (start) {
			case 0:
				//对第一行进行列遍历，获取第一行的数据，即标题
				for (int i = start; i < cols; i++) {
					Cell a00 = rs.getCell(i, 0);
					String stra00 = a00.getContents();
					list.add(i, stra00);
				}
				break;

			case 1:

				aa: for (int d = start; d < rows; d++) { //行循环,Excel的行列是从（0，0）开始的，在这里，我们去除第一行的标题，所以下标从1开始
					Object object = clz.newInstance();
					int j = 0;
					for (int t = 0; t < cols; t++) { // 列循环
						Cell b00 = rs.getCell(t, d);
						//System.out.println(b00.getType()); //获取内容里面的类型
						//得到每一列的值
						String strb = b00.getContents();
						if ("".equals(strb) || strb == null) {
							j++;
						}
						//System.out.println("strb:"+strb);
						setVoluation(object, set, t, strb);
					}
					//System.out.println(j);
					if (j == cols) {
						break aa;
					} else {
						list.add(object);
					}
				}
				break;
			}
		}
		//关闭IO流操作
		in.close();
		//System.out.println(list.size());
		return list;
	}

	/**
	 * 给目标类的所有set方法一一赋值
	 * @param object
	 * @param set  目标类的所有set方法集合
	 * @param i  当前第几个set方法
	 * @param str   读取出来的值
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private static void setVoluation(Object object, Method[] set, int i, String str) throws IllegalAccessException,
			InvocationTargetException {
		// 得到setter方法的参数
		Type[] ts = set[i].getGenericParameterTypes();
		// 只要第一个参数，即set方法的参数的类型
		String xclass = ts[0].toString();
		//验证数据的合法性
		checkValidity(set, object, i, str, xclass);
	}

	/**
	 * 得到目标类的set方法集合
	 * @param clz  目标类
	 * @return
	 */
	private static Method[] reflectFunction(Class clz) {
		// 得到目标类的所有的字段列表
		Field filed[] = clz.getDeclaredFields();

		// 得到目标类的所有的方法列表
		Method[] m = clz.getDeclaredMethods();
		// 得到目标类的所有的方法列表的set方法
		Method[] set = new Method[filed.length];
		String methodName[] = new String[filed.length];
		for (int i = 0; i < filed.length; i++) {
			//System.out.println(filed[i].getName());
			methodName[i] = "set" + filed[i].getName().substring(0, 1).toUpperCase() + filed[i].getName().substring(1);
		}

		for (int i = 0; i < methodName.length; i++) {
			//System.out.println("methodname:"+methodName[i]);
			for (Method method : m) {
				if (method.getName().equals(methodName[i])) {
					set[i] = method;
				}
			}
		}
		/*for (Method method : set) {
			System.out.println(method.getName());
		}*/
		return set;
	}

	/**
	 * 判断数据的类型
	 * @param set
	 * @param object
	 * @param t
	 * @param strb
	 * @param xclass
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private static void checkValidity(Method[] set, Object object, int t, String strb, String xclass)
			throws IllegalAccessException, InvocationTargetException {
		if (xclass.equals("class java.lang.String")) {
			set[t].invoke(object, strb);
		} else if (xclass.equals("class java.util.Date")) {
			if (isEmpty(strb)) {
				set[t].invoke(object, "");
			} else {
				set[t].invoke(object, new SimpleDateFormat("yyyy-MM-dd HH:mm:SS").format(strb));
			}
		} else if (xclass.equals("class java.lang.Boolean")) {
			boolenCheck(set, object, t, strb);
		} else if (xclass.equals("class java.lang.Long")) {
			checkEmpty(set, object, t, strb, "long");
		} else if (xclass.equals("class java.lang.Integer")) {
			checkEmpty(set, object, t, strb, "int");
		} else if (xclass.equals("class java.lang.Double")) {
			checkEmpty(set, object, t, strb, "double");
		} else if (xclass.equals("class java.lang.Float")) {
			checkEmpty(set, object, t, strb, "float");
		} else if (xclass.equals("int")) {
			checkEmpty(set, object, t, strb, "int");
		} else if (xclass.equals("double")) {
			checkEmpty(set, object, t, strb, "double");
		} else if (xclass.equals("float")) {
			checkEmpty(set, object, t, strb, "float");
		} else if (xclass.equals("long")) {
			checkEmpty(set, object, t, strb, "long");
		} else if (xclass.equals("boolean")) {
			boolenCheck(set, object, t, strb);
		}
	}

	/**
	 * 给boolean类型的值赋值
	 * @param set
	 * @param object
	 * @param t
	 * @param strb
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private static void boolenCheck(Method[] set, Object object, int t, String strb) throws IllegalAccessException,
			InvocationTargetException {
		Boolean boolname = true;
		if ("否".equals(strb)) {
			boolname = false;
		}
		set[t].invoke(object, boolname);
	}

	/**
	 * 判断Excel的列的值是否为空，如果为空，进行判断
	 * @param set
	 * @param object
	 * @param t
	 * @param strb
	 * @param classType
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private static void checkEmpty(Method[] set, Object object, int t, String strb, String classType)
			throws IllegalAccessException, InvocationTargetException {
		if (isEmpty(strb)) {
			if ("long".equals(classType)) {
				set[t].invoke(object, (long) 0);
			} else if ("int".equals(classType)) {
				set[t].invoke(object, 0);
			} else if ("float".equals(classType) || "double".equals(classType)) {
				set[t].invoke(object, 0.00);
			}
		} else {
			if ("long".equals(classType)) {
				set[t].invoke(object, new Long(strb));
			} else if ("float".equals(classType)) {
				set[t].invoke(object, new Float(strb));
			} else if ("int".equals(classType)) {
				set[t].invoke(object, new Integer(strb));
			} else if ("double".equals(classType)) {
				set[t].invoke(object, new Double(strb));
			}
		}
	}

	//判断Excel的值是否为空，返回true表示为空，否则就表示不为空
	public static boolean isEmpty(String str) {
		boolean result = false;
		if ("".equals(str)) {
			result = true;
		}
		return result;
	}

	/**
	 * 获取序列的下一个值
	 * 
	 * @param SeqName
	 *            序列名称
	 * @return
	 */
	public static String getNextVal(String SeqName) {
		String sql = " SELECT " + SeqName + ".NEXTVAL FROM DUAL ";
		DBAction db = new DBAction();
		try {
			return db.executeSearch(sql)[0];
		} catch (Exception ex) {
			Log.debug("生成序列号出错！");
		}
		return null;
	}
}
