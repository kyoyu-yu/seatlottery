package com.kyoyu.seatlottery.service;

import com.kyoyu.seatlottery.model.Participant;

public class LotteryTest {
	public static void main(String[] args) {
		
		LotteryService lotteryservice = new LotteryService();

		for (Participant participant : lotteryservice.getParticipants()) {
		    System.out.println(
		        participant.getName() + " : " + participant.getSeat()
		    );
		}
		
	}
}
