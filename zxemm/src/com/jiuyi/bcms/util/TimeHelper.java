/**
 * <p>Title:             TimeHelper.java
 * <p>Description:       日期时间操作的一些常用方法
 * <p>Copyright:         Copyright (C), 2002-2003, Blueder Tech. Co., Ltd.
 * <p>Company:           Blueder
 * @author               xuf
 * @version	             v1.0 2002-12-12
 * @see		             com.blueder.util.TimeHelper
 *
 ********************************************************************************************
 *   Date      *      Developers ID      *      Modlog        *         Description         *
 ********************************************************************************************
 * 12/11/2002	          xuf	                             	         v1.0
 * 07/23/2002	          zhangzh	                             	 v1.1 getDateAfter 方法返回的天没作两位处理

 */

package com.jiuyi.bcms.util;

import java.util.Calendar;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;


public class TimeHelper
{
	static Logger Log=Logger.getLogger(TimeHelper.class);
	
	
	public static String getNowDateTime ()
	{
		/* 创建一个Calendar对象 */
		Calendar now=Calendar.getInstance();
		//Calendar now=Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00")); 
		
        /* 设置日期格式为yyyyMMdd */
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        /* 格式化为已设置的日期格式 */
        String date=formatter.format( now.getTime() );

        return date ;		
	}
	public static String getNowDateTime2 ()
	{
		/* 创建一个Calendar对象 */
		Calendar now=Calendar.getInstance();
		//Calendar now=Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00")); 
		
        /* 设置日期格式为yyyyMMdd */
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");

        /* 格式化为已设置的日期格式 */
        String date=formatter.format( now.getTime() );

        return date ;		
	}
	
	/**
	 *Function: getNowDate  静态方法
	 *Description: 获得当前日期,以yyyyMMdd形式表示
	 *Calls:
	 *Called By:
	 *Table Accessed:
	 *Table Updated:
     *@return String 当前日期
     */
	public static String getNowDate()
	{
		getNowDateTime () ;
		
		/* 创建一个Calendar对象 */
		Calendar now=Calendar.getInstance();
		//Calendar now=Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00")); 

        /* 设置日期格式为yyyyMMdd */
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

        /* 格式化为已设置的日期格式 */
        String date=formatter.format(now.getTime());

        return date ;
	}
	
	public static String getFmtDate ( Date indate ) 
	{
       /* 设置日期格式为yyyyMMdd */
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		return 	formatter.format( 	indate ) ;
	}
	
	public static String getFmtTime ( Date indate ) 
	{
 		/* 设置时间格式为HH:mm:ss */
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		return 	formatter.format( 	indate ) ;
	}
	
	/**
	 *Function: getNowTime  静态方法
	 *Description: 取当前时间,以HHmmss形式表示
	 *Calls:
	 *Called By:
	 *Table Accessed:
	 *Table Updated:
     *@return String 当前时间
     */
	public static String getNowTime()
	{
		/* 创建一个Calendar对象 */
		Calendar now=Calendar.getInstance();
		//Calendar now=Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00")); 

		/* 设置时间格式为HH:mm:ss */
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");

		/* 格式化为已设置的时间格式 */
		String time = formatter.format(now.getTime()) ;

		return time ;
	}

	/**
	 *Function: getDateAfter
	 *Description: 得到给出日期之后n个月的日期 , 日期以yyyyMMdd形式表示
	 *Calls:
	 *Called By:
	 *Table Accessed:
	 *Table Updated:
     *@param String 当前日期
     *@param int 前进月数
     *@return String 前进后日期
     */
	public static String getDateAfter(String curDate , int month)
	{
        String ret = null;
		try
		{
		    /* 从日期字符串中分别提取年, 月, 日 */
		    int curYear = Integer.parseInt(curDate.substring(0,4));
		    int curMonth = Integer.parseInt(curDate.substring(4,6));
		    int curDay = Integer.parseInt(curDate.substring(6,8));
	
		    /* 换算成增加的年数 */
		    int addYear = month/12 ;
	
		    /* 增加的月数 */
		    int addMonth = month%12 ;
	
		    /* 计算增加之后的年数 */
		    curYear = curYear + addYear ;
	
		    int monthTmp = curMonth + addMonth ;
	
		    /* 有可能出现大于12的情况 */
		    if(monthTmp>12)
		    {
		        curYear ++ ; //年数加1
		        curMonth = monthTmp - 12 ;
		    }
		    else
		    {
		        curMonth = monthTmp ;
		    }
	
		    /* 当月数小于10,前面补0 */
		    if(curMonth<10)
		    {
		        ret =  "" + curYear + "0" + curMonth ;
		    }
		    else
		    {
		        ret = "" + curYear + curMonth;
		    }
	            if(curDay<10)
	            {
	                ret = ret + "0" + curDay ;
	            }
	            else
	            {
	                ret = ret + curDay ;
	            }
            return ret;
    	}
    	catch (Exception e ) 
    	{
    		return "" ;
    	}
	}

	
	/**
	 * 取当前月最后一天的日期
	 *@return String 当前月最后一天日期
	 */
	public static String getLastDayOfThisMonth()
	{
		Calendar now = Calendar.getInstance();

		int curYear = now.get(Calendar.YEAR);
		int curMonth = now.get(Calendar.MONTH)+1 ;
		now.set(Calendar.DATE, 1);
		now.roll(Calendar.DATE, -1);
		now.set(Calendar.DATE, 1);
		now.roll(Calendar.DATE, -1);
		int day= now.get(Calendar.DATE) ;
		String dayStr="";
		if(day<10)
			dayStr="0"+day;
		else
			dayStr=""+day;
		if(curMonth < 10)
		{
			return "" + curYear + "0" + curMonth + dayStr;
		}
		else
		{
			return "" + curYear + curMonth + dayStr;
		}
	}
	/**
	 *Function: minusDate  静态方法
	 *Description: 计算两个日期之间的天数
	 *             (注,按一年360天,一月30天计算)
	 *Calls:
	 *Called By:
	 *Table Accessed:
	 *Table Updated:
     *@return int 天数
     */
	public static int minusDate(String startDate, String endDate)
	{
		/* 从日期字符串中分别提取年, 月, 日 */
		int startYear = Integer.parseInt(startDate.substring(0, 4));
		int startMonth = Integer.parseInt(startDate.substring(4, 6));
		int startDay = Integer.parseInt(startDate.substring(6, 8));

		int endYear = Integer.parseInt(endDate.substring(0, 4));
		int endMonth = Integer.parseInt(endDate.substring(4, 6));
		int endDay = Integer.parseInt(endDate.substring(6, 8));

		int rtnValue = (endYear - startYear)*360 + (endMonth - startMonth)*30 + (endDay - startDay);
		return rtnValue;
	}

