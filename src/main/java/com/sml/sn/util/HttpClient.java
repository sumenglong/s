package com.sml.sn.util;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpClient {

/*    @Test
    public void  main(){
        String aa=doGet("https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx995f8d79ad30ca6b&secret=94516bd85b82da735812be1673a070d9&code=0616LjGa14rWJA0PtjFa1pMaVE06LjGx&grant_type=authorization_code");
        Gson gson = new Gson();
        Map<String, Object> maps = new HashMap<String, Object>();
        maps = gson.fromJson(aa, maps.getClass());
        System.out.println(maps);
    }*/
 /*   @Test
    public void  main1(){
        String aa=doGet("https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=wx995f8d79ad30ca6b&grant_type=refresh_token&refresh_token=43_8Y1ial64MTz7F5W6UhmuFYa_r123L9CYMGTGq8uCa7iklnZghMvMGXICrnxedMKb9rqdJCYdPA6dFpuWAtQv3NnkaZmM8kIZZADe5dgAK4I");
        System.out.println(aa);
    }
    */
   /* @Test
    public void  main2(){
        String aa=doGet("https://api.weixin.qq.com/sns/userinfo?access_token=43_UC7HZA_1Bkq2oRRpG7rwOkWL_1szNkrDP5h253zrni4JmTeLTXiWfh_74Yz5sTniStnWKAIQrUohcMa711JBSH3qxbkhnbl3V4w18CJu4n0&openid=o33HI6f8_cvGhH11oKvwTwOZKtlw&lang=zh_CN");
        System.out.println(aa);
        Gson gson = new Gson();
        Map<String, Object> maps = new HashMap<String, Object>();
        maps = gson.fromJson(aa, maps.getClass());
        System.out.println(maps);
    }
*/
@Test
public void  main2(){
    Jedis redis=new Jedis("127.0.0.1");
    redis.set("sml","sml");
    System.out.println(redis.get("sml"));
   // System.out.println(UUID.randomUUID().toString());
}

    public static String doGet(String httpurl) {
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        String result = null;// 返回结果字符串
        try {
            // 创建远程url连接对象  手机
            URL url = new URL(httpurl);
            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接方式：get
            connection.setRequestMethod("GET");
            // 设置连接主机服务器的超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(60000);
            // 发送请求
            connection.connect();
            // 通过connection连接，获取输入流
            if (connection.getResponseCode() == 200) {
                is = connection.getInputStream();
                // 封装输入流is，并指定字符集
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                // 存放数据
                StringBuffer sbf = new StringBuffer();
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            connection.disconnect();// 关闭远程连接
        }

        return result;
    }

    public static String doPost(String httpUrl, String param) {

        HttpURLConnection connection = null;
        InputStream is = null;
        OutputStream os = null;
        BufferedReader br = null;
        String result = null;
        try {
            URL url = new URL(httpUrl);
            // 通过远程url连接对象打开连接
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接请求方式
            connection.setRequestMethod("POST");
            // 设置连接主机服务器超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取主机服务器返回数据超时时间：60000毫秒
            connection.setReadTimeout(60000);

            // 默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
            connection.setDoOutput(true);
            // 默认值为：true，当前向远程服务读取数据时，设置为true，该参数可有可无
            connection.setDoInput(true);
            // 设置传入参数的格式:请求参数应该是 name1=value1&name2=value2 的形式。
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            // 设置鉴权信息：Authorization: Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0
            connection.setRequestProperty("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");
            // 通过连接对象获取一个输出流
            os = connection.getOutputStream();
            // 通过输出流对象将参数写出去/传输出去,它是通过字节数组写出的
            os.write(param.getBytes());
            // 通过连接对象获取一个输入流，向远程读取
            if (connection.getResponseCode() == 200) {

                is = connection.getInputStream();
                // 对输入流对象进行包装:charset根据工作项目组的要求来设置
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

                StringBuffer sbf = new StringBuffer();
                String temp = null;
                // 循环遍历一行一行读取数据
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 断开与远程地址url的连接
            connection.disconnect();
        }
        return result;
    }
}
