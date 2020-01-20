package com.mp.util;

import com.alibaba.fastjson.JSONObject;

public class JsonResult {
    public static JSONObject success(Object obj) {
        JSONObject json = new JSONObject();
        json.put("status", 200);
        json.put("message", "成功");
        if (null != obj) {
            json.put("obj", obj);
        }
        return json;
    }

    public static JSONObject fail(Object obj) {
        JSONObject json = new JSONObject();
        json.put("status", 200);
        json.put("message", "失败");
        if (null != obj) {
            json.put("obj", obj);
        }
        return json;
    }
    public static JSONObject toJSONObject(Integer status, String msg, Object obj) {
        JSONObject json = new JSONObject();
        json.put("status", status);
        json.put("message", msg);
        if (null != obj) {
            json.put("obj", obj);
        }
        return json;
    }
}