	/**
	 * 取当前月的下一个月的第一日的日期
	 *@return String 下月第一日日期
	 */
	public static String getFirstDayOfNextMonth()
	{
		Calendar now = Calendar.getInstance();

		int curYear = now.get(Calendar.YEAR);
		int nextMonth = now.get(Calendar.MONTH) + 2;

        if(nextMonth == 13)
        {
        	return "" + (curYear + 1) + "0101";
        }
        else if(nextMonth < 10)
		{
			return "" + curYear + "0" + nextMonth + "01";
		}
		else
		{
			return "" + curYear + nextMonth + "01";
		}
	}

	/**
	 * 取当前月第一日的日期
	 *@return String 当前月第一日日期
	 */
	public static String getFirstDayOfThisMonth()
	{
		Calendar now = Calendar.getInstance();

		int curYear = now.get(Calendar.YEAR);
		int curMonth = now.get(Calendar.MONTH) + 1;

		if(curMonth < 10)
		{
			return "" + curYear + "0" + curMonth + "01";
		}
		else
		{
			return "" + curYear + curMonth + "01";
		}
	}

	//取当前日期的上一天日期
	//例如 20030519 上一天日期为 20030518
	public static String getLastDayDate(String curDate)
	{
		String lastDay;
		if(curDate.substring(4, 8).equals("0101"))
		{
			int year = Integer.parseInt(curDate.substring(0, 4)); //取年份
			lastDay = "" + (year-1) + "1231" ; //年份减1, 月为12月31日
		}
		else
		{
			try
			{
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat from_sdf = new SimpleDateFormat("yyyyMMdd");
				Date thisDate = from_sdf.parse(curDate);
				cal.setTime(thisDate);

				//上滚一日
				//当日期为yyyy0101时,不能调用该方法
				cal.roll(Calendar.DAY_OF_YEAR, -1);

			    lastDay = from_sdf.format(cal.getTime());
			}
		    catch(java.text.ParseException e)
		    {
		    	Log.error(e.getMessage());
		    	return null;
		    }
		}
		return lastDay;
	}

	//取当前日期的上一月日期
	//例如 20030512 上一月日期为20030412
	public static String getLastMonthDate(String curDate)
	{
		String lastMonth;
		if(curDate.substring(4, 6).equals("01"))
		{
			int year = Integer.parseInt(curDate.substring(0, 4)); //取年份
			lastMonth = "" + (year-1) + "12" + curDate.substring(6, 8) ; //年份减1, 月为12月,日同当前日
		}
		else
		{
			try
			{
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat from_sdf = new SimpleDateFormat("yyyyMMdd");
				Date thisDate = from_sdf.parse(curDate);
				cal.setTime(thisDate);

				//上滚一日
				//当日期为yyyy01dd时,不能调用该方法
				cal.roll(Calendar.MONTH, -1);

			    lastMonth = from_sdf.format(cal.getTime());
		    }
		    catch(java.text.ParseException e)
		    {
		    	Log.error(e.getMessage());
		    	return null;
		    }
		}
		return lastMonth;
	}

	/**
	* 取得当前日期n天之后的日期
	*@param String 当前日期
	*@param int 往后的天数
	*@return String
	*/
	public static String getDateAfterDay(String curDate , int days)
	{
		String resultDate ;
		try
		{
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat from_sdf = new SimpleDateFormat("yyyyMMdd");
			Date thisDate = from_sdf.parse(curDate);
			cal.setTime(thisDate);
			cal.add(Calendar.DAY_OF_YEAR, days);

		    resultDate = from_sdf.format(cal.getTime());
	    }
	    catch(java.text.ParseException e)
	    {
	    	Log.error(e.getMessage());
	    	return null;
	    }
	    return resultDate;
	}
	
	//计算当前时间前多少秒的时间 ，返回格式yyyyMMddHH:mm:ss
	//@param int 	lMs 间隔秒 负值表示以前 ，正值表示以后
	//@return String
	public static String getDateTimeDiffM (  int lMs )
	{
		String resultDate ;
		try
		{
			Calendar cal = Calendar.getInstance();
			
			cal.add( Calendar.SECOND, lMs ) ;
			
			SimpleDateFormat from_sdf = new SimpleDateFormat("yyyyMMddHH:mm:ss");

		    resultDate = from_sdf.format(cal.getTime());
	    }
	    catch( Exception e)
	    {
	    	Log.error(e.getMessage());
	    	return null;
	    }
	    return resultDate;		
	}
	
 	 public static void main(String[] args)
	{
		System.out.println( getNowDate ()+ getNowTime () ) ;
		System.out.println( getDateTimeDiffM ( -60 ) ) ;
	}	
}