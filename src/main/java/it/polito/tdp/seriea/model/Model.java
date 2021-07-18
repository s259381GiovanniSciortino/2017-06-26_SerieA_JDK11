package it.polito.tdp.seriea.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.seriea.db.SerieADAO;

public class Model {
	Graph<Team,DefaultWeightedEdge> grafo;
	
	public String doCreaGrafo() {
		SerieADAO dao = new SerieADAO();
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(grafo, dao.listTeams());
		List<Team> t1 = new ArrayList<>(grafo.vertexSet());
		List<Team> t2 = new ArrayList<>(grafo.vertexSet());
		
		for(int i = 0; i<t1.size();i++) {
			for(int j= 0; j<t2.size(); j++) {
				if(i>j) {
					int peso = dao.getPeso(t1.get(i), t2.get(j));
					if(peso>0) {
						Graphs.addEdge(grafo, t1.get(i), t2.get(j), peso);
					}
				}
			}
		}
		String result = "";
		if(this.grafo==null) {
			result ="Grafo non creato";
			return result;
		}
		result = "Grafo creato con :\n# "+this.grafo.vertexSet().size()+" VERTICI\n# "+this.grafo.edgeSet().size()+" ARCHI\n";
		return result;
					
	}
	
	public String doConnessioniSquadra(Team t) {
		List<TeamWeight> teamWeight  = new ArrayList<>();
		for(Team vicino:Graphs.neighborListOf(grafo, t))
			teamWeight.add(new TeamWeight(vicino,(int) grafo.getEdgeWeight(grafo.getEdge(t, vicino))));
		Collections.sort(teamWeight);
		String res = "\nElenco di quadre con cui ha giocato "+t+"\n";
		for(TeamWeight te: teamWeight)
			res+=te;
		return res;
	}
	
	
	public Set<Team> getVertex(){
		return grafo.vertexSet();
	}
	
	
	public String doSimulazione(Season season) {
		Simulator sim= new Simulator(season);
		sim.init();
		String msg = sim.run();
		return msg;
	}
	public List<Season> getSeason(){
		SerieADAO dao = new SerieADAO();
		return dao.listSeasons();
	}
}
