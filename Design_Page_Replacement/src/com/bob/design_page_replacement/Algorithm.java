package com.bob.design_page_replacement;

import java.util.LinkedList;
import java.util.Queue;

import android.R.array;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class Algorithm {
	public static String strs = "3 4 2 6 4 3 7 4 3 6 3 4 8 4 6";// 默认序列
	public static int[] arrays = new int[] { 3, 4, 2, 6, 4, 3, 7, 4, 3, 6, 3,
			4, 8, 4, 6 };
	public static int length = 15;// 序列长度
	public static int memory = 3;// 默认内存块数为3
	public static int missing_page = 0;// 定义缺页数
	public static Queue<Integer> queue = new LinkedList<Integer>();// 构造一个空的队列

	/*
	 * 三种访问算法
	 */
	public static void init(String string)// 生成一个等长数组
	{
		if (!"".equals(string)) {
			strs = string;// 初始化strs
			String[] ss = strs.split(" ");
			length = ss.length;
			arrays = new int[length];// 创建一个与分割后等长的数组
			for (int i = 0; i < ss.length; i++) {
				arrays[i] = Integer.parseInt(ss[i]);
			}
		}
	}

	public static void opt(Context context) {
		int i = 0, j = 0, maxIndex = 0, times;// maxIndex表示最靠后的一个元素的下标，times表示fore中排查元素的个数
		for (i = 0; i < length; i++) {// 依次遍历访问序列中的每一个元素
			if (!queue.contains(arrays[i])) {
				/*
				 * Log.i("result", ""+arrays[i]); show(i);
				 */
				missing_page++;// 如果队列中不包含当前元素，就直接记录缺页数+1

				// 然后分情况处理队列
				if (queue.size() < memory) {// 内存未满，入队
					queue.add(arrays[i]);
				} else {
					times = 0;// 在进入遍历队列之前，置零
					for (int a : queue) {// 开始寻找队列中哪一个元素不在后序队列中，遵循先到先处理原则
						times++;// 记录当前排查个数
						for (j = i + 1; j < length; j++) {
							if (a == arrays[j]) {
								if (maxIndex < j) {
									maxIndex = j;
								}
								break;
							}
						}
						if (j == length) {// 说明当前元素不再队列中
							queue.remove(a);
							queue.add(arrays[i]);
							break;// 置换完毕，跳出fore
						} else if (times == memory) {// 表示队列中所有元素均处于后序序列中
							queue.remove(arrays[maxIndex]);
							queue.add(arrays[i]);
							break;// 置换完毕，跳出fore
						}
					}
				}
			}

		}
		clearing(context);
	}

	/*
	 * 直接就是简单的先进先出原则
	 */
	public static void fifo(Context context) {
		for (int i = 0; i < length; i++) {
			if (!queue.contains(arrays[i])) {
				inOut(i);// 判空，然后先进先出
			}
		}
		clearing(context);
	}

	/*
	 * LRU算法有两种实现方法 1、可遵向前看原则，和Opt相对应
	 * 2、可以看作是fifo的变型，即就是缺页情况直接先进先出，不缺页则直接将当前访问页面移动到队列尾部
	 */
	public static void lru(Context context) {
		for (int i = 0; i < length; i++) {
			if (!queue.contains(arrays[i])) {// 缺页了
				inOut(i);
			} else {
				queue.remove(arrays[i]);
				queue.add(arrays[i]);
			}
		}
		clearing(context);
	}

	/*
	 * 判空，入队出队
	 */
	public static void inOut(int i) {
		missing_page++;
		if (queue.size() < memory) {// 内存未满，入队
			queue.add(arrays[i]);
		} else {// 队列满后，遵循先进先出原则
			queue.poll();
			queue.add(arrays[i]);
		}
	}

	/*
	 * 收尾的一些动作
	 */
	public static void clearing(Context context) {
		Toast.makeText(context, "缺页数为：" + missing_page, Toast.LENGTH_SHORT)
				.show();
		missing_page = 0;
		queue.clear();
	}

	/*
	 * debug public static void show(int i)//显示当前队列中元素 { String re= ""; for (int
	 * a : queue) { re+= a+" "; } Log.i("result", "置换之前"+re); String ele= "";
	 * for (; i < length; i++) { ele+= arrays[i]+" "; } Log.i("result", ele); }
	 */
}
