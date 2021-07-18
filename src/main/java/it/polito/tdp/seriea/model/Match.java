package it.polito.tdp.seriea.model;

import java.time.LocalDate;

public class Match implements Comparable<Match>{

	private LocalDate date;
	private Team homeTeam;
	private Team awayTeam;
	private int fthg; // full time home goals
	private int ftag; // full time away goals
	// E' possibile aggiungere altri campi, se risulteranno necessari

	/**
	 * New match
	 * 
	 * @param id
	 * @param season
	 * @param div
	 * @param date
	 * @param homeTeam
	 * @param awayTeam
	 * @param fthg
	 * @param ftag
	 * @param ftr
	 */
	public Match( LocalDate date, Team homeTeam, Team awayTeam, int fthg, int ftag) {
		super();
		
		this.date = date;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.fthg = fthg;
		this.ftag = ftag;
		
	}

	/**
	 * @return the id
	 */
	

	/**
	 * @return the season
	 */
	

	/**
	 * @return the div
	 */
	

	/**
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * @return the homeTeam
	 */
	public Team getHomeTeam() {
		return homeTeam;
	}

	/**
	 * @return the awayTeam
	 */
	public Team getAwayTeam() {
		return awayTeam;
	}

	/**
	 * @return the fthg
	 */
	public int getFthg() {
		return fthg;
	}

	/**
	 * @return the ftag
	 */
	public int getFtag() {
		return ftag;
	}

	/**
	 * @return the ftr
	 */
	

	/**
	 * @param id
	 * the id to set
	 */
	

	/**
	 * @param season
	 * the season to set
	 */
	

	/**
	 * @param div
	 * the div to set
	 */
	

	/**
	 * @param date
	 * the date to set
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * @param homeTeam
	 * the homeTeam to set
	 */
	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}

	/**
	 * @param awayTeam
	 * the awayTeam to set
	 */
	public void setAwayTeam(Team awayTeam) {
		this.awayTeam = awayTeam;
	}

	/**
	 * @param fthg
	 * the fthg to set
	 */
	public void setFthg(int fthg) {
		this.fthg = fthg;
	}

	/**
	 * @param ftag
	 * the ftag to set
	 */
	public void setFtag(int ftag) {
		this.ftag = ftag;
	}

	/**
	 * @param ftr
	 * the ftr to set
	 */
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	
	

	@Override
	public int compareTo(Match other) {
		return this.getDate().compareTo(other.getDate());
	}

}

