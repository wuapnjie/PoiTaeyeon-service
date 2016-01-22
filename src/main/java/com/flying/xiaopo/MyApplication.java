package com.flying.xiaopo;

import com.flying.xiaopo.resources.MyResource;
import com.flying.xiaopo.resources.TaeyeonResource;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * 应用配置类
 * Created by Flying SnowBean on 2016/1/22.
 */
public class MyApplication extends ResourceConfig {
    public MyApplication() {
        super(TaeyeonResource.class, MyResource.class, MultiPartFeature.class);
    }
}
