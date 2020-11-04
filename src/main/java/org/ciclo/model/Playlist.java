package org.ciclo.model;

import java.util.Set;

public class Playlist implements IPlaylists {
	/**
	 * Name of Playlist
	 */
	 private String name;
	 /**
	  * Database Id
	  */
     private int id;
     /**
      * Description of Playlist
      */
     private String desciption;
     /**
      * Creator of Playlist
      */
     private IUser creator;
     /**
      * Susbcribers of Playlist
      */
     private Set<IUser> susbcribers;
     /**
      * Songs of Playlist
      */
     private Set<ISong> songs; 
     /**
      * Comments of Playlist
      */
     private Set<IComments> comments; 
     
     
     
	
	

     
     /**
      * Constructor
      */
     public Playlist() {
		super();
	}

/**
 * Parametrized Constructor 
 * @param name
 * @param id
 * @param desciption
 * @param creator
 * @param susbcribers
 * @param songs
 * @param comments
 */
	public Playlist(String name, int id, String desciption, IUser creator, Set<IUser> susbcribers, Set<ISong> songs,
			Set<IComments> comments) {
		super();
		this.name = name;
		this.id = id;
		this.desciption = desciption;
		this.creator = creator;
		this.susbcribers = susbcribers;
		this.songs = songs;
		this.comments = comments;
	}
     

	/**
 	 * Name setter
 	 * @param name Name to assign
 	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Id setter
	 * @param id Id to assign
	 */

	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Desciption setter
	 * @param desciption Desciption to assign
	 */

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}
	
	/**
	 * Creator setter
	 * @param creator Creator to assign
	 */

	public void setCreator(IUser creator) {
		this.creator = creator;
	}
	
	/**
	 * Susbcribers setter
	 * @param susbcribers Susbcribers to assign
	 */

	public void setSusbcribers(Set<IUser> susbcribers) {
		this.susbcribers = susbcribers;
	}
	
	/**
	 * Songs setter
	 * @param songs Songs to assign
	 */

	public void setSongs(Set<ISong> songs) {
		this.songs = songs;
	}
	
	/**
	 * Comments setter
	 * @param comments Comments to assign
	 */

	public void setComments(Set<IComments> comments) {
		this.comments = comments;
	}
	
	
	
	/**
	 * Name getter
	 * @return Name of Playlist
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	/**
	 * Id getter
	 * @return Id of Playlist
	 */

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return id;
	}
	
	/**
	 * Description getter
	 * @return Description of Playlist
	 */

	@Override
	public String getDesciption() {
		// TODO Auto-generated method stub
		return desciption;
	}
	
	/**
	 * Creator getter
	 * @return Creator of Playlist
	 */

	@Override
	public IUser getCreator() {
		// TODO Auto-generated method stub
		return creator;
	}
	
	/**
	 * Suscribers getter
	 * @return Suscribers of Playlist
	 */

	@Override
	public Set<IUser> getSusbcribers() {
		// TODO Auto-generated method stub
		return susbcribers;
	}
	
	/**
	 * Songs getter
	 * @return Songs of Playlist
	 */

	@Override
	public Set<ISong> getSongs() {
		// TODO Auto-generated method stub
		return songs;
	}
	
	/**
	 * Comments getter
	 * @return Comments of Playlist
	 */

	@Override
	public Set<IComments> getComments() {
		// TODO Auto-generated method stub
		return comments;
	}
	
	
	@Override
	public int compareTo(IPlaylists o) {
		
		return this.name.compareTo(o.getName());
	}

	@Override
	public boolean equals(Object obj) {
		 if (this == obj) return true;
	        if (!(obj instanceof Playlist)) return false;

	        Playlist playlist = (Playlist) obj;

	        return this.id==playlist.id;
		
		
	}
	
	

}
