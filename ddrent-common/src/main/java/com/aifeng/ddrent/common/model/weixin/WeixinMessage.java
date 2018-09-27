package com.aifeng.ddrent.common.model.weixin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/** 
 * 微信消息，包括text、img、voice、video、file、textCard、news、mpnews类型
 * @author: imart·deng
* @date 创建时间：2017年9月25日 上午11:44:04
* @version 1.0
* @since
* 
*/
public class WeixinMessage implements Serializable {
	private static final long serialVersionUID = 275327983794648919L;
	
	//消息为群聊消息的时候使用
	private String chatid;

	/** 成员ID列表（消息接收者，多个接收者用‘|’分隔，最多支持1000个）。特殊情况：指定为@all */
	private String touser;
	
	/** 部门ID列表，多个接收者用‘|’分隔，最多支持100个。当touser为@all时忽略本参数 */
	private String toparty;
	
	/** 标签ID列表，多个接收者用‘|’分隔，最多支持100个。当touser为@all时忽略本参数*/
	private String totag;
	
	/** 消息类型，此时固定为：image，可选：text、image、voice*/
	private String msgtype = "text";
	
	/** 企业应用的id，整型。可在应用的设置页面查看 ,rpc接口默认为当前应用id*/
	private String agentid;
	
	/** 表示是否是保密消息，0表示否，1表示是，默认0*/
	private Integer safe;
	
	/** 文本消息 */
	private Text text;
	
	/** 图片消息*/
	private Image image;
	
	/** 语音消息*/
	private Voice voice;
	
	//视频消息
	private Video video;
	
	//文件消息
	private File file;
	
	//文本卡消息
	private Textcard textcard;
	
	//图文消息
	private Map<String, List<Articles>> news;
	
	//图文消息
	private Map<String, List<NpArticles>> mpnews;
	
	//文本消息
	public static final String MSGTYPE_TEXT = "text";
	
	//图片消息
	public static final String MSGTYPE_IMAGE = "image";
	
	//语音消息
	public static final String MSGTYPE_VOICE = "voice";
	
	//视频消息
	public static final String MSGTYPE_VIDEO = "video";
	
	//文件消息
	public static final String MSGTYPE_FILE = "file";
	
	//文本卡消息
	public static final String MSGTYPE_TEXTCARD = "textcard";
	
	//图文消息
	public static final String MSGTYPE_NEWS = "news";
	public static final String MSGTYPE_NEWS_ARTICLES = "articles";
	
	//图文消息
	public static final String MSGTYPE_MPNEWS = "mpnews";
	public static final String MSGTYPE_NPNEWS_ARTICLES = "articles";
	
	public static class Text implements Serializable{
		private static final long serialVersionUID = -2316995036541415157L;
		//消息内容，最长不超过2048个字节
		String content;

		public Text(String content) {
			super();
			this.content = content;
		}
	}
	
	public static class Image implements Serializable{
		private static final long serialVersionUID = 2414891144871944783L;
		//媒体文件id，可以调用上传临时素材接口获取
		String media_id;

		public Image(String media_id) {
			super();
			this.media_id = media_id;
		}

	}
	
	public static class Voice implements Serializable{
		private static final long serialVersionUID = -5141078627041645895L;
		//媒体文件id，可以调用上传临时素材接口获取
		String media_id;

		public Voice(String media_id) {
			super();
			this.media_id = media_id;
		}
		
	}
	
	public static class Video implements Serializable{
		private static final long serialVersionUID = 736765607061760075L;
		//视频媒体文件id，可以调用上传临时素材接口获取
		String media_id;
		//视频消息的标题，不超过128个字节，超过会自动截断
		String title;
		//视频消息的描述，不超过512个字节，超过会自动截断
		String description;
		public Video(String media_id, String title, String description) {
			super();
			this.media_id = media_id;
			this.title = title;
			this.description = description;
		}
	}
	
	public static class File implements Serializable{
		private static final long serialVersionUID = -990705179946166641L;
		//文件id，可以调用上传临时素材接口获取
		String media_id;

