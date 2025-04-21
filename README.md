# Android 实验项目 - 动态图片瀑布流展示

## 📚 项目知识点概览

### 1. **RecyclerView 与 Adapter**
   - **瀑布流布局**：使用 `StaggeredGridLayoutManager` 实现 2 列垂直布局。
   - **自定义 Adapter**：继承 `RecyclerView.Adapter`，实现数据绑定与视图复用。
   - **ViewHolder 模式**：优化列表项视图查找效率，分离数据与 UI 逻辑。

### 2. **事件处理**
   - **单击事件**：通过 `OnItemClickListener` 接口实现整项点击跳转详情页。
   - **长按事件**：绑定到图片区域，触发删除确认对话框。
   - **自定义交互**：收藏按钮状态切换（点赞/取消）及颜色反馈。

### 3. **页面跳转与数据传递**
   - **Intent 通信**：使用 `Intent` 在 `MainActivity` 和 `DetailActivity` 间传递图片资源 ID 和标题。
   - **Bundle 数据解析**：在详情页通过 `getIntent().getExtra()` 接收并展示数据。

### 4. **UI 组件与布局**
   - **动态高度图片**：通过代码动态设置 `ImageView` 的高度。
   - **XML 布局设计**：
     - `activity_main.xml`：全屏 `RecyclerView` 容器。
     - `item_image.xml`：列表项布局（图片 + 标题 + 时间 + 收藏按钮）。
     - `activity_detail.xml`：详情页布局（大图 + 标题）。

### 5. **对话框与状态管理**
   - **AlertDialog**：长按图片弹出删除确认对话框。
   - **数据更新**：通过 `notifyItemRemoved()` 和 `notifyItemRangeChanged()` 实现列表项删除后 UI 刷新。

---

## 🛠️ 实现功能清单

### 核心功能
1. **瀑布流图片展示**
   - 支持动态高度的图片项，2 列垂直布局。
   - 图片、标题、时间信息完整展示。

2. **交互操作**
   - **单击列表项**：跳转至详情页，展示大图及标题。
   - **长按图片**：弹出删除确认对话框，支持数据删除与 UI 同步更新。
   - **收藏按钮**：点击切换点赞状态，图标颜色实时反馈（红/灰）。

3. **数据传递**
   - 通过 `Intent` 将图片资源 ID 和标题传递至详情页。
   - 详情页接收并展示对应数据。

---

## 📂 项目结构

### 关键代码文件
- **`MainActivity.java`**  
  主界面，初始化 `RecyclerView`，处理列表项点击事件。
- **`CustomAdapter.java`**  
  自定义 Adapter，实现数据绑定、点击/长按事件处理。
- **`DetailActivity.java`**  
  详情页，展示大图及标题信息。
- **`ImageItem.java`**  
  数据模型类，封装图片资源 ID、标题、时间、收藏状态。

### 布局文件
- **`activity_main.xml`**：主页面布局
- **`item_image.xml`**：列表项布局
- **`activity_detail.xml`**：详情页布局

---

## ⚠️ 注意事项

1. **资源准备**
   - 确保 `res/drawable` 中存在测试图片资源（如 `image1`, `image2`）。
   - 颜色资源需在 `res/values/colors.xml` 中定义 `red` 和 `gray`。

2. **依赖配置**
   ```gradle
   dependencies {
       implementation 'androidx.recyclerview:recyclerview:1.3.2'
       implementation 'androidx.appcompat:appcompat:1.6.1'
   }

3. **常见问题**
   - **点击事件不触发**：检查子视图是否设置了 `android:clickable="false"`。
   - **图片不显示**：验证资源 ID 是否匹配，或替换为有效图片。

---

## 🚀 扩展建议

1. **网络请求**：使用 Retrofit 或 Volley 从服务器加载图片数据。
2. **本地存储**：集成 Room 数据库保存收藏状态和图片信息。
3. **动画效果**：添加列表项入场动画或详情页转场动画。

---

**项目来源**：Android本科课程实验2  
**项目作者**：Dayrk0   
**最后更新**：2025年4月  
**License**：MIT
