/**
 * Copyright © 2018 aifeng club All rights reserved.
 *
 * @Package: com.aifeng.ddrent.web.filter
 * @author imart·deng
 * @date 创建时间：2018/10/18 15:47
 * @version 1.0
 */
package com.aifeng.ddrent.web.filter;

import com.aifeng.ddrent.api.auth.AuthRPCService;
import com.aifeng.ddrent.api.auth.response.UserAuthInfoDTO;
import com.aifeng.ddrent.api.auth.response.UserTokenInfoDTO;
import com.aifeng.ddrent.common.constant.session.SessionConstant;
import com.aifeng.ddrent.common.enums.system.ErrorCodeEnum;
import com.aifeng.ddrent.common.util.data.id.SequenceGeneratorUtil;
import com.aifeng.ddrent.common.util.system.StringUtils;
import com.aifeng.ddrent.web.filter.sessionManager.SessionStatusManager;
import com.aifeng.ddrent.web.response.commons.SessionInfo;
import org.springframework.beans.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @ClassName: AuthenticationFilter
 * @Description: 授权控制
 * @author: imart·deng
 * @date: 2018/10/18 15:47 
 *
 */
public class AuthenticationFilter implements Filter {

    public static final String EXCLUDE_PATTERN = "EXCLUDE_PATTERN";

    // 被排除的 uri
    private static List<Pattern> excludePatterns;

    private AuthRPCService authRPCService;

    // session 状态控制器
    private SessionStatusManager sessionStatusManager;

    public AuthenticationFilter(AuthRPCService authRPCService) {
        this.authRPCService = authRPCService;
        this.sessionStatusManager = SessionStatusManager.getInstance();
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String excludeUrls = filterConfig.getInitParameter(EXCLUDE_PATTERN);
        excludeUrls = null == excludeUrls ? "" : excludeUrls;

        // 获取排除uri pattern
        excludePatterns = Arrays.stream(excludeUrls.split(",")).filter(StringUtils::isNotBlank).map(Pattern::compile).collect(Collectors.toList());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        String uri = getUri((HttpServletRequest)request);

        // 如果不需要 过滤则直接通过
        if(isExclude(uri)) { chain.doFilter(request, response); return ; }

        String accessToken = getToken(request);

        // accessToken 不存在，则返回token失效
        if(StringUtils.isBlank(accessToken)) {
            writeAuthTimeOut(response);
            return;
        }


        UserAuthInfoDTO userAuthInfoDTO = authRPCService.authByToken(accessToken);

        // token 验证无效，则返回token失效
        if(null == userAuthInfoDTO){
            writeAuthTimeOut(response);
            return;
        }

        // 校验是否有权限
        UserTokenInfoDTO userTokenInfoDTO = userAuthInfoDTO.getUserTokenInfoDTO();
        boolean isRight = userTokenInfoDTO.getUriPattern().stream().anyMatch(pattern -> pattern.matcher(uri).find());

        // 缓存会话信息，仅缓存个人信息
        SessionStatusManager.Session session = sessionStatusManager.get(accessToken);
        if(null == session){
            SessionInfo sessionInfo = new SessionInfo();
            // 缓存 个人基础信息
            BeanUtils.copyProperties(userAuthInfoDTO.getUserInfoDTO(), sessionInfo);

            // 缓存授权信息
            sessionInfo.setLoginTime(userTokenInfoDTO.getCreateTime());
            sessionInfo.setRoles(userTokenInfoDTO.getUserRoles());

            sessionStatusManager.add(SequenceGeneratorUtil.nextId()+"", new Date(), accessToken, sessionInfo);
        }

        // 有权限
        if(isRight) {
            chain.doFilter(request, response);
        }else{
            writeAuthFailded(response);
        }

    }

    /**
     * 请求无权限
     * @param response          httpresponse
     * @throws IOException      网络IO异常
     */
    private void writeAuthFailded(ServletResponse response) throws IOException {
        writeAuthFailed(response, ErrorCodeEnum.AUTH_NORIGHT);
    }

    /**
     * 登陆超时
     * @param response          httpresponse
     * @throws IOException      网络IO异常
     */
    private void writeAuthTimeOut(ServletResponse response) throws IOException {
        writeAuthFailed(response, ErrorCodeEnum.AUTH_LOGIN_TIMEOUT);
    }

    /**
     * 向response写入异常结果
     * @param response          httpresponse
     * @param errorCodeEnum     错误编码枚举
     * @throws IOException      网络IO异常
     */
    private void writeAuthFailed(ServletResponse response, ErrorCodeEnum errorCodeEnum) throws IOException {
        PrintWriter out = response.getWriter();
        out.write(String.format("{\"success\":\"%s\",\"code\":\"%s\",\"message\":\"%s\"}", Boolean.FALSE, errorCodeEnum.code(), errorCodeEnum.msg()));
        out.flush();
        out.close();
    }

    @Override
    public void destroy() {

    }

    /**
     * 查询是否为 排除参数
     * @param uri   请求uri
     * @return  true 为被排除的uri，false则需要过滤的uri
     */
    private boolean isExclude(final String uri){
        return excludePatterns.stream().anyMatch(pattern -> pattern.matcher(uri).find());
    }

    public static void main(String[] args) {
        String excludeUrls = "abc,edf,fue,fao,ghg,dei,iew/ffb,/user";
        String uri = "/usewr2/login";
        excludePatterns = Arrays.stream(excludeUrls.split(",")).filter(StringUtils::isNotBlank).map(Pattern::compile).collect(Collectors.toList());

        System.out.println(excludePatterns.stream().anyMatch(pattern -> pattern.matcher(uri).find()));
    }

    /**
     * 获取请求uri
     * @param request
     * @return
     */
    public String getUri(HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String uri = requestUri.substring(contextPath.length());
        return  uri;
    }

    /**
     * 获取token
     * @param request
     * @return
     */
    private String getToken(ServletRequest request){
        Cookie[] cookies = ((HttpServletRequest) request).getCookies();
        for (Cookie cookie: cookies) {
            if(SessionConstant.ACCESS_TOKEN.equals(cookie.getName())) return cookie.getValue();
        }
        return null;
    }
}
