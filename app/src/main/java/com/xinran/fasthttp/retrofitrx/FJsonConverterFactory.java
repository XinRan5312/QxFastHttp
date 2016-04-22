package com.xinran.fasthttp.retrofitrx;

import retrofit.Converter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;



/**
 * Created by qixinh on 16/4/18.
 */
public class FJsonConverterFactory extends Converter.Factory {
    public static FJsonConverterFactory create() {
        return new FJsonConverterFactory();
    }





    /**
     * 需要重写父类中responseBodyConverter，该方法用来转换发送给服务器的数据
     */


    @Override
    public Converter<?, com.squareup.okhttp.RequestBody> toRequestBody(Type type, Annotation[] annotations) {
        return new FastJsonRequestBodyConverter<>();
    }
    /**
     * 需要重写父类中responseBodyConverter，该方法用来转换服务器返回数据
     */
    @Override
    public Converter<com.squareup.okhttp.ResponseBody, ?> fromResponseBody(Type type, Annotation[] annotations) {
        return new FastJsonResponseBodyConverter<>(type);
    }
}
