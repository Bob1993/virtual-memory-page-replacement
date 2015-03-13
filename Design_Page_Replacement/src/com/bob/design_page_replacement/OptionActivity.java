package com.bob.design_page_replacement;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class OptionActivity extends Activity implements OnClickListener {
	private EditText ed_input;
	private Button bt_auto;
	private Button bt_over;
	private boolean isAuto = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.option_layout);

		ed_input = (EditText) findViewById(R.id.ed_input);
		bt_auto = (Button) findViewById(R.id.auto_arrays);
		bt_over = (Button) findViewById(R.id.over);

		bt_auto.setOnClickListener(this);
		bt_over.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.auto_arrays: {
			isAuto = true;// 设置标志位自动生成
			auto_generation();
		}
			break;

		case R.id.over: {
			if (!isAuto) {// 非自动生成的序列

				Algorithm.init(ed_input.getText().toString());
			}

			//Intent intent = new Intent(OptionActivity.this, MainActivity.class);
			//startActivity(intent);
			setResult(RESULT_OK);
			finish();// 结束当前活动
		}
		}
	}

	public void auto_generation()// 默认生成15位长的访问序列
	{
		String temp = "";
		Random random = new Random(System.currentTimeMillis());// 利用系统时间来作为随机数的种子
		for (int i = 0; i < 15; i++) {
			temp += random.nextInt(10) + " ";
		}
		Algorithm.init(temp);// 初始化
		Toast.makeText(OptionActivity.this, "已成功生成访问序列", Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		//super.onBackPressed();//不必调用，因为finish我们自己已经写了一遍了
		//Intent intent = new Intent(OptionActivity.this, MainActivity.class);
		setResult(RESULT_OK);//在这里没有需要返回的数据，因此就不需要使用intent了
		//startActivity(intent);
		finish();// 结束当前活动
	}

}
