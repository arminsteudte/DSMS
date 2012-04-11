package de.haw.dsms.dragdrop;

import de.haw.dmsms.R;
import de.haw.dsms.Drag_And_Drop_HauseholdActivity;
import android.content.ClipData;
import android.content.Context;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;

/**
 * Realisiert das Drag&Drop-Verhalten, dass bei einem langen Halten des
 * Imageview ein Drag Shadow mit dem gleichen Bild des ImageViews erzeugt wird.
 * @author armin
 *
 */
public class ApplianceOnLongClickListener implements OnLongClickListener {

	private Context mContext;
	
	public ApplianceOnLongClickListener(Context context) {
		
		mContext = context;
		
	}
	
	public boolean onLongClick(View v) {
		
		// ClipData Attribut zur Unterscheidung der Symbole (Socket/Appliance) während des
		// Drag&Drop-Vorgangs
		ClipData data = ClipData.newPlainText(Drag_And_Drop_HauseholdActivity.APPLIANCE_CLIPDATA_LABEL, "");
		DragShadowBuilder shadowBuilder = new DragShadowBuilder(v);
		ImageView appliance = new ImageView(mContext);
		appliance.setImageResource(R.drawable.washer);
		//Drag&Drop-Geste einleiten
		v.startDrag(data, shadowBuilder, appliance, 0);
		//v.setVisibility(View.INVISIBLE);
		
		return true;
	}

}
