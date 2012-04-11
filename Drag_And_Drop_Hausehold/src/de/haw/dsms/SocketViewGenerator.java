
package de.haw.dsms;

import de.haw.dmsms.R;
import de.haw.dsms.dragdrop.SocketOnDragListener;
import de.haw.dsms.dragdrop.SocketOnLongClickListener;
import de.haw.dsms.dragdrop.SocketOnTouchListener;
import android.content.Context;
import android.widget.AbsoluteLayout;
import android.widget.ImageView;

/**
 * @author armin
 *
 */
@SuppressWarnings("deprecation")
public class SocketViewGenerator {
	
	private AbsoluteLayout movementLayer;
	private Context mContext;
	private AbsoluteLayout.LayoutParams layoutParams;
	
	public SocketViewGenerator(AbsoluteLayout movement, Context context){
		
		movementLayer = movement;
		mContext = context;
		
	}
	
	public void addNewSocket(int posX, int posY) {
		
		ImageView socket = new ImageView(mContext);
		socket.setImageResource(R.drawable.socket);
		socket.setOnDragListener(new SocketOnDragListener());
		socket.setOnTouchListener(new SocketOnTouchListener());
		socket.setOnLongClickListener(new SocketOnLongClickListener());
		
		layoutParams = new AbsoluteLayout.LayoutParams(AbsoluteLayout.LayoutParams.WRAP_CONTENT, AbsoluteLayout.LayoutParams.WRAP_CONTENT, posX, posY);
		movementLayer.addView(socket, layoutParams);
		
	}
	
}
