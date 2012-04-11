package de.haw.dsms.dragdrop;

import android.content.ClipData;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnLongClickListener;
import de.haw.dsms.Drag_And_Drop_HauseholdActivity;

/**
 * @author armin
 * Diese Klasse realisiert das Drag&Drop-Verhalten der Socketobjekte.
 * Es wird ein Drag Shadow mit identischem Bild erzeugt und der Drag&Drop-
 * Prozess gestartet.
 */
public class SocketOnLongClickListener implements OnLongClickListener {
	
	/* (non-Javadoc)
	 * @see android.view.View.OnLongClickListener#onLongClick(android.view.View)
	 */
	public boolean onLongClick(View v) {		
		// ClipData Attribut zur Unterscheidung der Symbole (Socket/Appliance) während des
		// Drag&Drop-Vorgangs
		ClipData data = ClipData.newPlainText(Drag_And_Drop_HauseholdActivity.SOCKET_CLIPDATA_LABEL, "");
		DragShadowBuilder shadowBuilder = new DragShadowBuilder(v);
		// Drag&Drop-Geste einleiten	
		v.startDrag(data, shadowBuilder, v, 0);
		
		return true;
	}

}
