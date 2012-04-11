package de.haw.dsms.dragdrop;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AbsoluteLayout;
import android.widget.ImageView;

@SuppressWarnings("deprecation")
public class SocketOnTouchListener implements OnTouchListener {

	private static final String TAG = "SocketOnTouchListener";
	
//	private static final int INVALID_POINTER_ID = -1;
	
//	private int mActivePointerId = INVALID_POINTER_ID;
	
	private float mLastTouchX = 0;
	private float mLastTouchY = 0;
	
	public boolean onTouch(View v, MotionEvent event) {
		
		boolean handled = false;
		
		ImageView view = (ImageView) v;
		AbsoluteLayout.LayoutParams lp = (AbsoluteLayout.LayoutParams) view.getLayoutParams();
		
		final int action = event.getAction();
		
		float x = event.getX();
		float y = event.getY();
		Log.d(TAG,"OnTouch x: "+x+" y: "+y);
		
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			 
			// Ausgangskoordinaten der Touchgeste speichern
			mLastTouchX = x;
			mLastTouchY = y;
			
			handled = true;
			
			break;
		case MotionEvent.ACTION_MOVE:
			
			// Bewegungsvektor berechnen
			final float dx = x - mLastTouchX;
			final float dy = y - mLastTouchY;
			
			Log.d(TAG, "ACTION_MOVE dx: "+dx+" dy: "+dy);
			
			// View bewegen
			lp.x += dx;
			lp.y += dy;
			view.setLayoutParams(lp);
			
			Log.d(TAG, "SetLayoutParams durchgeführt");
			
			handled = true;
			
		default:
			
			break;
		}
		
		
		return handled;
	}

}