		public File(String media_id) {
			super();
			this.media_id = media_id;
		}
	}
	
	public static class Textcard implements Serializable{
		private static final long serialVersionUID = -6276898084883354755L;
		//标题，不超过128个字节，超过会自动截断
		String title;
		//描述，不超过512个字节，超过会自动截断
		String description;
		//点击后跳转的链接。
		String url;
		//按钮文字。 默认为“详情”， 不超过4个文字，超过自动截断。
		String btntxt;
		public Textcard(String title, String description, String url, String btntxt) {
			super();
			this.title = title;
			this.description = description;
			this.url = url;
			this.btntxt = btntxt;
		}
	}
	
	public static class Articles implements Serializable{
		private static final long serialVersionUID = 7600322405776150127L;
		//标题，不超过128个字节，超过会自动截断
		String title;
		//描述，不超过512个字节，超过会自动截断
		String description;
		//点击后跳转的链接。
		String url;
		//按钮文字，仅在图文数为1条时才生效。 默认为“阅读全文”， 不超过4个文字，超过自动截断。该设置只在企业微信上生效，微工作台（原企业号）上不生效。
		String btntxt;
		//图文消息的图片链接，支持JPG、PNG格式，较好的效果为大图640320，小图8080。
		String picurl;
		public Articles(String title, String description, String url, String btntxt, String picurl) {
			super();
			this.title = title;
			this.description = description;
			this.url = url;
			this.btntxt = btntxt;
			this.picurl = picurl;
		}
	}
	
	public static class NpArticles implements Serializable{
		private static final long serialVersionUID = -7815988635905273101L;
		//标题，不超过128个字节，超过会自动截断
		String title;
		//图文消息缩略图的media_id, 可以通过素材管理接口获得。此处thumb_media_id即上传接口返回的media_id
		String thumb_media_id;
		//图文消息的作者，不超过64个字节
		String author;
		//图文消息点击“阅读原文”之后的页面链接
		String content_source_url;
		//图文消息的内容，支持html标签，不超过666 K个字节
		String content;
		//图文消息的描述，不超过512个字节，超过会自动截断
		String digest;
		public NpArticles(String title, String thumb_media_id, String author, String content_source_url, String content,
				String digest) {
			super();
			this.title = title;
			this.thumb_media_id = thumb_media_id;
			this.author = author;
			this.content_source_url = content_source_url;
			this.content = content;
			this.digest = digest;
		}
	}
	
	/**
	 * 发送微信消息通知，默认为发送给全员，发送个指定个人、部门或者标签组需要手动设置 touser、toparty或者totag属性
	 * @param agentid	应用id
	 * @param content	文本消息内容
	 */
	public WeixinMessage(String agentid, String content) {
		super();
//		this.touser = "@all";
		this.agentid = agentid;
		this.msgtype = MSGTYPE_TEXT;
		this.text = new Text(content);
	}
	
	/**
	 * 发送图片消息
	 * @param agentid
	 * @param image
	 */
	public WeixinMessage(String agentid, Image image) {
		super();
//		this.touser = "@all";
		this.agentid = agentid;
		this.msgtype = MSGTYPE_IMAGE;
		this.image = image;
	}
	
	/**
	 * 发送语音消息
	 * @param agentid
	 * @param voice
	 */
	public WeixinMessage(String agentid, Voice voice) {
		super();
//		this.touser = "@all";
		this.agentid = agentid;
		this.msgtype = MSGTYPE_VOICE;
		this.voice = voice;
	}
	
	/**
	 * 发送视频消息
	 * @param agentid
	 * @param video
	 */
	public WeixinMessage(String agentid, Video video) {
		super();
//		this.touser = "@all";
		this.agentid = agentid;
		this.msgtype = MSGTYPE_VIDEO;
		this.video = video;
	}
	
	/**
	 * 发送文件消息
	 * @param agentid
	 * @param video
	 */
	public WeixinMessage(String agentid, File file) {
		super();
//		this.touser = "@all";
		this.agentid = agentid;
		this.msgtype = MSGTYPE_FILE;
		this.file = file;
	}
	
