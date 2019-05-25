package io.techministry;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.text.TextUtils;

public class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setupBaseActionbar(Toolbar toolBar, String title, boolean back){
        if (toolBar != null) {
            setSupportActionBar(toolBar);
            if(back) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
            if(!TextUtils.isEmpty(title)){
                getSupportActionBar().setTitle(title);
            }
        }
    }
}
