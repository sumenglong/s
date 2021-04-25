package com.sml.sn.util;

import java.io.Serializable;

/**
 * ϵͳToken���ƶ���:�洢�û���Ϣ ���û�Ȩ����Ϣ
 * ����¼�ɹ��Ժ��Token������Ϣ��Ӧ���ͻ���,
 * ������ÿ��ͨ��token ��������� ��¼����
 * @Description:   Token���ƶ���
 */
public class Token implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userId;//�û�Id
	
	private String userName;//�û���
	
	private String password;//�û�����

	public Token() {
		// TODO Auto-generated constructor stub
	}

	public Token(String userId, String userName, String password) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Token [userId=" + userId + ", userName=" + userName
				+ ", password=" + password + "]";
	}
	
	
}
