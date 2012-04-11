package de.haw.dsms.dragdrop;

import android.graphics.Color;
import android.view.DragEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.widget.AbsoluteLayout;
import android.widget.ImageView;
import de.haw.dsms.Drag_And_Drop_HauseholdActivity;

/**
 * @author armin
 * Realisiert das Verhalten der Socketobjekte in den verschiedenen
 * Drag&Drop-Phasen. Hierbei wird die Farbe für Hervorhebung gesetzt und
 * zum Abschluss der Geste das Bild des ImageViews geändert.
 */
@SuppressWarnings("deprecation")
public class SocketOnDragListener implements OnDragListener {

	/* (non-Javadoc)
	 * @see android.view.View.OnDragListener#onDrag(android.view.View, android.view.DragEvent)
	 */
	public boolean onDrag(View v, DragEvent event) {
		boolean interested = false;
		
		int action = event.getAction();
			
		ImageView dragView = (ImageView) event.getLocalState();
		ImageView view = (ImageView) v;
		
		switch (action) {
		case DragEvent.ACTION_DRAG_STARTED:
			if(Drag_And_Drop_HauseholdActivity.APPLIANCE_CLIPDATA_LABEL.equals(event.getClipDescription().getLabel())) {
				
				view.setColorFilter(Color.CYAN);
				interested = true;
				
			}
			break;
		case DragEvent.ACTION_DRAG_ENTERED:
			if(Drag_And_Drop_HauseholdActivity.APPLIANCE_CLIPDATA_LABEL.equals(event.getClipDescription().getLabel())) {
				
				view.setColorFilter(Color.GREEN);
				interested = true;
				
			}
			break;
		case DragEvent.ACTION_DRAG_EXITED:
			if(Drag_And_Drop_HauseholdActivity.APPLIANCE_CLIPDATA_LABEL.equals(event.getClipDescription().getLabel())) {
				
				view.setColorFilter(Color.CYAN);
				interested = true;
				
			}
			break;
		case DragEvent.ACTION_DROP:
			if(Drag_And_Drop_HauseholdActivity.APPLIANCE_CLIPDATA_LABEL.equals(event.getClipDescription().getLabel())) {
				
				view.clearColorFilter();
				AbsoluteLayout parent = (AbsoluteLayout) view.getParent();
				AbsoluteLayout.LayoutParams lp = (AbsoluteLayout.LayoutParams) view.getLayoutParams();
				parent.removeView(view);
				parent.addView(dragView, lp);
				
//				dragView.setVisibility(View.VISIBLE);
				interested = true;
				
			}							
			break;
		case DragEvent.ACTION_DRAG_ENDED:
				view.clearColorFilter();
				dragView.setVisibility(View.VISIBLE);
				interested = true;
			break;
		default:
			break;
		}
	
		return interested;
	}

}
