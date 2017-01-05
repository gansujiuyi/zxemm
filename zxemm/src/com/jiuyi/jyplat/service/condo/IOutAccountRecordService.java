package com.jiuyi.jyplat.service.condo;

import java.util.List;

import com.jiuyi.jyplat.entity.condo.OutAccountRecord;

/**
 * 出账信息实体Service层接口
 * @author wsf
 *
 */
public interface IOutAccountRecordService {

	/**
	 * 根据 houseNo 地幢号 查询 OutAccountRecord 所有记录
	 * @param houseNo
	 * @return
	 * @throws Exception
	 */
	public List<OutAccountRecord> getByHouseNo(String houseNo)throws Exception;
}
