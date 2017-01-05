package com.jiuyi.jyplat.service.base.impl;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.iec.file.utils.PicMoveUtils;
import com.iec.file.utils.ResultVo;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.jiuyi.jyplat.dao.base.SyscontentDao;
import com.jiuyi.jyplat.entity.base.Syscontent;
import com.jiuyi.jyplat.entity.system.Operator;
import com.jiuyi.jyplat.service.base.ISyscontentService;
import com.jiuyi.jyplat.util.ConfigManager;
import com.jiuyi.jyplat.util.Constant;
import com.jiuyi.jyplat.util.DateUtils;
import com.jiuyi.jyplat.util.FileUtil;
import com.jiuyi.jyplat.util.SessionUtil;
import com.jiuyi.jyplat.util.Utils;
import com.jiuyi.jyplat.util.imgSynchronous.RsyncUtil;
import com.jiuyi.jyplat.web.page.PageFinder;
import com.jiuyi.jyplat.web.page.Query;
import com.jiuyi.jyplat.web.util.IsSynchronousImgUtil;


@Service("syscontService")
public class SyscontentService  implements ISyscontentService {
	
	@Resource
	private SyscontentDao syscontDao;
	
	Logger log = Logger.getLogger(SyscontentService.class);

	/**
	 * 分页查询枚举总表中的所有数据
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
	public PageFinder<Syscontent> querySyscont(Syscontent syscont,String cityid,String type, Query query) throws Exception {
		
		Criteria criteria = this.syscontDao.createCriteria();
		if(null!=syscont){
			if( null!=syscont.getContname() && !"".equals(syscont.getContname()) )
				criteria.add( Restrictions.like("contname", syscont.getContname().trim(), MatchMode.ANYWHERE) );
			if( Utils.notEmptyString(syscont.getStarttime()) )
				criteria.add( Restrictions.ge("starttime", syscont.getStarttime().trim()) );
			if( Utils.notEmptyString(syscont.getEndtime()) )
				criteria.add( Restrictions.le("endtime", syscont.getEndtime().trim()) );
		}
		
		criteria.add(Restrictions.eq("viewType", cityid));
		
		
		return this.syscontDao.pagedByCriteria(criteria, query.getPage(), query.getPageSize(), Order.desc("contid"));
	}
	
	
	@Override
	@Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
	public Syscontent querySyscontById(String sysContId) throws Exception {
		if(sysContId==null || sysContId.trim().equals("")){
			throw new Exception("系统内容编号为空！");
		}
		Syscontent syscontent = null;
		Criteria criteria = syscontDao.createCriteria();
		criteria.add( Restrictions.eq("contid", Integer.parseInt(sysContId.trim())) );
		syscontent = (Syscontent)criteria.uniqueResult();
		
		return syscontent;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void saveSyscont(Syscontent syscont,File file,String fileName) throws Exception {
		if(syscont==null){
			throw new Exception("系统内容数据为空！");
		}
		//获取当前时间（yyyyMMdd HH:mm:ss）
		String createTime = DateUtils.dateToDateString(new Date(), DateUtils.DEFAULT_TIME);
		Operator oper = SessionUtil.getOperator();
		String memberNo=oper.getOperNo();
		ResultVo vo =	PicMoveUtils.commitPic(syscont.getContimg(), Long.parseLong(memberNo+""));

		
		syscont.setCreatetime(createTime);
		//获取当前操作员编号
		syscont.setContimg(vo.getList());
		syscontDao.save(syscont);
		
		// 上传图片信息
		if( file != null && fileName != null ){
			syscont.setContimg(this.uploadImage(file, fileName, syscont.getContid().toString()));
			syscontDao.updateSyscont(syscont ,null);
		}
		
		
		log.info("新增系统内容信息成功！");
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateSyscontStatus(String contid, String status) throws Exception {
		if(contid==null || contid.trim().equals("")){
			throw new Exception("系统内容编号为空！");
		}
		String[] ids = contid.trim().split(",");	//将页面请求的系统内容编号以逗号分割为数组
		for (String id : ids) {
			Syscontent syscont = new Syscontent();
			syscont.setContid( Integer.parseInt(id) );
			syscont.setStatus( status );	// 删除
			syscontDao.updateSyscont( syscont,null );
			log.info("系统内容状态变更成功！系统内容编号："+id.trim()+",状态为:"+status);
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateSyscont(Syscontent syscont,File file,String fileName) throws Exception{
		if(syscont==null){
			throw new Exception("系统内容数据为空！");
		}
		//获取当前时间（yyyyMMdd HH:mm:ss）
		String updateTime = DateUtils.dateToDateString(new Date(), DateUtils.DEFAULT_TIME);
		String actImg = "";
		Operator oper = SessionUtil.getOperator();
		String memberNo=oper.getOperNo();
		ResultVo vo =	PicMoveUtils.commitPic(syscont.getContimg(), Long.parseLong(memberNo+""));

		actImg=vo.getList();
		
		
		syscont.setModifytime(updateTime);
		syscont.setStatus("0003");
		
		
		if( file != null && fileName != null ){
			actImg = this.uploadImage(file, fileName, syscont.getContid().toString());
			File delOldImgUrl=new File(ConfigManager.getInstance().getConfigItem("base_upload_path","defaultVal")+syscont.getContimg());
			if(null!=actImg&&delOldImgUrl.isFile()){
				delOldImgUrl.delete();//如果图片上传成功，而且原来的图片还在，则删除原来的图片资源
			}
		}
		
		syscontDao.updateSyscont(syscont,actImg);
		log.info("修改系统内容信息成功！系统内容编号："+ syscont.getContid());
	}
	
	
	//***************** 上传图片 **********************//
	private String getExtention(String fileName) {
		int pos = fileName.lastIndexOf(".");
		return fileName.substring(pos);
	}
	
