package com.jiuyi.jyplat.util;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.helpers.Loader;

/**
 * properties文件的管理器,动态加载在系统运行期间改动过的properties文件
 * 
 * @author leiyongjun Dec 11, 2011 4:05:52 PM
 */
public class ConfigManager {

	Logger log = Logger.getLogger(ConfigManager.class);

	private static ConfigManager m_instance;
	private static String PFILE;

	synchronized public static ConfigManager getInstance() {
		if (m_instance == null) {
			if (PFILE == null) {
				URL url = Loader.getResource("application.properties");
				PFILE = url.getPath();
				PFILE = PFILE.replaceAll("%20", " ");
			}
			m_instance = new ConfigManager();
		}
		return m_instance;
	}

	private File m_file = null;
	private long m_lastModifiedTime = 0;
	private Properties m_props = null;

	private ConfigManager() {
		m_file = new File(PFILE);
		m_lastModifiedTime = m_file.lastModified();

		//		if (m_lastModifiedTime == 0) {
		//			System.err.println(PFILE + " file does not exist!");
		//		}

		m_props = new Properties();

		try {
			m_props.load(new FileInputStream(PFILE));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("加载资源文件[" + PFILE + "]出错!");
		}
	}

	/**
	 * 获取application.properties资源文件中配置值
	 * @param name
	 * @param defaultVal
	 * @return
	 */
	final public Object getConfigItem(String name, Object defaultVal) {
		long newTime = m_file.lastModified();
		if (newTime == 0) {
			if (m_lastModifiedTime == 0) {
				log.error("加载资源文件[" + PFILE + "]出错." + " file does not exist!");
			} else {
				log.error("加载资源文件[" + PFILE + "]出错." + " file was deleted!!");
			}
			return defaultVal;
		} else if (newTime > m_lastModifiedTime) {
			m_props.clear();
			try {
				m_props.load(new FileInputStream(PFILE));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		m_lastModifiedTime = newTime;

		Object val = m_props.getProperty(name);
		if (val == null) {
			return defaultVal;
		} else {
			return val;
		}
	}

	public static String getAppGateIP() {
		if (m_instance == null) {
			return ConfigManager.getInstance().getConfigItem("uxun.appgate.ip", "192.16.16.82").toString();
		} else {
			return m_instance.getConfigItem("uxun.appgate.ip", "192.16.16.82").toString();
		}
	}

	public static Integer getAppGatePort() {
		if (m_instance == null) {
			return Integer.parseInt(ConfigManager.getInstance().getConfigItem("uxun.appgate.port", "192.16.16.82")
					.toString());
		} else {
			return Integer.parseInt(m_instance.getConfigItem("uxun.appgate.port", "192.16.16.82").toString());
		}
	}

	public static String getAppGateIPCD() {
		if (m_instance == null) {
			return ConfigManager.getInstance().getConfigItem("uxun.appgate.ipcd", "192.16.16.82").toString();
		} else {
			return m_instance.getConfigItem("uxun.appgate.ipcd", "192.16.16.82").toString();
		}
	}

	public static Integer getAppGatePortCD() {
		if (m_instance == null) {
			return Integer.parseInt(ConfigManager.getInstance().getConfigItem("uxun.appgate.portcd", "192.16.16.82")
					.toString());
		} else {
			return Integer.parseInt(m_instance.getConfigItem("uxun.appgate.portcd", "192.16.16.82").toString());
		}
	}

	public static String getString(String strKey, String defaultVal) {
		if (m_instance == null) {
			return ConfigManager.getInstance().getConfigItem(strKey, defaultVal).toString();
		} else {
			String retStr = m_instance.getConfigItem(strKey, defaultVal).toString();
			if (retStr == null || "".equals(retStr))
				retStr = defaultVal;
			return retStr;
		}
	}

}