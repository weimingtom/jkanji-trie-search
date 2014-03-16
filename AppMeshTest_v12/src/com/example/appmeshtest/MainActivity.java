package com.example.appmeshtest;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	private Button button1;
	private TextView textView1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button1 = (Button)this.findViewById(R.id.button1);
		textView1 = (TextView) this.findViewById(R.id.textView1);
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				test();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	private void test() {
    	try {
			InputStream inputStream = this.getAssets().open("words_mini.bin");
			InputStream istr = inputStream;
	    	byte[] bytes = new byte[16];
    		istr.mark(0);
    		istr.skip(1);
			istr.read(bytes);
	    	trace(Arrays.toString(bytes));
			istr.reset();
			istr.skip(2);
			istr.read(bytes);
			trace(Arrays.toString(bytes));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void trace(String str) {
		System.out.println(str);
		textView1.append("\n" + str + "\n");
	}
}
