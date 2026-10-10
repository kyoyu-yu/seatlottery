package com.kyoyu.seatlottery.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.kyoyu.seatlottery.model.Participant;
import com.kyoyu.seatlottery.repository.ParticipantRepository;

// 席番号抽選　参加者名簿読み込み→確認→抽選処理

public class LotteryService {
	private ParticipantRepository repository;

	public LotteryService() {
		repository = new ParticipantRepository();
	}

	public Integer draw(String name) {
		List<Participant> participants = repository.load();
		Participant target = null;
		for (Participant participant : participants) {
			if (participant.getName().equals(name)) {
				target = participant;
			}

		}

		//参加者名簿にない場合
		if (target == null) {
			throw new IllegalArgumentException("参加者が見つかりません");
		}

		//抽選済みか確認
		if (target.isDrawn()) {
			throw new IllegalStateException("既に抽選済みです");
		}

		//1～10までのリスト（抽選番号）を作成
		List<Integer> availableSeats = new ArrayList<>();

		for (int i = 1; i <= 10; i++) {
			availableSeats.add(i);
		}

		//抽選番号から、抽選済を削除
		for (Participant participant : participants) {
			if (participant.isDrawn()) {
				availableSeats.remove(participant.getSeat());
			}
		}

		//抽選
		if (availableSeats.isEmpty()) {
			throw new IllegalStateException("空いている席がありません");
		}

		Random random = new Random();
		int index = random.nextInt(availableSeats.size());
		Integer selectedSeat = availableSeats.get(index);
		target.setSeat(selectedSeat);

		//保存
		repository.save(participants);

		return selectedSeat;

	}

	public List<Participant> getParticipants() {
		return repository.load();
	}

	public Map<Integer, String> getSeatMap() {

		List<Participant> participants = repository.load();
		Map<Integer, String> seatMap = new HashMap<>();

		for (Participant participant : participants) {
			if (participant.isDrawn()) {
				seatMap.put(
						participant.getSeat(),
						participant.getName());
			}
		}

		return seatMap;
	}

	public void resetParticipant(String name) {
		List<Participant> participants = repository.load();

		boolean found = false;

		for (Participant participant : participants) {
			if (participant.getName().equals(name)) {
				participant.setSeat(null);
				found = true;
				break;
			}
		}

		if (!found) {
			throw new IllegalArgumentException("参加者が見つかりません");
		}

		repository.save(participants);
	}

	public void resetAll() {
		List<Participant> participants = repository.load();
		for (Participant participant : participants) {
			participant.setSeat(null);
		}
		repository.save(participants);
	}
}