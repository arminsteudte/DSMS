package de.haw.dsms;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.ImageView;

@SuppressWarnings("deprecation")
public class SocketView extends ImageView implements OnGestureListener, OnDoubleTapListener{
	
	private final static String TAG = "SocketView";
	
	private GestureDetector gc;
	
	private float mLastTouchX;
	private float mLastTouchY;
	
	public SocketView(Context context) {
		this(context, null, 0);
	}
	
	public SocketView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	
	public SocketView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		mLastTouchX = 0;
		mLastTouchY = 0;
		
		gc = new GestureDetector(this);
		gc.setOnDoubleTapListener(this);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		boolean interested = false;
		
		AbsoluteLayout.LayoutParams lp = (AbsoluteLayout.LayoutParams) getLayoutParams();
		
		final int action = event.getAction();
		
		float x = event.getX();
		float y = event.getY();
		Log.d(TAG,"OnTouch x: "+x+" y: "+y);
		
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			 
			// Ausgangskoordinaten der Touchgeste speichern
			mLastTouchX = x;
			mLastTouchY = y;
			
			interested = true;
			
			break;
		case MotionEvent.ACTION_MOVE:
			
			// Bewegungsvektor berechnen
			final float dx = x - mLastTouchX;
			final float dy = y - mLastTouchY;
			
			Log.d(TAG, "ACTION_MOVE dx: "+dx+" dy: "+dy);
			
			// View bewegen
			lp.x += dx;
			lp.y += dy;
			setLayoutParams(lp);
			
			Log.d(TAG, "SetLayoutParams durchgeführt");
			
			interested = true;
			
		default:
			
			break;
		}
		
		
		return interested;
	}
	
	@Override
	public boolean onDragEvent(DragEvent event) {
		boolean interested = false;
		
		int action = event.getAction();
			
		ImageView dragView = (ImageView) event.getLocalState();
		
		switch (action) {
		case DragEvent.ACTION_DRAG_STARTED:
			if(Drag_And_Drop_HauseholdActivity.APPLIANCE_CLIPDATA_LABEL.equals(event.getClipDescription().getLabel())) {
				
				setColorFilter(Color.CYAN);
				interested = true;
				
			}
			break;
		case DragEvent.ACTION_DRAG_ENTERED:
			if(Drag_And_Drop_HauseholdActivity.APPLIANCE_CLIPDATA_LABEL.equals(event.getClipDescription().getLabel())) {
				
				setColorFilter(Color.GREEN);
				interested = true;
				
			}
			break;
		case DragEvent.ACTION_DRAG_EXITED:
			if(Drag_And_Drop_HauseholdActivity.APPLIANCE_CLIPDATA_LABEL.equals(event.getClipDescription().getLabel())) {
				
				setColorFilter(Color.CYAN);
				interested = true;
				
			}
			break;
		case DragEvent.ACTION_DROP:
			if(Drag_And_Drop_HauseholdActivity.APPLIANCE_CLIPDATA_LABEL.equals(event.getClipDescription().getLabel())) {
				
				clearColorFilter();
				AbsoluteLayout parent = (AbsoluteLayout) getParent();
				AbsoluteLayout.LayoutParams lp = (AbsoluteLayout.LayoutParams) getLayoutParams();
				parent.removeView(this);
				parent.addView(dragView, lp);
				parent.invalidate();
				
				interested = true;
				
			}							
			break;
		case DragEvent.ACTION_DRAG_ENDED:
				clearColorFilter();
				dragView.setVisibility(View.VISIBLE);
				interested = true;
			break;
		default:
			break;
		}
	
		return interested;
	}

	public boolean onDoubleTap(MotionEvent arg0) {
		// Elternview ermitteln 
		ViewGroup parent = (ViewGroup) getParent();
		// Sich aus der Viewhierarchie löschen
		parent.removeView(this);
		// Parent neuzeichnen lassen
		parent.invalidate();
		
		return true;
	}

	public boolean onDoubleTapEvent(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean onSingleTapConfirmed(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean onDown(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean onFling(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	public void onLongPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	public void onShowPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public boolean onSingleTapUp(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
