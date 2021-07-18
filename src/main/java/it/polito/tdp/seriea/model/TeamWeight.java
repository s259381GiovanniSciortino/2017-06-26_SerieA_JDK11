package it.polito.tdp.seriea.model;

public class TeamWeight implements Comparable<TeamWeight>{
	Team team;
	Integer peso;
	public TeamWeight(Team team, Integer peso) {
		super();
		this.team = team;
		this.peso = peso;
	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	public Integer getPeso() {
		return peso;
	}
	public void setPeso(Integer peso) {
		this.peso = peso;
	}
	@Override
	public int compareTo(TeamWeight other) {
		return other.getPeso().compareTo(this.getPeso());
	}
	@Override
	public String toString() {
		return team + " | " + peso + " volte\n";
	}
	
}
