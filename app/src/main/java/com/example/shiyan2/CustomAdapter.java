package com.example.shiyan2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private final List<ImageItem> imageItems;
    private final Context context;

    // 构造函数接收可变List（建议使用ArrayList）
    public CustomAdapter(Context context, List<ImageItem> imageItems) {
        this.context = context;
        this.imageItems = imageItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_image, parent, false);
        return new ViewHolder(view); // 需要传递 view 参数
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ImageItem item = imageItems.get(position);

        // ==== 核心绑定逻辑 ====
        // 1. 设置动态图片高度
        ViewGroup.LayoutParams imageParams = holder.imageView.getLayoutParams();
        imageParams.height = item.getImageHeight();
        holder.imageView.setLayoutParams(imageParams);

        // 2. 加载图片和文字内容
        holder.imageView.setImageResource(item.getImageResId());
        holder.tvTitle.setText(item.getTitle());
        holder.tvTime.setText(item.getFormattedTime());

        // ==== 新增长按删除功能 ====
        holder.imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int currentPosition = holder.getAdapterPosition();
                if (currentPosition != RecyclerView.NO_POSITION) {
                    showDeleteDialog(currentPosition);
                }
                return true; // 消费长按事件
            }
        });

        // 3. 收藏按钮交互逻辑
        holder.ivFavorite.setOnClickListener(v -> {
            // 更新收藏状态
            boolean newState = !item.isFavorite();
            item.setFavorite(newState);

            // 更新图标颜色
            int colorRes = newState ? R.color.red : R.color.gray;
            holder.ivFavorite.setColorFilter(
                    context.getResources().getColor(colorRes, context.getTheme())
            );

            // 显示反馈提示
            String msg = newState ? "已点赞：" : "取消点赞：";
            Toast.makeText(context, msg + item.getTitle(), Toast.LENGTH_SHORT).show();
        });

        holder.itemView.setOnClickListener(v -> {
            if (itemClickListener != null) {
                int currentPosition = holder.getAdapterPosition();
                if (currentPosition != RecyclerView.NO_POSITION) {
                    itemClickListener.onItemClick(currentPosition);
                }
            }
        });
    }

    // ==== 删除确认对话框 ====
    private void showDeleteDialog(final int position) {
        new AlertDialog.Builder(context)
                .setTitle("删除确认")
                .setMessage("确定要删除这个项目吗？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteItem(position);
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }

    // ==== 删除项目方法 ====
    private void deleteItem(int position) {
        if (position >= 0 && position < imageItems.size()) {
            imageItems.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, imageItems.size());
        }
    }

    @Override
    public int getItemCount() {
        return imageItems.size();
    }

    // ==== ViewHolder 内部类 ====
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvTitle;
        TextView tvTime;
        ImageView ivFavorite;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvTime = itemView.findViewById(R.id.tvTime);
            ivFavorite = itemView.findViewById(R.id.ivFavorite);

            // 初始化收藏图标颜色
            ivFavorite.setColorFilter(
                    itemView.getContext().getResources().getColor(R.color.gray, itemView.getContext().getTheme())
            );
        }
    }

    // 在 CustomAdapter 类中添加以下代码
// ==== 新增代码：单击事件接口 ====
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private OnItemClickListener itemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }
}