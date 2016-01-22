package com.flying.xiaopo.resources;

import com.flying.xiaopo.domain.Taeyeon;
import com.flying.xiaopo.domain.Taeyeons;
import com.flying.xiaopo.services.TaeyeonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Flying SnowBean on 2016/1/16.
 */
@Path("taeyeons")
public class TaeyeonResource {

    private static final Logger LOGGER = LogManager.getLogger(TaeyeonResource.class);

    @Autowired
    private TaeyeonService taeyeonService;

    /**
     * 根据id获得相应的数据
     * @param id  id
     * @return   对应id的数据
     */
    @GET
    @Produces("application/json")
    @Path("taeyeon")
    public Taeyeon getByid(@QueryParam("id") final int id) {
        return taeyeonService.getById(id);
    }

    /**
     * 获取所有数据
     * @return   数据集合
     */
    @GET
    @Produces({"application/json", "application/xml"})
    public Taeyeons getAll() {
        LOGGER.error(taeyeonService.getAll());
        return taeyeonService.getAll();
    }

    /**
     * 获得最近的数据
     * @return   最近的数据
     */
    @GET
    @Path("taeyeon/latest")
    @Produces("application/json")
    public Taeyeon getLatest() {
        return taeyeonService.getLatest();
    }

//    @POST
//    @Consumes("application/json")
//    @Produces("application/json")
//    public Taeyeon postTaeyeon(final Taeyeon taeyeon) {
//        return taeyeonService.save(taeyeon);
//    }

    /**
     * 根据表单上传数据
     * @param describe      描述
     * @param describeDisposition      描述的信息
     * @param picInputStream    文件的输入流
     * @param picDisposition    文件的信息
     * @param context     servlet的context
     * @return         保存成功的数据
     * @throws IOException
     */
    @POST
    @Path("taeyeon/one")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces("application/json")
    public Taeyeon postOneTaeyeon(
            @FormDataParam("string") String describe,
            @FormDataParam("string") FormDataContentDisposition describeDisposition,
            @FormDataParam("file") InputStream picInputStream,
            @FormDataParam("file") FormDataContentDisposition picDisposition,
            @Context ServletContext context) throws IOException {
        String rootPath = context.getRealPath("/");
        System.out.println(rootPath);
        System.out.println(context);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = simpleDateFormat.format(new Date());
        String picName = picDisposition.getFileName();
        String pic = taeyeonService.writeToFile(picInputStream,rootPath, date, picName);
        final Taeyeon taeyeon = new Taeyeon(pic, describe, date);
        taeyeonService.save(taeyeon);
        return taeyeon;
    }

}
