package com.aifeng.ddrent.core.dao.model.weixin.enterprise;


public class WeixinAppDO {
    /**
     * 企业微信应用编号
     */
    private String agentId;

    /**
     * 企业微信应用密钥
     */
    private String secret;

    /**
     * 消息回调url
     */
    private String callbackUrl;

    /**
     * 消息回调token参数
     */
    private String callbackToken;

    /**
     * 消息回调aes密钥
     */
    private String callbackEncodingAesKey;

    /**
     * 应用可信域名
     */
    private String domain;

    /**
     * 应用主页
     */
    private String url;

    /**
     * 获取企业微信应用编号
     *
     * @return agent_id - 企业微信应用编号
     */
    public String getAgentId() {
        return agentId;
    }

    /**
     * 设置企业微信应用编号
     *
     * @param agentId 企业微信应用编号
     */
    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    /**
     * 获取企业微信应用密钥
     *
     * @return secret - 企业微信应用密钥
     */
    public String getSecret() {
        return secret;
    }

    /**
     * 设置企业微信应用密钥
     *
     * @param secret 企业微信应用密钥
     */
    public void setSecret(String secret) {
        this.secret = secret;
    }

    /**
     * 获取消息回调url
     *
     * @return callback_url - 消息回调url
     */
    public String getCallbackUrl() {
        return callbackUrl;
    }

    /**
     * 设置消息回调url
     *
     * @param callbackUrl 消息回调url
     */
    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    /**
     * 获取消息回调token参数
     *
     * @return callback_token - 消息回调token参数
     */
    public String getCallbackToken() {
        return callbackToken;
    }

    /**
     * 设置消息回调token参数
     *
     * @param callbackToken 消息回调token参数
     */
    public void setCallbackToken(String callbackToken) {
        this.callbackToken = callbackToken;
    }

    /**
     * 获取消息回调aes密钥
     *
     * @return callback_encoding_aes_key - 消息回调aes密钥
     */
    public String getCallbackEncodingAesKey() {
        return callbackEncodingAesKey;
    }

    /**
     * 设置消息回调aes密钥
     *
     * @param callbackEncodingAesKey 消息回调aes密钥
     */
    public void setCallbackEncodingAesKey(String callbackEncodingAesKey) {
        this.callbackEncodingAesKey = callbackEncodingAesKey;
    }

    /**
     * 获取应用可信域名
     *
     * @return domain - 应用可信域名
     */
    public String getDomain() {
        return domain;
    }

    /**
     * 设置应用可信域名
     *
     * @param domain 应用可信域名
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * 获取应用主页
     *
     * @return url - 应用主页
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置应用主页
     *
     * @param url 应用主页
     */
    public void setUrl(String url) {
        this.url = url;
    }

	@Override
	public String toString() {
		return "WeixinAppDO [agentId=" + agentId + ", secret=" + secret + ", callbackUrl=" + callbackUrl
				+ ", callbackToken=" + callbackToken + ", callbackEncodingAesKey=" + callbackEncodingAesKey
				+ ", domain=" + domain + ", url=" + url + "]";
	}
}