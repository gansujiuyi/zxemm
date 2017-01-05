package com.jiuyi.jyplat.service.acegiManage.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jiuyi.jyplat.dao.acegiManage.InstitutionDao;
import com.jiuyi.jyplat.dao.base.LogInfoDao;
import com.jiuyi.jyplat.entity.system.Institution;
import com.jiuyi.jyplat.entity.system.Operator;
import com.jiuyi.jyplat.service.acegiManage.InstitutionService;
import com.jiuyi.jyplat.util.DBAction;
import com.jiuyi.jyplat.util.OperConstant;
import com.jiuyi.jyplat.web.page.PageFinder;
import com.jiuyi.jyplat.web.page.Query;

/**
 * 机构维护
 * @author leiyongjun
 *  May 24, 2011  5:41:41 PM
 */
@Service("instService")
public class InstitutionServiceImpl implements InstitutionService {

	Logger log = Logger.getLogger(InstitutionServiceImpl.class);

	@Resource
	private InstitutionDao institutionDao;
	@Resource
	private LogInfoDao logDao;

	private static Hashtable instTable = new Hashtable(); // 该表存放机构号对应机构名称
	private static TopInst topInst = null;

	static {
		try {
			init();
		} catch (Exception e) {
			System.out.println("查询所有机构失败.原因：" + e.getMessage());
		}
	}

