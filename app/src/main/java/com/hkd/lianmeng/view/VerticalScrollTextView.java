package com.hkd.lianmeng.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.TextView;

import com.hkd.lianmeng.model.Sentence;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xushilin
 * 
 */
public class VerticalScrollTextView extends TextView {
	private Paint mPaint;
	private float mX;
	private Paint mPathPaint;	
	public int index = 0;
	private List<Sentence> list;
	public float mTouchHistoryY;
	private int mY;	
	private float middleY;// y���м�
	private static final int DY = 40; // ÿһ�еļ��
	public VerticalScrollTextView(Context context) {
		super(context);
		init();
	}
	public VerticalScrollTextView(Context context, AttributeSet attr) {
		super(context, attr);
		init();
	}
	public VerticalScrollTextView(Context context, AttributeSet attr, int i) {
		super(context, attr, i);
		init();
	}
	private void init() {
		setFocusable(true);
		if(list==null){
			list=new ArrayList<Sentence>();
			Sentence sen=new Sentence(0,"��ʱû��֪ͨ����");
			list.add(0, sen);
		}		
	
		// �Ǹ�������
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setTextSize(16);
		mPaint.setColor(Color.BLACK);
		mPaint.setTypeface(Typeface.SERIF);
		
		// �������� ��ǰ���
		mPathPaint = new Paint();
		mPathPaint.setAntiAlias(true);
		mPathPaint.setColor(Color.RED);
		mPathPaint.setTextSize(18);
		mPathPaint.setTypeface(Typeface.SANS_SERIF);
	}
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawColor(0xEFeffff);
		Paint p = mPaint;
		Paint p2 = mPathPaint;
		p.setTextAlign(Paint.Align.CENTER);
		if (index == -1)
			return;
		p2.setTextAlign(Paint.Align.CENTER);
		// �Ȼ���ǰ�У�֮���ٻ�����ǰ��ͺ��棬�����ͱ��ֵ�ǰ�����м��λ��
		canvas.drawText(list.get(index).getName(), mX, middleY, p2);
		float tempY = middleY;
		// ��������֮ǰ�ľ���
		for (int i = index - 1; i >= 0; i--) {			
			tempY = tempY - DY;
			if (tempY < 0) {
				break;
			}
			canvas.drawText(list.get(i).getName(), mX, tempY, p);			
		}
		tempY = middleY;
		// ��������֮��ľ���
		for (int i = index + 1; i < list.size(); i++) {
			// ��������
			tempY = tempY + DY;
			if (tempY > mY) {
				break;
			}
			canvas.drawText(list.get(i).getName(), mX, tempY, p);			
		}
	}
	protected void onSizeChanged(int w, int h, int ow, int oh) {
		super.onSizeChanged(w, h, ow, oh);
		mX = w * 0.5f; 
		mY = h;
		middleY = h * 0.5f;
	}

	public long updateIndex(int index) {	
		if (index == -1)
			return -1;
		this.index=index;		
		return index;
	}
	
	public List<Sentence> getList() {
		return list;
	}
	
	public void setList(List<Sentence> list) {
		this.list = list;
	}
	public void updateUI(){
		new Thread(new updateThread()).start();
	}
	class updateThread implements Runnable {
		long time = 1000; // ��ʼ ��ʱ�䣬����Ϊ�㣬����ǰ�漸����û����ʾ����
		int i=0;
		public void run() {
			while (true) {
				long sleeptime = updateIndex(i);
				time += sleeptime;
				mHandler.post(mUpdateResults);
				if (sleeptime == -1)
					return;
				try {
					Thread.sleep(time);
					i++;
					if(i==getList().size())
						i=0;
				} catch (InterruptedException e) {					
					e.printStackTrace();
				}
			}
		}
	}
	Handler mHandler = new Handler();
	Runnable mUpdateResults = new Runnable() {
		public void run() {
			invalidate(); // ������ͼ
		}
	};
}