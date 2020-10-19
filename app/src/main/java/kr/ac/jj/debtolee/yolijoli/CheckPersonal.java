package kr.ac.jj.debtolee.yolijoli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import kr.ac.jj.debtolee.yolijoli.user.RegisterActivity;

public class CheckPersonal extends AppCompatActivity implements View.OnClickListener {
    CheckBox[] checkBoxes;
   final int[] r = {R.id.checkAll,R.id.check1,R.id.check2,R.id.check3,R.id.check4,R.id.check5
            ,R.id.check6,R.id.check7,R.id.check8};
   Button btnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_personal);

        btnNext = (Button)findViewById(R.id.btnNext);
        btnNext.setOnClickListener(this);
        checkBoxes = new CheckBox[9];
        for(int i = 0; i< r.length;i++)
            checkBoxes[i] = (CheckBox)findViewById(r[i]);

        checkBoxes[0].setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.checkAll:
                if(checkBoxes[0].isChecked()){
                    for(int i=1;i<r.length;i++)
                        checkBoxes[i].setChecked(true);
                }else{
                    for(int i=1;i<r.length  ;i++)
                        checkBoxes[i].setChecked(false);
                }
                break;
            case R.id.btnNext:
                int tmp=0;
                for(int i = 1;i<r.length;i++){
                    if(checkBoxes[i].isChecked()==true)
                        tmp++;
                }
                if(tmp==8){
                    Intent intent = new Intent(CheckPersonal.this, RegisterActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    tmp=0;
                    Toast.makeText(this, "약관에 동의하여주세요", Toast.LENGTH_SHORT).show();
                }



        }


    }
}