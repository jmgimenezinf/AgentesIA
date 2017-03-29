package practico;
import jade.core.*;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.util.Logger;
import practico.SaludoBehaviour;

public class Saludo extends Agent {
	
	private Logger myLogger = Logger.getMyLogger(getClass().getName());
	private SaludoBehaviour behaviour;
	
	protected void setup() {
		// Registration with the DF 
		DFAgentDescription dfd = new DFAgentDescription();
		ServiceDescription sd = new ServiceDescription();   
		sd.setType("Saludo"); 
		sd.setName(getName());
		sd.setOwnership("TILAB");
		dfd.setName(getAID());
		dfd.addServices(sd);
		try {
			DFService.register(this,dfd);
			this.setBehaviour(new SaludoBehaviour(this));
			this.addBehaviour(this.getBehaviour());
			
		} catch (FIPAException e) {
			myLogger.log(Logger.SEVERE, "Agent "+getLocalName()+" - Cannot register with DF", e);
			doDelete();
		}
	}
	
	private SaludoBehaviour getBehaviour(){
		return this.behaviour;
	}
	private void setBehaviour(SaludoBehaviour behaviour){
		this.behaviour=behaviour;
	}

}
