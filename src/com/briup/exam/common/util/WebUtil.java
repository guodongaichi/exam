package com.briup.exam.common.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.apache.struts2.ServletActionContext;

import com.briup.exam.common.util.jsonConfig.DateJsonValueProcessor;



/**
 * WebUtil <br/>
 * 1 �ṩ����Json�ַ�����ǰ�˵ķ���<br/>
 * �Զ������������ת����Json��ʽ���ַ��������ṩ�������Թ��˵Ĺ��� <br/>
 * 2 �ṩ���ʹ�����Ϣ��������ķ�������ʽΪjson <br/>
 * 
 */
public class WebUtil {
	/**
	 * ���ʹ�����Ϣ���ͻ���
	 * 
	 * @param errorMsg
	 *            ������Ϣ
	 */
	public static void sendErrorMsg(final String errorMsg) {
		final HttpServletResponse response = ServletActionContext.getResponse();
		final Message message = new Message(errorMsg,Message.FAIL);
		response.setStatus(UtilConstants.SERVER_ERROR);
		final JSONObject jsonMessage = JSONObject.fromObject(message);
		write(jsonMessage.toString(), response);
	}
	
	/**
	 * ���ͳɹ���Ϣ���ͻ���
	 * @date 2014-3-12
	 * @param msg ��Ϣ����
	 * @param type ��Ϣ����
	 */
	public static void sendMessage(final String msg,final String type) {
		final HttpServletResponse response = ServletActionContext.getResponse();
		final Message message = new Message(msg,type);
		final JSONObject jsonMessage = JSONObject.fromObject(message);
		write(jsonMessage.toString(), response);
	}
	
	public static void sendInfoMsg(final String msg) {
		sendMessage(msg, Message.INFO);
	}
	
	public static void sendSuccessMsg(final String msg) {
		sendMessage(msg, Message.SUCCESS);
	}

	/**
	 * ������ת��ΪJson��ʽ�����͵��ͻ���
	 * 
	 */
	public static void sendJSONObjectResponse(final Object object) {
		sendJSONObjectResponse(object, null);
	}

	/**
	 * ������ת��ΪJson��ʽ�����͵��ͻ���
	 * 
	 * @param excludes
	 *            �����󲿷�����
	 */
	public static void sendJSONObjectResponse(final Object object,
			final String[] excludes) {
		final JsonConfig jsonConfig = new JsonConfig();
		if (excludes != null) {
			jsonConfig.setExcludes(excludes);
		}
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.registerJsonValueProcessor(Date.class,
				new DateJsonValueProcessor());
		final JSONObject jsonObject = JSONObject.fromObject(object, jsonConfig);
		sendResponse(jsonObject.toString());
	}

	/**
	 * ������ת��ΪJson��ʽ�����͵��ͻ���
	 * 
	 */
	public static void sendJSONArrayResponse(final Object array) {
		sendJSONArrayResponse(array, new String[] {});
	}

	/**
	 * ������ת��ΪJson��ʽ�����͵��ͻ��� ���ṩȥ�����󲿷����ԵĹ���
	 * 
	 * @param excludes
	 *            �����󲿷�����
	 */
	public static void sendJSONArrayResponse(final Object array,
			final String[] excludes) {
		sendJSONArrayResponse(array, excludes, null);
	}

	/**
	 * ������ת��ΪJson��ʽ�����͵��ͻ��� ���ṩȥ�����󲿷����ԵĹ��� �ṩ��ҳ����
	 */
	public static void sendJSONArrayResponse(final Object array,
			final String[] excludes, final IPageInfo pageInfo) {
		final JsonConfig config = new JsonConfig();
		config.setExcludes(excludes);
		sendJSONArrayResponse(array, config, pageInfo);
	}

	/**
	 * ������ת��ΪJson��ʽ�����͵��ͻ��� ����JsonConfig
	 * 
	 */
	@Deprecated
	public static void sendJSONArrayResponse(final Object array,
			final JsonConfig jsonConfig) {
		sendJSONArrayResponse(array, jsonConfig, null);
	}

	/**
	 * ������ת��ΪJson��ʽ�����͵��ͻ��� <br>
	 * ����JsonConfig ֧�ַ�ҳ��ѯ
	 * 
	 */
	@Deprecated
	public static void sendJSONArrayResponse(final Object array,
			final JsonConfig jsonConfig, final IPageInfo pageInfo) {
		jsonConfig.registerJsonValueProcessor(Date.class,
				new DateJsonValueProcessor());
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		final JSONArray jsonArray = JSONArray.fromObject(array, jsonConfig);
		if (pageInfo != null) {
			final JSONObject jsonObject = new JSONObject();
			jsonObject.put("total", pageInfo.getTotalCount());
			jsonObject.put("rows", jsonArray);
			sendResponse(jsonObject.toString());
		} else {
			sendResponse(jsonArray.toString());
		}
	}

	/**
	 * �����ַ���
	 * 
	 */
	public static void sendResponse(final String text) {
		HttpServletResponse response = ServletActionContext.getResponse();
		write(text, response);
	}

	/**
	 * ���ַ���д��response writer����
	 * 
	 */
	private static void write(final String context,
			final HttpServletResponse response) {
		PrintWriter writer = null;
		try {
			response.setContentType("text/plain");
			response.setCharacterEncoding("utf-8");
			writer = response.getWriter();
			writer.write(context);
		} catch (IOException e) {

		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	public static HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	public static HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}
	
	public static int getParameter(String paramName,int defaultValue){
		HttpServletRequest req = getRequest();
		return getParameter(req, paramName, defaultValue);
	}
	
	public static String getParameter(String paramName,String defaultValue){
		HttpServletRequest req = getRequest();
		return getParameter(req, paramName, defaultValue);
	}
	
	public static int getParameter(HttpServletRequest request,String paramName,int defaultValue){
		String value = request.getParameter(paramName);
		if(value!=null){
			try {
				return Integer.parseInt(value);
			} catch (NumberFormatException e) {
			}
		}
		return defaultValue;
	}
	
	public static String getParameter(HttpServletRequest request,String paramName,String defaultValue){
		String value = request.getParameter(paramName);
		if(value!=null){
			return value;
		}
		return defaultValue;
	}
}

