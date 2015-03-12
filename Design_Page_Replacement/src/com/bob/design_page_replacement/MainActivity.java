package com.bob.design_page_replacement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.view.CircleMenuLayout;
import com.zhy.view.CircleMenuLayout.OnMenuItemClickListener;

/**
 * <pre>
 * @author bob
 * </pre>
 */
public class MainActivity extends Activity {

	private CircleMenuLayout mCircleMenuLayout;
	private TextView current_array;

	private String[] mItemTexts = new String[] { "OPT", "FIFO", "LRU" };
	private int[] mItemImgs = new int[] { R.drawable.item_alg,
			R.drawable.item_alg, R.drawable.item_alg };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main_activity);
		current_array = (TextView) findViewById(R.id.current_array);
		mCircleMenuLayout = (CircleMenuLayout) findViewById(R.id.id_menulayout);
		mCircleMenuLayout.setMenuItemIconsAndTexts(mItemImgs, mItemTexts);// 为布局传入图片和文字
		mCircleMenuLayout// 设置监听事件
				.setOnMenuItemClickListener(new OnMenuItemClickListener() {

					@Override
					public void itemClick(View view, int pos) {
						switch (pos) {
						case 0:
							Algorithm.opt(MainActivity.this);
							break;
						case 1:
							Algorithm.fifo(MainActivity.this);
							break;
						case 2:
							Algorithm.lru(MainActivity.this);
							break;
						}
					}

					@Override
					public void itemCenterClick(View view) {
						Intent intent = new Intent(MainActivity.this,
								OptionActivity.class);
						startActivity(intent);
						finish();
					}
				});
		show();

	}

	public void show() {
		current_array.setText("当前访问序列:\n" + Algorithm.strs);
	}

}
