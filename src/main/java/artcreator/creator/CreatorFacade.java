package artcreator.creator;

import artcreator.creator.impl.CreatorImpl;
import artcreator.creator.port.Creator;
import artcreator.domain.DomainFactory;
import artcreator.statemachine.StateMachineFactory;
import artcreator.statemachine.port.StateMachine;
import artcreator.statemachine.port.State.S;

public class CreatorFacade implements CreatorFactory, Creator {

	private CreatorImpl creator;
	private StateMachine stateMachine;
	
	@Override
	public Creator creator() {
		if (this.creator == null) {
			this.stateMachine = StateMachineFactory.FACTORY.stateMachine();
			this.creator = new CreatorImpl(stateMachine, DomainFactory.FACTORY.domain());
		}
		return this;
	}

	@Override
	public void loadImageFromPath(String path) {
		if (this.stateMachine.getState().isSubStateOf( S.TEMPLATE_GENERATED ) || this.stateMachine.getState().isSubStateOf( S.INITIAL_STATE )) {}
			this.creator.loadImageFromPath(path);
			this.stateMachine.setState(S.IMAGE_IMPORTED);
	}

	@Override
	public synchronized void startGeneration() {
		if (this.stateMachine.getState().isSubStateOf( S.IMAGE_IMPORTED ))
			this.creator.startGeneration();
			this.stateMachine.setState(S.TEMPLATE_GENERATED);
	}
}
