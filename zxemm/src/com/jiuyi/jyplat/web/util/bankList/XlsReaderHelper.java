package com.jiuyi.jyplat.web.util.bankList;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;

/**
 * excel读取辅助工具。
 * 
 * @author Administrator
 * 
 */
public class XlsReaderHelper {

	public static String getStr(Row row, int col) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = "";
		if (row != null) {
			Cell cell = row.getCell(col);
			if (cell != null) {
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING: // 字符串
					str = cell.getStringCellValue().trim();
					break;
				case Cell.CELL_TYPE_FORMULA: // 公式
					str = cell.getCellFormula();
					break;
				case Cell.CELL_TYPE_BOOLEAN: // boolean
					str = Boolean.toString(cell.getBooleanCellValue());
					break;
				case Cell.CELL_TYPE_NUMERIC: // 数字
					double value = cell.getNumericCellValue();
					if(DateUtil.isCellDateFormatted(cell)){
						Date date = DateUtil.getJavaDate(value);
						str = sdf.format(date);
					}else{
						str = String.format("%1$.0f", value);
						//str = Double.toString(value);
					}
					break;
				case Cell.CELL_TYPE_BLANK: // 空白
					break;
				case Cell.CELL_TYPE_ERROR: // 故障
					break;
				default:
					str = "未知类型";
					break;
				}
			}
		}

		return str;
	}

}
