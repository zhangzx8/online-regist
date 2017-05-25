package com.haoxw.terminal.business.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 序列化工具类
 * @author haoxw
 *
 */
public class SerializeUtil {
	private final static Logger logger = LoggerFactory
			.getLogger(SerializeUtil.class);

	/**
	 * 字节数组转换对象
	 * 
	 * @param bytes
	 * @return
	 */
	public static Object byteToObject(byte[] bytes) {
		Object obj = null;
		try {
			ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
			ObjectInputStream oi = new ObjectInputStream(bi);

			obj = oi.readObject();
			bi.close();
			oi.close();
		} catch (Exception e) {
			logger.error("", e);
		}
		return obj;
	}

	/**
	 * 对象转换成字节数组
	 * @param obj
	 * @return
	 */
	public static byte[] objectToByte(java.lang.Object obj) {
		byte[] bytes = null;
		try {
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			ObjectOutputStream oo = new ObjectOutputStream(bo);
			oo.writeObject(obj);
			bytes = bo.toByteArray();

			bo.close();
			oo.close();
		} catch (Exception e) {
			logger.error("", e);
		}
		return bytes;

	}
}
