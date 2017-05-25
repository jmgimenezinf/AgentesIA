package practico;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.util.Logger;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.ControllerException;
import jade.wrapper.PlatformController;
import jade.wrapper.StaleProxyException;


public class AgenteCreador extends Agent {

	private AgenteCreadorBehaviour behaviour;
	private Logger myLogger = Logger.getMyLogger(getClass().getName());
	private DFAgentDescription dfd;

	protected void setup(){
		this.setDfd(new DFAgentDescription()); ;
		ServiceDescription sd = new ServiceDescription();
		sd.setType("AgenteCreador");
		sd.setName(getName());
		sd.setOwnership("Los4M^2.P.S");
		this.getDfd().setName(getAID());
		this.getDfd().addServices(sd);
		
		try {
			DFService.register(this,this.getDfd());
			this.setBehaviour(new AgenteCreadorBehaviour(this,1000));
			this.addBehaviour(this.getBehaviour());
		} 	
		catch (FIPAException e) {
			
			myLogger.log(Logger.SEVERE, "Agent "+getLocalName()+" - Cannot register with DF", e);
			doDelete();
		}

		
		ContainerController contenedor = getContainerController();
		try {
			AgentController agenteCreado = contenedor.createNewAgent("Imprime","practico.Imprimir", null);
			agenteCreado.start();
		} catch (StaleProxyException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		
		
	}

	public void setBehaviour(AgenteCreadorBehaviour behavior){
		this.behaviour=behavior;
	}
	
	public AgenteCreadorBehaviour getBehaviour(){
		return this.behaviour;
	}
	public DFAgentDescription getDfd() {
		return dfd;
	}
	public void setDfd(DFAgentDescription dfd) {
		this.dfd = dfd;
	}

}