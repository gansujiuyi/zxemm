package com.jiuyi.jyplat.util;

import java.io.BufferedInputStream;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Properties;
import java.util.StringTokenizer;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.jiuyi.jyplat.web.util.IsSynchronousImgUtil;

public class FileUtil {

	private static Logger log = Logger.getLogger(FileUtil.class);

	public FileUtil() {
	}

	/**
	 * 创建文件夹,传入参数 如: D:/Tomcat 6.0/webapps/uxunplat/UploadImages/20110901/test
	 * 将创建至最后一级的test文件夹. 分隔符必须为/
	 * @param path
	 */
	public static File createFolder(String path) throws Exception {
		File file = null;
		try {
			StringTokenizer st = new StringTokenizer(path, "/");
			String p = "";
			if (path.startsWith("/"))
				p = "/";
			while (st.hasMoreElements()) {
				p += (p.equals("") ? "" : "/") + String.valueOf(st.nextElement());
				file = new File(p);
				if (!file.exists()) {
					file.mkdir();
				}
			}
		} catch (Exception e) {
			log.error("创建文件夹失败[" + path + "]:" + e.getMessage());
			throw e;
		}
		return file;
	}
	
	/**
	 * 
	 * <p>
	 * 将制定的字符串生成一个制定的文件
	 * </p>
	 * 
	 * @param str
	 * @param path
	 * @return
	 * @author 
	 */
	public static boolean fileFactory(String str, String path) {
		OutputStreamWriter writer = null;
		try {
			File file = new File(path);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			writer = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
			writer.write(str);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	/**
	 * 
	 * <p>
	 * 解压zip压缩文件
	 * </p>
	 * 
	 * @param zipPath
	 * @param targetPath
	 *            解压文件夹路径
	 * @throws Exception
	 * @author
	 */
	public static boolean unZip(File zipPath, String targetPath) {
		try {
			ZipFile zipFile = new ZipFile(zipPath, "GBK");
			Enumeration e = zipFile.getEntries();
			byte ch[] = new byte[256];
			while (e.hasMoreElements()) {
				ZipArchiveEntry zipEntry = (ZipArchiveEntry) e.nextElement();
				String temp = zipEntry.getName();
				System.out.println("unziping " + zipEntry.getName());
				File zfile = new File(targetPath + temp);
				File fpath = new File(zfile.getParentFile().getPath());

				if (zipEntry.isDirectory()) {
					if (!zfile.exists())
						zfile.mkdirs();
				} else {
					if (!fpath.exists())
						fpath.mkdirs();
					FileOutputStream fouts = new FileOutputStream(zfile);
					InputStream in = zipFile.getInputStream(zipEntry);
					int i;
					while ((i = in.read(ch)) != -1)
						fouts.write(ch, 0, i);
					fouts.close();
					in.close();
				}
			}
			return true;

		} catch (Exception e) {
			System.err.println("Exception from ZipUtil -> unZip() : "
					+ e.getMessage());
			e.printStackTrace(System.err);
			return false;
		}
	}

	public static String readPropertiesFile(String s, String s1)
			throws IOException {
		FileInputStream fileinputstream = new FileInputStream(new File(s));

		Properties properties = new Properties();
		properties.load(fileinputstream);
		try {

		} catch (Exception exception) {
			StringBuffer stringbuffer = new StringBuffer();
			stringbuffer
					.append("\u951F\u65A4\u62F7\u951F\u6770\u8BB9\u62F7\u53D6\u951F\u65A4\u62F7\u951F\u65A4\u62F7\u951F\u4FA5\u7877\u62F7readPropertiesFile error: ");
			stringbuffer.append(s);
			stringbuffer
					.append("\u951F\u65A4\u62F7\u786E\u951F\u4FA5\u7877\u62F7\u951F\u89D2\u51E4\u62F7\u951F\u65A4\u62F7\u951F\u65A4\u62F7\u951F\u8857\u9769\u62F7\u951F\u65A4\u62F7\u951F\u94F0\u51E4\u62F7\u951F\u65A4\u62F7\u951F\uFFFD");
			throw new IOException(stringbuffer.toString());
		}
		if (properties.containsKey(s1))
			return properties.getProperty(s1, null);
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer
				.append("\u951F\u65A4\u62F7\u951F\u65A4\u62F7\u951F\u4FA5\u7877\u62F7\u951F\u53EB\u8BE7\u62F7\u951F\u65A4\u62F7(\u951F\u65A4\u62F7\u951F\u65A4\u62F7\u951F\u65A4\u62F7");
		stringbuffer.append(s1);
		throw new IOException(stringbuffer.toString());
	}
	
	/**
	 * 
	 * @param sourceFile
	 *            源文件
	 * @param imgName
	 *            文件名
	 * @param destFileUrl
	 *            目标文件夹
	 */
	public static void uploadImage(File sourceFile, String imgName,
			String destFileUrl) {
		try {
			File destFile = null;
			if (sourceFile != null) {
				File temp = new File(destFileUrl);
				destFile = new File(temp, imgName);
				if (!destFile.getParentFile().exists()) {
					destFile.getParentFile().mkdirs();
				}
				FileUtils.copyFile(sourceFile, destFile);
				//add guys 20150205 同步shell脚本物理路径
				IsSynchronousImgUtil.isSynchronousImg();
			}
		} catch (IOException e) {
			log.error("上传图片失败", e);
			e.printStackTrace();
		}
	}

	/**
	 * 下载文件模版
	 * 
	 * @param request
	 * @param response
	 * @param extName 保存出来的模版的名称
	 * @param fileName 服务器上的模版名称
	 * @throws Exception
	 */
	public static void downloadFile(HttpServletRequest request, HttpServletResponse response, String fileName,
			String extName) throws Exception {
		String fullPath = request.getSession().getServletContext().getRealPath("/") + "excel/template/" + fileName;

		InputStream inputStream = null;
		BufferedInputStream bis = null;
		ServletOutputStream outputStream = null;
		BufferedOutputStream bos = null;
		try {
			// 设置响应类型及响应头
			// 读取文件
			response.setContentType("application/x-msdownload");
			response.addHeader("Content-Disposition", "attachment;filename=\"" + URLEncoder.encode(extName, "utf-8")
					+ "\"");
			inputStream = new FileInputStream(fullPath);
			bis = new BufferedInputStream(inputStream);
			byte[] bytes = new byte[1024];
			outputStream = response.getOutputStream();
			bos = new BufferedOutputStream(outputStream);
			int readLength = 0;
			while ((readLength = bis.read(bytes)) != -1) {
				bos.write(bytes, 0, readLength);
			}
		} catch (Exception e) {
			throw new Exception("文件模板下载失败，请联系管理人员！");
		} finally {
			// 释放资源
			inputStream.close();
			bis.close();
			bos.flush();
			outputStream.close();
			bos.flush();
		}
	}
}
