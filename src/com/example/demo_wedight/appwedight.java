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
				//���÷���
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
		//С�ؼ�������
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
			sdf = new SimpleDateFormat("yyyy��MM��dd�� hhʱmm��ss��");
		}
		//��ʼ��ʱ��
		String text = sdf.format(System.currentTimeMillis());
		//��ʼ��Ҫ��ʾ�Ĳ���
		rv = new RemoteViews(mcontext.getPackageName(),
				com.example.demo_wedight.R.layout.item);
		//���ı���ֵ
		rv.setTextViewText(com.example.demo_wedight.R.id.tv_name, text);
		//����
		am.updateAppWidget(new ComponentName(mcontext, appwedight.class), rv);
		//�ӳ�1 s����
		mhandler.postDelayed(r, 1000);
	}

}
