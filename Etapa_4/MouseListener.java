import javax.swing.JPanel;

import java.awt.event.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class MouseListener extends MouseAdapter {
   private MyWorld world;
   private PhysicsElement currentElement;
   public MouseListener (MyWorld w){
      world = w;
   } 
   public void mouseMoved(MouseEvent e) {
      Point2D.Double p = new Point2D.Double(0,0); // Change mouse coordenates from
      MyWorldView.SPACE_INVERSE_TRANSFORM.transform(e.getPoint(),p);// pixels to meters.
      PhysicsElement newElement = world.find(p.getX(), p.getY()); 
 if(world.isPausado()){
      if (newElement == currentElement) return;
      
      if (currentElement != null) {
         currentElement.setReleased();
         currentElement = null;
      }
      if (newElement != null) { 
         currentElement = newElement;
         currentElement.setSelected();
      }
 }
      world.repaintView();
   }
   public void mouseDragged(MouseEvent e) {
	   Point2D.Double p = new Point2D.Double(0,0); // Change mouse coordenates from
	   MyWorldView.SPACE_INVERSE_TRANSFORM.transform(e.getPoint(),p);// pixels to meters.
	 //  PhysicsElement newElement = world.find(p.getX(), p.getY()); 
	   if(world.isPausado()){
	  // if (newElement != null) { 
	  if (currentElement != null) { 
				 //        currentElement = newElement;
	         //currentElement.setSelected();
	         currentElement.dragTo(p.getX());
	      }
	   }
	   world.repaintView();
   }
   public void mouseReleased(MouseEvent e) {
	   if(world.isPausado()){
	   
	   if (currentElement == null) return;
      if (currentElement instanceof Spring) {
         Point2D.Double p= new Point2D.Double(0,0);
         MyWorldView.SPACE_INVERSE_TRANSFORM.transform(e.getPoint(),p);

          // we dragged a spring, so we look for and attachable element near by  
         SpringAttachable element = world.findAttachableElement(p.getX());
         if (element != null) {
            // we dragged a spring and it is near an attachable element,
            // so we hook it to a spring end.
            Spring spring = (Spring) currentElement;
            double a=spring.getAendPosition();
            if (a==p.getX())spring.attachAend(element);
            double b=spring.getBendPosition();
            if (b==p.getX())spring.attachBend(element);
          }
      }    
      currentElement.setReleased();
      currentElement = null;
      world.repaintView();
   }
   }
}