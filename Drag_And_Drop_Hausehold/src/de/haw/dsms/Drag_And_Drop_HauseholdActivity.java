package de.haw.dsms;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.widget.AbsoluteLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.sileria.android.Kit;

import de.haw.dmsms.R;
import de.haw.dsms.dragdrop.ApplianceOnLongClickListener;
import de.haw.dsms.dragdrop.MovementLayerOnDragListener;
import de.haw.dsms.dragdrop.SocketOnLongClickListener;

@SuppressWarnings("deprecation")
public class Drag_And_Drop_HauseholdActivity extends Activity {
    /** Called when the activity is first created. */
	
	//---------------------------------------------------------------
	// Konstanten
	//---------------------------------------------------------------
	public final static String APPLIANCE_CLIPDATA_LABEL = "APPLIANCE";
	public final static String SOCKET_CLIPDATA_LABEL = "SOCKET";
	
	//---------------------------------------------------------------
	// Instanzvariablen
	//---------------------------------------------------------------
	private final static int X_POS_SPAWN = 640;
	private final static int Y_POS_SPAWN = 400;
	
	
	//---------------------------------------------------------------
	// Methoden
	//---------------------------------------------------------------
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	// Initialisierung Aniqroid Library
    	Kit.init(getApplicationContext());
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // MovementLayer
        AbsoluteLayout movementLayer = (AbsoluteLayout) findViewById(R.id.movementLayer);
        movementLayer.setOnDragListener(new MovementLayerOnDragListener(this));
        
        // RelativeLayout
        RelativeLayout stack = (RelativeLayout) findViewById(R.id.stack);
        stack.getParent().requestDisallowInterceptTouchEvent(true);
        
        WrappingSlidingDrawer drawer = (WrappingSlidingDrawer) findViewById(R.id.drawer);
        drawer.setOnDragListener(new OnDragListener() {
			
			public boolean onDrag(View v, DragEvent event) {
				
				Log.d("Drawer", "OnDrag erhalten: "+event.getAction());
				
				return false;
			}
		});
        
        LinearLayout drawerLayer = (LinearLayout) findViewById(R.id.drawerLayer);
        drawerLayer.setOnDragListener(new View.OnDragListener() {
			
			public boolean onDrag(View v, DragEvent event) {

				Log.d("DrawerLayer", "OnDrag erhalten: "+event.getAction());
				
				return false;
			}
		});
        
        // Initialisierung nur beim App-Start
        if(savedInstanceState == null) {
        
	        //ImageViews der Icons besorgen
	        ImageView washerView = (ImageView) findViewById(R.id.imageView1);
	        ImageView socketView = (ImageView) findViewById(R.id.imageView2);
	        ImageView socketView2 = (ImageView) findViewById(R.id.imageView3);
	        ImageView washerView2 = (ImageView) findViewById(R.id.imageView4);
	        
	        /*
	         * Mit OnLongClickListener auf langes Halten des Images
	         * reagieren und Drag&Drop-Operation einleiten.
	         * Als DragShadow identische View (Bild) verwenden. 
	         */
	        washerView.setOnLongClickListener(new ApplianceOnLongClickListener(this));
	        washerView2.setOnLongClickListener(new ApplianceOnLongClickListener(this));
	        socketView.setOnLongClickListener(new SocketOnLongClickListener());
	        socketView2.setOnLongClickListener(new SocketOnLongClickListener());
	        
	        SocketViewGenerator socktes = new SocketViewGenerator((AbsoluteLayout) findViewById(R.id.movementLayer), this);
	        socktes.addNewSocket(X_POS_SPAWN, Y_POS_SPAWN);
	        
//	        socketView.setOnDragListener(new SocketOnDragListener());
        }
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	
    	// Termienieren von der Aniqroid Library
    	Kit.destroy();
    }
}