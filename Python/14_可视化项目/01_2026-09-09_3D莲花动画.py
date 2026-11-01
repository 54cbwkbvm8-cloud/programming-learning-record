"""
简介：使用 NumPy 和 Matplotlib 绘制旋转的 3D 莲花动画。
功能：
- 参数化生成多层、多片弯曲花瓣网格。
- 绘制花蕊、雄蕊和花瓣内闪烁微粒。
- 通过 FuncAnimation 让视角旋转并更新粒子大小与颜色。
用途：综合练习数组运算、三维曲面、颜色插值和动画帧更新。
最后修改：2026-09-09
"""
import numpy as np
import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D
from matplotlib.animation import FuncAnimation
GLOBAL_SCALE = 0.6
np.random.seed(42)
def rotation_matrix_from_z_to(n):
    n = n / np.linalg.norm(n)
    z = np.array([0.0, 0.0, 1.0])
    v = np.cross(z, n)
    c = np.dot(z, n)
    s = np.linalg.norm(v)
    if s < 1e-6:
        if c > 0:
            return np.eye(3)
        else:
            return np.array([[1, 0, 0], [0, -1, 0], [0, 0, -1]])
    vx = np.array([[0, -v[2], v[1]], [v[2], 0, -v[0]], [-v[1], v[0], 0]])
    R = np.eye(3) + vx + vx @ vx * ((1 - c) / (s * s))
    return R
def create_petal_mesh(u_start, u_end, num_points, scale, tip_strength, base_strength, openness, inward):
    u = np.linspace(u_start, u_end, 100)
    v = np.linspace(-np.pi/2, np.pi/2, 30)
    U, V = np.meshgrid(u, v)
    X = (U * 1.6 * scale + 0.03 - inward * (U ** 2) * scale) * GLOBAL_SCALE
    Y = np.sin(np.pi * U) ** 0.8 * 0.42 * np.sin(V) * scale * GLOBAL_SCALE
    base_curve = -np.sin(np.pi * U) * base_strength * np.cos(V)
    lateral_curl = np.sin(np.pi * U) * 0.2 * (np.abs(np.sin(V)) ** 3)
    tip_curl = (U ** 2) * tip_strength
    z_slope = openness * U * 1.6 * scale
    Z = (z_slope + base_curve + lateral_curl + tip_curl) * scale * GLOBAL_SCALE
    return X, Y, Z, U, V