	/**
	 * 根据Id查询机构信息
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Institution getInstById(Integer instId) throws Exception {
		return institutionDao.getById(instId);
	}

	/**
	 * 分页查询所有机构信息
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public PageFinder<Institution> queryInstsByName(String instName, Query query) throws Exception {
		Criteria criteria = institutionDao.createCriteria();
		if (null != instName && !"".equalsIgnoreCase(instName.trim())) {
			criteria.add(Restrictions.like("institutionName", "%" + instName.trim() + "%"));
		}
		return institutionDao.pagedByCriteria(criteria, query.getPage(), query.getPageSize(),
				Order.asc("institutionNo"));
	}

	/**
	 * 查询所有机构信息
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Institution> queryInstsList() throws Exception {
		Criteria criteria = institutionDao.createCriteria();
		criteria.addOrder(Order.desc("institutionNo"));
		return criteria.list();
	}

	/**
	 * 根据属性查询所有组织机构信息
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Institution> queryInstsList(Institution inst) throws Exception {
		Criteria criteria = institutionDao.createCriteria();
		if (inst.getInstitutionName() != null && !"".equals(inst.getInstitutionName())) {
			criteria.add(Restrictions.eq("institutionName", inst.getInstitutionName().trim()));
		}
		return criteria.list();
	}

	/**
	 * 保存新增机构信息
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void insertInsts(Institution inst) throws Exception {
		institutionDao.save(inst);
		//生成操作员日志
		logDao.addLog(OperConstant.TYPE_BASE, OperConstant.TYPE_BASE_INST, OperConstant.ACT_ADD, "");
	}

	/**
	 * 修改机构信息
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateInsts(Institution inst) throws Exception {
		institutionDao.update(inst);
		//生成操作员日志
		logDao.addLog(OperConstant.TYPE_BASE, OperConstant.TYPE_BASE_INST, OperConstant.ACT_EDIT,
				"机构编号：" + inst.getInstitutionId());
	}

	/**
	 * 删除机构信息
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteByNo(Institution inst) throws Exception {
		if (inst == null || inst.getInstitutionId().equals("0"))
			throw new Exception("数据格式错误,删除组织机构失败");
		institutionDao.deleteByNo(inst);
		//生成操作员日志
		logDao.addLog(OperConstant.TYPE_BASE, OperConstant.TYPE_BASE_INST, OperConstant.ACT_DEL,
				"机构编号：" + inst.getInstitutionId());
	}

	//----------------------------add by lzb on 2012/08/06 -----------------------------------------------------------------------------
	// 机构初始化,读入当前系统所有机构
	public static void init() throws SQLException {
		// long start = System.currentTimeMillis();
		try {
			String allInstSql = "SELECT INST.INSTITUTIONNO,INST.INSTITUTIONNAME,INST.PARENTINSTITUTIONID "
					+ " FROM INSTITUTION INST ORDER BY INST.INSTITUTIONID ";
			String[][] allInst = new DBAction().executeSearchAll(allInstSql);

			if (allInst == null)
				return;

			// 第一次循环取第一级机构(即分行)
			for (int i = 0; i < allInst.length; i++) {
				if (allInst[i][2].equals("0") || allInst[i][2].equals(allInst[i][0])) {
					instTable.put(allInst[i][0], allInst[i][1]);
					topInst = new TopInst(allInst[i][0], allInst[i][1]);
					break;
				}
			}

			// 第二次循环取第二级机构(支行一级)
			for (int i = 0; i < allInst.length; i++) {
				if (allInst[i][2].equals(topInst.getInst_no()) && (!allInst[i][2].equals(allInst[i][0]))) {
					topInst.setMidInst(new MidInst(allInst[i][0], allInst[i][1]));
					instTable.put(allInst[i][0], allInst[i][1]);
				}
			}

			// 第三次循环取三级机构
			MidInst _midInst;
			for (int i = 0; i < allInst.length; i++) {
				if ((!allInst[i][2].equals(topInst.getInst_no()) && (!allInst[i][2].equals("")))) {
					_midInst = topInst.getMidInstByNo(allInst[i][2]);
					if (_midInst != null) {
						_midInst.setLowerInst(new LowerInst(allInst[i][0], allInst[i][1]));
						instTable.put(allInst[i][0], allInst[i][1]);
					}
				}
			}
		} catch (Exception e) {
			throw new SQLException("查询所有机构失败");
		}

	}

	/**
	 * 根据当前用户权限生成机构的下拉框列表显示
	 * 
	 * @return String html语句,形式如 <option value=''>*</option> 权限为 1则不能选择机构
	 */
	public List<String> queryInstNosByOper(Operator oper) {
		String curInstNo = oper.getInst().getInstitutionNo(); // 取当前用户所属机构号
		String operLvl = oper.getOperLvl();
		List<String> instNos = new ArrayList<String>();

		// 如果操作员级别为1-行员级，则可以看本机构和本机构的下属机构，不能看所有
		if (operLvl.equals("1")) {
			instNos.add(curInstNo);
			return instNos;
		}
		// 如果操作员级别为2-机构级，则可以看本机构和本机构的下属机构，不能看所有
		else if (operLvl.equals("2")) {
			MidInst midInst = topInst.getMidInstByNo(curInstNo);

			if (midInst == null) // 当前机构号不是二级机构时,返回当前机构号
			{
				instNos.add(curInstNo);
			} else // 是二级机构,返回当前机构和所有二级机构
			{
				instNos.add(curInstNo);

				LowerInst[] allLower = midInst.getAllLowerInst();
				for (int i = 0; (allLower != null) && (i < allLower.length); i++) {
					instNos.add(allLower[i].getInst_no());
				}
				return instNos;
			}
		}

		// 如果操作员级别为3-管理员，则即使不是9999机构，也能查看所有的机构列表
		if (operLvl.equals("3")) {
			return null;
		}

		// 当前机构号是一级机构的时候,返回所有机构下拉显示
		if (topInst.getInst_no().equals(curInstNo)) {
			return null;
		} else {

			MidInst midInst = topInst.getMidInstByNo(curInstNo);
			if (midInst == null) // 当前机构号不是二级机构时,返回当前机构号
			{
				instNos.add(curInstNo);
			} else // 是二级机构,返回当前机构和所有二级机构
			{
				instNos.add(curInstNo);

				LowerInst[] allLower = midInst.getAllLowerInst();
				for (int i = 0; (allLower != null) && (i < allLower.length); i++) {
					instNos.add(allLower[i].getInst_no());
				}
			}
			return instNos;
		}
	}

