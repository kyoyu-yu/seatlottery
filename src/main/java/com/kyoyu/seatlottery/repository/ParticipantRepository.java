package com.kyoyu.seatlottery.repository;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import com.kyoyu.seatlottery.model.Participant;

public class ParticipantRepository {

	private static final String FILE_PATH = "participants.dat";
	
	public void save(List<Participant> participants) {


		try (
				FileOutputStream fos = new FileOutputStream(FILE_PATH);
				ObjectOutputStream oos = new ObjectOutputStream(fos);) {
			oos.writeObject(participants);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	
	public List<Participant> load() {

		List<Participant> participants = null;
		try (
				FileInputStream fis = new FileInputStream(FILE_PATH);
				ObjectInputStream ois = new ObjectInputStream(fis);) {
			participants = (List<Participant>) ois.readObject();

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return participants;

	}

}
