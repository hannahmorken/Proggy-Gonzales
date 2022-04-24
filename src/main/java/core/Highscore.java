package core;

import java.util.ArrayList;

public class Highscore {
	
	GamePanel gp;
	WriteToFile writer;
	
	public Highscore(GamePanel gp) {
		this.gp = gp;
		writer = new WriteToFile();
	}
	
	/**
	 * Calculates the score based on coins and time left
	 * @return
	 */
	private int calculateScore() {
		int score = gp.collisionChecker.coins;
		String[] strArr = gp.getTimerDisplay().timeLeft().split(":");
		boolean minutesDone = false;
		for(String s: strArr) {
			if (minutesDone) {
				score += Integer.parseInt(s)*60; //regner minutter til sekunder
			}
			else {
				score += Integer.parseInt(s); //legger til sekunder
			}
		}
		System.out.println(score);
		return score;
	}
	
	private void compareScores(int i) {
		ArrayList<Integer> list = gp.highscores;
		for(int j: list) {
			if(i > j) {
				list.add(list.indexOf(j), i);
				list.remove(5);
				writer.overWriteFile(list);
				break;
			}
		}
		gp.highscores = list;
	}
	
	public void updateHighscore() {
		int currentScore = calculateScore();
		compareScores(currentScore);
	}
}
