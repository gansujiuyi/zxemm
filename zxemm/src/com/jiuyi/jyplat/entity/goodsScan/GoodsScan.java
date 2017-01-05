package com.jiuyi.jyplat.entity.goodsScan;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * 浏览记录实体
 * <P>TODO</P>
 * @author pengq
 */
@Entity(name = "t_goodsScan")
public class GoodsScan {
	
		//设置主键自增
		@Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGen")
		@SequenceGenerator(name = "seqGen", sequenceName = "S_GOODSSCAN")
		private Long scanId;//浏览字段id
		private String goodsId;//商品id
		private Integer memberNo;//会员id
		private Integer delflag;//删除状态  0为删除状态；
		private Integer count;//浏览的次数
		private String lastTime;//最后浏览时间
		
		
		public Long getScanId() {
			return scanId;
		}
		public void setScanId(Long scanId) {
			this.scanId = scanId;
		}
		public String getGoodsId() {
			return goodsId;
		}
		public void setGoodsId(String goodsId) {
			this.goodsId = goodsId;
		}
		public Integer getMemberNo() {
			return memberNo;
		}
		public void setMemberNo(Integer memberNo) {
			this.memberNo = memberNo;
		}
		public Integer getDelflag() {
			return delflag;
		}
		public void setDelflag(Integer delflag) {
			this.delflag = delflag;
		}
		public Integer getCount() {
			return count;
		}
		public void setCount(Integer count) {
			this.count = count;
		}
		public String getLastTime() {
			return lastTime;
		}
		public void setLastTime(String lastTime) {
			this.lastTime = lastTime;
		}
		
		
}
