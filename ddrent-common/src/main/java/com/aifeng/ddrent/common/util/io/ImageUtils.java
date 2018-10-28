/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.common.util.io 
 * @author imart·deng
 * @date 创建时间：2018年9月18日 下午2:57:02
 * @version 1.0
 */
package com.aifeng.ddrent.common.util.io;

import com.aifeng.ddrent.common.enums.io.FileType;
import com.aifeng.ddrent.common.util.system.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

/** 
 * @ClassName: ImageUtils 
 * @Description: 图片工具
 * @author: imart·deng
 * @date: 2018年9月18日 下午2:57:02  
 */
public class ImageUtils {
	private static final Logger logger = LoggerFactory.getLogger(ImageUtils.class);
	
	private static final String FONT_NAME = "微软雅黑";
	private static final int FONT_STYLE = Font.ITALIC;
	private static final int FONT_SIZE = 40;//文字大小
	private static final float alpha = 0.5f;//透明度
	
	/**
	 * 图片添加文字水印
	 * 
	 * @param inputStream
	 * @param watermarkText
	 * @param outputStream
	 */
	public static void imageAddWatermark(InputStream inputStream, String watermarkText, OutputStream outputStream){
		try {
			BufferedImage bufferedImage = ImageIO.read(inputStream);
			int imageHeight = bufferedImage.getHeight();
			int imageWidth = bufferedImage.getWidth();
			
			//创建图片绘图工具
			Graphics2D graphics = bufferedImage.createGraphics();
			graphics.drawImage(bufferedImage, imageWidth, imageHeight, null);
			
			//水印文字风格
			graphics.setFont(new Font(FONT_NAME, FONT_STYLE, FONT_SIZE));
			graphics.setColor(Color.GRAY);
			
			//水印文本宽高
			int waterMarkTextHeight = FONT_SIZE;
			int waterMarkTextWidth = FONT_SIZE * getTextLength(watermarkText);
			
			//水印展示方式
			graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
			//水印文字倾斜角度
			graphics.rotate(Math.toRadians(30), bufferedImage.getWidth()/2, bufferedImage.getHeight()/2);
			 
			//写入水印
			int x = -imageWidth/2;
			int y = -imageHeight/2;
			while(x < imageWidth*1.5){
				y = -imageHeight/2;
				while(y < imageHeight*1.5){
					graphics.drawString(watermarkText, x, y);
					y+=waterMarkTextHeight+200;
				}
				x+=waterMarkTextWidth+100;
			}
			graphics.dispose();
			
			ImageIO.write(bufferedImage, "png", outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//计算水印文本长度
	//1、中文长度即文本长度 2、英文长度为文本长度二分之一
	public static int getTextLength(String text){
		 //水印文字长度
		 int length = text.length();
		 
		 for (int i = 0; i < text.length(); i++) {
			 String s =String.valueOf(text.charAt(i));
			 if (s.getBytes().length>1) {
				 length++;
			 }
	 	 }
		 length = length%2==0?length/2:length/2+1;
		 return length;
	}

	/**
	 * 校验是否为允许的图片类型
	 * @param path		图片文件路径
	 * @param types		允许的图片类型
	 * @return 如果符合允许图片后缀返回图片后缀(.png ...), 否则返回null
	 */
	public static String verifyImageType(String path, FileType ... types){
		if(StringUtils.isEmpty(path)) return null;
		if(null == types) types = new FileType[]{FileType.JPEG,FileType.PNG};
		byte[] b = new byte[28];
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(new File(path));
			inputStream.read(b, 0, 28);
			String suffixHex = byte1Hex(b);
			FileType fileType = FileType.getByValue(suffixHex);
			if(null != fileType) {
				for (FileType fileType1: types) {
					// office 文件特殊处理
					if(fileType1.equals(FileType.XLS_DOC) && fileType1.equals(fileType)) {
						path = path.substring(path.lastIndexOf("."));
						if(path.indexOf("xls")==0 || path.indexOf("doc") == 0) return "." + path;
					}
					if(fileType1.equals(fileType)) fileType.getSuffix();
				}
			}
		} catch (IOException e) {
			logger.error("[校验文件类型] 文件流失败, 请求path:{}, 失败原因:{}", path, e.getMessage());
		}finally {
			if(null != inputStream){
				try {
					inputStream.close();
				} catch (IOException e) {
					logger.error("[校验文件类型] 关闭文件流失败, 请求path:{}, 失败原因:{}", path, e.getMessage());
				}
			}
		}

		return null;
	}

	private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
	/**
	 * 字节转16进制字符串
	 * @param bytes
	 * @return
	 */
	public static String byte1Hex(byte[] bytes){
		if(null == bytes) return "";
		char[] hexChars = new char[bytes.length * 2];
		for ( int j = 0; j < bytes.length; j++ ) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}

}
