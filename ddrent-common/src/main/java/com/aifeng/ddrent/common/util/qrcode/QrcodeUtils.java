/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.common.util.qrcode 
 * @author imart·deng
 * @date 创建时间：2018年9月18日 下午2:53:12
 * @version 1.0
 */
package com.aifeng.ddrent.common.util.qrcode;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Hashtable;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;

import com.aifeng.ddrent.common.util.io.ImageUtils;
import com.google.zxing.EncodeHintType;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;

/** 
 * @ClassName: QrcodeUtil 
 * @Description: 二维码工具
 * @author: imart·deng
 * @date: 2018年9月18日 下午2:53:12  
 */
public class QrcodeUtils {
//	private static int IMAGE_HALFWIDTH = 50;
//
//    /**
//     * 生成二维码，默认大小为500*500
//     *
//     * @param text 需要生成二维码的文字、网址等
//     * @return bitmap
//     */
//    public static ByteArrayOutputStream createQRCode(String text) {
//        return createQRCode(text, 500);
//    }
//
//    /**
//     * 生成二维码
//     *
//     * @param text 文字或网址
//     * @param size 生成二维码的大小
//     * @return bitmap
//     */
//    public static ByteArrayOutputStream  createQRCode(String text, int size) {
//    	
//    	
//    	
//    	return QRCode.from(text).withSize(size, size).stream();
//    }
//
//    /**
//     * bitmap的颜色代替黑色的二维码
//     *
//     * @param text
//     * @param size
//     * @param mBitmap
//     * @return
//     */
//    public static Bitmap createQRCodeWithLogo2(String text, int size, Bitmap mBitmap) {
//        try {
//            IMAGE_HALFWIDTH = size / 10;
//            Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
//            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
//
//            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
//            BitMatrix bitMatrix = new QRCodeWriter().encode(text,
//                    BarcodeFormat.QR_CODE, size, size, hints);
//            
//
//            //将logo图片按martix设置的信息缩放
//            mBitmap = Bitmap.createScaledBitmap(mBitmap, size, size, false);
//
//            int[] pixels = new int[size * size];
//            int color = 0xffffffff;
//            for (int y = 0; y < size; y++) {
//                for (int x = 0; x < size; x++) {
//                    if (bitMatrix.get(x, y)) {
//                        pixels[y * size + x] = mBitmap.getPixel(x, y);
//                    } else {
//                        pixels[y * size + x] = color;
//                    }
//
//                }
//            }
//            Bitmap bitmap = Bitmap.createBitmap(size, size,
//                    Bitmap.Config.ARGB_8888);
//            bitmap.setPixels(pixels, 0, size, 0, 0, size, size);
//            return bitmap;
//        } catch (WriterException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    /**
//     * bitmap作为底色
//     *
//     * @param text
//     * @param size
//     * @param mBitmap
//     * @return
//     */
//    public static Bitmap createQRCodeWithLogo3(String text, int size, Bitmap mBitmap) {
//        try {
//            IMAGE_HALFWIDTH = size / 10;
//            Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
//            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
//
//            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
//            BitMatrix bitMatrix = new QRCodeWriter().encode(text,
//                    BarcodeFormat.QR_CODE, size, size, hints);
//
//            //将logo图片按martix设置的信息缩放
//            mBitmap = Bitmap.createScaledBitmap(mBitmap, size, size, false);
//
//            int[] pixels = new int[size * size];
//            int color = 0xfff92736;
//            for (int y = 0; y < size; y++) {
//                for (int x = 0; x < size; x++) {
//                    if (bitMatrix.get(x, y)) {
//                        pixels[y * size + x] = color;
//                    } else {
//                        pixels[y * size + x] = mBitmap.getPixel(x, y) & 0x66ffffff;
//                    }
//                }
//            }
//            Bitmap bitmap = Bitmap.createBitmap(size, size,
//                    Bitmap.Config.ARGB_8888);
//            bitmap.setPixels(pixels, 0, size, 0, 0, size, size);
//            return bitmap;
//        } catch (WriterException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    /**
//     * 比方法2的颜色黑一些
//     *
//     * @param text
//     * @param size
//     * @param mBitmap
//     * @return
//     */
//    public static Bitmap createQRCodeWithLogo4(String text, int size, Bitmap mBitmap) {
//        try {
//            IMAGE_HALFWIDTH = size / 10;
//            Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
//            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
//
//            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
//            BitMatrix bitMatrix = new QRCodeWriter().encode(text,
//                    BarcodeFormat.QR_CODE, size, size, hints);
//
//            //将logo图片按martix设置的信息缩放
//            mBitmap = Bitmap.createScaledBitmap(mBitmap, size, size, false);
//
//            int[] pixels = new int[size * size];
//            boolean flag = true;
//            for (int y = 0; y < size; y++) {
//                for (int x = 0; x < size; x++) {
//                    if (bitMatrix.get(x, y)) {
//                        if (flag) {
//                            flag = false;
//                            pixels[y * size + x] = 0xff000000;
//                        } else {
//                            pixels[y * size + x] = mBitmap.getPixel(x, y);
//                            flag = true;
//                        }
//                    } else {
//                        pixels[y * size + x] = 0xffffffff;
//                    }
//                }
//            }
//            Bitmap bitmap = Bitmap.createBitmap(size, size,
//                    Bitmap.Config.ARGB_8888);
//            bitmap.setPixels(pixels, 0, size, 0, 0, size, size);
//            return bitmap;
//        } catch (WriterException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    /**
//     * 生成带logo的二维码
//     * @param text
//     * @param size
//     * @param mBitmap
//     * @return
//     */
//    public static Bitmap createQRCodeWithLogo5(String text, int size, Bitmap mBitmap) {
//        try {
//            IMAGE_HALFWIDTH = size / 10;
//            Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
//            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
//
//            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
//            BitMatrix bitMatrix = new QRCodeWriter().encode(text,
//                    BarcodeFormat.QR_CODE, size, size, hints);
//
//            //将logo图片按martix设置的信息缩放
//            mBitmap = Bitmap.createScaledBitmap(mBitmap, size, size, false);
//
//            int width = bitMatrix.getWidth();//矩阵高度
//            int height = bitMatrix.getHeight();//矩阵宽度
//            int halfW = width / 2;
//            int halfH = height / 2;
//
//            Matrix m = new Matrix();
//            float sx = (float) 2 * IMAGE_HALFWIDTH / mBitmap.getWidth();
//            float sy = (float) 2 * IMAGE_HALFWIDTH
//                    / mBitmap.getHeight();
//            m.setScale(sx, sy);
//            //设置缩放信息
//            //将logo图片按martix设置的信息缩放
//            mBitmap = Bitmap.createBitmap(mBitmap, 0, 0,
//                    mBitmap.getWidth(), mBitmap.getHeight(), m, false);
//
//            int[] pixels = new int[size * size];
//            for (int y = 0; y < size; y++) {
//                for (int x = 0; x < size; x++) {
//                    if (x > halfW - IMAGE_HALFWIDTH && x < halfW + IMAGE_HALFWIDTH
//                            && y > halfH - IMAGE_HALFWIDTH
//                            && y < halfH + IMAGE_HALFWIDTH) {
//                        //该位置用于存放图片信息
//                        //记录图片每个像素信息
//                        pixels[y * width + x] = mBitmap.getPixel(x - halfW
//                                + IMAGE_HALFWIDTH, y - halfH + IMAGE_HALFWIDTH);
//                    } else {
//                        if (bitMatrix.get(x, y)) {
//                            pixels[y * size + x] = 0xff37b19e;
//                        } else {
//                            pixels[y * size + x] = 0xffffffff;
//                        }
//                    }
//                }
//            }
//            Bitmap bitmap = Bitmap.createBitmap(size, size,
//                    Bitmap.Config.ARGB_8888);
//            bitmap.setPixels(pixels, 0, size, 0, 0, size, size);
//            return bitmap;
//        } catch (WriterException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    /**
//     * 修改三个顶角颜色的，带logo的二维码
//     * @param text
//     * @param size
//     * @param mBitmap
//     * @return
//     */
//    public static Bitmap createQRCodeWithLogo6(String text, int size, Bitmap mBitmap) {
//        try {
//            IMAGE_HALFWIDTH = size / 10;
//            Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
//            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
//            /*
//             * 设置容错级别，默认为ErrorCorrectionLevel.L
//             * 因为中间加入logo所以建议你把容错级别调至H,否则可能会出现识别不了
//             */
//            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
//            BitMatrix bitMatrix = new QRCodeWriter().encode(text,
//                    BarcodeFormat.QR_CODE, size, size, hints);
//
//            //将logo图片按martix设置的信息缩放
//            mBitmap = Bitmap.createScaledBitmap(mBitmap, size, size, false);
//
//            int width = bitMatrix.getWidth();//矩阵高度
//            int height = bitMatrix.getHeight();//矩阵宽度
//            int halfW = width / 2;
//            int halfH = height / 2;
//
//            Matrix m = new Matrix();
//            float sx = (float) 2 * IMAGE_HALFWIDTH / mBitmap.getWidth();
//            float sy = (float) 2 * IMAGE_HALFWIDTH
//                    / mBitmap.getHeight();
//            m.setScale(sx, sy);
//            //设置缩放信息
//            //将logo图片按martix设置的信息缩放
//            mBitmap = Bitmap.createBitmap(mBitmap, 0, 0,
//                    mBitmap.getWidth(), mBitmap.getHeight(), m, false);
//
//            int[] pixels = new int[size * size];
//            for (int y = 0; y < size; y++) {
//                for (int x = 0; x < size; x++) {
//                  if (x > halfW - IMAGE_HALFWIDTH && x < halfW + IMAGE_HALFWIDTH
//                            && y > halfH - IMAGE_HALFWIDTH
//                            && y < halfH + IMAGE_HALFWIDTH) {
//                        //该位置用于存放图片信息
//                        //记录图片每个像素信息
//                        pixels[y * width + x] = mBitmap.getPixel(x - halfW
//                                + IMAGE_HALFWIDTH, y - halfH + IMAGE_HALFWIDTH);
//                    } else {
//                        if (bitMatrix.get(x, y)) {
//                            pixels[y * size + x] = 0xff111111;
//                            if(x<115&&(y<115||y>=size-115)||(y<115&&x>=size-115)){
//                                pixels[y * size + x] = 0xfff92736;
//                            }
//                        } else {
//                            pixels[y * size + x] = 0xffffffff;
//                        }
//                    }
//                }
//            }
//            Bitmap bitmap = Bitmap.createBitmap(size, size,
//                    Bitmap.Config.ARGB_8888);
//            bitmap.setPixels(pixels, 0, size, 0, 0, size, size);
//            return bitmap;
//        } catch (WriterException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
	
