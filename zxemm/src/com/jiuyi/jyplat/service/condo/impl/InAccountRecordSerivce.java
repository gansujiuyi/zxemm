package com.jiuyi.jyplat.service.condo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jiuyi.jyplat.dao.condo.InAccountRecordDao;
import com.jiuyi.jyplat.entity.condo.InAccountRecord;
import com.jiuyi.jyplat.service.condo.IInAccountRecordService;


/**
 * 入账信息实体Service层实现类
 * @author wsf
 *
 */
@Service("inAccountRecordSerivce")
public class InAccountRecordSerivce implements IInAccountRecordService {
	
	Logger log = Logger.getLogger(InAccountRecordSerivce.class);
	
	@Resource
	private InAccountRecordDao inAccountRecordDao;

	/**
	 * 根据houseNo 地幢号  查询所有InAccountRecord记录
	 * @param houseNo
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = true , propagation = Propagation.SUPPORTS)
	public List<InAccountRecord> getByHouseNo(String houseNo) throws Exception {
		//测试数据
		List<InAccountRecord> list = new ArrayList<InAccountRecord>();
		InAccountRecord record1 = new InAccountRecord();
		record1.setId("1");
		record1.setHouseNo("2016");
		record1.setAmt("1000.00");
		record1.setBuyerName("小小");
		record1.setBuyerIdNo("421182199211012530");
		record1.setInDate("2016-03-28 18:18:19");
		record1.setHosueAddr("三单元405号");
		list.add(record1);
		InAccountRecord record2 = new InAccountRecord();
		record2.setId("2");
		record2.setHouseNo("2016");
		record2.setAmt("2000.00");
		record2.setBuyerName("大大");
		record2.setBuyerIdNo("421182199211012531");
		record2.setInDate("2016-03-27 18:18:19");
		record2.setHosueAddr("三单元406号");
		list.add(record2);
		return list;
		//return inAccountRecordDao.findBy("houseNo", houseNo);
	}
	
	
}
