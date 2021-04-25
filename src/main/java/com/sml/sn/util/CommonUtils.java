package com.sml.sn.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * 存储文件
 * Created by shkstart on 2020/4/30.
 */
//工具类注解
@Component
public class CommonUtils {


    /**
     * 文件上传封装：如果不注入到Spring容器中就使用静态类的new的方式调用此方法。
     * savePath：文件保存路径
     */
    public static String fileUpload(MultipartFile file, String savePath){
        //生成一个新的文件名
        String picName = UUID.randomUUID().toString();
        //截取文件的扩展名（后缀名：如：.jsp）
        String oriName = file.getOriginalFilename();
        String extName = oriName.substring(oriName.lastIndexOf("."));


        //创建文件夹
        File dirFile = new File(savePath);        if (!dirFile.exists()){
            dirFile.mkdirs();  //创建多级目录
        }
        String newFileName = "";
        if(extName.equals(".dat"))
            newFileName = (new StringBuilder()).append(picName).append(".jpg").toString();
        else
            newFileName = (new StringBuilder()).append(picName).append(extName).toString();
        //新文件名
        File targetFile = new File(savePath, newFileName);

        try {
            //保存文件
            file.transferTo(targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return newFileName;
    }

    /**
     * 日期格式转换
     */
    public static String copyFile(String srcPathStr, String desPathStr) {
        //1.获取源文件的名称
        String newFileName = srcPathStr.substring(srcPathStr.lastIndexOf("\\")+1); //目标文件地址
        String extName = newFileName.substring(newFileName.lastIndexOf("."));
        newFileName = UUID.randomUUID().toString();
        System.out.println(newFileName);
        desPathStr = desPathStr + newFileName+extName; //源文件地址
        System.out.println(desPathStr);
        try{
            //2.创建输入输出流对象
            FileInputStream fis = new FileInputStream(srcPathStr);
            FileOutputStream fos = new FileOutputStream(desPathStr);

            //创建搬运工具
            byte datas[] = new byte[1024*8];
            //创建长度
            int len = 0;
            //循环读取数据
            while((len = fis.read(datas))!=-1){
                fos.write(datas,0,len);
            }
            //3.释放资源
            fis.close();
            fis.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return desPathStr;
    }


}