fig = plt.figure(figsize=(5, 6), dpi=100, facecolor='black')
ax = fig.add_subplot(111, projection='3d', facecolor='black')
ax.set_xlim(-0.7, 0.7)
ax.set_ylim(-0.7, 0.7)
ax.set_zlim(-0.5, 1.3)
ax.set_axis_off()
ax.grid(False)
layers = [
    {'petals': 6, 'scale': 1.0,  'tip': 0.2, 'base': 0.1, 'openness': 0.0, 'alpha': 0.15, 'tip_alpha': 0.65, 'inward': 0.0},
    {'petals': 6, 'scale': 0.75, 'tip': 0.5, 'base': 0.2, 'openness': 0.4, 'alpha': 0.25, 'tip_alpha': 0.50, 'inward': 0.1},
    {'petals': 5, 'scale': 0.55, 'tip': 0.9, 'base': 0.3, 'openness': 0.9, 'alpha': 0.35, 'tip_alpha': 0.40, 'inward': 0.2},
    {'petals': 4, 'scale': 0.35, 'tip': 1.4, 'base': 0.4, 'openness': 1.4, 'alpha': 0.45, 'tip_alpha': 0.30, 'inward': 0.35}
]
P_surf = np.array([0.0, 0.0, 0.0])
n = np.array([0.0, 0.0, 1.0])
R = rotation_matrix_from_z_to(n)
all_p_x, all_p_y, all_p_z = [], [], []
base_sizes, phases = [], []
for layer in layers:
    num_petals = layer['petals']
    scale = layer['scale']
    alpha = layer['alpha']
    tip_alpha = layer['tip_alpha']
    base_strength = layer['base']
    tip_strength = layer['tip']
    openness = layer['openness']
    inward = layer['inward']
    base_color = np.array([1.0, 0.75, 0.85, alpha])
    tip_color = np.array([0.85, 0.05, 0.1, tip_alpha])
    X_petal, Y_petal, Z_petal, U, V = create_petal_mesh(0, 1, 100, scale, tip_strength, base_strength, openness, inward)
    t = (U - 0.6) / 0.4
    t = np.clip(t, 0, 1)
    t = t ** 1.5
    face_colors = np.zeros(U.shape + (4,))
    for i in range(4):
        face_colors[:, :, i] = base_color[i] * (1 - t) + tip_color[i] * t
    for i in range(num_petals):
        angle = i * (2 * np.pi / num_petals)
        X_rot = X_petal * np.cos(angle) - Y_petal * np.sin(angle)
        Y_rot = X_petal * np.sin(angle) + Y_petal * np.cos(angle)
        Z_rot = Z_petal
        points = np.stack([X_rot, Y_rot, Z_rot], axis=-1).reshape(-1, 3)
        points_world = points @ R.T + P_surf
        X_final = points_world[:, 0].reshape(X_petal.shape)
        Y_final = points_world[:, 1].reshape(X_petal.shape)
        Z_final = points_world[:, 2].reshape(X_petal.shape)
        ax.plot_surface(X_final, Y_final, Z_final,
                        facecolors=face_colors,
                        rstride=2, cstride=2,
                        edgecolor=(1.0, 0.6, 0.8, 0.1),
                        linewidth=0.1,
                        antialiased=True, shade=False)
        v_indices = range(0, 30, 4)
        for v_idx in v_indices:
            x_line = X_final[v_idx, :]
            y_line = Y_final[v_idx, :]
            z_line = Z_final[v_idx, :]
            u_line = U[v_idx, :]
            ax.plot(x_line, y_line, z_line, color=(1.0, 0.7, 0.85, 0.9), linewidth=0.1)
            tip_mask = u_line > 0.7
            ax.plot(x_line[tip_mask], y_line[tip_mask], z_line[tip_mask],
                    color=(1.0, 0.2, 0.6, 0.6), linewidth=0.4)
            ax.plot(x_line[tip_mask], y_line[tip_mask], z_line[tip_mask],
                    color=(1.0, 0.9, 1.0, 0.9), linewidth=0.15)
        num_particles = 4
        u_p = np.random.uniform(0.6, 1.0, num_particles)
        v_p = np.random.uniform(-np.pi/2, np.pi/2, num_particles)
        pX = (u_p * 1.6 * scale + 0.03 - inward * (u_p ** 2) * scale) * GLOBAL_SCALE
        pY = np.sin(np.pi * u_p) ** 0.8 * 0.42 * np.sin(v_p) * scale * GLOBAL_SCALE
        p_base_curve = -np.sin(np.pi * u_p) * base_strength * np.cos(v_p)
        p_lateral_curl = np.sin(np.pi * u_p) * 0.2 * (np.abs(np.sin(v_p)) ** 3)
        p_tip_curl = (u_p ** 2) * tip_strength
        p_z_slope = openness * u_p * 1.6 * scale
        pZ = (p_z_slope + p_base_curve + p_lateral_curl + p_tip_curl) * scale * GLOBAL_SCALE
        pX_rot = pX * np.cos(angle) - pY * np.sin(angle)
        pY_rot = pX * np.sin(angle) + pY * np.cos(angle)
        p_points = np.stack([pX_rot, pY_rot, pZ], axis=-1)
        p_world = p_points @ R.T + P_surf
        all_p_x.extend(p_world[:, 0])
        all_p_y.extend(p_world[:, 1])
        all_p_z.extend(p_world[:, 2])
        for _ in range(num_particles):
            base_sizes.append(np.random.uniform(0.05, 0.8))
            phases.append(np.random.uniform(0, 2 * np.pi))
all_p_x = np.array(all_p_x)
all_p_y = np.array(all_p_y)
all_p_z = np.array(all_p_z)
base_sizes = np.array(base_sizes)
phases = np.array(phases)
scat = ax.scatter(all_p_x, all_p_y, all_p_z, s=base_sizes, c='white', alpha=0.8)
start_center_local = np.array([0.0, 0.0, 0.0])
end_center_local = np.array([0.0, 0.0, 0.35 * GLOBAL_SCALE])
start_center = start_center_local @ R.T + P_surf
end_center = end_center_local @ R.T + P_surf
ax.plot([start_center[0], end_center[0]],
        [start_center[1], end_center[1]],
        [start_center[2], end_center[2]],
        color='#FFD700', linewidth=1.5)
STAMEN_HEIGHT = 0.35 * GLOBAL_SCALE
BASE_RADIUS = 0.05 * GLOBAL_SCALE
num_stamens = 40
angles = np.linspace(0, 2 * np.pi, num_stamens, endpoint=False)
for j in range(num_stamens):
    theta = angles[j]
    extent = np.random.uniform(0.05, 0.15) * GLOBAL_SCALE
    t_points = np.linspace(0, 1, 50)
    x_local = (BASE_RADIUS + extent * t_points) * np.cos(theta)
    y_local = (BASE_RADIUS + extent * t_points) * np.sin(theta)
    z_local = STAMEN_HEIGHT * (t_points ** 2)
    pts_local = np.stack([x_local, y_local, z_local], axis=-1)
    pts_world = pts_local @ R.T + P_surf
    ax.plot(pts_world[:, 0], pts_world[:, 1], pts_world[:, 2], color='#FFD700', linewidth=1.0)
    ax.scatter(pts_world[-1, 0], pts_world[-1, 1], pts_world[-1, 2], color='#CC8800', s=2)
def update(frame):
    azim = 60 + frame * 4.0
    ax.view_init(elev=25, azim=azim)
    sizes = base_sizes * (1 + 0.5 * np.sin(frame * 0.2 + phases))
    scat.set_sizes(sizes)
    colors = np.zeros((len(sizes), 4))
    colors[:, 0] = 1.0
    colors[:, 1] = 0.7 + 0.3 * np.abs(np.sin(frame * 0.3 + phases))
    colors[:, 2] = 0.85
    colors[:, 3] = 1.0
    scat.set_facecolors(colors)
    return scat,
