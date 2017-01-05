package com.jiuyi.jyplat.entity.goodsCollect;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * 保存商品收藏
 * <P>TODO</P>
 * @author pengq
 */
@Entity(name = "t_goodscollect")
public class GoodsCollect {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGen")
	@SequenceGenerator(name = "seqGen", sequenceName = "S_GOODSCOLLECT")
	private Long collId;//收藏字段id
	private String goodsId;//商品id
	private Integer memberNo;//会员id
	private Integer delflag;//删除状态  0为删除状态；

	public GoodsCollect() {
		super();
	}

	public Long getCollId() {
		return collId;
	}

	public void setCollId(Long collId) {
		this.collId = collId;
	}

	public Integer getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(Integer memberNo) {
		this.memberNo = memberNo;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getDelflag() {
		return delflag;
	}

	public void setDelflag(Integer delflag) {
		this.delflag = delflag;
	}
}
