package com.mikewoo.autotest.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mikewoo.autotest.common.ResponseData;
import org.testng.annotations.Test;

/**
 * @author Eric Gui
 * @date 2018/12/28
 */
public class GsonTest {

    @Test
    public void test() {
        String json = "{\"status\":200,\"msg\":\"OK\",\"data\":[{\"id\":1,\"username\":\"eric\",\"password\":\"123456\",\"age\":29,\"sex\":0,\"permission\":0,\"isDel\":0}]}";
        Gson gson = buildGson();
        ResponseData data = gson.fromJson(json, ResponseData.class);
        System.out.println(json);
        System.out.println(data.getData());
    }

    private  Gson buildGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(ResponseDataTypeAdaptor.FACTORY);
        return gsonBuilder.create();
    }

}
