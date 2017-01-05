package com.jiuyi.jyplat.service.condo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jiuyi.jyplat.dao.condo.OutAccountRecordDao;
import com.jiuyi.jyplat.entity.condo.InAccountRecord;
import com.jiuyi.jyplat.entity.condo.OutAccountRecord;
import com.jiuyi.jyplat.service.condo.IOutAccountRecordService;

/**
 * 出账信息实体Service层实现类
 * @author wsf
 *
 */
@Service("outAccountRecordService")
public class OutAccountRecordService implements IOutAccountRecordService {

	Logger log = Logger.getLogger(OutAccountRecordService.class);
	
	@Resource
	private OutAccountRecordDao outAccountRecordDao;

	/**
	 * 根据 houseNo 地幢号 查询 OutAccountRecord 所有记录
	 * @param houseNo
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = true , propagation = Propagation.SUPPORTS)
	public List<OutAccountRecord> getByHouseNo(String houseNo) throws Exception {
		//测试数据
		List<OutAccountRecord> list = new ArrayList<OutAccountRecord>();
		OutAccountRecord record1 = new OutAccountRecord();
		record1.setId("1");
		record1.setHouseNo("2016");
		record1.setAmt("1000.00");
		record1.setDate("2016-03-18 18:18:12");
		list.add(record1);
		OutAccountRecord record2 = new OutAccountRecord();
		record2.setId("2");
		record2.setHouseNo("2016");
		record2.setAmt("2000.00");
		record2.setDate("2016-03-18 18:18:12");
		list.add(record2);
		return list;
		
		//return outAccountRecordDao.findBy("houseNo", houseNo);
	}
}
