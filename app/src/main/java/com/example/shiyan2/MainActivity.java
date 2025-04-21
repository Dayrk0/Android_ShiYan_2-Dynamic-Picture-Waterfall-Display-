package com.example.shiyan2;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements CustomAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private CustomAdapter adapter;
    private List<ImageItem> imageItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化 RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        // 设置瀑布流布局（2列）
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(
                2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        // 准备测试数据
        imageItems = new ArrayList<>();
        imageItems.add(new ImageItem(R.drawable.image1, "Education 1")); // 假设高度为400dp
        imageItems.add(new ImageItem(R.drawable.image2, "Education 2"));
        imageItems.add(new ImageItem(R.drawable.image3, "Education 3"));
        imageItems.add(new ImageItem(R.drawable.image4, "Education 4"));

        // 设置适配器并绑定点击监听
        adapter = new CustomAdapter(this, imageItems);
        adapter.setOnItemClickListener(this); // 关键：设置监听器
        recyclerView.setAdapter(adapter);
    }

    // ==== 点击事件回调 ====
    @Override
    public void onItemClick(int position) {
        ImageItem item = imageItems.get(position);
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("image_res_id", item.getImageResId());
        intent.putExtra("title", item.getTitle());
        startActivity(intent);
    }
}