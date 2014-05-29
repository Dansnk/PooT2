import java.awt.*;
import java.awt.geom.*;

public class FixedHookView {
   private final double width=2;  // fixed point width
   private Color color = Color.GREEN;
   private Rectangle2D.Double shape = null;
   private FixedHook hook;

   
   public FixedHookView (FixedHook f){
	   hook=f;
	   shape= new Rectangle2D.Double();
   }
   public boolean contains (double x, double y){
	   return shape.getBounds2D().contains(x,y);
   }
   public void setSelected (){
	      color = Color.RED;
	   }
   
   public void setReleased() {
	      color = Color.GREEN;
	   }
   void updateView(Graphics2D g) {	     
	      shape.setFrame(hook.getPosition()-0.125, -0.125, 0.25, 0.25);
	      g.setColor(color);
	      g.fill(shape);
	   }
   
}
