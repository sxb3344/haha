package bawei.com.okhttp_feng.data.utils;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OkHttpUtils {
    private static OkHttpUtils istance;
    private OkHttpClient okHttpClient;
    ///私有哦构造方法
    private OkHttpUtils(){
        if(null == okHttpClient){
            synchronized (OkHttpUtils.class){
                if(null == okHttpClient){
                   okHttpClient =  new OkHttpClient();
                }
            }
        }
    }
    public static OkHttpUtils getInstance(){
        if(null == istance){
            synchronized (OkHttpUtils.class){
                if(null == istance){
                  istance =  new OkHttpUtils();
                }
            }
        }
        return istance;
    }
    public void  get(String geturl, Callback callback){
        Request request = new Request.Builder().url(geturl).build();
        okHttpClient.newCall(request).enqueue(callback);
    }
    public void post(FormBody formBody,String postUrl,Callback callback){
        Request request = new Request.Builder().method("POST",formBody).url(postUrl).build();
        okHttpClient.newCall(request).enqueue(callback);
    }

}
