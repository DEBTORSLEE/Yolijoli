package kr.ac.jj.debtolee.yolijoli;

import android.util.Log;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FileUploadUtils {
    public static void send2Server(File file, String email,String partImage) {
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("img", file.getName(), RequestBody.create(MultipartBody.FORM, file))
                .addFormDataPart("name",email)
                .addFormDataPart("partImage",partImage)
                .build();
        Request request = new Request.Builder().url("https://vudghk0000.iwinv.net/yolijoli/yolijoli_upload.php")
                .post(requestBody).build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("TEST : ", response.body().string());
            }
        });
    }
}

