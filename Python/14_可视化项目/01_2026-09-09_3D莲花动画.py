"""
简介：使用 NumPy 和 Matplotlib 绘制旋转的 3D 莲花动画。
功能：
- 参数化生成多层、多片弯曲花瓣网格。
- 绘制花蕊、雄蕊和花瓣内闪烁微粒。
- 通过 FuncAnimation 让视角旋转并更新粒子大小与颜色。
用途：综合练习数组运算、三维曲面、颜色插值和动画帧更新。
最后修改：2026-09-09
"""
# 文件用途：本文件围绕“3D莲花动画”展开。
# 核心知识点：列表与字典、函数、循环、分支判断、可视化。
# 基本流程：根据代码中的输入或初始数据，按条件、循环和函数调用完成处理，再输出结果。
# 输入/输出：以本文件实际使用的参数、数据结构或其他操作为准；没有显式输入输出时，不代表没有计算或状态变化。
# 阅读提醒：注释用于解释当前代码的实际行为；若示例输入范围有限，扩展使用前应自行验证边界情况。
# 模块依赖：numpy as np, matplotlib.pyplot as plt, mpl_toolkits.mplot3d, matplotlib.animation。
import numpy as np  # 导入 numpy 库，用于数组和数学运算
import matplotlib.pyplot as plt  # 导入 matplotlib 的绘图模块，用于画 2D/3D 图形
from mpl_toolkits.mplot3d import Axes3D  # 导入 3D 坐标轴工具，支持三维绘图
from matplotlib.animation import FuncAnimation  # 导入动画函数类，用来做帧动画
GLOBAL_SCALE = 0.6  # 定义全局缩放比例，控制整朵花的大小
np.random.seed(42)  # 设置随机数种子，让每次运行结果可重复
def rotation_matrix_from_z_to(n):  # 定义函数：把 z 轴旋转到向量 n 方向的旋转矩阵
    n = n / np.linalg.norm(n)  # 把向量 n 单位化（长度变为 1）
    z = np.array([0.0, 0.0, 1.0])  # 定义原始 z 轴单位向量
    v = np.cross(z, n)  # 计算 z 与 n 的叉积，得到转轴方向 v
    c = np.dot(z, n)  # 计算 z 与 n 的点积，得到夹角余弦值 c
    s = np.linalg.norm(v)  # 计算 v 的长度，即夹角正弦值 s
    if s < 1e-6:  # 如果 v 长度几乎为 0，说明 n 与 z 平行（特殊情形）
        if c > 0:  # 且余弦为正，说明 n 与 z 同向
            return np.eye(3)  # 返回单位矩阵（无需旋转）
        else:  # 否则 n 与 z 反向
            return np.array([[1, 0, 0], [0, -1, 0], [0, 0, -1]])  # 返回绕 z 轴旋转 180 度的矩阵
    vx = np.array([[0, -v[2], v[1]], [v[2], 0, -v[0]], [-v[1], v[0], 0]])  # 构造叉积对应的反对称矩阵
    R = np.eye(3) + vx + vx @ vx * ((1 - c) / (s * s))  # 用罗德里格斯公式计算旋转矩阵 R
    return R  # 返回得到的旋转矩阵
# inward 参数控制向内卷 ---
def create_petal_mesh(u_start, u_end, num_points, scale, tip_strength, base_strength, openness, inward):  # 定义函数：生成一片花瓣网格
    u = np.linspace(u_start, u_end, 100)  # 在 u 方向均匀取 100 个点（沿花瓣长度方向）
    v = np.linspace(-np.pi/2, np.pi/2, 30)  # 在 v 方向均匀取 30 个点（包裹花瓣宽度方向）
    U, V = np.meshgrid(u, v)  # 由 u、v 生成二维网格坐标数组 U、V
    # X轴公式：加入向内卷曲项 inward * (U**2) * scale
    # 根部(U=0)不动，越靠近尖端(U=1)向内收缩得越厉害
    X = (U * 1.6 * scale + 0.03 - inward * (U ** 2) * scale) * GLOBAL_SCALE  # 计算花瓣的 X 坐标（含向内卷曲修正）
    Y = np.sin(np.pi * U) ** 0.8 * 0.42 * np.sin(V) * scale * GLOBAL_SCALE  # 计算花瓣的 Y 坐标（左右包裹形状）
    base_curve = -np.sin(np.pi * U) * base_strength * np.cos(V)  # 计算根部弯曲量
    lateral_curl = np.sin(np.pi * U) * 0.2 * (np.abs(np.sin(V)) ** 3)  # 计算侧面卷曲量
    tip_curl = (U ** 2) * tip_strength  # 计算尖端上卷量，随 u 增大而增大
    z_slope = openness * U * 1.6 * scale  # 计算由开放度带来的 Z 轴倾斜量
    Z = (z_slope + base_curve + lateral_curl + tip_curl) * scale * GLOBAL_SCALE  # 综合各部分得到 Z 坐标
    return X, Y, Z, U, V  # 返回花瓣网格坐标和参数网格
