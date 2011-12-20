/**
 * Project: jsonp-plugin
 * 
 * File Created at 2011-12-20
 * $Id$
 * 
 * Copyright 2010 dianping.com.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Dianping Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with dianping.com.
 */
package com.demo.struts.jsonp.beans;

/**
 * TODO Comment of JSONPBean
 * @author pai.li
 *
 */
public class JSONPBean {

	private String functionName;
	private Object jsonObj;
		
	public JSONPBean() {
		super();
	}
	public JSONPBean(String functionName, Object jsonObj) {
		super();
		this.functionName = functionName;
		this.jsonObj = jsonObj;
	}
	/**
	 * @return the functionName
	 */
	public String getFunctionName() {
		return functionName;
	}
	/**
	 * @param functionName the functionName to set
	 */
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	/**
	 * @return the jsonObj
	 */
	public Object getJsonObj() {
		return jsonObj;
	}
	/**
	 * @param jsonObj the jsonObj to set
	 */
	public void setJsonObj(Object jsonObj) {
		this.jsonObj = jsonObj;
	}
	
}
