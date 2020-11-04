package org.ciclo.model;

import java.util.Set;

public class Song implements ISong {
	
	/**
     * Database Id
     */
	private int Id;
	/**
     * Name of song
     */
	private String name;
	/**
     * Duration of song
     */
	private int duration;
	/**
     * Genre of song
     */
	private Genre genre;
	/**
     * Song playlist
     */
	private Set<IPlaylists> list;
	/**
     * Song of disc
     */
	private IDisc disc;
	/**
     * Song reproduction
     */
	private Set<IReproduction> Reproductions;
	
	
	
	
	
	
	
	

	
/**
 * Constructor
 */
	public Song() {
		super();
	}
	
	
/**
 * Parametrized constructor
 * @param name
 * @param duration
 * @param list
 * @param disc
 */
	public Song(String name, int duration, Set<IPlaylists> list, IDisc disc
			) {
		super();
		this.name = name;
		this.duration = duration;
		this.list = list;
		this.disc = disc;
		
	}
	


/**
 * Parametrized constructor
 * @param name
 * @param duration
 * @param disc
 */
	public Song(String name, int duration, IDisc disc) {
		super();
		this.name = name;
		this.duration = duration;
		this.disc = disc;
	}
	


/**
 * PlayList setter
 * @param list Playlist to assign
 */
	public void setList(Set<IPlaylists> list) {
		this.list = list;
	}
	/**
	 * Id setter
	 * @param id Id to assign
	 */

	public void setId(int id) {
		Id = id;
	}

	/**
	 * Name setter
	 * @param name Name to assign
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Duration setter
	 * @param duration Duration to assign
	 */

	public void setDuration(int duration) {
		this.duration = duration;
	}
/**
 * Genre setter
 * @param genre Genre to assign
 */
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	/**
	 * Disc setter
	 * @param disc Disc to assign
	 */

	public void setDisc(IDisc disc) {
		this.disc = disc;
	}
/**
 * Reproductions setter
 * @param reproductions Reproductions to assign
 */
	public void setReproductions(Set<IReproduction> reproductions) {
		Reproductions = reproductions;
	}
	
	/**
	 * Id getter
	 * @return Id of song
	 */
	@Override
	public Integer getId() { 
		
		return Id;
	}
	/**
	 * Name getter
	 * @return Name of song
	 */

	@Override
	public String getName() {
		
		return name;
	}
	/**
	 * Duration getter
	 * @return Duration of song
	 */

	@Override
	public Integer getDuration() {
		// TODO Auto-generated method stub
		return duration;
	}
	/**
	 * Genre getter
	 * @return Genre of song
	 */

	@Override
	public IGenre getGenre() {
		// TODO Auto-generated method stub
		return genre;
	}
	/**
	 * Playlist getter
	 * @return Playlist of song
	 */
	

	@Override
	public Set<IPlaylists> getLists() {
		// TODO Auto-generated method stub
		return list;
	}
	/**
	 * Disc getter
	 * @return Disc of song
	 */

	@Override
	public IDisc getDisc() {
		// TODO Auto-generated method stub
		return disc;
	}
	/**
	 * Reproductions getter
	 * @return Reproductions of song
	 */

	@Override
	public Set<IReproduction> getReproductions() {
		// TODO Auto-generated method stub
		return Reproductions;
	}
	
	@Override
	public int compareTo(ISong o) {
		
		return this.name.compareTo(o.getName());
	}



	@Override
	public boolean equals(Object obj) {
		 if (this == obj) return true;
	        if (!(obj instanceof Song)) return false;

	        Song song = (Song) obj;

	        return this.Id==song.Id && this.name.equals(song.name);
		
		
	}



	
	
	

}
