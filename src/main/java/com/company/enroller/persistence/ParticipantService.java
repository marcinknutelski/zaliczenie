package com.company.enroller.persistence;


import java.util.Collection;

import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.company.enroller.model.Participant;

@Component("participantService")
public class ParticipantService {

	DatabaseConnector connector;

	public ParticipantService() {
		connector = DatabaseConnector.getInstance();
	}

	public Collection<Participant> getAll() {
		return connector.getSession().createCriteria(Participant.class).list();
	}
	
	public Participant findByLogin(String login) {
		return (Participant) connector.getSession().get(Participant.class, login);
	}

	public void add(Participant participant) {
		Transaction transaction = connector.getSession().beginTransaction();
		connector.getSession().save(participant);
		transaction.commit();		
	}

	public void delete(Participant participant) {
		Transaction deleteTransaction = connector.getSession().beginTransaction();
		connector.getSession().delete(participant);
		deleteTransaction.commit();		
	}

	public void update(Participant participant) {
		Transaction updateTransaction = connector.getSession().beginTransaction();
		connector.getSession().update(participant);
		updateTransaction.commit();		
	}
	
	public Participant findParticipantId(String string) {
		return (Participant) connector.getSession().get(Participant.class, string);
	}


}