fig = plt.figure(figsize=(5, 6), dpi=100, facecolor='black')  # 创建画布，尺寸 5×6 英寸，黑色背景
ax = fig.add_subplot(111, projection='3d', facecolor='black')  # 添加一个 3D 子图，黑色背景
ax.set_xlim(-0.7, 0.7)  # 设置 X 轴显示范围
ax.set_ylim(-0.7, 0.7)  # 设置 Y 轴显示范围
ax.set_zlim(-0.5, 1.3)  # 设置 Z 轴显示范围
ax.set_axis_off()  # 关闭坐标轴显示
ax.grid(False)  # 关闭网格线
# --- 四层参数 inward 值 (外层0，内层最大) ---
layers = [  # 定义四层花瓣的参数列表
    {'petals': 6, 'scale': 1.0,  'tip': 0.2, 'base': 0.1, 'openness': 0.0, 'alpha': 0.15, 'tip_alpha': 0.65, 'inward': 0.0},  # 最外层不卷
    {'petals': 6, 'scale': 0.75, 'tip': 0.5, 'base': 0.2, 'openness': 0.4, 'alpha': 0.25, 'tip_alpha': 0.50, 'inward': 0.1},  # 次外层微卷
    {'petals': 5, 'scale': 0.55, 'tip': 0.9, 'base': 0.3, 'openness': 0.9, 'alpha': 0.35, 'tip_alpha': 0.40, 'inward': 0.2},  # 内层明显卷
    {'petals': 4, 'scale': 0.35, 'tip': 1.4, 'base': 0.4, 'openness': 1.4, 'alpha': 0.45, 'tip_alpha': 0.30, 'inward': 0.35}  # 最内层卷得最深
]
P_surf = np.array([0.0, 0.0, 0.0])  # 定义花瓣表面的世界坐标原点
n = np.array([0.0, 0.0, 1.0])  # 定义朝向向量，默认让花瓣朝上（z 轴方向）
R = rotation_matrix_from_z_to(n)  # 计算从 z 轴到朝向向量 n 的旋转矩阵
all_p_x, all_p_y, all_p_z = [], [], []  # 三个空列表，分别收集所有微尘的 X、Y、Z 坐标
base_sizes, phases = [], []  # 两个空列表，分别记录微尘基础大小和闪动相位
for layer in layers:  # 遍历每一层花瓣参数
    num_petals = layer['petals']  # 取出该层花瓣数量
    scale = layer['scale']  # 取出该层缩放比例
    alpha = layer['alpha']  # 取出该层根部颜色透明度
    tip_alpha = layer['tip_alpha']  # 取出该层尖端颜色透明度
    base_strength = layer['base']  # 取出根部弯曲强度
    tip_strength = layer['tip']  # 取出尖端上卷强度
    openness = layer['openness']  # 取出开放度
    inward = layer['inward']  # 取出向内卷程度
    base_color = np.array([1.0, 0.75, 0.85, alpha])  # 定义根部颜色（粉白）和透明度
    tip_color = np.array([0.85, 0.05, 0.1, tip_alpha])  # 定义尖端颜色（红）和透明度
    # 传入 inward 参数
    X_petal, Y_petal, Z_petal, U, V = create_petal_mesh(0, 1, 100, scale, tip_strength, base_strength, openness, inward)  # 生成当前层的一片花瓣网格
    t = (U - 0.6) / 0.4  # 计算归一化参数 t（0.6 到 1.0 对应 0 到 1）
    t = np.clip(t, 0, 1)  # 把 t 限制在 0 到 1 范围内
    t = t ** 1.5  # 对 t 做幂运算，让颜色过渡更平滑
    face_colors = np.zeros(U.shape + (4,))  # 创建与网格同形状的 RGBA 颜色数组
    for i in range(4):  # 遍历 R、G、B、A 四个通道
        face_colors[:, :, i] = base_color[i] * (1 - t) + tip_color[i] * t  # 按 t 在根部色与尖端色之间插值
    for i in range(num_petals):  # 遍历该层的每一片花瓣
        angle = i * (2 * np.pi / num_petals)  # 计算当前花瓣绕中心旋转的角度
        X_rot = X_petal * np.cos(angle) - Y_petal * np.sin(angle)  # 绕 Z 轴旋转后的 X 坐标
        Y_rot = X_petal * np.sin(angle) + Y_petal * np.cos(angle)  # 绕 Z 轴旋转后的 Y 坐标
        Z_rot = Z_petal  # Z 坐标旋转后保持不变
        points = np.stack([X_rot, Y_rot, Z_rot], axis=-1).reshape(-1, 3)  # 将坐标拼成点集并展平为 N 行 3 列
        points_world = points @ R.T + P_surf  # 做旋转并平移到世界坐标系
        X_final = points_world[:, 0].reshape(X_petal.shape)  # 取出世界坐标 X 并恢复为网格形状
        Y_final = points_world[:, 1].reshape(X_petal.shape)  # 取出世界坐标 Y 并恢复为网格形状
        Z_final = points_world[:, 2].reshape(X_petal.shape)  # 取出世界坐标 Z 并恢复为网格形状
        ax.plot_surface(X_final, Y_final, Z_final,  # 绘制当前花瓣的曲面
                        facecolors=face_colors,  # 传入逐点颜色
                        rstride=2, cstride=2,  # 设置行、列采样间隔为 2
                        edgecolor=(1.0, 0.6, 0.8, 0.1),  # 设置网格线颜色（很淡的粉色）
                        linewidth=0.1,  # 设置网格线宽度
                        antialiased=True, shade=False)  # 开启抗锯齿，关闭明暗着色
        v_indices = range(0, 30, 4)  # 取 0 到 29 之间每隔 4 个的 v 索引，作为要画线的行
        for v_idx in v_indices:  # 遍历这些 v 索引
            x_line = X_final[v_idx, :]  # 取出该行对应的 X 坐标序列
            y_line = Y_final[v_idx, :]  # 取出该行对应的 Y 坐标序列
            z_line = Z_final[v_idx, :]  # 取出该行对应的 Z 坐标序列
            u_line = U[v_idx, :]  # 取出该行对应的 u 参数序列
            ax.plot(x_line, y_line, z_line, color=(1.0, 0.7, 0.85, 0.9), linewidth=0.1)  # 画一条贯穿花瓣的淡粉色曲线
            tip_mask = u_line > 0.7  # 找出 u 大于 0.7（接近尖端）的位置
            ax.plot(x_line[tip_mask], y_line[tip_mask], z_line[tip_mask],  # 在尖端部分画第一条强调线
                    color=(1.0, 0.2, 0.6, 0.6), linewidth=0.4)  # 用玫红颜色、较粗线宽
            ax.plot(x_line[tip_mask], y_line[tip_mask], z_line[tip_mask],  # 在尖端部分画第二条高光线
                    color=(1.0, 0.9, 1.0, 0.9), linewidth=0.15)  # 用浅粉色、很细的线宽
        num_particles = 4  # 每片花瓣生成 4 颗微尘粒子
        u_p = np.random.uniform(0.6, 1.0, num_particles)  # 随机生成粒子的 u 参数（靠近尖端）
        v_p = np.random.uniform(-np.pi/2, np.pi/2, num_particles)  # 随机生成粒子的 v 参数（包裹方向）
        # 匹配向内卷的X轴公式
        pX = (u_p * 1.6 * scale + 0.03 - inward * (u_p ** 2) * scale) * GLOBAL_SCALE  # 计算粒子 X 坐标（与花瓣 X 公式一致）
        pY = np.sin(np.pi * u_p) ** 0.8 * 0.42 * np.sin(v_p) * scale * GLOBAL_SCALE  # 计算粒子 Y 坐标
        p_base_curve = -np.sin(np.pi * u_p) * base_strength * np.cos(v_p)  # 计算粒子所在位置的根部弯曲量
        p_lateral_curl = np.sin(np.pi * u_p) * 0.2 * (np.abs(np.sin(v_p)) ** 3)  # 计算粒子处侧面卷曲量
        p_tip_curl = (u_p ** 2) * tip_strength  # 计算粒子处尖端上卷量
        p_z_slope = openness * u_p * 1.6 * scale  # 计算粒子处倾斜量
        pZ = (p_z_slope + p_base_curve + p_lateral_curl + p_tip_curl) * scale * GLOBAL_SCALE  # 综合得到粒子 Z 坐标
        pX_rot = pX * np.cos(angle) - pY * np.sin(angle)  # 粒子绕 Z 轴旋转后的 X 坐标
        pY_rot = pX * np.sin(angle) + pY * np.cos(angle)  # 粒子绕 Z 轴旋转后的 Y 坐标
        p_points = np.stack([pX_rot, pY_rot, pZ], axis=-1)  # 把粒子坐标拼成点集
        p_world = p_points @ R.T + P_surf  # 旋转并平移到世界坐标系
        all_p_x.extend(p_world[:, 0])  # 收集粒子世界坐标的 X
        all_p_y.extend(p_world[:, 1])  # 收集粒子世界坐标的 Y
        all_p_z.extend(p_world[:, 2])  # 收集粒子世界坐标的 Z
        for _ in range(num_particles):  # 遍历每个粒子
            base_sizes.append(np.random.uniform(0.05, 0.8))  # 随机生成粒子基础大小并记录
            phases.append(np.random.uniform(0, 2 * np.pi))  # 随机生成闪动相位并记录
