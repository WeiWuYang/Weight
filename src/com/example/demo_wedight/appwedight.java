package com.example.demo_wedight;

import java.text.SimpleDateFormat;


import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.os.Handler;
import android.widget.RemoteViews;

public class appwedight extends AppWidgetProvider {
	Handler mhandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				//调用方法
				showtext();
				break;

			default:
				break;
			}
		};  
	};

	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		mcontext = context;
		//小控件管理器
		am = AppWidgetManager.getInstance(mcontext);
		showtext();
	};


	SimpleDateFormat sdf = null;
	Context mcontext;
	private RemoteViews rv;
	private AppWidgetManager am;
	Runnable r = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			mhandler.sendEmptyMessage(1);

		}
	};

	public void showtext() {
		if (sdf == null) {
			sdf = new SimpleDateFormat("yyyy年MM月dd日 hh时mm分ss秒");
		}
		//初始化时间
		String text = sdf.format(System.currentTimeMillis());
		//初始化要显示的布局
		rv = new RemoteViews(mcontext.getPackageName(),
				com.example.demo_wedight.R.layout.item);
		//给文本赋值
		rv.setTextViewText(com.example.demo_wedight.R.id.tv_name, text);
		//启动
		am.updateAppWidget(new ComponentName(mcontext, appwedight.class), rv);
		//延迟1 s发送
		mhandler.postDelayed(r, 1000);
	}

}
