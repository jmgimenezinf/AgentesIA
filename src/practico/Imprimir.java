package practico;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.util.Logger;


public class Imprimir extends Agent {
	private ImprimirBehaviour behaviour;
	private Logger myLogger = Logger.getMyLogger(getClass().getName());
	private DFAgentDescription dfd;
	

	protected void setup(){
		this.setDfd(new DFAgentDescription());
		ServiceDescription sd = new ServiceDescription();
		sd.setType("Imprimir");
		sd.setName(getName());
		this.getDfd().setName(getAID());
		this.getDfd().addServices(sd);
			try {
				DFService.register(this, dfd);
			} catch (FIPAException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				myLogger.log(Logger.SEVERE, "Agent "+getLocalName()+" - Cannot register with DF",e);

			}
			this.setBehavior(new ImprimirBehaviour(this,1000));
			this.addBehaviour(this.getBehavior());

		
	}
	
	public void setBehavior(ImprimirBehaviour behavior){
		this.behaviour=behavior;
	}
	
	public ImprimirBehaviour getBehavior(){
		return this.behaviour;
	}

	public DFAgentDescription getDfd() {
		return dfd;
	}

	public void setDfd(DFAgentDescription dfd) {
		this.dfd = dfd;
	}

}