all_p_x = np.array(all_p_x)  # 把粒子 X 坐标列表转成数组
all_p_y = np.array(all_p_y)  # 把粒子 Y 坐标列表转成数组
all_p_z = np.array(all_p_z)  # 把粒子 Z 坐标列表转成数组
base_sizes = np.array(base_sizes)  # 把粒子大小列表转成数组
phases = np.array(phases)  # 把相位列表转成数组
# 花瓣内微尘闪烁
scat = ax.scatter(all_p_x, all_p_y, all_p_z, s=base_sizes, c='white', alpha=0.8)  # 在坐标系里画出所有微尘粒子
# --- 花蕊 ---
start_center_local = np.array([0.0, 0.0, 0.0])  # 定义花蕊中心线的本地起点
end_center_local = np.array([0.0, 0.0, 0.35 * GLOBAL_SCALE])  # 定义花蕊中心线的本地终点
start_center = start_center_local @ R.T + P_surf  # 把起点变换到世界坐标
end_center = end_center_local @ R.T + P_surf  # 把终点变换到世界坐标
ax.plot([start_center[0], end_center[0]],  # 绘制花蕊中心线的 X 起点和终点
        [start_center[1], end_center[1]],  # 对应 Y 起点和终点
        [start_center[2], end_center[2]],  # 对应 Z 起点和终点
        color='#FFD700', linewidth=1.5)  # 用金色绘制，线宽 1.5
