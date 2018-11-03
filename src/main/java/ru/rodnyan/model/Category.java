package ru.rodnyan.model;

public class Category extends AbstractModel {

	private String href;
	private Icon[] icons;
	private String id;
	private String name;

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public Icon[] getIcons() {
		return icons;
	}

	public void setIcons(Icon[] icons) {
		this.icons = icons;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
