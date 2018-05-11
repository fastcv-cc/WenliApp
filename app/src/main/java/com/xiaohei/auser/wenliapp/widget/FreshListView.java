package com.xiaohei.auser.wenliapp.widget;

import com.xiaohei.auser.wenliapp.R;

import android.content.Context;
import android.os.Handler;
import android.text.format.Time;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class FreshListView extends ListView implements
		AbsListView.OnScrollListener {

	private IReflashListener reflashListener;
	private View header;
	private int headerHeight;
	private int scrollState=1;
	private int fristVisibleItem;
	private boolean isRemark;
	private int startY;
	private int state;
	final int NONE = 0;  //无操作时
	final int PULL = 1;   //下拉时
	final int RELESE = 2;   //可刷新时
	final int REFLASHING = 3;   //正在刷新
	final int REFLASHING_SUCCESS = 4;   //刷新成功
	final int REFLASHING_FAIL = 5;   //刷新失败

	public FreshListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(context);
	}

	public FreshListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	public FreshListView(Context context) {
		super(context);
		initView(context);
	}

	private void initView(Context context) {
		LayoutInflater inflater = LayoutInflater.from(context);
		header = inflater.inflate(R.layout.header_layout, null);
		measureView(header);
		headerHeight = header.getMeasuredHeight();
		topPadding(-headerHeight);
		Log.i("tag", "headerHeight=" + headerHeight);
		this.addHeaderView(header);
		this.setOnScrollListener(this);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN: {
			if (fristVisibleItem == 0) {
				isRemark = true;
				startY = (int) ev.getY();
				Log.e("startY", startY+"");
			}
		}
			break;

		case MotionEvent.ACTION_MOVE:

		{
			onMove(ev);
		}
			break;
		case MotionEvent.ACTION_UP: {
			if (state == RELESE) {
				state = REFLASHING;
				reflashViewByState();
				reflashListener.onReflash();
			} else if (state == PULL) {
				state = NONE;
				isRemark = false;
				reflashViewByState();
			}
		}
			break;

		}
		return super.onTouchEvent(ev);
	}

	private void onMove(MotionEvent ev) {
		if (!isRemark) {
			return;
		}
		int tempY = (int) ev.getY();

		int space = tempY - startY;
		Log.e("space", space+"");
		int topPadding = space - headerHeight;
		switch (state) {
		case NONE: {
			if (space > 0) {
				state = PULL;
				Log.e("state", state+"");
				reflashViewByState();
				
			}
		}
			break;

		case PULL: {
			topPadding(topPadding);
			Log.e("space pull", space+"");
			Log.e("headerHeight", headerHeight+"");
			if (space > headerHeight + 50
					&& scrollState == SCROLL_STATE_TOUCH_SCROLL) {
				state = RELESE;
				Log.e("state", state+"");
				reflashViewByState();
			}
		}
			break;
		case RELESE: {
			topPadding(topPadding);
			if (space < headerHeight + 50) {
				state = PULL;
				Log.e("state", state+"");
				reflashViewByState();
			} else if (space <= 0) {
				state = NONE;
				Log.e("state", state+"");
				isRemark = false;
				reflashViewByState();
			}
		}

			break;
		}
	}


	private void measureView(View view) {
		ViewGroup.LayoutParams p = view.getLayoutParams();
		if (p == null) {
			p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
		}
		int width = ViewGroup.getChildMeasureSpec(0, 0, p.width);
		int height;
		int tempHeight = p.height;
		if (tempHeight > 0) {
			height = MeasureSpec.makeMeasureSpec(tempHeight,
					MeasureSpec.EXACTLY);
		} else {
			height = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
		}
		view.measure(width, height);
	}


	private void topPadding(int topPadding) {

		header.setPadding(header.getPaddingLeft(), topPadding,
				header.getPaddingRight(), header.getPaddingBottom());
		header.invalidate();
	}


	public void onScrollStateChanged(AbsListView view, int scrollState) {
		this.scrollState = scrollState;
		Log.e("scrollState", scrollState+"");
	}

	public void onScroll(AbsListView view, int fristVisibleItem,
			int visibleItemCount, int totalItemCount) {
		this.fristVisibleItem = fristVisibleItem;
//		Log.e("fristVisibleItem", fristVisibleItem+"");
	}


	private void reflashViewByState() {
		TextView tip = (TextView) header.findViewById(R.id.tip);
		ImageView arrow = (ImageView) header.findViewById(R.id.arrow);
		ProgressBar progressBar = (ProgressBar) header
				.findViewById(R.id.progress);
		RotateAnimation anim = new RotateAnimation(0, 180,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		anim.setDuration(500);
		anim.setFillAfter(true);

		RotateAnimation anim1 = new RotateAnimation(180, 0,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		anim1.setDuration(500);
		anim1.setFillAfter(true);
		switch (state) {
		case NONE: {
			arrow.clearAnimation();
			topPadding(-headerHeight);
		}
			break;
		case PULL: {
			arrow.setVisibility(View.VISIBLE);
			progressBar.setVisibility(View.GONE);
			tip.setText("下拉刷新");
			arrow.clearAnimation();
			arrow.setAnimation(anim1);
		}
			break;
		case RELESE: {
			arrow.setVisibility(View.VISIBLE);
			progressBar.setVisibility(View.GONE);
			tip.setText("松开刷新");
			arrow.clearAnimation();
			arrow.setAnimation(anim);
		}
			break;
		case REFLASHING: {
			topPadding(50);
			arrow.clearAnimation();
			arrow.setVisibility(View.GONE);
			progressBar.setVisibility(View.VISIBLE);
			tip.setText("正在刷新...");
		}
		break;
		case REFLASHING_SUCCESS: {
			topPadding(50);
			arrow.clearAnimation();
			arrow.setVisibility(View.GONE);
			progressBar.setVisibility(View.VISIBLE);
			tip.setText("刷新成功...");
		}
		break;
		case REFLASHING_FAIL: {
			topPadding(50);
			arrow.clearAnimation();
			arrow.setVisibility(View.GONE);
			progressBar.setVisibility(View.VISIBLE);
			tip.setText("刷新失败...");
		}
			break;
		}
	    
	}

	public void reflashComplete(boolean flag) {
		if(flag){
			state = REFLASHING_SUCCESS;
			reflashViewByState();
			handler.sendEmptyMessageDelayed(1, 500);
		}else{
			state = REFLASHING_FAIL;
			reflashViewByState();
			handler.sendEmptyMessageDelayed(1, 500);
		}
		
	}

	public void setInterface(IReflashListener reflashListener) {
		this.reflashListener = reflashListener;
	}

	public interface IReflashListener {
		public void onReflash();
	}
	
	Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			state = NONE;
			isRemark = false;
			reflashViewByState();
			TextView lastUpdatetime = (TextView) header
					.findViewById(R.id.lastupdate_time);
			lastUpdatetime.setText(getTime());
		};
	};

	private String getTime() {
		// TODO Auto-generated method stub
		Time t = new Time();
		t.setToNow();
		String stime = t.year + "年" + (Integer.valueOf(t.month) + 1) + "月"
				+ t.monthDay + "日" + t.hour + ":" + t.minute + ":"
				+ t.second;
		return stime;
	}
}
