import java.util.*;
import java.io.*;
import javax.swing.Timer;
import java.awt.event.*;

/**
 * Esta clase define la clase Mundo, en la cual se implementaran los objetos
 * @version 22/05/2014/Final
 * @author Daniel Veas, Matias Lacasia, Carlos Polanco
 */
public class MyWorld implements ActionListener {
   private PrintStream out;
   private ArrayList<PhysicsElement> elements;  // array to hold everything in my world.
   private MyWorldView view;   // NEW
   private Timer passingTime;   // NEW
   private double t;        // simulation time
   private double delta_t;        // in seconds
   private double refreshPeriod;  // in seconds
   private int stopped=0;
   
   /**
    * Constructor por defecto de la clase MyWorld
    */
   public MyWorld(){
      this(System.out);  // delta_t= 0.1[ms] and refreshPeriod=200 [ms]
   }
   
   /**
    * Constructor principal de la Clase, la cual inicia todos los datos con que se trabajara.
    * @param output
    */
   public MyWorld(PrintStream output){
      out = output;
      t = 0;
      refreshPeriod = 0.06;      // 60 [ms]
      delta_t = 0.00001;          // 0.01 [ms]
      elements = new ArrayList<PhysicsElement>();
      view = null;
      passingTime = new Timer((int)(refreshPeriod*1000), this);    
   }
   
   /**
    * Metodo que adhiere un elemento Fisico a my World.
    * Ademas actualiza la Vista de MyWorls para que aparezca el objeto
    * @param e
    */
   public void addElement(PhysicsElement e) {
      elements.add(e);
      view.repaintView();
   }
   
   /**
    * Metodo que selecciona la vista con la que trabajara MyWorld
    * @param view Objeto del tipo MyWorld que 
    */
   public void setView(MyWorldView view) {
      this.view = view;
   }
   
   /**
    * Metodo que selecciona el delta de tiempo con el que se trabajara
    * @param delta Tiempo en el cual se trabajara
    */
   public void setDelta_t(double delta) {
      delta_t = delta;
   }
   
   /**
    * Metodo que selecciona el periodo de refresco con el que se trabajara
    * @param rp Tiempo de refresco para trabajar en MyWorld
    */
   public void setRefreshPeriod (double rp) {
      refreshPeriod = rp;
      passingTime.setDelay((int)(refreshPeriod*1000)); // convert from [s] to [ms]
   }
   
   /**
    * Metodo que comienza a correr el tiempo de trabajo.
    */
   public void start() {
      if(passingTime.isRunning()) return;
      passingTime.start();    
      stopped=1;
   }
   
   /**
    * Metodo que detiene el tiempo cuando esta corriendo.
    */
   public void stop(){
	   passingTime.stop();
	   stopped=0;
   }
   
   /**
    * Metodo que revisa si el tiempo esta pausado o no
    * @return True si se encuentra pausado
    */
   public boolean isPausado(){
	   
	   if(stopped==0){
		   return true;
	   }
	   else
		   return false;
	   
   }
   
   /** Metodo que captura los eventos realizados, en el cual ante cualquier cambio en MyWorld, genera los futuros estados de los Elementos Fisicos
    * @event Evento-Accion realizadas
    */
   public void actionPerformed (ActionEvent event) {  // like simulate method of Assignment 1, 
	   this.repaintView();
	   double nextStop=t+refreshPeriod;                // the arguments are attributes here.
      for (; t<nextStop; t+=delta_t){
         for (PhysicsElement e: elements)
            if (e instanceof Simulateable) {
               Simulateable s = (Simulateable) e;
               s.computeNextState(delta_t,this); // compute each element next state based on current global state
               this.repaintView();
            }
         for (PhysicsElement e: elements)  // for each element update its state. 
            if (e instanceof Simulateable) {
               Simulateable s = (Simulateable) e;
               s.updateState();   
               this.repaintView();// update its state
            }
      }
 
   }
   
   /**
    * Metodoque actualiza la vista en el mundo, vuelve a pintar los objetos.
    */
   public void repaintView(){
      view.repaintView();
   }
   
   /**
    * Metodo que toma una bola y analiza los elementos en el mundo en ese momento para ver si hay colision con otra bola. 
    * Compara si el elemento es una bola y no es la misma, para luego analizar si existe colision en ese momento entre ellas.
    * @param me Bola a la cual se desea saber si hay colision
    * @return null si no hay colision, o Bal b que es la bola con la cual se colisiona
    */
   public Ball findCollidingBall(Ball me) {
      for (PhysicsElement e: elements)
         if ( e instanceof Ball) {
            Ball b = (Ball) e;
            if ((b!=me) && b.collide(me)) return b;
         }
      return null;
   }
   
   /**
    * Metodo que retorna una ArrayList de elementos fisicos de MyWorld
    * @return lista de elementos que se encuentran en MyWorld
    */
   public ArrayList<PhysicsElement> getPhysicsElements(){
      return elements;
   }
   
   /**
    * Retorna el elemento fisico que se encuentra en esa posicion x,y
    * @param x posicion en el eje X del elemento
    * @param y posicion en el eje Y del elemento
    * @return Elemento Fisico en la posicion si es que es encontrado
    */
   public PhysicsElement find(double x, double y) {
      for (PhysicsElement e: elements)
            if (e.contains(x,y)) return e;
      return null;
   }
   
   /**
    * Metodo que busca un Spring
    * @param x
    * @return
    */
	public SpringAttachable findAttachableElement(double x) {	
		for(PhysicsElement e: elements){
	        if((e instanceof SpringAttachable) && e.contains(x,0)){
	        	 return (SpringAttachable) e;
	         }
		}
		return null;
	}
}  
