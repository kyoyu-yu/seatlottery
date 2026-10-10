package com.kyoyu.seatlottery.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import com.kyoyu.seatlottery.model.Participant;

public class ParticipantReader {

	public static void main(String[] args) {
		// participants.datを開く

		try (
				FileInputStream fis = new FileInputStream("participants.dat");
				// ObjectInputStreamで読む
				ObjectInputStream ois = new ObjectInputStream(fis);) {
			List<Participant> participants = (List<Participant>) ois.readObject();
			
			for(Participant participant:participants) {
				System.out.println(participant.getName());
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}
