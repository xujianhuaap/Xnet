package com.github.xujianhua.xnet.network;

import com.github.xujianhua.xnet.util.LogUtil;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by xujianhua on 2016/3/21.
 */
public class UrlTool {
    private static final String TAG=UrlTool.class.getName();
    private String host;
    private String path;
    private Hashtable parameters;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Hashtable getParameters() {
        return parameters;
    }

    public void setParameters(Hashtable parameters) {
        this.parameters = parameters;
    }

    /***
     *
     * @param urlTool
     * @return
     */
    public static String generateUrlStr(UrlTool urlTool){
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append(urlTool.getHost().trim()).append(urlTool.getPath().trim());
        Hashtable<String,String> params=urlTool.getParameters();
        if(params!=null){
            Set<String> keys=params.keySet();
            if(!keys.isEmpty()){
                stringBuffer.append("?");
            }
            Iterator<String> iterator=keys.iterator();
            boolean isFirst=true;
            while (iterator.hasNext()){
                String key=iterator.next();
                if(isFirst){
                    isFirst=false;
                }else {
                    stringBuffer.append("&");
                }
                stringBuffer.append(key);
                stringBuffer.append("=");
                stringBuffer.append(params.get(key));
            }
        }

        LogUtil.i(TAG,"UrlTool url \t %1$s",stringBuffer.toString());
        return stringBuffer.toString();
    }
    public static class Builder{
        private String host;
        private String path;
        private Hashtable parameters;

        public Builder setHost(String host){
            this.host=host;
            return this;
        }
        public Builder setPath(String path){
            this.path=path;
            return this;
        }
        public Builder setParameter(Hashtable parameters){
            this.parameters=parameters;
            return this;
        }
        public UrlTool build(){
            UrlTool urlTool=new UrlTool();
            urlTool.setHost(host);
            urlTool.setPath(path);
            urlTool.setParameters(parameters);
            return urlTool;
        }
    }
}
