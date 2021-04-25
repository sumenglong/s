package com.sml.sn.util;


import com.google.gson.Gson;
import com.sml.sn.entity.Modules;

import java.util.ArrayList;
import java.util.List;


public class UITreeFactory {
	/**
	 * 获取父节点的菜单
	 */
	private static List<Node> nodeTreeList=new ArrayList<Node>();//所有节点集合
	/**
	 * 初始化树形结构
	 */
	public static void loadTree(List<Modules> mList){
		if(mList!=null && mList.size()>0){
			for (Modules ms : mList) {
				if(ms.getParentId()==0){
					Node node=new Node(ms.getId(), ms.getName(),ms.getPath(),ms.getWeight(), false,ms.getParentId(), new ArrayList<Node>());
					createChildren(node,mList);
					nodeTreeList.add(node);
				}
			}

		}
	}

	/**
	 * 为节点node增加子节点
	 * @param
	 * @param
	 */
	private static void createChildren(Node node,List<Modules> mList){
		for(Modules ms : mList){
			if(node.getId()==ms.getParentId()){
				Node ziNode=new Node(ms.getId(), ms.getName(),ms.getPath(),ms.getWeight(), false,ms.getParentId(), new ArrayList<Node>());
				node.getChildren().add(ziNode);
				createChildren(ziNode,mList);//递归调用
			}
		}

	}

	/**
	 * 返回树形格式的json串
	 */
	public static String toJsonTree(List<Modules> mList){
		nodeTreeList.clear();
		loadTree(mList);//初始化模块树
		Gson gson=new Gson();
		return gson.toJson(nodeTreeList);
	}


}
