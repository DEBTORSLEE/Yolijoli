package kr.ac.jj.debtolee.yolijoli.frag;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import kr.ac.jj.debtolee.yolijoli.R;
import kr.ac.jj.debtolee.yolijoli.adapter.randomAdapter;
import kr.ac.jj.debtolee.yolijoli.dto.randomDTO;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RandomMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RandomMenuFragment extends Fragment {
    String mJsonString = null;
    GetData getData;
    GridView gridview;
    randomAdapter dao;
    View v;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RandomMenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RandomMenuFragment newInstance(String param1, String param2) {
        RandomMenuFragment fragment = new RandomMenuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v =inflater.inflate(R.layout.fragment_randommenu,container,false);
        gridview = v.findViewById(R.id.randommenu_listview);
        dao = new randomAdapter();
        getData = new GetData();
        getData.execute("","");





        // Inflate the layout for this fragment
        return v;
    }

    private class GetData extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(getContext(),
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "response - " + result);

            if (result == null){

            }
            else {

                mJsonString = result;
                showResult();
            }
        }


        @Override
        protected String doInBackground(String... params) {

            String searchKeyword1 = params[0];
            String searchKeyword2 = params[1];

            String serverURL = "http://vudghk0000.iwinv.net/yolijoli/yolijoli_dbSelect_CookInfo.php";


            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "response code - " + responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }


                bufferedReader.close();


                return sb.toString().trim();


            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);
                errorString = e.toString();

                return null;
            }

        }
    }


    private void showResult(){
        String TAG_JSON = "cookinfo";
        String TAG_i_recipe_code = "i_recipe_code";
        String TAG_i_recipe_name = "i_recipe_name";
        String TAG_i_recipe_title = "i_recipe_title";
        String TAG_i_recipe_part_code = "i_recipe_part_code";
        String TAG_i_recipe_part_name = "i_recipe_part_name";
        String TAG_i_recipe_food_code = "i_recipe_food_code";
        String TAG_i_recipe_food_name = "i_recipe_food_name";
        String TAG_i_recipe_cook_time = "i_recipe_cook_time";
        String TAG_i_recipe_kcal = "i_recipe_kcal";
        String TAG_i_recipe_dose = "i_recipe_dose";
        String TAG_i_recipe_difficulty = "i_recipe_difficulty";
        String TAG_i_recipe_material = "i_recipe_material";
        String TAG_i_recipe_price= "i_recipe_price";
        String TAG_i_recipe_img = "i_recipe_img";
        String TAG_i_recipe_rating = "i_recipe_rating";


        randomDTO dto = null;

        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);
            int j = 0;

            for(int i=0;i<jsonArray.length();i++){

                JSONObject item = jsonArray.getJSONObject(i);

                dto = new randomDTO();
                dto.setI_recipe_code(item.getString(TAG_i_recipe_code));
                dto.setI_recipe_title(item.getString(TAG_i_recipe_title));
                dto.setI_recipe_name(item.getString(TAG_i_recipe_name));
                dto.setI_recipe_part_code(item.getString(TAG_i_recipe_part_code));
                dto.setI_recipe_part_name(item.getString(TAG_i_recipe_part_name));
                dto.setI_recipe_food_code(item.getString(TAG_i_recipe_food_code));
                dto.setI_recipe_food_name(item.getString(TAG_i_recipe_food_name));
                dto.setI_recipe_cook_time(item.getString(TAG_i_recipe_cook_time));
                dto.setI_recipe_kcal(item.getString(TAG_i_recipe_kcal));
                dto.setI_recipe_dose(item.getString(TAG_i_recipe_dose));
                dto.setI_recipe_difficulty(item.getString(TAG_i_recipe_difficulty));
                dto.setI_recipe_material(item.getString(TAG_i_recipe_material));
                dto.setI_recipe_price(item.getString(TAG_i_recipe_price));
                dto.setI_recipe_rating(item.getInt(TAG_i_recipe_rating));
                dto.setI_recipe_img(item.getString(TAG_i_recipe_img));




            dao.addItem(dto);



            }


            gridview.setAdapter(dao);
        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }

    }

}