	/**
	 * 发送文本卡消息
	 * @param agentid
	 * @param video
	 */
	public WeixinMessage(String agentid, Textcard textcard) {
		super();
//		this.touser = "@all";
		this.agentid = agentid;
		this.msgtype = MSGTYPE_TEXTCARD;
		this.textcard = textcard;
	}
	
	/**
	 * 发送图文消息
	 * @param agentid
	 * @param articles
	 */
	public WeixinMessage(String agentid, Articles articles) {
		super();
//		this.touser = "@all";
		this.agentid = agentid;
		this.msgtype = MSGTYPE_NEWS;
		this.news = new HashMap<String, List<WeixinMessage.Articles>>(2);
		List<Articles> arts = new ArrayList<Articles>(2);
		arts.add(articles);
		this.news.put(MSGTYPE_NEWS_ARTICLES, arts);
	}
	
	/**
	 * 发送图文消息
	 * @param agentid
	 * @param articles
	 */
	public WeixinMessage(String agentid, NpArticles articles) {
		super();
//		this.touser = "@all";
		this.agentid = agentid;
		this.msgtype = MSGTYPE_MPNEWS;
		this.mpnews = new HashMap<String, List<WeixinMessage.NpArticles>>(2);
		List<NpArticles> arts = new ArrayList<NpArticles>(2);
		arts.add(articles);
		this.mpnews.put(MSGTYPE_NPNEWS_ARTICLES, arts);
	}
	
	/**
	 * 消息发送给所有人
	 */
	public void setMessageToAll() {
		this.touser = "@all";
	}

	public String getTouser() {
		return touser;
	}
	
	//分隔符
	public final static String SEPARATOR = "|";
	
	/**
	 * 添加接收者
	 * @param tousers
	 */
	public void setTouser(List<String> tousers) {
		if(null != tousers && tousers.size() > 0) {
			StringBuilder sb = new StringBuilder();
			for (String str : tousers) {
				sb.append(str).append(SEPARATOR);
			}
			this.touser = sb.substring(0, sb.length()-1);
		}
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getToparty() {
		return toparty;
	}

	/**
	 * 添加接收单位
	 * @param topartys
	 */
	public void setToparty(List<String> topartys) {
		if(null != topartys && topartys.size() > 0) {
			StringBuilder sb = new StringBuilder();
			for (String str : topartys) {
				sb.append(str).append(SEPARATOR);
			}
			this.toparty = sb.substring(0, sb.length()-1);
		}
	}
	
	public void setToparty(String toparty) {
		this.toparty = toparty;
	}

	public String getTotag() {
		return totag;
	}
	
	/**
	 * 设置接收者标签
	 * @param tags
	 */
	public void setToTag(List<String> tags) {
		if(null != tags && tags.size()>0) {
			StringBuilder sb = new StringBuilder();
			for (String str : tags) {
				sb.append(str).append(SEPARATOR);
			}
			this.totag = sb.substring(0, sb.length()-1);
		}
	}

	public void setTotag(String totag) {
		this.totag = totag;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

	public String getAgentid() {
		return agentid;
	}

	public void setAgentid(String agentid) {
		this.agentid = agentid;
	}

	public Integer getSafe() {
		return safe;
	}

	public void setSafe(Integer safe) {
		this.safe = safe;
	}

	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Voice getVoice() {
		return voice;
	}

	public void setVoice(Voice voice) {
		this.voice = voice;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Textcard getTextcard() {
		return textcard;
	}

	public void setTextcard(Textcard textcard) {
		this.textcard = textcard;
	}

	public Map<String, List<NpArticles>> getMpnews() {
		return mpnews;
	}

	public void setMpnews(Map<String, List<NpArticles>> mpnews) {
		this.mpnews = mpnews;
	}

	public void setNews(Map<String, List<Articles>> news) {
		this.news = news;
	}

	public Map<String, List<Articles>> getNews() {
		return news;
	}

	public String getChatid() {
		return chatid;
	}

	public void setChatid(String chatid) {
		this.chatid = chatid;
	}
	
}
