package org.ciclo.model;

import java.util.Set;

public class Song implements ISong {
	
	private int Id;
	
	private String name;
	private int duration;
	private Genre genre;
	private Set<IPlaylists> list;
	private IDisc disc;
	private Set<IReproduction> Reproductions;
	
	
	
	
	
	
	
	

	

	public Song() {
		super();
	}
	
	

	public Song(String name, int duration, Set<IPlaylists> list, IDisc disc
			) {
		super();
		this.name = name;
		this.duration = duration;
		this.list = list;
		this.disc = disc;
		
	}
	



	public Song(String name, int duration, IDisc disc) {
		super();
		this.name = name;
		this.duration = duration;
		this.disc = disc;
	}
	



	public Set<IPlaylists> getList() {
		return list;
	}

	public void setList(Set<IPlaylists> list) {
		this.list = list;
	}

	public void setId(int id) {
		Id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public void setDisc(IDisc disc) {
		this.disc = disc;
	}

	public void setReproductions(Set<IReproduction> reproductions) {
		Reproductions = reproductions;
	}
	

	@Override
	public Integer getId() { 
		
		return Id;
	}

	@Override
	public String getName() {
		
		return name;
	}

	@Override
	public Integer getDuration() {
		// TODO Auto-generated method stub
		return duration;
	}

	@Override
	public IGenre getGenre() {
		// TODO Auto-generated method stub
		return genre;
	}
	

	@Override
	public Set<IPlaylists> getLists() {
		// TODO Auto-generated method stub
		return this.getList();
	}

	@Override
	public IDisc getDisc() {
		// TODO Auto-generated method stub
		return this.getDisc();
	}

	@Override
	public Set<IReproduction> getReproductions() {
		// TODO Auto-generated method stub
		return this.getReproductions();
	}
	
	@Override
	public int compareTo(ISong o) {
		
		return this.name.compareTo(o.getName());
	}



	@Override
	public boolean equals(Object obj) {
		
		return super.equals(obj);
	}



	
	
	

}
