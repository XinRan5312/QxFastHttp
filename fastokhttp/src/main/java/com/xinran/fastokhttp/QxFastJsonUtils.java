package com.xinran.fastokhttp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.List;

/**
 * Created by qixinh on 16/3/30.
 */
public class QxFastJsonUtils {


        /**
         * 将java类型的对象转换为JSON格式的字符串
         * @param object java类型的对象
         * @return JSON格式的字符串
         */
        //ArrayList<Person> list = new ArrayList<Person>();
        //String json = FastJSONHelper.serialize(list);
        public static <T> String parseAnyObjToJsonString(T object) {
            return JSON.toJSONString(object);
        }

        /**
         * 将JSON格式的字符串转换为java类型的对象或者java数组类型的
         象，不包括java集合类型
         * @param json JSON格式的字符串
         * @param clz java类型或者java数组类型，不包括java集合类型
         * @return java类型的对象或者java数组类型的对象，不包括java集
        类型的对象
         */
        //Person[] persons = FastJSONHelper.deserialize(json, Person[].class);
        public static <T> T parseJsonStringToAnyObjNoList(String json, Class<T> clz) {
            return JSON.parseObject(json, clz);
        }

        /**
         * 将JSON格式的字符串转换为List<T>类型的对象
         * @param json JSON格式的字符串
         * @param clz 指定泛型集合里面的T类型
         * @return List<T>类型的对象
         */
        //List<String> list = new ArrayList<String>();
        //list = FastJSONHelper.deserializeList(json, String.class);
        public static <T> List<T> parseJsonStringToList(String json, Class<T> clz) {
            return JSON.parseArray(json, clz);
        }

        /**
         * 将JSON格式的字符串转换成任意Java类型的对象
         * @param json JSON格式的字符串
         * @param type 任意Java类型
         * @return 任意Java类型的对象
         */
        //List<HashMap<String, Object>> list = new ArrayList<HashMap<String,
       //  list = FastJSONHelper.deserializeAny(json,
        //    new TypeReference<List<HashMap<String, Object>>>() {
       // });

    public static <T> T parseJsonStringToAnyObj(String json, TypeReference<T> type){

        return JSON.parseObject(json, type);
    }

}


