import java.util.*;
import java.awt.*;

public class FixedHook extends PhysicsElement implements SpringAttachable {
	private static int id=0;
	private  double pos_t;     // current position at time t
	private final double speed_t;   // speed at time t
	private ArrayList<Spring> springs; 
	private FixedHookView view;
	   
	protected FixedHook(double position) {
		super(id++);
	    pos_t = position;
	    speed_t = 0;
	    springs = new ArrayList<Spring>();
	    view = new FixedHookView(this);
	}

	@Override
	  public void attachSpring(Spring spring){
		   springs.add(spring);		
	   }

	@Override
	public void detachSpring(Spring s) {
		springs.remove(s);		
	}

	@Override
	public double getPosition() {
		return pos_t;
	}

	@Override
	public String getDescription() {
		String s = "Hook"+this.getId();
		   return s;
	}

	@Override
	public String getState() {
		 String s;
		   s=Double.toString(this.pos_t);
		   return  s ;
	}

	@Override
	public void updateView(Graphics2D g) {
		// TODO Auto-generated method stub
		 view.updateView(g);
	}

	@Override
	public boolean contains(double x, double y) {
		// TODO Auto-generated method stub
		return view.contains(x,y);
	}

	@Override
	public void setSelected() {
		view.setSelected();
		
	}

	@Override
	public void setReleased() {
		// TODO Auto-generated method stub
		view.setReleased();
	}

	@Override
	public void dragTo(double x) {
		// TODO Auto-generated method stub
		pos_t=x;
	}
/* to be coded
*/}