ani = FuncAnimation(fig, update, frames=200, interval=50, blit=False)
plt.show()
# # ====================================================================
# # 文件名：01_2026-09-09_3D莲花动画.py
# # 作用：用 NumPy（数组计算）和 Matplotlib（画图）画一朵会旋转的 3D 莲花动画：
# #       1. 用数学公式生成一片花瓣的曲面，再复制、旋转，拼成多层花瓣
# #       2. 给花瓣从根部到尖端做颜色渐变，画出花瓣上的线条
# #       3. 画花蕊（一根金色的线）和 40 根雄蕊，还有花瓣上会闪烁的小亮点
# #       4. 用动画让视角不断转动，同时让亮点的大小和颜色变化
# #       运行前需要安装依赖：在终端里运行 python3 -m pip install numpy matplotlib
# #       说明：这是图形程序，会弹出一个窗口播放动画，没有文字输出。
# #
# # 怎么运行（在 Mac 终端里，先 cd 到这个文件所在的文件夹）：
# #   运行：python3 "01_2026-09-09_3D莲花动画.py"
# #   说明：Python 是边读边执行的，不需要先编译。
# # ====================================================================
# # 文件开头用三引号包起来的文字叫"文档字符串"（docstring），用来说明这个文件做什么。运行时不会输出它。
# """
# 简介：使用 NumPy 和 Matplotlib 绘制旋转的 3D 莲花动画。
# 功能：
# - 参数化生成多层、多片弯曲花瓣网格。
# - 绘制花蕊、雄蕊和花瓣内闪烁微粒。
# - 通过 FuncAnimation 让视角旋转并更新粒子大小与颜色。
# 用途：综合练习数组运算、三维曲面、颜色插值和动画帧更新。
# 最后修改：2026-09-09
# """
#
#
# # --------------------------------------------------------------------
# # 第一部分：引入工具箱，设置全局参数
# # --------------------------------------------------------------------
# # import 模块 as 别名：引入一个工具箱，并给它起个短名字。import numpy as np 之后，用 np.xxx 调用 NumPy 的功能。
# # NumPy 是做"数组和数学计算"的工具箱：能对一大批数一次性做运算，比用 for 循环快得多、写起来也短。
# import numpy as np
#
# # Matplotlib 是画图工具箱，pyplot 是其中"像画画一样一步步画图"的部分，别名 plt。
# import matplotlib.pyplot as plt
#
# # 引入 3D 绘图支持（有些版本需要先引入它才能画 3D 图）。这里没有直接用到 Axes3D 这个名字，引入是为了让 3D 功能可用。
# from mpl_toolkits.mplot3d import Axes3D
#
# # FuncAnimation 是"动画工具"：让一个函数每隔一小段时间运行一次，每次更新画面，连起来就是动画。
# from matplotlib.animation import FuncAnimation
#
# # GLOBAL_SCALE 是"整体缩放比例"：下面所有尺寸都会乘它，想让整朵花变大变小，只改这一个数。全大写的变量名是"常量"的习惯写法。
# GLOBAL_SCALE = 0.6
#
# # 设置随机数的"种子"：种子固定为 42，每次运行产生的"随机数"序列都一样，所以每次画出来的小亮点位置都相同。
# np.random.seed(42)
#
#
# # --------------------------------------------------------------------
# # 第二部分：函数 —— 计算旋转矩阵
# # --------------------------------------------------------------------
# # def 函数名(参数): 用来定义函数 = 一段有名字、可以反复使用的代码。
# # 作用：求一个"旋转矩阵"，能把"朝上的方向（z 轴）"转到方向 n。用来决定花朝哪个方向开。
# # 在本程序里 n 就是朝上 (0, 0, 1)，所以最后得到的是"不旋转"的单位矩阵（这个函数留作以后扩展用）。
# # （数学细节不用深究：它用的是"罗德里格斯旋转公式"。重点是理解"函数 = 输入 n，输出一个矩阵"。）
# def rotation_matrix_from_z_to(n):
#     # np.linalg.norm(n) 求向量 n 的长度；n 除以长度，得到长度为 1 的"单位方向向量"。数组除以一个数，是对每个元素都做除法。
#     n = n / np.linalg.norm(n)
#
#     # np.array([...]) 用列表创建一个 NumPy 数组。这是 z 轴的方向（朝上）。
#     z = np.array([0.0, 0.0, 1.0])
#
#     # np.cross 是"叉积"：得到同时垂直于 z 和 n 的向量，它表示旋转轴。
#     v = np.cross(z, n)
#
#     # np.dot 是"点积"：反映两个方向的夹角大小（c 就是夹角的余弦值）。
#     c = np.dot(z, n)
#
#     # 叉积向量的长度，是夹角的正弦值。
#     s = np.linalg.norm(v)
#
#     # 1e-6 是科学计数法，等于 0.000001。s 几乎是 0，说明 z 和 n 平行（同向或反向），不需要复杂计算。
#     if s < 1e-6:
#         # c 大于 0：两个方向相同，不用旋转，直接返回单位矩阵。
#         if c > 0:
#             # np.eye(3) 创建 3×3 的单位矩阵（对角线是 1，其余是 0），表示"原样不动"。return 把结果交回给调用者。
#             return np.eye(3)
#         else:
#             # 方向正好相反：返回"绕 x 轴转 180 度"的矩阵。列表里再套列表，就创建了一个二维数组（矩阵）。
#             return np.array([[1, 0, 0], [0, -1, 0], [0, 0, -1]])
#
#     # 由旋转轴 v 构造一个特殊的 3×3 矩阵（"反对称矩阵"），是旋转公式的一个部分。v[2] 表示 v 的第 3 个元素（下标从 0 开始）。
#     vx = np.array([[0, -v[2], v[1]], [v[2], 0, -v[0]], [-v[1], v[0], 0]])
#
#     # 旋转矩阵的公式。@ 是"矩阵乘法"运算符（不是逐个元素相乘）；数组之间用 + 是对应位置相加。
#     R = np.eye(3) + vx + vx @ vx * ((1 - c) / (s * s))
#
#     # 返回计算好的旋转矩阵。
#     return R
#
#
# # --------------------------------------------------------------------
# # 第三部分：函数 —— 生成一片花瓣的曲面网格
# # --------------------------------------------------------------------
# # 作用：用数学公式生成一片花瓣的三维形状（曲面上一大批点的 X、Y、Z 坐标）。
# # 参数：u_start、u_end 是花瓣"从根到尖"的范围；scale 是大小；tip_strength 是尖端卷曲的程度；
# # base_strength 是根部弯曲的程度；openness 是花瓣张开的程度；inward 是向内收拢的程度。
# # （参数 num_points 没有被用到，原代码如此。）
# def create_petal_mesh(u_start, u_end, num_points, scale, tip_strength, base_strength, openness, inward):
#     # np.linspace(起点, 终点, 个数)：生成均匀分布的一排数。这里是从 u_start 到 u_end 的 100 个数。
#     # u 表示花瓣"从根部（0）到尖端（1）"的位置。
#     u = np.linspace(u_start, u_end, 100)
#
#     # v 表示花瓣"横向"的位置：从 -π/2 到 π/2（两条边之间），共 30 个数。np.pi 是圆周率 π。
#     v = np.linspace(-np.pi/2, np.pi/2, 30)
#
#     # np.meshgrid 把两排数"编成网格"：得到两个二维数组 U、V，同一位置的 (U, V) 对应花瓣表面上的一个点。
#     # 这样下面的公式可以一次性算出整片花瓣所有点的坐标。
#     U, V = np.meshgrid(u, v)
#
#     # X 是花瓣每个点的 x 坐标（沿花瓣伸展的方向）。** 是"乘方"：U ** 2 是 U 的平方。
#     # 整个公式对 U 这个二维数组里的每个元素都同时计算，不需要写 for 循环（这是 NumPy 的"向量化"）。
#     X = (U * 1.6 * scale + 0.03 - inward * (U ** 2) * scale) * GLOBAL_SCALE
#
#     # Y 是 y 坐标（花瓣的宽度方向）。np.sin 是正弦函数：中间宽、两头窄，所以花瓣是"纺锤形"。
#     Y = np.sin(np.pi * U) ** 0.8 * 0.42 * np.sin(V) * scale * GLOBAL_SCALE
#
#     # 根部的弯曲量（会让花瓣像小船一样兜起来）。np.cos 是余弦函数。
#     base_curve = -np.sin(np.pi * U) * base_strength * np.cos(V)
#
#     # 两侧边缘的卷曲量。np.abs 是绝对值。
#     lateral_curl = np.sin(np.pi * U) * 0.2 * (np.abs(np.sin(V)) ** 3)
#
#     # 尖端的卷曲量：越靠近尖端（U 越大）卷得越厉害。
#     tip_curl = (U ** 2) * tip_strength
#
#     # 花瓣的"斜度"：openness 越大，花瓣越向上张开。
#     z_slope = openness * U * 1.6 * scale
#
#     # 把四种高度加起来，得到 z 坐标（花瓣的高度）。
#     Z = (z_slope + base_curve + lateral_curl + tip_curl) * scale * GLOBAL_SCALE
#
#     # 返回多个值：Python 会把它们打包成元组一起返回，调用的地方可以一次接收多个。
#     return X, Y, Z, U, V
#
#
# # --------------------------------------------------------------------
# # 第四部分：创建画布和 3D 坐标系
# # --------------------------------------------------------------------
# # plt.figure 创建一个"画布"（窗口）：figsize=(5, 6) 是宽 5、高 6（英寸）；dpi=100 是清晰度；facecolor='black' 背景是黑色。
# fig = plt.figure(figsize=(5, 6), dpi=100, facecolor='black')
#
# # 在画布上添加一个 3D 坐标系 ax：111 表示"一行一列的第 1 个"；projection='3d' 表示三维；背景同样是黑色。后面的花都画在 ax 上。
# ax = fig.add_subplot(111, projection='3d', facecolor='black')
#
# # 设置 x、y、z 三个方向显示的范围，保证整朵花在画面里。
# ax.set_xlim(-0.7, 0.7)
# ax.set_ylim(-0.7, 0.7)
# ax.set_zlim(-0.5, 1.3)
#
# # 关掉坐标轴，不显示刻度和边框，画面里只留下花。
# ax.set_axis_off()
#
# # 关掉网格线。
# ax.grid(False)
#
#
# # --------------------------------------------------------------------
# # 第五部分：定义花瓣的层次
# # --------------------------------------------------------------------
# # 列表里放 4 个字典，每个字典描述"一层花瓣"的参数：从外到内，花瓣数量越来越少、越来越小，越往里越向上卷曲。
# # 字典 {'键': 值} 用"键: 值"成对保存数据：'petals' 花瓣数，'scale' 大小，'tip' 尖端卷曲，'base' 根部弯曲，
# # 'openness' 张开程度，'alpha' 根部透明度，'tip_alpha' 尖端透明度，'inward' 向内收拢。
# layers = [
#     {'petals': 6, 'scale': 1.0,  'tip': 0.2, 'base': 0.1, 'openness': 0.0, 'alpha': 0.15, 'tip_alpha': 0.65, 'inward': 0.0},
#     {'petals': 6, 'scale': 0.75, 'tip': 0.5, 'base': 0.2, 'openness': 0.4, 'alpha': 0.25, 'tip_alpha': 0.50, 'inward': 0.1},
#     {'petals': 5, 'scale': 0.55, 'tip': 0.9, 'base': 0.3, 'openness': 0.9, 'alpha': 0.35, 'tip_alpha': 0.40, 'inward': 0.2},
#     {'petals': 4, 'scale': 0.35, 'tip': 1.4, 'base': 0.4, 'openness': 1.4, 'alpha': 0.45, 'tip_alpha': 0.30, 'inward': 0.35}
# ]
#
# # 花的根部（底面）位置，在原点。
# P_surf = np.array([0.0, 0.0, 0.0])
#
# # 花朝向的方向：朝上。
# n = np.array([0.0, 0.0, 1.0])
#
# # 调用前面的函数，得到旋转矩阵。因为 n 就是朝上，所以 R 是单位矩阵（不旋转）。
# R = rotation_matrix_from_z_to(n)
#
# # 一次给三个变量赋值：每个都是空列表。它们用来收集所有"闪烁小亮点"的 x、y、z 坐标。
# all_p_x, all_p_y, all_p_z = [], [], []
#
# # base_sizes 存每个亮点的基础大小，phases 存每个亮点的"相位"（让每个亮点闪烁的节奏不一样）。
# base_sizes, phases = [], []
#
#
# # --------------------------------------------------------------------
# # 第六部分：逐层生成花瓣
# # --------------------------------------------------------------------
# # for 变量 in 列表: 让 layer 依次取每一层的字典，每取一个，就把下面缩进的代码执行一遍。
# for layer in layers:
#     # 用键取出字典里的值，给这一层的各个参数起名字，方便下面使用。
#     num_petals = layer['petals']
#     scale = layer['scale']
#     alpha = layer['alpha']
#     tip_alpha = layer['tip_alpha']
#     base_strength = layer['base']
#     tip_strength = layer['tip']
#     openness = layer['openness']
#     inward = layer['inward']
#
#     # 颜色用 [红, 绿, 蓝, 透明度] 四个数表示，每个数在 0 到 1 之间。这是花瓣根部的颜色（淡粉色，透明度由这一层的 alpha 决定）。
#     base_color = np.array([1.0, 0.75, 0.85, alpha])
#
#     # 花瓣尖端的颜色（深红色）。
#     tip_color = np.array([0.85, 0.05, 0.1, tip_alpha])
#
#     # 调用函数生成这一层"一片花瓣"的曲面，一次接收 5 个返回值（叫"解包"）。
#     X_petal, Y_petal, Z_petal, U, V = create_petal_mesh(0, 1, 100, scale, tip_strength, base_strength, openness, inward)
#
#     # 做颜色渐变用的系数 t：U 从 0.6 到 1.0 时，t 从 0 变到 1（0.6 之前是负数）。数组运算：对每个点都算一遍。
#     t = (U - 0.6) / 0.4
#
#     # np.clip(数组, 最小, 最大)：把数限制在 0 到 1 之间，小于 0 的变成 0，大于 1 的变成 1。
#     t = np.clip(t, 0, 1)
#
#     # 乘方 1.5 让渐变"先慢后快"，颜色过渡更自然。
#     t = t ** 1.5
#
#     # np.zeros(形状) 创建全是 0 的数组。U.shape 是网格的形状（行数, 列数），加上 (4,) 变成 (行数, 列数, 4)：每个点配 4 个数（红、绿、蓝、透明度）。
#     face_colors = np.zeros(U.shape + (4,))
#
#     # 循环 4 次，依次处理红、绿、蓝、透明度四个通道。
#     for i in range(4):
#         # 线性混合：t 是 0 时用根部颜色，t 是 1 时用尖端颜色，中间按比例混合。
#         # [:, :, i] 是切片："所有行、所有列、第 i 个通道"。
#         face_colors[:, :, i] = base_color[i] * (1 - t) + tip_color[i] * t
#
#     # 把这一层的花瓣复制 num_petals 片，每片旋转一个角度，排成一圈。
#     for i in range(num_petals):
#         # 2π 是一整圈，除以花瓣数得到相邻花瓣的夹角；乘 i 就是第 i 片花瓣要转的角度（单位是弧度）。
#         angle = i * (2 * np.pi / num_petals)
#
#         # 绕 z 轴（竖直方向）旋转 angle 角度的标准公式：新 x = x·cosθ - y·sinθ，新 y = x·sinθ + y·cosθ。
#         X_rot = X_petal * np.cos(angle) - Y_petal * np.sin(angle)
#         Y_rot = X_petal * np.sin(angle) + Y_petal * np.cos(angle)
#
#         # 绕竖直轴旋转，高度不变。
#         Z_rot = Z_petal
#
#         # np.stack(..., axis=-1) 把三个二维数组叠成 (行, 列, 3)，每个点有 (x, y, z)；.reshape(-1, 3) 压成"N 行 3 列"的点列表，方便统一做矩阵运算。
#         # -1 表示"让 NumPy 自己算这一维是多少"。
#         points = np.stack([X_rot, Y_rot, Z_rot], axis=-1).reshape(-1, 3)
#
#         # 运算符 @ 是矩阵乘法，R.T 是 R 的转置：把所有点按旋转矩阵 R 转到花朝向的方向，再平移到根部位置 P_surf。这里 R 是单位矩阵、P_surf 是原点，所以点的位置不变。
#         points_world = points @ R.T + P_surf
#
#         # points_world[:, 0] 取出所有点的第 0 列（x 坐标）；.reshape(...) 变回原来网格的形状。下面两行同理取 y、z。
#         X_final = points_world[:, 0].reshape(X_petal.shape)
#         Y_final = points_world[:, 1].reshape(X_petal.shape)
#         Z_final = points_world[:, 2].reshape(X_petal.shape)
#
#         # ax.plot_surface：在 3D 坐标系里画一个曲面。参数：
#         #   facecolors=face_colors  ：每个小格子的颜色（上面算好的渐变色）；
#         #   rstride=2, cstride=2    ：每隔 2 行、2 列取一个格子，减少绘制量；
#         #   edgecolor=...           ：网格边线的颜色（淡粉色，半透明）；linewidth=0.1：边线很细；
#         #   antialiased=True        ：抗锯齿，边缘更平滑；shade=False：不做光照阴影，颜色就是我们指定的颜色。
#         # （这条语句写成了多行，括号没有闭合前可以换行。）
#         ax.plot_surface(X_final, Y_final, Z_final,
#                         facecolors=face_colors,
#                         rstride=2, cstride=2,
#                         edgecolor=(1.0, 0.6, 0.8, 0.1),
#                         linewidth=0.1,
#                         antialiased=True, shade=False)
#
#         # range(起点, 终点, 步长)：0, 4, 8, ……, 28，一共 8 个。从 30 行里每隔 4 行挑一行，用来画花瓣上的纵向线条。
#         v_indices = range(0, 30, 4)
#
#         # 对挑出来的每一行画一条线。
#         for v_idx in v_indices:
#             # X_final[v_idx, :] 取出第 v_idx 行的全部列：一条"从根到尖"的线上所有点的 x 坐标。下面同理取 y、z 和 U。
#             x_line = X_final[v_idx, :]
#             y_line = Y_final[v_idx, :]
#             z_line = Z_final[v_idx, :]
#             u_line = U[v_idx, :]
#
#             # ax.plot：在 3D 坐标系里画一条线。color 是 (红, 绿, 蓝, 透明度)，linewidth 是线的粗细。
#             ax.plot(x_line, y_line, z_line, color=(1.0, 0.7, 0.85, 0.9), linewidth=0.1)
#
#             # u_line > 0.7 对数组的每个元素做比较，得到一个由 True / False 组成的数组（叫"布尔掩码"）：True 表示"靠近花瓣尖端的点"。
#             tip_mask = u_line > 0.7
#
#             # 用掩码取数组：x_line[tip_mask] 只保留掩码是 True 的点，也就是只画尖端那一段。
#             # 先画一条较粗的粉红线，再在上面画一条很细的近白色线，合起来像尖端"发光"的效果。（两条语句都写成了两行。）
#             ax.plot(x_line[tip_mask], y_line[tip_mask], z_line[tip_mask],
#                     color=(1.0, 0.2, 0.6, 0.6), linewidth=0.4)
#             ax.plot(x_line[tip_mask], y_line[tip_mask], z_line[tip_mask],
#                     color=(1.0, 0.9, 1.0, 0.9), linewidth=0.15)
#
#         # 每片花瓣上放 4 个闪烁的小亮点。
#         num_particles = 4
#
#         # np.random.uniform(最小, 最大, 个数)：在这个范围内随机取若干个数。u_p 是亮点在花瓣上"从根到尖"的位置（只放在靠近尖端的 0.6～1.0）。
#         u_p = np.random.uniform(0.6, 1.0, num_particles)
#
#         # 亮点在花瓣上"横向"的位置，随机。
#         v_p = np.random.uniform(-np.pi/2, np.pi/2, num_particles)
#
#         # 下面这几行（pX、pY、p_base_curve……pZ）用的是和花瓣曲面完全相同的公式，只是把 U、V 换成了亮点的随机位置，
#         # 这样亮点就正好落在花瓣表面上。
#         pX = (u_p * 1.6 * scale + 0.03 - inward * (u_p ** 2) * scale) * GLOBAL_SCALE
#         pY = np.sin(np.pi * u_p) ** 0.8 * 0.42 * np.sin(v_p) * scale * GLOBAL_SCALE
#         p_base_curve = -np.sin(np.pi * u_p) * base_strength * np.cos(v_p)
#         p_lateral_curl = np.sin(np.pi * u_p) * 0.2 * (np.abs(np.sin(v_p)) ** 3)
#         p_tip_curl = (u_p ** 2) * tip_strength
#         p_z_slope = openness * u_p * 1.6 * scale
#         pZ = (p_z_slope + p_base_curve + p_lateral_curl + p_tip_curl) * scale * GLOBAL_SCALE
#
#         # 亮点也跟着这一片花瓣绕 z 轴转同样的角度。
#         pX_rot = pX * np.cos(angle) - pY * np.sin(angle)
#         pY_rot = pX * np.sin(angle) + pY * np.cos(angle)
#
#         # 把亮点的 x、y、z 叠成"每行一个点"的数组。
#         p_points = np.stack([pX_rot, pY_rot, pZ], axis=-1)
#
#         # 同样转到花朝向的方向并平移（这里不改变位置）。
#         p_world = p_points @ R.T + P_surf
#
#         # 列表.extend(...)：把一批元素逐个添加到列表末尾。把这一片花瓣上亮点的 x、y、z 坐标收集起来，最后统一画。
#         all_p_x.extend(p_world[:, 0])
#         all_p_y.extend(p_world[:, 1])
#         all_p_z.extend(p_world[:, 2])
#
#         # _ 是"不需要用到的变量"的习惯写法：这里只要重复 4 次，不关心循环变量的值。
#         for _ in range(num_particles):
#             # 列表.append(元素)：添加一个元素。给每个亮点随机一个基础大小。
#             base_sizes.append(np.random.uniform(0.05, 0.8))
#
#             # 给每个亮点随机一个"相位"（0 到 2π），让每个亮点的闪烁节奏错开，看起来更自然。
#             phases.append(np.random.uniform(0, 2 * np.pi))
#
#
# # --------------------------------------------------------------------
# # 第七部分：画亮点、花蕊和雄蕊
# # --------------------------------------------------------------------
# # 把收集好的列表转成 NumPy 数组，方便后面做数组运算。
# all_p_x = np.array(all_p_x)
# all_p_y = np.array(all_p_y)
# all_p_z = np.array(all_p_z)
# base_sizes = np.array(base_sizes)
# phases = np.array(phases)
#
# # ax.scatter：在 3D 坐标系里画"散点"。s 是每个点的大小，c 是颜色（白色），alpha 是透明度。
# # 把返回的对象存进 scat，动画里要用它来更新点的大小和颜色。
# scat = ax.scatter(all_p_x, all_p_y, all_p_z, s=base_sizes, c='white', alpha=0.8)
#
# # 花蕊是一根短短的金色线：起点在花的中心根部，终点在中心上方 0.35 × GLOBAL_SCALE 的位置。
# start_center_local = np.array([0.0, 0.0, 0.0])
# end_center_local = np.array([0.0, 0.0, 0.35 * GLOBAL_SCALE])
#
# # 同样用旋转矩阵和平移，转到世界坐标（这里位置不变）。
# start_center = start_center_local @ R.T + P_surf
# end_center = end_center_local @ R.T + P_surf
#
# # 画一条线段：三个列表分别是两个端点的 x、y、z 坐标。color='#FFD700' 是金色（十六进制颜色码），linewidth 是线宽。
# ax.plot([start_center[0], end_center[0]],
#         [start_center[1], end_center[1]],
#         [start_center[2], end_center[2]],
#         color='#FFD700', linewidth=1.5)
#
# # 雄蕊的参数：STAMEN_HEIGHT 是雄蕊的高度，BASE_RADIUS 是雄蕊根部离中心的距离，num_stamens 是雄蕊根数（40 根）。
# STAMEN_HEIGHT = 0.35 * GLOBAL_SCALE
# BASE_RADIUS = 0.05 * GLOBAL_SCALE
# num_stamens = 40
#
# # 在一整圈里均匀取 40 个角度。endpoint=False 表示不包含终点 2π（因为 2π 和 0 是同一个位置，不能重复）。
# angles = np.linspace(0, 2 * np.pi, num_stamens, endpoint=False)
#
# # 对每一根雄蕊，计算一条弯曲的线并画出来。
# for j in range(num_stamens):
#     # 这根雄蕊所在的角度。
#     theta = angles[j]
#
#     # 随机决定这根雄蕊向外伸展多少，让 40 根不完全一样。
#     extent = np.random.uniform(0.05, 0.15) * GLOBAL_SCALE
#
#     # 从 0 到 1 的 50 个数，表示沿着雄蕊"从根到尖"的位置。
#     t_points = np.linspace(0, 1, 50)
#
#     # 半径从 BASE_RADIUS 逐渐增大，再乘 cos、sin 转成 x、y 坐标。
#     x_local = (BASE_RADIUS + extent * t_points) * np.cos(theta)
#     y_local = (BASE_RADIUS + extent * t_points) * np.sin(theta)
#
#     # 高度随 t 的平方增长：根部平缓，越往尖端抬升得越快，形成弯弯的曲线。
#     z_local = STAMEN_HEIGHT * (t_points ** 2)
#
#     # 把 x、y、z 叠成"每行一个点"的数组。
#     pts_local = np.stack([x_local, y_local, z_local], axis=-1)
#     pts_world = pts_local @ R.T + P_surf
#
#     # 画出这根雄蕊的线（金色）。pts_world[:, 0] 取所有点的 x 坐标，依次类推。
#     ax.plot(pts_world[:, 0], pts_world[:, 1], pts_world[:, 2], color='#FFD700', linewidth=1.0)
#
#     # 在雄蕊的尖端画一个深金色的小点。下标 -1 表示"最后一个"（尖端的那个点）。
#     ax.scatter(pts_world[-1, 0], pts_world[-1, 1], pts_world[-1, 2], color='#CC8800', s=2)
#
#
# # --------------------------------------------------------------------
# # 第八部分：动画 —— 每一帧要做的事
# # --------------------------------------------------------------------
# # update 是"动画的更新函数"：动画工具会每隔一小段时间调用它一次，frame 是当前的帧编号（0、1、2……）。
# def update(frame):
#     # azim 是水平转动的角度：帧编号越大，转得越多，所以画面会不断旋转。
#     azim = 60 + frame * 4.0
#
#     # 设置观察的角度：elev=25 是俯视的高度角，azim 是水平方向的角度。
#     ax.view_init(elev=25, azim=azim)
#
#     # 让亮点的大小随时间变化：np.sin 的值在 -1 和 1 之间摆动，所以大小在基础大小的 0.5 倍到 1.5 倍之间"呼吸"。
#     # phases 让每个亮点的节奏不同。这一行对所有亮点同时计算（数组运算）。
#     sizes = base_sizes * (1 + 0.5 * np.sin(frame * 0.2 + phases))
#
#     # 把新的大小设置给之前画好的散点对象 scat。
#     scat.set_sizes(sizes)
#
#     # 创建一个"每个亮点 4 个数（红、绿、蓝、透明度）"的颜色数组，先全是 0。len(sizes) 是亮点的个数。
#     colors = np.zeros((len(sizes), 4))
#
#     # 给所有亮点的第 0 列（红色）设成 1.0；下面几行设置绿色、蓝色和透明度。
#     colors[:, 0] = 1.0
#
#     # 绿色在 0.7 和 1.0 之间变化，所以亮点的颜色会在"粉色"和"淡黄白色"之间闪烁。
#     colors[:, 1] = 0.7 + 0.3 * np.abs(np.sin(frame * 0.3 + phases))
#     colors[:, 2] = 0.85
#     colors[:, 3] = 1.0
#
#     # 把新的颜色设置给散点。
#     scat.set_facecolors(colors)
#
#     # 返回被修改的对象。scat 后面的逗号很重要：它让返回值成为"只有一个元素的元组"。
#     return scat,
#
#
# # --------------------------------------------------------------------
# # 第九部分：启动动画
# # --------------------------------------------------------------------
# # FuncAnimation(画布, 更新函数, ...)：frames=200 表示共 200 帧；interval=50 表示每帧间隔 50 毫秒；blit=False 表示每帧重画整个画面。
# # 一定要把结果存进变量 ani：如果不保存，动画对象会被 Python 当作垃圾回收掉，动画就不会播放。
# ani = FuncAnimation(fig, update, frames=200, interval=50, blit=False)
#
# # plt.show() 显示窗口，开始播放动画。关掉窗口，程序就结束。
# plt.show()
#
# # ====================================================================
# # 这是一个图形动画程序：运行后会弹出一个窗口，播放旋转的 3D 莲花动画（共 200 帧），没有文字输出。
# # 我在"没有显示器"的环境里，用无窗口模式（Agg）把整个程序运行了一遍：代码能完整跑完，没有报错。
# # 如果提示找不到 numpy 或 matplotlib，先在终端运行：python3 -m pip install numpy matplotlib
# # ====================================================================