	/**
	 * 获取二维码
	 * @param text 	内容
	 * @param size	大小
	 * @param out	输出流
	 */
	public static void getQRCode(String text, int size, OutputStream out) {
		QRCode
			.from(text)
			.withSize(size, size)
			.withCharset("UTF-8")
			.to(ImageType.PNG)
			.writeTo(out);
	}
	
	private static final String FONT_NAME = "宋体";
	private static final int FONT_STYLE = Font.ITALIC;
	private static final int FONT_SIZE = 16;//文字大小
	private static final float alpha = 0.8f;//透明度
	
	/**
	 * 图片添加文字水印
	 * 
	 * @param inputStream
	 * @param watermarkText
	 * @param outputStream
	 */
	public static OutputStream qrcodeWithMessage(String text, String watermarkText, OutputStream outputStream){
		
		try {
			
			/** 创建二维码二进制流 */
			ByteArrayOutputStream bos = QRCode
					.from(text)
					.withSize(300, 300)
					.withCharset("UTF-8")
					.to(ImageType.PNG)
					.stream();
			
			
			/** 把二进制流程通过管道移到 */
			PipedInputStream pis = new PipedInputStream();
			PipedOutputStream pos;
			pos = new PipedOutputStream(pis);
			bos.writeTo(pos);
			
			BufferedImage bufferedImage = ImageIO.read(pis);
			int imageHeight = bufferedImage.getHeight();
			int imageWidth = bufferedImage.getWidth();
			
			//创建图片绘图工具
			Graphics2D graphics = bufferedImage.createGraphics();
			graphics.drawImage(bufferedImage, imageWidth, imageHeight, null);
			
			//水印文字风格
			graphics.setFont(new Font(FONT_NAME, FONT_STYLE, FONT_SIZE));
			graphics.setColor(Color.BLUE);
			
			//水印文本宽高
			int waterMarkTextHeight = FONT_SIZE;
			int waterMarkTextWidth = FONT_SIZE * (getTextLength(watermarkText) + 2);
			
			//水印展示方式
			graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP , alpha));


