package io.techministry;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;

public class BaseActivity extends AppCompatActivity {

//    TODO: Should this class be added to the Manifest or Not?
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
