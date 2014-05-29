import javax.swing.*;

import java.awt.Container;

public class PhysicsLab {
   public static void main(String[] args) {
      PhysicsLab_GUI lab_gui = new PhysicsLab_GUI();
      lab_gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      lab_gui.setVisible(true);
   }
}

class PhysicsLab_GUI extends JFrame {
   public PhysicsLab_GUI() {
      setTitle("My Small and Nice Physics Laboratory");
      setSize(MyWorldView.WIDTH, MyWorldView.HEIGHT+50);  // height+50 to account for menu height
      MyWorld world = new MyWorld();
      MyWorldView  worldView = new MyWorldView(world);
      world.setView(worldView);
      add(worldView);  
      LabMenuListener menuListener = new LabMenuListener(world);
      /*  .....   */;
      setJMenuBar(createLabMenuBar(menuListener));
   }

   public JMenuBar createLabMenuBar(LabMenuListener menu_l) {
      JMenuBar mb = new JMenuBar();
      
      JMenu menu = new JMenu ("Configuration");
      mb.add(menu);
      JMenu subMenu = new JMenu("Insert");  
      menu.add(subMenu);
      
      JMenuItem menuItem = new JMenuItem("Ball");
      menuItem.addActionListener(menu_l);
      subMenu.add(menuItem);
      
      JMenuItem menuItem_1 = new JMenuItem("Spring");
      menuItem_1.addActionListener(menu_l);
      subMenu.add(menuItem_1);
      
      JMenuItem menuItem_2 = new JMenuItem("Fixed Hook");
      menuItem_2.addActionListener(menu_l);
      subMenu.add(menuItem_2);
      
      JMenuItem menuItem_3 = new JMenuItem("My scenario");
      menuItem_3.addActionListener(menu_l);
      subMenu.add(menuItem_3);
      
      
 /*....*/      
      menu = new JMenu("MyWorld");
      mb.add(menu);
      menuItem = new JMenuItem("Start");
      JMenuItem menuItem2 = new JMenuItem("Stop");
      JMenu menuItem3 = new JMenu("Simulator");
      
      menuItem.addActionListener(menu_l);
      menuItem2.addActionListener(menu_l);
      menuItem3.addActionListener(menu_l);
      menu.add(menuItem);
      menu.add(menuItem2);
      menu.add(menuItem3);
      
      //JMenuItem submenuItem = new JMenuItem("refresh time");
     JMenuItem submenuItem2 = new JMenuItem("Delta time");
      
      JMenuItem submenuItem1 = new JMenuItem("View Refresh time");
      submenuItem1.addActionListener(menu_l);
      menuItem3.add(submenuItem1);
      submenuItem2.addActionListener(menu_l);
      menuItem3.add(submenuItem2);
      return mb;          
   }   
}