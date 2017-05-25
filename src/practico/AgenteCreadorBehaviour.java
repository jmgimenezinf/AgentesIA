package practico;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.util.Logger;


public class AgenteCreadorBehaviour extends TickerBehaviour{
	private Logger myLogger = Logger.getMyLogger(getClass().getName());
	
	public AgenteCreadorBehaviour(Agent a, long period) {
		super(a, period);
		// TODO Auto-generated constructor stub
	}

	public void reset(){
		super.reset();
		myLogger.log(Logger.SEVERE,"se reseteo");
	}
	
	protected void onTick() {
		myLogger.log(Logger.SEVERE,this.getAgent().getLocalName());
		System.out.println("hola agente creador");
		
	}	

}
