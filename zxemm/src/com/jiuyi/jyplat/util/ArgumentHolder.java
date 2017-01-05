/**
 * <p>Title:             AugumentHolder.java
 * <p>Description:       参数封装类
 * <p>Copyright:         Copyright (C), 2002-2003,UXUN. Co., Ltd.
 * <p>Company:           UXUN
 * @author               xuf
 * @version	             v1.0
 * @see		             com.jiuyi.util.ArgumentHolder
 */

package com.jiuyi.jyplat.util;

/**
*类ArgumentHolder
*用于调用参数的封装，实现变长参数及
*  不同类型参数的统一形式地传递
*成员变量:
* Class[] cl   参数类型数组
* Object[] args  参数对象数组
*方法: 
* getArgumentClasses()返回参数类型数组
* getArguments()   返回参数对象数组 
* setArgument()   在参数列表中增加项目
*
*/
public class ArgumentHolder {
	protected Class[] cl;
	protected Object[] args;
	protected int argc;

	public ArgumentHolder() {
		argc = 0;
		cl = new Class[0];
		args = new Object[0];
	}

	public ArgumentHolder(int argc) {
		this.argc = argc;
		cl = new Class[argc];
		args = new Object[argc];
	}

	public Class[] getArgumentClasses() {
		return cl;
	}

	public Object[] getArguments() {
		return args;
	}

	public int setArgument(int i) {
		return this.setArgument(argc, new Integer(i), Integer.TYPE);
	}

	public int setArgument(long l) {
		return this.setArgument(argc, new Long(l), Long.TYPE);
	}

	public int setArgument(float f) {
		return this.setArgument(argc, new Float(f), Float.TYPE);
	}

	public int setArgument(double d) {
		return this.setArgument(argc, new Double(d), Double.TYPE);
	}

	public int setArgument(Object obj) {
		return this.setArgument(argc, obj, obj.getClass());
	}

	//以下setArgument函数具体实现以对象形式
	// 提供的参数封装。
	//具体操作为：增加计数器argc的值、
	// 在cl和args中增加相应内容
	//特别注意:  obj不能为null
	public int setArgument(int argnum, Object obj, Class c) {
		if (argnum >= args.length) {
			argc = argnum + 1;
			Class[] clExpanded = new Class[argc];
			Object[] argsExpanded = new Object[argc];
			System.arraycopy(cl, 0, clExpanded, 0, cl.length);
			System.arraycopy(args, 0, argsExpanded, 0, args.length);
			cl = clExpanded;
			args = argsExpanded;
		}
		args[argnum] = obj;
		cl[argnum] = c;
		return argnum;
	}
}