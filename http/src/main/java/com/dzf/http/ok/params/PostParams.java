package com.dzf.http.ok.params;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by zzbmi on 2016/2/20.
 */
public class PostParams {
    private HashMap<String, Object> map = new HashMap<>();
    public PostParams add(String key, String value){
        map.put(key,value);
        return this;
    }

    public PostParams add(String key, Object value){
        map.put(key,value);
        return this;
    }
    public PostParams add(String key, boolean value){
        map.put(key,value);
        return this;
    }
    public PostParams add(String key, File file){
        map.put(key,file);
        return this;
    }

    /**
     * 多文件上传
     * @param key
     * @param files
     * @return
     */
    public PostParams add(String key, List files){
        map.put(key,files);
        return this;
    }

    public PostParams add(String key, int value){
        map.put(key, String.valueOf(value));
        return this;
    }
    public PostParams add(String key, long value){
        map.put(key, String.valueOf(value));
        return this;
    }
    public boolean isMultipart(){
        Set<Map.Entry<String, Object>> entrySet = map.entrySet();
        for (Map.Entry<String, Object> entry : entrySet) {
            Object value = entry.getValue();
            if (value instanceof File) {
                return true;
            }
            if (value instanceof List && ((List) value).get(0) instanceof File) {
                return true;
            }
        }
        return false;
    }

    public PostParams putAll(Map baseParams) {
        if (map == null) {
            map = new HashMap<>();
        }
        map.putAll(baseParams);
        return this;
    }

    public HashMap<String, Object> getParams(){
        return map;
    }
}
