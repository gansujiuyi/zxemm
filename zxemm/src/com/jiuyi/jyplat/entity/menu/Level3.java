package com.jiuyi.jyplat.entity.menu;

public class Level3 {
	private String parent_id;
	private String menu_id; //��˵�ID
	private String menu_name; //��˵���
	private String img_src; //�˵�ͼ��·��
	private String servlet_class; //ӳ���servlet·��

	public Level3() {
	}

	public Level3(String menu_id, String menu_name, String img_src, String servlet_class) {
		this.menu_id = menu_id;
		this.menu_name = menu_name;
		this.img_src = img_src;
		this.servlet_class = servlet_class;
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

	public void setServlet_class(String servlet_class) {
		this.servlet_class = servlet_class;
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

	public String getServlet_class() {
		return (this.servlet_class);
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
}