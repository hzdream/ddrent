/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.common.util.io 
 * @author imart·deng
 * @date 创建时间：2018年9月18日 下午2:57:02
 * @version 1.0
 */
package com.aifeng.ddrent.common.util.io;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

/** 
 * @ClassName: ImageUtils 
 * @Description: 图片工具
 * @author: imart·deng
 * @date: 2018年9月18日 下午2:57:02  
 */
public class ImageUtils {
	
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

}
