package com.flying.xiaopo.services;

import com.flying.xiaopo.dao.TaeyeonDao;
import com.flying.xiaopo.domain.Taeyeon;
import com.flying.xiaopo.domain.Taeyeons;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CommonsLogWriter;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 中间的处理数据类
 * Created by Flying SnowBean on 2016/1/16.
 */
@Service
public class TaeyeonService {

    @Autowired
    private TaeyeonDao taeyeonDao;

    public TaeyeonService() {
    }

    /**
     * 保存数据
     * @param taeyeon   需要保存的数据
     * @return     保存好的数据
     */
    public Taeyeon save(final Taeyeon taeyeon){
        return taeyeonDao.save(taeyeon);
    }

    /**
     * 获取对应id的数据
     * @param id   id
     * @return   对应id的数据
     */
    public Taeyeon getById(final int id){
        return taeyeonDao.findById(id);
    }

    /**
     * 获取所有的数据
     * @return   数据集合的包装类
     * @see Taeyeons
     */
    public Taeyeons getAll(){
        final Taeyeons taeyeons = new Taeyeons(taeyeonDao.findAll());
        return taeyeons;
    }

    /**
     * 获取最近的数据
     * @return   最近的数据
     */
    public Taeyeon getLatest(){
        final Taeyeon taeyeon = taeyeonDao.findLatest();
        return taeyeon;
    }

    /**
     * 储存文件方法
     * @param inputStream    文件的输入流
     * @param rootPath      web项目根路径
     * @param date           文件上传的日期
     * @param fileName      文件名
     * @return              文件的相对路径
     * @throws IOException
     */
    public String writeToFile(InputStream inputStream,String rootPath,String date,String fileName) throws IOException {
        String filePath = rootPath+"photos"+File.separator+fileName;
        System.out.println(filePath);
        File file = new File(filePath);
        OutputStream outputStream = new FileOutputStream(file);
        int read = 0;
        byte[] buffer = new byte[1024];
        while ((read=inputStream.read(buffer,0,buffer.length))!=-1){
            outputStream.write(buffer,0,buffer.length);
        }
        outputStream.flush();
        outputStream.close();

        return "/photos/"+fileName;
    }
}
