package com.ai.yc.common.api.syssensitive.param;

import java.io.Serializable;

public class DeleteSysSensitive implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 编号
	 */
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
