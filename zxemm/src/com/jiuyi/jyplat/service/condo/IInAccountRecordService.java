package com.jiuyi.jyplat.service.condo;

import java.util.List;

import com.jiuyi.jyplat.entity.condo.InAccountRecord;

/**
 * 入账信息实体Service层接口
 * @author wsf
 *
 */
public interface IInAccountRecordService {

	/**
	 * 根据houseNo 地幢号  查询所有InAccountRecord记录
	 * @param houseNo
	 * @return
	 * @throws Exception
	 */
	public List<InAccountRecord> getByHouseNo(String houseNo) throws Exception;
}
