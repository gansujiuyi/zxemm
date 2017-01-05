package com.jiuyi.jyplat.entity.menu;

import java.util.List;

public class Level2 {
	private String parent_id;
	private String menu_id; //�����˵�ID
	private String menu_name; //�����˵���
	private String img_src; //�˵�ͼ��·��
	private List<Level3> level3; //�ö����˵��µ�������˵�

	public Level2() {
	}

	public Level2(String menu_id, String menu_name, String img_src, List<Level3> level3) {
		this.menu_id = menu_id;
		this.menu_name = menu_name;
		this.img_src = img_src;
		this.level3 = level3;
	}

	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public void setImg_src(String img_src) {
		this.img_src = img_src;
	}

	public String getMenu_id() {
		return (this.menu_id);
	}

	public String getMenu_name() {
		return (this.menu_name);
	}

	public String getImg_src() {
		return (this.img_src);
	}

	public List<Level3> getLevel3() {
		return level3;
	}

	public void setLevel3(List<Level3> level3) {
		this.level3 = level3;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

}