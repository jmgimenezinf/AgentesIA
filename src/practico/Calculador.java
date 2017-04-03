package practico;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.util.Logger;


public class Calculador extends Agent {
	private CalculadorBehaviour behaviour;
	private Logger myLogger = Logger.getMyLogger(getClass().getName());
	
	protected void setup(){
		DFAgentDescription dfd = new DFAgentDescription();
		ServiceDescription sd = new ServiceDescription();
		sd.setType("Imprimir");
		sd.setName(getName());
		sd.setOwnership("TILAB");
		dfd.setName(getAID());
		dfd.addServices(sd);
		try {
			DFService.register(this, dfd);
			this.setBehavior(new CalculadorBehaviour(this,10000));
			this.addBehaviour(this.getBehavior());
		} 	
		catch (FIPAException e) {
			myLogger.log(Logger.SEVERE, "Agent "+getLocalName()+" - Cannot register with DF", e);
		}

		
	}
	
	public void setBehavior(CalculadorBehaviour behavior){
		this.behaviour=behavior;
	}
	
	public CalculadorBehaviour getBehavior(){
		return this.behaviour;
	}

}