package it.polito.tdp.seriea.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import it.polito.tdp.seriea.db.SerieADAO;

public class Simulator {
	Map<Team,Integer> tifosiSquadra;
	Season season;
	PriorityQueue<Match> partiteStagionali;
	Map<Team,Integer> classifica;
	SerieADAO dao = new SerieADAO();
	
	int TifosiPartenza=1000;
	int P=10;
	
	public Simulator(Season season) {
		this.season=season;
	}
	
	public void init() {
		List<Team> squadre = new ArrayList<>();
		for(String s : dao.getTeamSeason(season.getSeason()))
			squadre.add(new Team(s));
		tifosiSquadra = new HashMap<>();
		classifica = new HashMap<>();
		for(Team t: squadre) {
			classifica.put(t, 0);
			tifosiSquadra.put(t, TifosiPartenza);
		}
		
		partiteStagionali = new PriorityQueue<>(dao.getAllMatchSeason(season.getSeason()));	
	}
	
	public String run() {
		while(!this.partiteStagionali.isEmpty()) {
			Match m  = partiteStagionali.poll();
			Team t1 = m.getHomeTeam();
			Team t2 = m.getAwayTeam();
			int goalT1 = m.getFthg();
			int goalT2 = m.getFtag();
			int tifosiT1 = tifosiSquadra.get(t1);
			int tifosiT2 = tifosiSquadra.get(t2);
			if(tifosiT1<tifosiT2) {
				double prob = 1.0-(1.0*(tifosiT1/tifosiT2));
				double rand = Math.random();
				if(rand<prob) {
					goalT1--;
				}
			}else {
				double prob = 1.0-(1.0*(tifosiT2/tifosiT1));
				double rand = Math.random();
				if(rand<prob) {
					goalT2--;
				}
			}
			int diff = goalT1-goalT2;
			if(diff>0) {
				this.handleVittoria(t1);
				this.handleSconfitta(t1, t2, diff);
			}
			if(diff==0) {
				this.handlePareggio(t1);
				this.handlePareggio(t2);
			}
			if(diff<0) {
				diff=diff*(-1);
				this.handleVittoria(t2);
				this.handleSconfitta(t2, t1, diff);
			}
		}
		String res = "\nSimulazione avvenuta con successo!!!\nLa classifica finale della stagione simulata "+season+" risulterebbe:\n";
		List<TeamWeight> classificaOrdinata = new ArrayList<>();
		for(Team t : classifica.keySet()) {
			classificaOrdinata.add(new TeamWeight(t,classifica.get(t)));
		}
		Collections.sort(classificaOrdinata);
		for(TeamWeight tw: classificaOrdinata) {
			res+=tw;
		}
		return res;
	}
	public void handleVittoria(Team t) {
		classifica.put(t, classifica.get(t)+3);
	}
	public void handlePareggio(Team t){
		classifica.put(t, classifica.get(t)+1);
	}
	public void handleSconfitta(Team vittoriosa, Team sconfitta, int differenzaReti) {
		int tifosi = ((differenzaReti*P)/100)*(tifosiSquadra.get(sconfitta));
		tifosiSquadra.put(vittoriosa, tifosiSquadra.get(vittoriosa)+tifosi);
		tifosiSquadra.put(sconfitta, tifosiSquadra.get(sconfitta)-tifosi);
	}
	
}
