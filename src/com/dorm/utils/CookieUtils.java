package com.dorm.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CookieUtils {

    /**
     * cookie工具类:用户操作cookie
     * @param cookieName cookie名字
     * @param time cookie存活时间
     * @param request cookie请求对象
     * @param response cookie响应对象
     * @param stuCode cookie学号
     * @param password cookie密码
     */
    public static void addCookie(String cookieName, int time,
                                 HttpServletRequest request, HttpServletResponse response,String stuCode,String password) {
        Cookie cookie=getCookieByName(request,cookieName);
        if (cookie!=null){
//            浏览器中有保存用户登录的cookie，则更新cookie值
            cookie.setValue(stuCode+"#"+password);
        }else {
//            浏览器中没有cookie，则创建新的cookie，并设置cookie值
            cookie=new Cookie(cookieName,stuCode+"#"+password);
        }
//        设置cookie存货时间
        cookie.setMaxAge(time);
//        设置作用域
        cookie.setPath(request.getContextPath());
//        将cookie返回给浏览器
        response.addCookie(cookie);
    }

    /**
     * 根据cookieName获取cookie
     * @param request
     * @param cookieName
     * @return cookie对象
     */
    private static Cookie getCookieByName(HttpServletRequest request, String cookieName) {
//        获取浏览器中所有的cookie
        Cookie[] cookies=request.getCookies();
        if (cookies !=null&& cookies.length>0){
            for (Cookie cookie:cookies) {
                if (cookie.getName().equals(cookieName)){
                    return cookie;
                }
            }
        }
        return null;
    }
}
