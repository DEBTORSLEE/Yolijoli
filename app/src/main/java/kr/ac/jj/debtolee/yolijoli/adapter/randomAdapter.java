package kr.ac.jj.debtolee.yolijoli.adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import kr.ac.jj.debtolee.yolijoli.DrawUrlImageTask;
import kr.ac.jj.debtolee.yolijoli.R;
import kr.ac.jj.debtolee.yolijoli.dto.randomDTO;

public class randomAdapter extends BaseAdapter {
    public String imgUrl1 ="";
    public String imgUrl2 ="";
    Bitmap bitmap;
    Bitmap bitmap2;

    private ArrayList<randomDTO> listCustom = new ArrayList<>();


    // ListView에 보여질 Item 수
    @Override
    public int getCount() {
        return listCustom.size();
    }

    // 하나의 Item(ImageView 1, TextView 2)
    @Override
    public Object getItem(int position) {
        return listCustom.get(position);
    }

    // Item의 id : Item을 구별하기 위한 것으로 position 사용
    @Override
    public long getItemId(int position) {
        return position;
    }

    // 실제로 Item이 보여지는 부분
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CustomViewHolder holder = new CustomViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.random_menu_item, null, false);
            //layout.item_list_data.xml에 있는 텍스트뷰 변수로 연결

            holder.img1 = (ImageView) convertView.findViewById(R.id.randommenu_item_img1);
            holder.rb1 = (RatingBar) convertView.findViewById(R.id.randommenu_item_ratingBar1);
            holder.title1 = (TextView)convertView.findViewById(R.id.randommenu_item_title1);
            holder.content1 = (TextView)convertView.findViewById(R.id.randommenu_item_content1);

            convertView.setTag(holder);
        } else {
            holder = (CustomViewHolder) convertView.getTag();
        } //PersonalData 생성자 불러 변수에 추가
        final randomDTO dto = listCustom.get(position);
        imgUrl1 = dto.getI_recipe_img();
        //이미지 url 불러와 이미지 적용
        new DrawUrlImageTask((ImageView) holder.img1)
                .execute(imgUrl1);


        holder.rb1.setRating(dto.getI_recipe_rating());

        holder.title1.setText(dto.getI_recipe_name());

        holder.content1.setText(dto.getI_recipe_title());
        return convertView;
    }

    class CustomViewHolder {
        //텍스트뷰 변수선언
       ImageView img1;

        RatingBar rb1;

        TextView title1;

        TextView content1;

    }

    // MainActivity에서 Adapter에있는 ArrayList에 data를 추가시켜주는 함수
    public void addItem(randomDTO dto) {
        listCustom.add(dto);
    }
}