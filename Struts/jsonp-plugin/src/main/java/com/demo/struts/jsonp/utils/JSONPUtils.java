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
package com.demo.struts.jsonp.utils;

import java.io.StringWriter;
import java.io.Writer;

import org.codehaus.jackson.map.ObjectMapper;
import com.demo.struts.jsonp.beans.JSONPBean;

/**
 * TODO Comment of JSONUtils
 * @author pai.li
 *
 */
public class JSONPUtils {

	public static String serialize(JSONPBean bean) throws Exception{
		
		if(bean == null){
			throw new RuntimeException("JSONPBean should not be null");
		}
		ObjectMapper mapper = new ObjectMapper();
		Writer strWriter = new StringWriter();
		mapper.writeValue(strWriter, bean.getJsonObj());
		String jsonStr = strWriter.toString();
		
		return bean.getFunctionName() + "(" + jsonStr + ");";
		
	}
	
}
