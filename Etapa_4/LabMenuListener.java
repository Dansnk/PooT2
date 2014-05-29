import java.awt.event.*; 

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class LabMenuListener implements ActionListener {
   private MyWorld  world;
   public LabMenuListener (MyWorld  w){
      world = w;
   }
   public void actionPerformed(ActionEvent e) {
      JMenuItem menuItem = (JMenuItem)(e.getSource());
      String text = menuItem.getText();
      
      // Actions associated to main manu options
      if (text.equals("My scenario")) {  // here you define Etapa2's configuration
    	  Ball b1 = new Ball(1, 0.1, 1, 0);
          FixedHook f1 = new FixedHook(0);
          Ball b2 = new Ball(1, 0.1, 2, 0);

          Spring spring = new Spring(1.5, 0.5);
          spring.attachAend(f1);
          spring.attachBend(b1);
          
          world.addElement(spring);
          world.addElement(f1);
          world.addElement(b1);
           world.addElement(b2);
      }
 if (text.equals("Ball")) {
    	  
    	  double mass = 1.0;      // 1 [kg] 
  	      double radius = 0.1;    // 10 [cm] 
  	      double position = 0.0;  // 1 [m] 
  	      double speed = 0;     // 0.5 [m/s]
  	      Ball b0 = new Ball(mass, radius, position, speed);
  	     // Ball b1 = new Ball(mass, radius, 2.0, 0);
  	      world.addElement(b0);
  	   //   world.addElement(b1);
  	      
  	     
        // nothing by now       
      }
      if (text.equals("Fixed Hook")) 
      {
    	  FixedHook f1 = new FixedHook(0);
           world.addElement(f1);
      }  // ; same as nothing
      if (text.equals("Spring")) 
      {
    	      //Ball b0 = new Ball(1, 0.1, 0, 0);
    	      //Ball b1 = new Ball(1, 0.1, 1.5, 0);
    	      Spring spring = new Spring(1.0, 0.5);
    	    // spring.attachAend(b0);
    	     //spring.attachBend(b1);
    	      world.addElement(spring);
    	      //world.addElement(b0);
    	      //world.addElement(b1);
  	        
    	  
      }

      // Actions associated to MyWorld submenu
      if (text.equals("Start")) 
    	  world.start();/* to be coded */
      if (text.equals("Stop"))    /* to be coded */
    	  world.stop();
    	  
    	  
      if (text.equals("Delta time")) {
         String data = JOptionPane.showInputDialog("Enter delta t [s]");
         world.setDelta_t(Double.parseDouble(data));
      }
      if (text.equals("View Refresh time")) {
    	  String data = JOptionPane.showInputDialog("Enter refresh period t [s]");
          world.setRefreshPeriod(Double.parseDouble(data));
      }
   }
}
