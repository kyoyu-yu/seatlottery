package com.kyoyu.seatlottery.service;

import java.util.ArrayList;
import java.util.List;

import com.kyoyu.seatlottery.model.Participant;
import com.kyoyu.seatlottery.repository.ParticipantRepository;

public class ParticipantInitializer {

	public static void main(String[] args) {

		List<Participant> participants = new ArrayList<>();

		participants.add(new Participant("平山"));
		participants.add(new Participant("榊原"));
		participants.add(new Participant("濱口"));
		participants.add(new Participant("豊川"));
		participants.add(new Participant("坂口"));
		participants.add(new Participant("時任"));
		participants.add(new Participant("齊藤"));
		participants.add(new Participant("竹下"));
		participants.add(new Participant("上岡"));
		participants.add(new Participant("菊本"));

		ParticipantRepository repository = new ParticipantRepository();
		repository.save(participants);
		
		List<Participant> loaded = repository.load();

		for (Participant participant : loaded) {
			System.out.println(participant.getName());
		}

	}
}