	private void copy(File src, File dist) throws Exception {
		log.debug("[src]--" + src);
		log.debug("[dist]--" + dist);
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(src),
						Constant.BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(dist),
						Constant.BUFFER_SIZE);
				byte[] buf = new byte[Constant.BUFFER_SIZE];
				while (in.read(buf) > 0) {
					out.write(buf);
				}
				out.close();
				in.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (in != null)
					in.close();
				if (out != null)
					out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	// 上传文件
	public String uploadImage(File file, String fileName, String goodsId) throws Exception {
		log.debug("[file]--" + file);
		log.debug("[file name]--" + fileName);
		try{
		String saveFilePath=(ConfigManager.getInstance().getConfigItem("upload_path","defaultVal"))+File.separator
				+"syscontent"+File.separator
				+ DateUtils.getYear() + File.separator + DateUtils.getMonth() + File.separator+DateUtils.getDay(new Date())+File.separator;
				
				//上传的根路径
				String base_upload_path=ConfigManager.getInstance().getConfigItem("base_upload_path","defaultVal")+"";
				
				String destFileUrl =base_upload_path + File.separator+saveFilePath;
				String upload_file_name="";
				String uuid_file_name=UUID.randomUUID().toString().replaceAll("[a-z]|-", "");
				if( file != null ){
					 upload_file_name = uuid_file_name+fileName.substring(fileName.lastIndexOf("."),fileName.length());//截取最后一个“.”后面的后缀，确保下面截取拼接的时候不会拼接在名字中间的情况
					FileUtil.uploadImage(file, upload_file_name, destFileUrl);
				}
				//add 同步图片 shell脚本物理路径  guys 20140922
				IsSynchronousImgUtil.isSynchronousImg();
				
		   return saveFilePath+upload_file_name;
		}catch (Exception e) {
			log.error("图片上传错误！");
		}
		return null;
	}

