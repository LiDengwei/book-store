package com.book.manager.util;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class CommonUtil {

	private static final Logger logger = Logger.getLogger(CommonUtil.class);
	

	private static final SimpleDateFormat sdf = new SimpleDateFormat();
	public static Map<String, String> filter = null;
	

	/**
	 * 获取到Request中的文件
	 */
	public static MultipartFile[] getFilesByRequest(HttpServletRequest request) {
		if (request instanceof MultipartHttpServletRequest) {
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			Map<String, MultipartFile> multipartFile = multipartHttpServletRequest.getFileMap();
			if (multipartFile != null && !multipartFile.isEmpty()) {
				MultipartFile[] files = new MultipartFile[multipartFile.values().size()];
				files = multipartFile.values().toArray(files);
				return files;
			}
		}
		return null;
	}




	public static void main(String[] args) {
		
	}

	public static Map saveFileToServer(HttpServletRequest request, String filePath, String saveFilePathName, String saveFileName, String[] extendes) throws IOException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile(filePath);
		Map map = new HashMap();
		if ((file != null) && (!file.isEmpty())) {
			String extend = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();
			if ((saveFileName == null) || (saveFileName.trim().equals(""))) {
				saveFileName = UUID.randomUUID().toString() + "." + extend;
			}
			if (saveFileName.lastIndexOf(".") < 0) {
				saveFileName = saveFileName + "." + extend;
			}
			float fileSize = Float.valueOf((float) file.getSize()).floatValue();
			List errors = new ArrayList();
			boolean flag = true;
			if (extendes != null) {
				for (String s : extendes) {
					if (extend.toLowerCase().equals(s))
						flag = true;
				}
			}
			if (flag) {
				File path = new File(saveFilePathName);
				if (!path.exists()) {
					path.mkdir();
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				DataOutputStream out = new DataOutputStream(new FileOutputStream(saveFilePathName + File.separator + saveFileName));
				InputStream is = null;
				try {
					is = file.getInputStream();
					int size = (int) fileSize;
					byte[] buffer = new byte[size];
					while (is.read(buffer) > 0)
						out.write(buffer);
				} catch (IOException exception) {
					exception.printStackTrace();
				} finally {
					if (is != null) {
						is.close();
					}
					if (out != null) {
						out.close();
					}
				}
				if (isImg(extend)) {
					File img = new File(saveFilePathName + File.separator + saveFileName);
					try {
						BufferedImage bis = ImageIO.read(img);
						int w = bis.getWidth();
						int h = bis.getHeight();
						map.put("width", Integer.valueOf(w));
						map.put("height", Integer.valueOf(h));
					} catch (Exception localException) {
					}
				}
				map.put("mime", extend);
				map.put("fileName", saveFileName);
				map.put("fileSize", Float.valueOf(fileSize));
				map.put("error", errors);
				map.put("oldName", file.getOriginalFilename());
			} else {
				errors.add("不允许的扩展名");
			}
		} else {
			map.put("width", Integer.valueOf(0));
			map.put("height", Integer.valueOf(0));
			map.put("mime", "");
			map.put("fileName", "");
			map.put("fileSize", Float.valueOf(0.0F));
			map.put("oldName", "");
		}
		return map;
	}

	public static boolean isImg(String extend) {
		boolean ret = false;
		List<String> list = new ArrayList<String>();
		list.add("jpg");
		list.add("jpeg");
		list.add("bmp");
		list.add("gif");
		list.add("png");
		list.add("tif");
		for (String s : list) {
			if (s.equals(extend))
				ret = true;
		}
		return ret;
	}

	/**
	 * 除法
	 * @param v1
	 * @param v2
	 * @param scale
	 * @return
	 */
	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 *
	 * @param v
	 *            需要四舍五入的数字
	 * @param scale
	 *            小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	/**
	 * 乘法
	 * @param v1
	 * @param v2
	 * @param scale
	 * @return
	 */
	public static double mul(double v1, double v2,int scale) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return  round(b1.multiply(b2).doubleValue(), scale);
	}
	/**
	 * 加法
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}
	/**
	 * 减法
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 字符串数组转Long数组
	 * @param temp
	 * @return
	 */
	public static Long[] stringToLong(String temp){
		if (temp!=null && temp.length()>0) {
			String[] id = temp.split(",");

			if(id!=null && id.length>0){
				Long[] result=new Long[id.length];
				for (int i=0;i<id.length;i++){
					result[i]=new Long(id[i]);
				}
				return result;
			}else {
				return null;
			}
		}else {
			return null;
		}
	}

	/**
	 * 字符串数组转Integer数组
	 * @param temp
	 * @return
	 */
	public static Integer[] stringToInteger(String temp){
		if (temp!=null && temp.length()>0) {
			String[] id = temp.split(",");

			if(id!=null && id.length>0){
				Integer[] result=new Integer[id.length];
				for (int i=0;i<id.length;i++){
					result[i]=new Integer(id[i]);
				}
				return result;
			}else {
				return null;
			}
		}else {
			return null;
		}
	}
}

