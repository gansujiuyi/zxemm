/**
 * <p>Title:             PageControl.java
 * <p>Description:       数据分页查询结果封装类
 * <p>Copyright:         Copyright (C), 2002-2003, Blueder Tech. Co., Ltd.
 * <p>Company:           Blueder
 * @author               xuf
 * @version	             v1.0 2002-12-06
 * @see		             com.blueder.util.Page
 *
 ********************************************************************************************
 *   Date      *      Developers ID      *      Modlog        *         Description         *
 ********************************************************************************************
 * 12/11/2002	          xuf	                             	         v1.0 
 */

package com.jiuyi.jyplat.util;

import java.util.*;

/**
 * 用于将分页所涉及到的一些关键的"控制数据"予以封装
 * 以及返回的单个页面结果集
 */
public class PageControl {
	public int curPage; //当前是第几页 
	public int maxPage; //一共有多少页 
	public int maxRowCount; //一共有多少行 
	public int rowsPerPage; //每页有多少行 
	private Object[] yourdata; //装载每页的数据

	/**
	 *Function: countMaxPage
	 *Description: 根据总行数计算总页数
	 *Calls:
	 *Called By: 
	 *Table Accessed: 
	 *Table Updated:  
	 *@return void
	 */
	public void countMaxPage() {
		if (this.maxRowCount % this.rowsPerPage == 0) {
			this.maxPage = this.maxRowCount / this.rowsPerPage;
		} else {
			this.maxPage = this.maxRowCount / this.rowsPerPage + 1;
		}
	}

	/*
	* empty constructor
	*/
	public PageControl() {
	}

	public PageControl(Page yourPL) {
		this.rowsPerPage = yourPL.getPageSize(); //得到每页显示行数 
		this.curPage = yourPL.getCurPage(); //得到当前页数
		this.maxRowCount = yourPL.getAvailableCount(); //得到总行数
		this.yourdata = yourPL.getResult(); //得到要显示于本页的数据
		this.countMaxPage(); //计算总页数
	}

	public PageControl(PageToObj yourPL) {
		this.rowsPerPage = yourPL.getPageSize(); //得到每页显示行数 
		this.curPage = yourPL.getCurPage(); //得到当前页数
		this.maxRowCount = yourPL.getAvailableCount(); //得到总行数
		this.yourdata = yourPL.getResult(); //得到要显示于本页的数据
		this.countMaxPage(); //计算总页数
	}

	public PageControl(Page yourPL, Object[] pageData) {
		this.rowsPerPage = yourPL.getPageSize(); //得到每页显示行数 
		this.curPage = yourPL.getCurPage(); //得到当前页数
		this.maxRowCount = yourPL.getAvailableCount(); //得到总行数
		this.yourdata = pageData; //得到要显示于本页的数据
		this.countMaxPage(); //计算总页数
	}

	/**
	 *getter方法
	 *取本页要显示数据，以二维数组来表示
	 */
	public Object[] getData() {
		return yourdata;
	}
}