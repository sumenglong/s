package com.sml.sn.util;

import java.util.List;

public class Node {
	private Integer id;// 节点Id
	private String text;// 节点名称
	private String path;//路径
	private String weight;//权重
	private boolean checked;//表示该节点是否被选中。
	private Integer parentId;
	private List<Node> children;// 子节点的集合
	public Node() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Node(Integer id, String text, String path, String weight,
				boolean checked, Integer parentId, List<Node> children) {
		super();
		this.id = id;
		this.text = text;
		this.path = path;
		this.weight = weight;
		this.checked = checked;
		this.parentId = parentId;
		this.children = children;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public List<Node> getChildren() {
		return children;
	}
	public void setChildren(List<Node> children) {
		this.children = children;
	}
	@Override
	public String toString() {
		return "Node [id=" + id + ", text=" + text + ", path=" + path
				+ ", weight=" + weight + ", checked=" + checked + ", parentId="
				+ parentId + ", children=" + children + "]";
	}

}
