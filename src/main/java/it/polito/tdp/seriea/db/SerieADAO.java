package it.polito.tdp.seriea.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.seriea.model.Match;
import it.polito.tdp.seriea.model.Season;
import it.polito.tdp.seriea.model.Team;

public class SerieADAO {

	public List<Match> getAllMatchSeason(Integer season){
		String sql="select m.Date as d, m.HomeTeam as ht, m.AwayTeam as aw, m.FTHG as fthg, m.FTAG as ftag "
				+ "from matches m "
				+ "where m.Season =?";
		List<Match> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, season);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(new Match(res.getDate("d").toLocalDate(),new Team(res.getString("ht")),new Team(res.getString("aw")),res.getInt("fthg"),res.getInt("ftag")));
			}

			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public List<String> getTeamSeason(Integer season){
		String sql = "select distinct m.HomeTeam as t "
				+ "from matches m, teams "
				+ "where m.Season =?";
		List<String> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, season);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(res.getString("t"));
			}

			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	public Integer getPeso(Team t1, Team t2) {
		String sql="select count(*) as p "
				+ "from matches m "
				+ "where (m.HomeTeam =? and m.AwayTeam =?) OR "
				+ "(m.HomeTeam =? and m.AwayTeam =?)";
		Integer peso = 0;
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, t1.toString());
			st.setString(2, t2.toString());
			st.setString(3, t2.toString());
			st.setString(4, t1.toString());
			ResultSet res = st.executeQuery();

			while (res.next()) {
				peso = res.getInt("p");
			}

			conn.close();
			return peso;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public List<Season> listSeasons() {
		String sql = "SELECT season, description FROM seasons";
		List<Season> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(new Season(res.getInt("season"), res.getString("description")));
			}

			conn.close();
			return result;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public List<Team> listTeams() {
		String sql = "SELECT team FROM teams";
		List<Team> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(new Team(res.getString("team")));
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}