STAMEN_HEIGHT = 0.35 * GLOBAL_SCALE  # 定义雄蕊的总体高度
BASE_RADIUS = 0.05 * GLOBAL_SCALE  # 定义雄蕊根部的基础半径
num_stamens = 40  # 定义雄蕊的数量为 40 根
angles = np.linspace(0, 2 * np.pi, num_stamens, endpoint=False)  # 均匀生成 40 个方位角，不包含终点
for j in range(num_stamens):  # 遍历每一根雄蕊
    theta = angles[j]  # 取出当前雄蕊的方位角
    extent = np.random.uniform(0.05, 0.15) * GLOBAL_SCALE  # 随机生成这根雄蕊向外伸展的长度
    t_points = np.linspace(0, 1, 50)  # 沿雄蕊长度取 50 个采样点
    x_local = (BASE_RADIUS + extent * t_points) * np.cos(theta)  # 雄蕊本地 X 坐标（随长度向外扩展）
    y_local = (BASE_RADIUS + extent * t_points) * np.sin(theta)  # 雄蕊本地 Y 坐标（随长度向外扩展）
    z_local = STAMEN_HEIGHT * (t_points ** 2)  # 雄蕊本地 Z 坐标（越往上越高，呈平方增长）
    pts_local = np.stack([x_local, y_local, z_local], axis=-1)  # 把雄蕊本地坐标拼成点集
    pts_world = pts_local @ R.T + P_surf  # 变换到世界坐标系
    ax.plot(pts_world[:, 0], pts_world[:, 1], pts_world[:, 2], color='#FFD700', linewidth=1.0)  # 画这根金色的雄蕊
    ax.scatter(pts_world[-1, 0], pts_world[-1, 1], pts_world[-1, 2], color='#CC8800', s=2)  # 在雄蕊顶端画一个小圆点
def update(frame):  # 定义动画更新函数，frame 是当前帧号
    azim = 60 + frame * 4.0  # 让观测角度随帧号递增，实现旋转
    ax.view_init(elev=25, azim=azim)  # 设置 3D 视图的仰角和方位角
    sizes = base_sizes * (1 + 0.5 * np.sin(frame * 0.2 + phases))  # 让粒子大小随时间上下波动
    scat.set_sizes(sizes)  # 更新粒子的显示尺寸
    colors = np.zeros((len(sizes), 4))  # 创建新的粒子颜色数组
    colors[:, 0] = 1.0  # 固定红色通道为 1.0
    colors[:, 1] = 0.7 + 0.3 * np.abs(np.sin(frame * 0.3 + phases))  # 让绿色通道随时间变化，产生闪烁
    colors[:, 2] = 0.85  # 固定蓝色通道为 0.85
    colors[:, 3] = 1.0  # 固定透明度为 1.0
    scat.set_facecolors(colors)  # 更新粒子的颜色
    return scat,  # 返回需要更新的对象
ani = FuncAnimation(fig, update, frames=200, interval=50, blit=False)  # 创建 200 帧、每帧 50 毫秒的动画
plt.show()  # 显示绘制好的图形窗口
