import java.util.*;
import java.awt.*;

/**
 * Esta clase define el elemento fisico Ball
 * @version 22/05/2014/Final
 * @author Daniel Veas, Matias Lacasia, Carlos Polanco
 */
public class Ball extends PhysicsElement implements Simulateable, SpringAttachable {
   private static int id=0;  // Ball identification number
   private final double mass;
   private final double radius;
   private double pos_t;     // current position at time t
   private double pos_tPlusDelta;  // next position in delta time in future
   private double speed_t;   // speed at time t
   private double speed_tPlusDelta;   // speed in delta time in future
   private BallView view;  // Ball view of Model-View-Controller design pattern
   private ArrayList<Spring> springs;
   private double a_t;    // acceleration at time t
   private double a_tMinusDelta;  // acceleration delta time ago;
   
   /**
    * Construye una nueva Ball, inicializada a masa 1kg, radio 0.1, posicion y velocidad 0.
    */
   private Ball(){   // nobody can create a block without state
     this(1.0,0.1,0,0);
   }
   
   /**
    * Constructor de la clase Bola, que toma los datos y genera las bolas
    * Ademas genera la lista de spring para unirse a la bola
    * @param mass Masa de la bola
    * @param radius Radio de la bola
    * @param position posicion actual donde se encuentra la bola
    * @param speed velocidad actual de la vola
    */
   public Ball(double mass, double radius, double position, double speed){
      super(id++);
      this.mass = mass;
      this.radius = radius;
      pos_t = position;
      speed_t = speed;
      view = new BallView(this);
      springs = new ArrayList<Spring>();
   }
   
   /**
    * Metodo que retorna la masa de la bola
    * @return Masa de la bola
    */ 
   public double getMass() {
      return mass;
   }
   
   /**
    * Metodo que retorna el radio de la bola
    * @return Radio de la bola
    */
   public double getRadius() {
      return radius;
   }
   
   /**
    * Metodo que retorna la posicion actual de la bola
    * @return Posicion de la bola en el momento actual
    */
   public double getPosition() {
      return pos_t;
   }
   
   /**
    * Metodo que retorna la velocidad actual de la bola
    * @return Velocidad de la bola en el momento actual
    */
   public double getSpeed() {
      return speed_t;
   }
   
   /**
    * Metodo que calcula los proximos valores de velocidad y posicion dado un delta de tiempo.
    * El calculo va a depender si colisionan o no.
    * @param delta_t Intervalo delta de tiempo con el cual se trabajara para calcular los datos
    * @param world   Instancia de MyWorld en la cual se encuentran los elementos fisicos a trabajar
    * @see 
    */
   public void computeNextState(double delta_t, MyWorld world) {
     Ball b;  // Assumption: on collision we only change speed.   
     if ((b=world.findCollidingBall(this))!= null){ /* elastic collision */
        speed_tPlusDelta=(speed_t*(mass-b.getMass())+2*b.getMass()*b.getSpeed())/(mass+b.getMass());
        pos_tPlusDelta = pos_t;
     } else {
    	 a_t= getNetForce()/mass;
         speed_tPlusDelta=speed_t + 0.5*(3*a_t-a_tMinusDelta)*delta_t;
         pos_tPlusDelta = pos_t + speed_t*delta_t + (1/6)*(4*a_t-a_tMinusDelta)*delta_t*delta_t;
     }
   }
   
   /** Metodo que calcula si hay colision entre dos objetos, en este caso son bolas.   
   * @param b Bola con la cual se desea probar la colision
   * @return True si existe colision y False si no existe colision
   */
   public boolean collide(Ball b) {
     if (this == b) return false;
     boolean closeEnougth = Math.abs(getPosition()-b.getPosition()) < (getRadius()+b.getRadius());
     boolean approaching = getSpeed() > b.getSpeed();
     if (b.getPosition() < getPosition())
        approaching = getSpeed() < b.getSpeed();
     return closeEnougth && approaching;
   }
   
   /**
    * Metodo que actualiza los datos de posicion, y velocidad con los datos calculados en ComputeNextState
    */
   public void updateState(){
     pos_t = pos_tPlusDelta;
     speed_t = speed_tPlusDelta;
   }
   
   /**
    * Metodo que actualiza la vista de esta bola.
    * @param Tipo de Grafica 2D con la que se esta trabajando, en este caso es Elipse2D
    */
   public void updateView (Graphics2D g) {   // NEW
     view.updateView(g);  // update this Ball's view in Model-View-Controller design pattern     
   }

   /** Metodo que llama al metodo con el mismo nombre de la vista asociada a est objeto.
    * @return True Si los puntos se encuentran dentro de x e y. False en otro caso
    * @see Ballview.contains(x,y)
    */
   public boolean contains(double x, double y) {
      return view.contains(x,y);
   }
   
   /**
    * Metodo que usa otro metodo de la vista del mismo nombre, el cual al ser llamado pinta del color especifico la bola.
    * @see BallView.setReleased()
    */
   public void setSelected(){
      view.setSelected();
   }
   
   /**
    * Metodo que usa otro metodo de la vista del mismo nombre, el cual al ser llamado pinta del color especifico la bola.
    * @see BallView.setReleased()
    */
   public void setReleased(){
      view.setReleased();
   }
   
   /**
    * Metodo que Arrastra el objeto hasta la posicion seleccionada.
    */
   public void dragTo(double x){
      pos_t=x;
   }
   
   /**
    * Metodo que calcula la fuerza neta que se trabaja en la bola.
    * @return La fuerza neta ejercida en la bola.
    */
   private double getNetForce() {
	   double NetForce = 0;
	   for (Spring spring: springs)
		   NetForce = NetForce + spring.getForce(this);
	   return NetForce;
   }
   
   /**
    * Metodo que entrega la descripcion del string
    * @return String con la id de la posicion y velocidad de una bola
    */
   public String getDescription() {
     return "Ball_" + getId()+":x";
   }
   
   /**
    * Metodo que entrega el estado actual de la posicion y la velocidad.
    * @return String con la posicion y velocidad actual
    */
   public String getState() {
     return getPosition()+"";
   }
   
   /**
    * Metodo que une un spring a una bola.
    * Guarda la bola en el spring dado.
    * @param sa Spring que se unira a la bola
    */
   public void attachSpring(Spring sa){
	   springs.add(sa);
   }
   
   /**
    * Metodo que desune un spring a una bola.
    * Remueve la bola en el spring dado.
    * @param sa Spring que se desunira de la bola
    */
   public void detachSpring(Spring s) {
	springs.remove(s);
   }
}
