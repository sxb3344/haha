package bawei.com.okhttp_feng;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import bawei.com.okhttp_feng.data.utils.OkHttpUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_OKHttpGetRequestThrowAsync)
    Button btnOKHttpGetRequestThrowAsync;
    @BindView(R.id.btn_OKHttpPostRequestThrowAsync)
    Button btnOKHttpPostRequestThrowAsync;
    @BindView(R.id.btn_OKHttpGetRequestThrowUtil)
    Button btnOKHttpGetRequestThrowUtil;
    @BindView(R.id.btn_OKHttpPostRequestThrowUtil)
    Button btnOKHttpPostRequestThrowUtil;
    public static final String GETURL_STRING = "http://112.124.22.238:8081/course_api/cniaoplay/featured2?p={%27page%27:0}";
    //post请求接口需要在请求体中提交请求参数
    public static final String POSTURL_STRING = "http://101.200.41.116:8099/UserQry";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_OKHttpGetRequestThrowAsync, R.id.btn_OKHttpPostRequestThrowAsync, R.id.btn_OKHttpGetRequestThrowUtil, R.id.btn_OKHttpPostRequestThrowUtil})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_OKHttpGetRequestThrowAsync:
                getRequsetByOkHttpthow();
                break;
            case R.id.btn_OKHttpPostRequestThrowAsync:
                postRequestByokhttpthow();
                break;
            case R.id.btn_OKHttpGetRequestThrowUtil:
                getRequestThowUtil();
                break;
            case R.id.btn_OKHttpPostRequestThrowUtil:
                postRequestThowUtil();
                break;
        }
    }

    private void postRequestThowUtil() {
        FormBody formbody = new FormBody.Builder()
                .add("token", "")
                .add("type", "0")
                .add("op_code", "")
                .add("phone_no", "18301154407")
                .build();
        OkHttpUtils.getInstance().post(formbody, POSTURL_STRING, new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, string, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void getRequestThowUtil() {
        OkHttpUtils.getInstance().get(GETURL_STRING, new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, string, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void postRequestByokhttpthow() {
        OkHttpClient okHttpClient = new OkHttpClient();

        FormBody formBody = new FormBody.Builder()
                .add("token", "")
                .add("type", "0")
                .add("op_code", "")
                .add("phone_no", "18301154407")
                .build();
        Request request = new Request.Builder().method("POST", formBody).url(POSTURL_STRING).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseMsg = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, responseMsg, Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    private void getRequsetByOkHttpthow() {
        //创建okhttpclient的请求对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //创建请求对象
        final Request request = new Request.Builder().url(GETURL_STRING).build();
        Call call = okHttpClient.newCall(request);

        //开启异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String responseMsg = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, responseMsg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
