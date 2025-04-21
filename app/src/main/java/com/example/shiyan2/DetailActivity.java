package com.example.shiyan2;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // 从Intent中获取数据
        int imageResId = getIntent().getIntExtra("image_res_id", 0);
        String title = getIntent().getStringExtra("title");
//        String time = getIntent().getStringExtra("time");

        // 绑定布局控件
        ImageView detailImage = findViewById(R.id.detail_image);
        TextView detailTitle = findViewById(R.id.detail_title);
//        TextView detailTime = findViewById(R.id.detail_time);

        // 设置数据
        detailImage.setImageResource(imageResId);
        detailTitle.setText(title);
//        detailTime.setText(time);
    }
}