	/**
	 * 取当前机构的所有下级机构, 包括当前机构
	 */
	public static String[] getAllLowerInst(String curInstNo) {
		Vector vecInst = new Vector();

		// 当前机构号是一级机构的时候,返回所有机构号
		if (topInst.getInst_no().equals(curInstNo)) {
			// Enumeration enu = instTable.elements();
			Enumeration enu = instTable.keys(); // 注：此处机构号为主键
			while (enu.hasMoreElements()) {
				vecInst.add(enu.nextElement());
			}
		} else {
			MidInst midInst = topInst.getMidInstByNo(curInstNo);
			if (midInst == null) // 当前机构号不是二级机构时,返回当前机构号
			{
				vecInst.add(curInstNo);
			} else // 是二级机构,返回当前机构和所有二级机构
			{
				vecInst.add(curInstNo); // 加入二级机构号
				LowerInst[] allLower = midInst.getAllLowerInst();
				for (int i = 0; (allLower != null) && (i < allLower.length); i++) {
					vecInst.add(allLower[i].getInst_no()); // 加入二级机构的所有下级机构号
				}
			}
		}

		String[] rtn = new String[vecInst.size()];
		vecInst.toArray(rtn);
		return rtn;
	}

	/**
	 * 判断输入的机构号是否是top机构
	 */
	public static boolean isTop(String inst_no) {
		if (topInst.getInst_no().equalsIgnoreCase(inst_no)) {
			return true;
		} else {
			return false;
		}
	}

	// public getter方法, 取总机构
	public static TopInst getTopInst() {
		return topInst;
	}

	// 总机构
	public static class TopInst {
		private String inst_no;
		private String inst_name;
		private Vector vMidInst;

		// 设置总机构号和总机构名
		public TopInst(String inst_no, String inst_name) {
			this.inst_no = inst_no;
			this.inst_name = inst_name;
			vMidInst = new Vector();
		}

		public String getInst_no() {
			return this.inst_no;
		}

		public String getInst_name() {
			return this.inst_name;
		}

		// 设置下级机构
		public void setMidInst(MidInst midInst) {
			vMidInst.add(midInst);
		}

		// 根据下级机构号取机构
		public MidInst getMidInstByNo(String instno) {
			MidInst midInst;
			for (int i = 0; i < vMidInst.size(); i++) {
				midInst = (MidInst) vMidInst.get(i);
				if (midInst.getInst_no().equals(instno)) {
					return midInst;
				}
			}
			return null;
		}

		// 取所有下级机构
		public MidInst[] getAllMidInst() {
			MidInst[] allMidInst = new MidInst[vMidInst.size()];
			vMidInst.toArray(allMidInst);
			return allMidInst;
		}

	}

	// 二级机构
	public static class MidInst {
		private String inst_no;
		private String inst_name;
		private Vector vLowerInst;

		// 设置二级机构号和二级机构名
		public MidInst(String inst_no, String inst_name) {
			this.inst_no = inst_no;
			this.inst_name = inst_name;
			vLowerInst = new Vector();
		}

		public String getInst_no() {
			return this.inst_no;
		}

		public String getInst_name() {
			return this.inst_name;
		}

		// 设置下级机构
		public void setLowerInst(LowerInst lowerInst) {
			vLowerInst.add(lowerInst);
		}

		// 取所有下级机构
		public LowerInst[] getAllLowerInst() {
			LowerInst[] allLowerInst = new LowerInst[vLowerInst.size()];
			vLowerInst.toArray(allLowerInst);
			return allLowerInst;
		}
	}

	// 三级机构
	public static class LowerInst {
		private String inst_no;
		private String inst_name;

		// 设置三级机构号和三级机构名
		public LowerInst(String inst_no, String inst_name) {
			this.inst_no = inst_no;
			this.inst_name = inst_name;
		}

		public String getInst_no() {
			return this.inst_no;
		}

		public String getInst_name() {
			return this.inst_name;
		}
	}

	//------------------------------------end add ---------------------------------------------------------------------------------------------
}
