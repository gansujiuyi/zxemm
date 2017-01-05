package com.jiuyi.jyplat.entity.housewhite;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 白名单实体类
 * @author hp
 *
 */

@Entity(name = "T_HOUSEWHITELIST")
public class HouseWhite implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	 private String houseid;//主键id
	
	 private String houseno;// 房屋编码
	 private String houseaddressno;// 房产证号
	 private String houseaddress;// 地址
	 private String houearea;// 面积
	 private String operNo; //操作员编号
	 private String createTime; //创建时间
	 private String str3; // 保留地址3(房源名称)
	 public HouseWhite() {
			super();
			// TODO Auto-generated constructor stub
		}
	 
	 public HouseWhite(String houseid, String houseno, String houseaddressno,
				String houseaddress, String houearea, String str1, String str2,
				String str3) {
			super();
			this.houseid = houseid;
			this.houseno = houseno;
			this.houseaddressno = houseaddressno;
			this.houseaddress = houseaddress;
			this.houearea = houearea;
			this.operNo = operNo;
			this.createTime = createTime;
			this.str3 = str3;
		}
	 
	 public String getHouseid() {
			return houseid;
		}

		public void setHouseid(String houseid) {
			this.houseid = houseid;
		}

		public String getHouseno() {
			return houseno;
		}

		public void setHouseno(String houseno) {
			this.houseno = houseno;
		}

		public String getHouseaddressno() {
			return houseaddressno;
		}

		public void setHouseaddressno(String houseaddressno) {
			this.houseaddressno = houseaddressno;
		}

		public String getHouseaddress() {
			return houseaddress;
		}

		public void setHouseaddress(String houseaddress) {
			this.houseaddress = houseaddress;
		}

		public String getHouearea() {
			return houearea;
		}

		public void setHouearea(String houearea) {
			this.houearea = houearea;
		}

		public String getOperNo() {
			return operNo;
		}

		public void setOperNo(String operNo) {
			this.operNo = operNo;
		}

		public String getCreateTime() {
			return createTime;
		}

		public void setCreateTime(String createTime) {
			this.createTime = createTime;
		}

		public String getStr3() {
			return str3;
		}

		public void setStr3(String str3) {
			this.str3 = str3;
		}

}
