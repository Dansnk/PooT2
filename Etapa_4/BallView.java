import java.awt.*;
import java.awt.geom.*;

/**
 * Esta clase define la vista el elemento fisico Ball
 * @version 22/05/2014/Final
 * @author Daniel Veas, Matias Lacasia, Carlos Polanco
 */
public class BallView {
   private Color color = Color.BLUE;
   private Ellipse2D.Double shape = null;
   private Ball ball;
   
   /**
    * Constructor de la clase BallView, asigna una ball y crea un trazo de elipse
    * @param b Bola asignada a la vista actual
    */
   public BallView (Ball b){
	   ball = b;
	   shape = new Ellipse2D.Double();
   }
   
   /**
   * Revisa si x,y esta dentro del shape de ese objeto.
   * @param x valor en el eje X.
   * @param y valor en el eje y.
   * @return True Si los puntos se encuentran dentro de x e y. False en otro caso
   */
   public boolean contains (double x, double y){
       return shape.getBounds2D().contains(x,y);
   }
   
   /**
    * Metodo que cambia el color a Rojo de la la vista de Ball cuando se le llama
    */
   public void setSelected (){
      color = Color.RED;
   }
   
   /**
    * Metodo que cambia el color a azul de la la vista de Ball cuando se le llama
    */
   public void setReleased() {
      color = Color.BLUE;
   }
   
   /**
    * Metodo que actualiza la vista del objeto, segun 
    * @param g
    */
   void updateView(Graphics2D g) {
      double radius = ball.getRadius();
      shape.setFrame(ball.getPosition()-radius, -radius, 2*radius, 2*radius);
      g.setColor(color);
      g.fill(shape);
   }
}
