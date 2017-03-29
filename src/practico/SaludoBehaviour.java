package practico;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.util.Logger;
import jade.core.behaviours.*;

public class SaludoBehaviour extends CyclicBehaviour {
	
	private Logger myLogger = Logger.getMyLogger(getClass().getName());
	private ACLMessage respuesta;
	private ACLMessage mensaje;
	
	public SaludoBehaviour(Agent agente) {
		super(agente);
	}

	public void action() {
		this.setMensaje(this.getAgent().receive()); 
		
		if (this.getMensaje() != null){
			this.setRespuesta(this.getMensaje().createReply());
			
			if (this.getMensaje().getPerformative() == ACLMessage.REQUEST){
				String cuerpoMsj = this.getMensaje().getContent();
				if (cuerpoMsj.equalsIgnoreCase("hola")){
					myLogger.log(Logger.INFO, "Agent "+this.getAgent().getLocalName()+" - Received HOLA Request from "+ this.getMensaje().getSender().getLocalName());
					this.getRespuesta().setPerformative(ACLMessage.INFORM);
					this.getRespuesta().setContent("Hola, Soy el agente 'Saludo',mucho gusto");
				}
				else{
					myLogger.log(Logger.INFO, "Agent "+ this.getAgent().getLocalName()+" - Unexpected request ["+cuerpoMsj+"] received from "+this.getMensaje().getSender().getLocalName());
					this.getRespuesta().setPerformative(ACLMessage.REFUSE);
					this.getRespuesta().setContent("( UnexpectedContent ("+cuerpoMsj+"))");
				}
			}
			this.getAgent().send(this.getRespuesta());
		}
		else{
			this.block();
		}
		
	}

	public ACLMessage getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(ACLMessage respuesta) {
		this.respuesta = respuesta;
	}

	public ACLMessage getMensaje() {
		return mensaje;
	}

	public void setMensaje(ACLMessage mensaje) {
		this.mensaje = mensaje;
	}
	
}
