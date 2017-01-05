package com.jiuyi.jyplat.util;

public class TheadUtil {

	//最大的批量并发数 
	public static int MaxBatchThreads = 100;
	//系统自动积分计算导入跑批已插入数
	public static int sysCreditCount = 0;
	//线程执行错误后,将改为True
	public static boolean ErrorState = false;

	//实时规则最大的批量并发数
	public static int MaxThreads_RealTime = 20;
	//线程执行错误后,将改为True
	public static boolean ErrorState_RealTime = false;

	public TheadUtil() {
	}

	private static int threadCount = 0;

	private static int threadCount_RealTime = 0;

	public synchronized static int getRunningThreadCount() {
		return threadCount;
	}

	public synchronized static int AddRunningThreadCount() {
		return threadCount++;
	}

	public synchronized static int DecRunningThreadCount() {
		return threadCount--;
	}

	public synchronized static void putError(boolean state) {
		ErrorState = state;
	}

	public synchronized static int getRealTimeThreadCount() {
		return threadCount_RealTime;
	}

	public synchronized static int AddRealTimeThreadCount() {
		return threadCount_RealTime++;
	}

	public synchronized static int DecRealTimeThreadCount() {
		return threadCount_RealTime--;
	}

	public synchronized static void putRealTimeError(boolean state) {
		ErrorState_RealTime = state;
	}
}