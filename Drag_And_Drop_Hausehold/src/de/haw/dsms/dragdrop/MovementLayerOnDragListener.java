package de.haw.dsms.dragdrop;

import android.content.Context;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.widget.AbsoluteLayout;
import de.haw.dmsms.R;
import de.haw.dsms.Drag_And_Drop_HauseholdActivity;
import de.haw.dsms.SocketView;

@SuppressWarnings("deprecation")
public class MovementLayerOnDragListener implements OnDragListener {
	
	private static final String TAG = "MovementLayerOnDragListener";

	private Context mContext;
	
	public MovementLayerOnDragListener(Context context) {
		
		mContext = context;
		
	}
	
	public boolean onDrag(View v, DragEvent event) {
		
		boolean handled = false;
		
		final int action = event.getAction();
		
//		Log.d(TAG, "OnDrag erhalten mit Label: "+event.getClipDescription().getLabel());
//		Log.d(TAG, "OnDrag erhalten mit Action: "+action);
		
		switch (action) {
		case DragEvent.ACTION_DRAG_STARTED:
			if(Drag_And_Drop_HauseholdActivity.SOCKET_CLIPDATA_LABEL.equals(event.getClipDescription().getLabel())) {
				
				Log.d(TAG, "ACTION_DRAG_STARTED erhalten");
				
				handled = true;
			}
			break;
		case DragEvent.ACTION_DROP:
			if(Drag_And_Drop_HauseholdActivity.SOCKET_CLIPDATA_LABEL.equals(event.getClipDescription().getLabel())) {
				Log.d(TAG, "ACTION_DROP erhalten");
				
				// X- und Y-Koordinaten merken wo der DragShadow abgelegt wurde
				final float x = event.getX();
				final float y = event.getY();
				
				// LayoutParameter mit aktuellen Koordinaten erstenn und View auf dem AbsoluteLayout platzieren
				AbsoluteLayout parent = (AbsoluteLayout) v;
				AbsoluteLayout.LayoutParams lp = new AbsoluteLayout.LayoutParams(AbsoluteLayout.LayoutParams.WRAP_CONTENT, AbsoluteLayout.LayoutParams.WRAP_CONTENT,(int) x,(int) y);
				SocketView socket = new SocketView(mContext);
				socket.setImageResource(R.drawable.socket);
				
				// Eventlistener für Verschieben und Drag&Drop bereitstellen
//				socket.setOnTouchListener(new SocketOnTouchListener());
//				socket.setOnDragListener(new SocketOnDragListener());
				
				// Listener hinzufügen
				parent.addView(socket, lp);	
				parent.invalidate();
				
				handled = true;
			}
			break;
		}
		
		return handled;
	}

}
