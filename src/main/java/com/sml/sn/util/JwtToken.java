package com.sml.sn.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class JwtToken {
  
    //��Կ  
    private static final String SECRET = "secret";
  
    //jackson  
    private static ObjectMapper mapper = new ObjectMapper();  
  
    /** 
	 * 1������JWTͷ
     * header���� 
     * @return 
     */  
    private static Map<String, Object> createHead() {  
        Map<String, Object> map = new HashMap<String, Object>();  
        map.put("typ", "JWT");  
        map.put("alg", "HS256");  
        return map;  
    }  
  
    /** 
     * ����token 
     * 2����Ч�غ�
     * @param obj    �������� 
     * @param maxAge ��Ч�ڣ���λ�����룩 
     * @param <T> 
     * @return 
     */  
    public static <T> String sign(T obj, long maxAge) throws UnsupportedEncodingException, JsonProcessingException {  
        JWTCreator.Builder builder = JWT.create();
  
        builder.withHeader(createHead())//header  
                .withSubject(mapper.writeValueAsString(obj));  //payload  
  
        if (maxAge >= 0) {  
            long expMillis = System.currentTimeMillis() + maxAge;  
            Date exp = new Date(expMillis);  
            builder.withExpiresAt(exp);  
        }  
  
        return builder.sign(Algorithm.HMAC256(SECRET));  
    }  
  
    /** 
     * ���� 
     * @param token   token�ַ��� 
     * @param classT  ���ܺ������ 
     * @param <T> 
     * @return 
     */  
    public static <T> T unsign(String token, Class<T> classT) throws IOException {  
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();  
        DecodedJWT jwt = verifier.verify(token);  
  
        Date exp = jwt.getExpiresAt();  
        if(exp!=null&&exp.after(new Date())){  
            String subject = jwt.getSubject();  
            return mapper.readValue(subject, classT);  
        }  
  
        return null;  
    }  
    
  
}