			graphics.drawString(watermarkText, imageWidth-waterMarkTextWidth, imageHeight-waterMarkTextHeight);
			graphics.dispose();
			
			ImageIO.write(bufferedImage, "png", outputStream);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return outputStream;
		
	}
	
	public static void zipparck(File[] srcfile,File zipfile) {
		byte[] buf=new byte[1024];
		ZipOutputStream out= null;
		try {
			//ZipOutputStream类：完成文件或文件夹的压缩
			out=new ZipOutputStream(new FileOutputStream(zipfile));
			for(int i=0;i<srcfile.length;i++){
				FileInputStream in=new FileInputStream(srcfile[i]);
				out.putNextEntry(new ZipEntry(srcfile[i].getName()));
				int len;
				while((len=in.read(buf))>0){
					out.write(buf,0,len);
					out.flush();
				}
				out.closeEntry();
				in.close();
				System.out.println("压缩完成");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(null != out) {
				try {
					out.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		
		File zip = new File("D:/test/qrcode/tag.zip");
//		
//		File file = new File("D:/test/qrcode/saf");
//		
//		File file1 = new File("D:/test/qrcode/test1.png");
//		File file2 = new File("D:/test/qrcode/test2.png");
//		File file3 = new File("D:/test/qrcode/test3.png");
//		File file4 = new File("D:/test/qrcode/test4.png");
//		File file5 = new File("D:/test/qrcode/test5.png");
//		File[] files = new File[] {file1,file2,file3,file4,file5};
//		zipparck(files, zip);
		
		ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zip));
		
//		for (File fie : files) {
//			ZipUtils.doZip(fie, zout, "");
//		}
		
		
		/** 把二进制流程通过管道移到 */
		PipedInputStream pis = new PipedInputStream();
		PipedOutputStream pos;
		try {
			pos = new PipedOutputStream(pis);
			QRCode
			.from("base test")
			.to(ImageType.PNG)
			.withSize(300, 300)
			.writeTo(pos);
			
			zout.putNextEntry(new ZipEntry("base.png"));
			byte[] buff = new byte[1024];
			int len = 0;
			
			File f = new File("D:/test/qrcode/base3.png");
			FileOutputStream fout = new FileOutputStream(f);
			
			
			
			while((pis.available()==0) && (len = pis.read(buff)) > 0) {
				zout.write(buff,0,len);
//				fout.write(buff,0,len);
				zout.flush();
			}
			System.out.println("gg");
			fout.flush();
			fout.close();
			zout.flush();
			zout.closeEntry();
			zout.close();
			pis.close();
			pos.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			System.out.println("gg");
		}
		
//		zout.closeEntry();
//		zout.finish();
//		zout.close();
		
//		try {
//			ZipUtils.doCompress(file, zip);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		try {
//			OutputStream os = new FileOutputStream(new File("D:/test/qrcode/test.png"));
//			qrcodeWithMessage("你好，中国！", "dengxf写的", os);
//			
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
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
	
	public static void getQRCodeWithMessages(String text, String message, String name) {
		/** 创建二维码二进制流 */
		ByteArrayOutputStream bos = QRCode
				.from(text)
				.withSize(300, 300)
				.withCharset("UTF-8")
				.to(ImageType.PNG)
				.stream();
		
		
		/** 把二进制流程通过管道移到 */
		PipedInputStream pis = new PipedInputStream();
		PipedOutputStream pos;
		
		try {
			pos = new PipedOutputStream(pis);
			bos.writeTo(pos);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
	public static void main3(String[] args) {
		ByteArrayOutputStream bos = QRCode
				.from("bptfm1wOH7HjxM^FEzEjnpwpk*cE!krs")
				.withSize(300, 300)
				.to(ImageType.PNG)
				.stream();
		
		PipedInputStream pis = new PipedInputStream();
		
		PipedOutputStream pos;
		try {
			pos = new PipedOutputStream(pis);
			bos.writeTo(pos);
			FileOutputStream fos = new FileOutputStream(new File("d:/test/qrcode/wt.png"));
			
			ByteArrayOutputStream bos2 = new ByteArrayOutputStream();
			
			ImageUtils.imageAddWatermark(pis, "dengxf", bos2);
			fos.flush();
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		ByteArrayInputStream bis = n
		
	}
	
	
//	public static void main2(String[] args) {
//		
////		QRCode.from("").
//		
//		try {
//			File file = 
//					QRCode
//					.from("http://www.apache.org/licenses/LICENSE-2.0.html")
//					.withCharset("UTF-8").file();
//			
//			
//			file = QRCode
//					.from("bptfm1wOH7HjxM^FEzEjnpwpk*cE!krs")
//					.withSize(600, 600)
//					.file();
//			
//			
//			VCard dengxf = new VCard("陈少云")
//                    .setEmail("chensy@yintong.com.cn")
//                    .setAddress("浙江省杭州市滨江区")
//                    .setTitle("可可型男")
//                    .setCompany("连连银通")
//                    .setPhoneNumber("18814868497")
//                    .setWebsite("");
////			file = QRCode.from(dengxf).withCharset("UTF-8").withSize(300, 300).file();
//			
//			FileInputStream fis = new FileInputStream(file);
//			byte[] buffer = new byte[1024] ;
//			FileOutputStream fos = null;
//			try {
//				fos = new FileOutputStream(new File("d:/test/qrcode/csy.png"));
//				while(fis.read(buffer) > 0 ) {
//					fos.write(buffer);
//				}
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}finally {
//				if(null != fos) {
//					try {
//						fos.flush();
//						fos.close();
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//			
//			
//			
//		} catch (FileNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
////		try {
////			QRCode.from("IOU").to(ImageType.JPG).writeTo(new FileOutputStream("d:/test/qrcode/test.jpg"));
////		} catch (FileNotFoundException e) {
////			e.printStackTrace();
////		}
//		
//		
//	}
	
	public void createQrCode(String content, int width, int height, String imagSavePath, File logoPath, String format, int fontColor, int bgColor, int logoCent) {
		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		
		hints.put(EncodeHintType.MARGIN, 1);
		
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		
		/** 点 矩阵 */
//		BitMatrix matrix = null;
//		
//		try {
//			matrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
//		} catch (WriterException e) {
//			e.printStackTrace();
//		}
	}
}
