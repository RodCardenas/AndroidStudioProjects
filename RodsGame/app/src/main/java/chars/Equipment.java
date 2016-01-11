package chars;

import com.rod.rodsgame.R.drawable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

public class Equipment { 
	
	public Head helmet;
	public Body armor;
	public Hand rGlove;
	public Hand lGlove;
	public Feet boots;
	
	public Equipment(Context c) 
	{		
		helmet = new Head();
		helmet.look = BitmapFactory.decodeResource(c.getResources(), drawable.white_blonde_hair);

		armor = new Body();
		armor.look 	= 	 BitmapFactory.decodeResource(c.getResources(),drawable.body_white);
		
		rGlove = new Hand();
		rGlove.look = 	 BitmapFactory.decodeResource(c.getResources(),drawable.hand_white);
		
		lGlove = new Hand();
		lGlove.look =	flip(BitmapFactory.decodeResource(c.getResources(),drawable.hand_white),2);
		
		boots = new Feet();
		boots.look 	= 	BitmapFactory.decodeResource(c.getResources(),drawable.feet_white);
	}

	
	public static final int FLIP_VERTICAL = 1;
	public static final int FLIP_HORIZONTAL = 2;
	 
	public static Bitmap flip(Bitmap src, int type) {
	    // create new matrix for transformation
	    Matrix matrix = new Matrix();
	    // if vertical
	    if(type == FLIP_VERTICAL) {
	        // y = y * -1
	        matrix.preScale(1.0f, -1.0f);
	    }
	    // if horizontal
	    else if(type == FLIP_HORIZONTAL) {
	        // x = x * -1
	        matrix.preScale(-1.0f, 1.0f);
	    // unknown type
	    } else {
	        return null;
	    }
	 
	    // return transformed image
	    return Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true);
	}
}
