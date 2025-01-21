package artcreator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import artcreator.creator.CreatorFactory;
import artcreator.creator.port.Creator;
import artcreator.domain.DomainFactory;
import artcreator.domain.port.Domain;
import artcreator.statemachine.StateMachineFactory;
import artcreator.statemachine.port.StateMachine;
import artcreator.statemachine.port.Subject;
import artcreator.statemachine.port.Observer;
import artcreator.statemachine.port.State;
import artcreator.statemachine.port.State.S;

class InitTest implements Observer {

	State s;

	@Test
	void testStateMachine() {
		StateMachine stateMachine = StateMachineFactory.FACTORY.stateMachine();
		Assertions.assertNotNull(stateMachine);
	}
	@Test
	void testStateMachineFactory() {
		StateMachine stateMachine = StateMachineFactory.FACTORY.stateMachine();
		Assertions.assertNotNull(stateMachine);
		Subject subject = StateMachineFactory.FACTORY.subject();
		Assertions.assertEquals(stateMachine, subject);
	}
	@Test
	void testDomainFactory() {
		Domain domain = DomainFactory.FACTORY.domain();
		Assertions.assertNotNull(domain);
	}
	@Test
	void testCreatorFactory() {
		Creator creator = CreatorFactory.FACTORY.creator();
		Assertions.assertNotNull(creator);
	}
	@Test
	void testCreatorFactoryLoadImage() {
		Subject subject = StateMachineFactory.FACTORY.subject();
		subject.attach(this);
		StateMachine stateMachine = StateMachineFactory.FACTORY.stateMachine();
		Creator creator = CreatorFactory.FACTORY.creator();
		Assertions.assertNotNull(creator);
		creator.loadImageFromPath("src/main/fuchs.jpg");
		Assertions.assertTrue(stateMachine.getState().isSubStateOf(S.IMAGE_IMPORTED));
		Assertions.assertEquals(S.IMAGE_IMPORTED, this.s);
		subject.detach(this);
	}
	@Test
	void testStartGeneration() {
		Subject subject = StateMachineFactory.FACTORY.subject();
		subject.attach(this);
		Creator creator = CreatorFactory.FACTORY.creator();
		creator.loadImageFromPath("src/main/fuchs.jpg");
		subject.detach(this);
		creator.startGeneration();
	}

//	@Test
//	void test() {
//		StateMachine stateMachine = StateMachineFactory.FACTORY.stateMachine();
//		Assertions.assertNotNull(stateMachine);
//		Subject subject = StateMachineFactory.FACTORY.subject();
//		Assertions.assertEquals(stateMachine, subject);
//		subject.attach(this);
//
//		Domain domain = DomainFactory.FACTORY.domain();
//		Assertions.assertNotNull(domain);
//
//		Creator creator = CreatorFactory.FACTORY.creator();
//		Assertions.assertNotNull(creator);
//
//		creator.loadImageFromPath("src/main/fuchs.jpg");
//
//		Assertions.assertTrue(stateMachine.getState().isSubStateOf(S.IMAGE_IMPORTED));
//		Assertions.assertEquals(S.IMAGE_IMPORTED, this.s);
//		subject.detach(this);
//
//	}

	@Override
	public void update(State currentState) {
		this.s = currentState;
	}

}
