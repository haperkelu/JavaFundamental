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
package com.demo.struts.jsonp;

import java.io.PrintWriter;
import java.lang.reflect.Field;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.StrutsStatics;
import com.demo.struts.jsonp.beans.JSONPBean;
import com.demo.struts.jsonp.utils.JSONPUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;

public class JSONPResult implements Result {

	
	private Object action;
	private static final String JSONP_FUNCTION_NAME_KEY = "jsonpFunctionName";
	private static final String JSONP_OBJECT_NAME_KEY = "jsonpObjectName";
	/**
	 * SerialVersionUID
	 */
	private static final long serialVersionUID = 8973513490651915853L;

	public void execute(ActionInvocation invocation) throws Exception {
		ActionContext actionContext = invocation.getInvocationContext();
        HttpServletResponse response = (HttpServletResponse) actionContext.get(StrutsStatics.HTTP_RESPONSE);
        this.action = invocation.getAction();      
        Field fieldFunc = this.action.getClass().getDeclaredField(JSONP_FUNCTION_NAME_KEY);
        String jsonpName = (String) fieldFunc.get(this.action);
        Field fieldComp = this.action.getClass().getDeclaredField(JSONP_OBJECT_NAME_KEY);
        Object jsonpObj = fieldComp.get(this.action);
        JSONPBean bean = new JSONPBean(jsonpName, jsonpObj);
        String result = JSONPUtils.serialize(bean);
        PrintWriter out = response.getWriter();
        out.print(result);
	}
	
}