	public void zoom(File imageFile,String destPath, String fileName) throws Exception {
		log.debug("[zoom][imageFile]--" + imageFile);
		try {
			// 缩略图存放路径
			File todir = new File(destPath);
			if (!todir.exists()) {
				todir.mkdir();
			}
			if (!imageFile.isFile())
				throw new Exception(imageFile
						+ " is not image file error in CreateThumbnail!");
			File sImg = new File(todir, fileName);
			BufferedImage Bi = ImageIO.read(imageFile);
			// 假设图片宽 高 最大为130 80,使用默认缩略算法
			Image Itemp = Bi.getScaledInstance(130, 80, Bi.SCALE_DEFAULT);
			double Ratio = 0.0;
			if ((Bi.getHeight() > 130) || (Bi.getWidth() > 80)) {
				if (Bi.getHeight() > Bi.getWidth())
					Ratio = 80.0 / Bi.getHeight();
				else
					Ratio = 130.0 / Bi.getWidth();
				AffineTransformOp op = new AffineTransformOp(AffineTransform
						.getScaleInstance(Ratio, Ratio), null);
				Itemp = op.filter(Bi, null);
			}
			ImageIO.write((BufferedImage) Itemp, "jpg", sImg);
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}

	public void watermark(File img) throws Exception {
		log.debug("[watermark file name]--" + img.getPath());
		try {
			if (!img.exists()) {
				throw new IllegalArgumentException("file not found!");
			}
			log.debug("[watermark][img]--" + img);
			// 创建一个FileInputStream对象从源图片获取数据流
			FileInputStream sFile = new FileInputStream(img);
			// 创建一个Image对象并以源图片数据流填充
			Image src = ImageIO.read(sFile);
			// 得到源图宽
			int width = src.getWidth(null);
			// 得到源图长
			int height = src.getHeight(null);
			// 创建一个BufferedImage来作为图像操作容器
			BufferedImage image = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			// 创建一个绘图环境来进行绘制图象
			Graphics2D g = image.createGraphics();
			// 将原图像数据流载入这个BufferedImage
			log.debug("width:" + width + " height:" + height);
			g.drawImage(src, 0, 0, width, height, null);
			// 设定文本字体
			g.setFont(new Font("宋体", Font.BOLD, 28));
			String rand = Constant.TEXT_TITLE;
			// 设定文本颜色
			g.setColor(Color.blue);
			// 设置透明度
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
					0.5f));
			// 向BufferedImage写入文本字符,水印在图片上的坐标
			g.drawString(rand, width - (width - 20), height - (height - 60));
			// 使更改生效
			g.dispose();
			// 创建输出文件流
			FileOutputStream outi = new FileOutputStream(img);
			// 创建JPEG编码对象
			JPEGImageEncoder encodera = JPEGCodec.createJPEGEncoder(outi);
			// 对这个BufferedImage (image)进行JPEG编码
			encodera.encode(image);
			// 关闭输出文件流
			outi.close();
			sFile.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}

	public void imageWaterMark(File imgFile) throws Exception {
		try {
			// 目标文件
			Image src = ImageIO.read(imgFile);
			int wideth = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(wideth, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			g.drawImage(src, 0, 0, wideth, height, null);
			// 水印文件 路径
			String waterImgPath = ServletActionContext.getServletContext()
					.getRealPath("/UploadImages")
					+ "/" + Constant.WATER_IMG_NAME;
			File waterFile = new File(waterImgPath);
			Image waterImg = ImageIO.read(waterFile);
			int w_wideth = waterImg.getWidth(null);
			int w_height = waterImg.getHeight(null);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
					0.5f));
			g.drawImage(waterImg, (wideth - w_wideth) / 2,
					(height - w_height) / 2, w_wideth, w_height, null);
			// 水印文件结束
			g.dispose();
			FileOutputStream out = new FileOutputStream(imgFile);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(image);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//***************** 上传图片 **********************//


	@Override
	public void delSyscont(Syscontent syscont) throws Exception {
		// TODO Auto-generated method stub
		syscontDao.remove(syscont);		
		
		
	}


	@Override
	public List<Syscontent> querySys(String conttype,String cityid) throws Exception {
		// TODO Auto-generated method stub
		return syscontDao.querySys(conttype,cityid);
	}
}
