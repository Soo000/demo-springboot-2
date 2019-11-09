package com.alisls.demo.springboot.web.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.alisls.demo.springboot.web.entity.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("tokenService")
public class TokenService {

    private static final String secret = "1q2w3e4r5t6y";
    Algorithm algHMAC256 = Algorithm.HMAC256(secret);

    /**
     * 获取 token
     * @param user
     * @return
     */
    public String getToken(User user) {
        //头部信息
        Map<String, Object> headerMap = new HashMap<String, Object>();
        headerMap.put("alg", "HMAC256");
        headerMap.put("typ", "JWT");

        Date nowDate = new Date();
        Date expireDate = getAfterDate(nowDate,0,0,0,2,0,0);//2小过期

        String token = JWT.create() // 将 userId 保存到 token里面
                /* 设置 header */
                .withHeader(headerMap)
                /* 设置 payload */
                .withIssuer("Service") // 用于说明该JWT是由谁签发的，例如服务器
                .withIssuedAt(nowDate) // 生成签名的时间
                .withSubject("这是主题") // 签名的主题
                //.withNotBefore(new Date()) // 定义在什么时间之前，该 jwt 都不可用
                .withAudience(String.valueOf(user.getUserId())) // 签名的观众，也可以理解谁接受签名的
                .withExpiresAt(expireDate) // 签名的过期时间
                .withClaim("loginName", user.getUsername()) //
                .sign(algHMAC256); // 签名
        return token;
    }

    public void verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("SERVICE")
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            String subject = jwt.getSubject();
            Map<String, Claim> claims = jwt.getClaims();
            Claim claim = claims.get("loginName");
            System.out.println(claim.asString());
            List<String> audience = jwt.getAudience();
            System.out.println(subject);
            System.out.println(audience.get(0));
        } catch (JWTVerificationException exception){
            exception.printStackTrace();
        }
    }

    public String decodeToken(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);

        String header = decodedJWT.getHeader();
        String payload = decodedJWT.getPayload();
        String signature = decodedJWT.getSignature();

        System.out.println("header = " + header);
        System.out.println("payload = " + payload);
        System.out.println("signature = " + signature);

        return header + "." + payload + "." + signature;
    }

    public String createTokenWithClaim() {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            Map<String, Object> map = new HashMap<String, Object>();
            Date nowDate = new Date();
            Date expireDate = getAfterDate(nowDate,0,0,0,2,0,0); //2小过期
            map.put("alg", "HS256");
            map.put("typ", "JWT");
            String token = JWT.create()
                    /*设置头部信息 Header*/
                    .withHeader(map)
                    /*设置 载荷 Payload*/
                    .withClaim("loginName", "lijunkui")
                    .withIssuer("SERVICE")//签名是有谁生成 例如 服务器
                    .withSubject("this is test token")//签名的主题
                    //.withNotBefore(new Date())//定义在什么时间之前，该jwt都是不可用的.
                    .withAudience("APP")//签名的观众 也可以理解谁接受签名的
                    .withIssuedAt(nowDate) //生成签名的时间
                    .withExpiresAt(expireDate)//签名过期的时间
                    /*签名 Signature */
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * 返回一定时间后的日期
     * @param date 开始计时的时间
     * @param year 增加的年
     * @param month 增加的月
     * @param day 增加的日
     * @param hour 增加的小时
     * @param minute 增加的分钟
     * @param second 增加的秒
     * @return
     */
    public  Date getAfterDate(Date date, int year, int month, int day, int hour, int minute, int second){
        if(date == null){
            date = new Date();
        }

        Calendar cal = new GregorianCalendar();

        cal.setTime(date);
        if(year != 0){
            cal.add(Calendar.YEAR, year);
        }
        if(month != 0){
            cal.add(Calendar.MONTH, month);
        }
        if(day != 0){
            cal.add(Calendar.DATE, day);
        }
        if(hour != 0){
            cal.add(Calendar.HOUR_OF_DAY, hour);
        }
        if(minute != 0){
            cal.add(Calendar.MINUTE, minute);
        }
        if(second != 0){
            cal.add(Calendar.SECOND, second);
        }
        return cal.getTime();
    }

}
