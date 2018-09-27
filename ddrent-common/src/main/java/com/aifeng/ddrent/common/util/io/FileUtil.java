//package com.aifeng.ddrent.common.util.io;
//
//import java.awt.AlphaComposite;
//import java.awt.Color;
//import java.awt.Font;
//import java.awt.Graphics2D;
//import java.awt.RenderingHints;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//
//import javax.imageio.ImageIO;
//
//import com.itextpdf.text.BadElementException;
//import com.itextpdf.text.BaseColor;
//import com.itextpdf.text.Element;
//import com.itextpdf.text.Image;
//import com.itextpdf.text.Rectangle;
//import com.itextpdf.text.pdf.BaseFont;
//import com.itextpdf.text.pdf.PdfContentByte;
//import com.itextpdf.text.pdf.PdfGState;
//import com.itextpdf.text.pdf.PdfReader;
//import com.itextpdf.text.pdf.PdfStamper;
//import com.aifeng.oa.model.dto.dfs.Authorization;
//
//
//public class FileUtil {
//	/**
//	 * 文件服务数据库认证对象
//	 */
//	public static final Authorization authorization ;
//	
//	static {
//		authorization = new Authorization(ResourcesUtil.getMongoAccount(), ResourcesUtil.getMongoPassword());
//	}
//	
//	public static final String FILE_PATH = "~/tempFile/";
//	
//	public static final String INSTITUTION_ZIP_FILE_PATH = "/institution/zip/";
//	
//	/**
//	 * 文件路径前缀
//	 */
//	public static final String INSTITUTION_FILE_PATH = "/aifeng/institution/";
//	
//	/**
//	 * 请假条图片文件路径
//	 */
//	public static final String LEAVE_ASK_FILE_PATH = "/aifeng/leaveAsk/";
//	
//	/**
//	 * 头像图片存放路径
//	 */
//	public static final String USER_HEAD_PATH = "/aifeng/userImage/";
//	
//	/**
//	 * 公告图片存放路径
//	 */
//	public static final String SYS_NOTICE_IMG_PATH = "/aifeng/userImage/";
//	
//	/**
//	 * 制度附件存储路径
//	 */
//	public static final String INSTITUTION_ATTACHMENT_PATH = "/aifeng/institutionAttachment/";
//	
//	
//	/**
//	 * 制度附件存储路径
//	 */
//	public static final String MEETING_ATTACHMENT_PATH = "/aifeng/meetingAttachment/";
//	/**
//	 * 差旅申请图片存放路径
//	 */
//	public static final String BUSINESS_TRIP_APPLY_PATH = "/aifeng/businessTripApply/";
//	/**
//	 * 合规文件存放路径
//	 */
//	public static final String COMPLIANCE_FILE_PATH = "/aifeng/compliance/";
//	
//	/**
//	 * 简历文件路径
//	 */
//	public static final String RESUME_FILE_PATH = "/aifeng/resume/";
//	
//	
//	/**
//	 * 
//	 * pdf文档添加水印
//	 * @param inputStream
//	 * @param watermarkContent
//	 * @param outputStream
//	 */
//	public static void pdfAddWatermark(InputStream inputStream, String watermarkContent, OutputStream outputStream) {
//		try {
//			
//			//待添加水印文件
//			PdfReader reader = new PdfReader(inputStream);
//			
//			//目标文件
//			PdfStamper stamper = new PdfStamper(reader, outputStream);
//			
//			int total = reader.getNumberOfPages() + 1;  
//			PdfContentByte content;  
//			// 设置字体  
//			BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",  
//			        BaseFont.NOT_EMBEDDED);  
//			//设置水印内容透明度
//			PdfGState pdfGs= new PdfGState();
//			pdfGs.setFillOpacity(0.4f);
//			
//			// 循环对每页插入水印  
//			for (int i = 1; i < total; i++) {  
//			    //水印内容
//				content = stamper.getOverContent(i);
//			    //content = stamper.getUnderContent(i);  
//			    // 开始  
//			    content.beginText();  
//			    // 设置颜色  
//			    content.setColorFill(BaseColor.GRAY);
//			    content.setGState(pdfGs);
//			    // 设置字体及字号  
//			    content.setFontAndSize(base, 14);  
//			    // 开始写入水印  
//			    Rectangle pageRect = stamper.getReader().getPageSizeWithRotation(i);
//			    float xSpace = pageRect.getWidth()/3;
//			    float ySpace = pageRect.getHeight()/5;
//			    for (int m = 0; m <= 3; m++) {
//			    	for(int n=0; n<=5; n++){
//			    		// 水印文字成35度角倾斜
//				        content.showTextAligned(Element.ALIGN_CENTER, watermarkContent, m*xSpace, n*ySpace, 35);
//			    	}
//			    }  
//			    content.endText();  
//			
//			}  
//			stamper.close();  
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	/**
//	 * pdf文档添加图片水印
//	 * @param inputStream
//	 * @param watermarkImage
//	 * @param outputStream
//	 */
//	public static void pdfAddImageWatermark(InputStream inputStream, Image watermarkImage, OutputStream outputStream) {
//		try {
//			
//			//待添加水印文件
//			PdfReader reader = new PdfReader(inputStream);
//			
//			//目标文件
//			PdfStamper stamper = new PdfStamper(reader, outputStream);
//			
//			int total = reader.getNumberOfPages() + 1;  
//			PdfContentByte content;  
//			
//			//设置水印内容透明度
//			PdfGState pdfGs= new PdfGState();
//			pdfGs.setFillOpacity(0.1f);
//			
//			// 循环对每页插入水印  
//			for (int i = 1; i < total; i++) {  
//			    //水印内容
//			    content = stamper.getUnderContent(i);  
//			    // 开始  
//			    content.beginText();  
//			    // 开始写入水印  
//			    Rectangle pageRect = stamper.getReader().getPageSizeWithRotation(i);
//			    float xSpace = pageRect.getWidth()/3;
//			    float ySpace = pageRect.getHeight()/5;
//			    //水印图片旋转角度
//				watermarkImage.setRotationDegrees(-35);
//				//自定义水印图片缩放比例
//		        watermarkImage.scalePercent(25);
//			    for (int m = 0; m <= 3; m++) {
//			    	for(int n=0; n<=5; n++){
//			    		//水印图片坐标
//			    		watermarkImage.setAbsolutePosition((m-1)*xSpace, (n-1)*ySpace);
//			    		content.addImage(watermarkImage);
//			    	}
//			    }  
//			    content.endText();  
//			}  
//			stamper.close();  
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	
//	private static final String FONT_NAME = "微软雅黑";
//	private static final int FONT_STYLE = Font.ITALIC;
//	private static final int FONT_SIZE = 40;//文字大小
//	private static final float alpha = 0.5f;//透明度
//	
//	/**
//	 * 图片添加文字水印
//	 * 
//	 * @param inputStream
//	 * @param watermarkText
//	 * @param outputStream
//	 */
//	public static void imageAddWatermark(InputStream inputStream, String watermarkText, OutputStream outputStream){
//		try {
//			BufferedImage bufferedImage = ImageIO.read(inputStream);
//			int imageHeight = bufferedImage.getHeight();
//			int imageWidth = bufferedImage.getWidth();
//			
//			//创建图片绘图工具
//			Graphics2D graphics = bufferedImage.createGraphics();
//			graphics.drawImage(bufferedImage, imageWidth, imageHeight, null);
//			
//			//水印文字风格
//			graphics.setFont(new Font(FONT_NAME, FONT_STYLE, FONT_SIZE));
//			graphics.setColor(Color.GRAY);
//			
//			//水印文本宽高
//			int waterMarkTextHeight = FONT_SIZE;
//			int waterMarkTextWidth = FONT_SIZE * getTextLength(watermarkText);
//			
//			//水印展示方式
//			graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
//			//水印文字倾斜角度
//			graphics.rotate(Math.toRadians(30), bufferedImage.getWidth()/2, bufferedImage.getHeight()/2);
//			 
//			//写入水印
//			int x = -imageWidth/2;
//			int y = -imageHeight/2;
//			while(x < imageWidth*1.5){
//				y = -imageHeight/2;
//				while(y < imageHeight*1.5){
//					graphics.drawString(watermarkText, x, y);
//					y+=waterMarkTextHeight+200;
//				}
//				x+=waterMarkTextWidth+100;
//			}
//			graphics.dispose();
//			
//			ImageIO.write(bufferedImage, "png", outputStream);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	//计算水印文本长度
//	//1、中文长度即文本长度 2、英文长度为文本长度二分之一
//	public static int getTextLength(String text){
//		 //水印文字长度
//		 int length = text.length();
//		 
//		 for (int i = 0; i < text.length(); i++) {
//			 String s =String.valueOf(text.charAt(i));
//			 if (s.getBytes().length>1) {
//				 length++;
//			 }
//	 	 }
//		 length = length%2==0?length/2:length/2+1;
//		 return length;
//	}
//	
//	
//	
//	
//	 /**
//	  * 图片添加图片水印
//	  * 
//	  * @param inputStream
//	  * @param watermarkImage
//	  * @param outputStream
//	  */
//	 public static void imageAddImageWatermark(InputStream inputStream, java.awt.Image watermarkImage, OutputStream outputStream){     
//	 	try {
//			BufferedImage bufferedImage = ImageIO.read(inputStream);
//			int imageHeight = bufferedImage.getHeight();
//			int imageWidth = bufferedImage.getWidth();
//			
//			//创建画笔
//			Graphics2D graphics = bufferedImage.createGraphics();
//			// 设置对线段的锯齿状边缘处理     
//			graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION,     
//			         RenderingHints.VALUE_INTERPOLATION_BILINEAR); 
//			graphics.drawImage(bufferedImage, imageWidth, imageHeight, null);
//			
//			BufferedImage bufferedWatermarkImage = (BufferedImage) watermarkImage;
//			int watermarkImageWidth = bufferedWatermarkImage.getWidth();
//			int watermarkImageHeight = bufferedWatermarkImage.getHeight();
//			
//			//水印图片展示方式
//			graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,     
//			         alpha)); 
//			//添加水印图片
//			graphics.drawImage(bufferedWatermarkImage, imageWidth-watermarkImageWidth, imageHeight-watermarkImageHeight, null);
//			graphics.dispose();    
//			
//			ImageIO.write(bufferedImage, "png", outputStream);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	 } 
//	 
//	  /**
//	   * 图片添加多图水印
//	   *    
//	   * @param inputStream
//	   * @param watermarkImage
//	   * @param outputStream
//	   */
//	  public static void imageAddMultiImageWatermark(InputStream inputStream, java.awt.Image watermarkImage, OutputStream outputStream){     
//	  	try {
//			BufferedImage bufferedImage = ImageIO.read(inputStream);
//			int imageHeight = bufferedImage.getHeight();
//			int imageWidth = bufferedImage.getWidth();
//			
//			// 创建画笔
//			Graphics2D graphics = bufferedImage.createGraphics();
//			// 设置对线段的锯齿状边缘处理     
//			graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION,     
//			          RenderingHints.VALUE_INTERPOLATION_BILINEAR); 
//			graphics.drawImage(bufferedImage, imageWidth, imageHeight, null);
//			//设置水印展现方式
//			graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,     
//			          alpha)); 
//			// 设置水印倾斜度
//			graphics.rotate(Math.toRadians(30), imageWidth/2, imageHeight/2);
//			
//			//水印图片
//			BufferedImage bufferedWatermarkImage = (BufferedImage) watermarkImage;
//			int watermarkImageWidth = bufferedWatermarkImage.getWidth();
//			int watermarkImageHeight = bufferedWatermarkImage.getHeight();
//			
//			int x = -imageWidth/2;
//			int y = -imageHeight/2;
//			 
//			while(x < imageWidth*1.5){
//				y = -imageHeight/2;
//				while(y < imageHeight*1.5){
//					graphics.drawImage(watermarkImage, x, y, null);
//					y+=watermarkImageHeight+200;
//				}
//				x+=watermarkImageWidth+100;
//			}
//			graphics.dispose(); 
//			
//			ImageIO.write(bufferedImage, "png", outputStream);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    }  
//	 
//	 
//	public static void main(String[] args){
//		//pdf添加文字水印
//		File pdfFile = new File("E:/watermark/水印示例原文档.pdf");
//		File textWatermarkPdfFile = new File("E:/watermark/textWatermark.pdf");
//		
//		try {
//			InputStream inputStreamPdf = new FileInputStream(pdfFile);
//			OutputStream outputStreamPdf = new FileOutputStream(textWatermarkPdfFile);
//			
//			pdfAddWatermark(inputStreamPdf, "添加水印", outputStreamPdf);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		//pdf添加图片水印
//		File imageWatermarkPdfFile = new File("E:/watermark/iamgeWatermark.pdf");
//		
//		try {
//			//水印图片
//			Image imageWatermark = Image.getInstance("E:/watermark/logo.png");
//			InputStream inputStreamPdf2 = new FileInputStream(pdfFile);
//			OutputStream outputStreamPdf2 = new FileOutputStream(imageWatermarkPdfFile);
//			pdfAddImageWatermark(inputStreamPdf2, imageWatermark, outputStreamPdf2);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (BadElementException | IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
//		//图片添加文字水印
//		File imageFile = new File("E:/watermark/aifeng.jpg");
//		File textWatermarkImageFile = new File("E:/watermark/textWatermarkImageFile.png");
//		
//		try {
//			InputStream inputStreamPdf3 = new FileInputStream(imageFile);
//			OutputStream outputStreamPdf3 = new FileOutputStream(textWatermarkImageFile);
//			imageAddWatermark(inputStreamPdf3, "添加水印示例", outputStreamPdf3);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		//图片添加单图片水印
//		File imageWatermarkImageFile = new File("E:/watermark/imageWatermarkImageFile.png");
//		File watermarkFile = new File("E:/watermark/logo.png");
//		
//		try {
//			InputStream inputStreamPdf4 = new FileInputStream(imageFile);
//			OutputStream outputStreamPdf4 = new FileOutputStream(imageWatermarkImageFile);
//			java.awt.Image watermarkImage = ImageIO.read(watermarkFile);
//			
//			imageAddImageWatermark(inputStreamPdf4, watermarkImage, outputStreamPdf4);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		//图片添加多图水印
//		File multiImageWatermarkImageFile = new File("E:/watermark/multiImageWatermarkImageFile.png");
//		
//		try {
//			InputStream inputStreamPdf5 = new FileInputStream(imageFile);
//			OutputStream outputStreamPdf5 = new FileOutputStream(multiImageWatermarkImageFile);
//			java.awt.Image watermarkImage = ImageIO.read(watermarkFile);
//			
//			imageAddMultiImageWatermark(inputStreamPdf5, watermarkImage, outputStreamPdf5);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//}
