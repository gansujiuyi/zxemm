package com.jiuyi.jyplat.entity.menu;

import java.util.List;

public class Level1 {
	private String menu_id; //��˵�ID
	private String menu_name; //��˵���
	private String img_src; //�˵�ͼ��·��
	private List<Level2> level2; //�����˵��µ����ж����˵�

	public Level1() {
	}

	public Level1(String menu_id, String menu_name, String img_src, List<Level2> level2) {
		this.menu_id = menu_id;
		this.menu_name = menu_name;
		this.img_src = img_src;
		this.level2 = level2;
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

	public List<Level2> getLevel2() {
		return level2;
	}

	public void setLevel2(List<Level2> level2) {
		this.level2 = level2;
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

}