package com.dd.generator.common.web.converters;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.dd.generator.common.exception.GeneratorRuntimeException;
import com.dd.generator.common.web.ResultCode;
import com.dd.generator.common.web.WebCommonRequest;
import com.dd.generator.common.web.WebCommonResponse;
import lombok.Setter;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;

import static com.dd.generator.common.util.MD5.encode;

public class JsonHttpMessageConverter extends AbstractHttpMessageConverter<Object> {

    private String charset = "UTF-8";

    private SerializerFeature[] features = new SerializerFeature[0];

    @Setter
    private boolean checkSignature = false;

    @Setter
    private boolean checkUser = false;

    @Setter
    private String salt;

    public JsonHttpMessageConverter() {
        super(new MediaType("application", "json", Charset.forName("UTF-8")));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return true;
    }

    public String getCharset() {
        return this.charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public SerializerFeature[] getFeatures() {
        return features;
    }

    public void setFeatures(SerializerFeature... features) {
        this.features = features;
    }

    @Override
    protected Object readInternal(Class<? extends Object> clazz, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {

        InputStream in = inputMessage.getBody();
        String jsonStr = IOUtils.toString(in, charset);
        WebCommonRequest request = JSON.parseObject(jsonStr, WebCommonRequest.class);

        String username = request.getUsername();
        String password = request.getPassword();
        String timestamp = request.getTimestamp();
        String signature = request.getSignature();

        String data = request.getData();

        if(data == null){
            throw new GeneratorRuntimeException("数据内容data不能为null");
        }

        checkTimestamp(timestamp);

        if(checkSignature){
            checkSignature(signature, data, timestamp);
        }

        if(checkUser){
            checkUser(username, password);
        }


        if (clazz.getName().startsWith("java.lang.")) {
            try {
                JSONObject jsonObj = JSON.parseObject(data);
                Set<Entry<String, Object>> set = jsonObj.entrySet();
                if (set.size() == 1) {
                    return JSON.parseObject(set.iterator().next().getValue().toString(), clazz);
                }
            } catch (Exception e) {
            }
        }
        Object obj = JSON.parseObject(data, clazz);

        if (obj == null && StringUtils.isNotEmpty(data)) {
        }

        return obj;
    }

    private void checkTimestamp(String timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        try {
            Date date = sdf.parse(timestamp);
            long time = System.currentTimeMillis() - date.getTime();
            if(Math.abs(time) > 15*60*1000){
                throw new GeneratorRuntimeException("timestamp无效");
            }
        } catch (ParseException e1) {
            throw new GeneratorRuntimeException("timestamp格式错误，正确格式为yyyyMMddHHmmssSSS", e1);
        }
    }

    private void checkUser(String username, String password) {
        try{
            //
        }catch (Exception e) {
            throw new GeneratorRuntimeException("用户名或密码错误", e);
        }
    }

    private void checkSignature(String signature, String data, String timestamp) {
        if(signature == null){
            throw new GeneratorRuntimeException("签名不能为空");
        }
        String temp = data + timestamp;
        if(salt != null){
            temp += salt;
        }
        String md5 = encode(temp, charset);
        if(!signature.equals(md5)){
            throw new GeneratorRuntimeException("签名验证不通过");
        }
    }

    @Override
    protected void writeInternal(Object obj, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {

        WebCommonResponse response = new WebCommonResponse();
        if (obj != null && obj instanceof LinkedHashMap) {
            LinkedHashMap<?, ?> map = (LinkedHashMap<?, ?>) obj;
            Object status = map.get("status");
            Object error = map.get("error");
            Object message = map.get("message");
            Object path = map.get("path");
            Object timestamp = map.get("timestamp");
            if (status != null && error != null && message != null && path != null && timestamp != null) {
                response.setResultCode(ResultCode.FAILED);
                response.setResultMessage((String) message);
            }
        }
        response.setData(obj);
        OutputStream out = outputMessage.getBody();
        String text = JSON.toJSONString(response, features);
        byte[] bytes = text.getBytes(charset);
        out.write(bytes);
    }
}
