package kr.ac.jj.debtolee.yolijoli.user;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    //서버 url 설정(php파일 연동)
    final static  private String URL="http://vudghk0000.iwinv.net/yolijoli/Register.php";
    private Map<String, String> map;
    //private Map<String, String>parameters;

    public RegisterRequest(String UserEmail, String UserPwd, String UserName,String UserBonth,String UserGender,String UserPhon,String UserQustion,String UserAnswer,Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("UserEmail", UserEmail);
        map.put("UserPwd", UserPwd);
        map.put("UserName", UserName);
        map.put("UserBonth",UserBonth);
        map.put("UserGender",UserGender);
        map.put("UserPhone",UserPhon);
        map.put("UserQuestion",UserQustion);
        map.put("UserAnswer",UserAnswer);

    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }
}