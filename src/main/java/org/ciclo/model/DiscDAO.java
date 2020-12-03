
package org.ciclo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.ciclo.model.connectManager.Connect;



public class DiscDAO extends Disc {
	   public static EntityManager manager;
    /**
     * Constructor
     */
    public DiscDAO() {

        super();
    }

    /**
     * Parametrized constructor
     *
     * @param d Disc to update
     */

    public DiscDAO(Disc d) {
        this.setArtist(d.getArtist());
        this.setId(d.getId());
        this.setName(d.getName());
        this.setPhoto(d.getPhoto());
        this.setReleaseDate(d.getReleaseDate());
        this.setSongs(d.getSongs());

    }

    /**
     * Constructor
     *
     * @param id of the Disc
     */

    public DiscDAO(Integer id) {
        this(DiscDAO.listById(id));
    }

    /**
     * List all disc
     *
     * @return All discs
     */

    public static List<Disc> listAll() {
       
        manager=Connect.getManager();
        List<Disc> disc=manager.createQuery("FROM Disc").getResultList();
      
        manager.close();

        return disc;
    }

    /**
     * List all disc by id
     *
     * @param id unique for all the discs
     * @return the disc with that id
     */

    public static Disc listById(Integer id) {
    	 manager=Connect.getManager();
    	 Disc disc=manager.find(Disc.class, id);
    	 
        
        manager.close();
        

        return disc;
    }

    /**
     * List all disc with that name
     *
     * @param name of the discs
     * @return the discs with that name
     */


    public static List<Disc> listByName(String name) {
    	 manager=Connect.getManager();
    	 List<Disc> disc=(List<Disc>) manager.createQuery("Select d FROM Disc d WHERE d._name = :discName").setParameter("discName", name).getResultList();
    	 manager.close();
        return disc;
    }

    /**
     * Update disc
     *
     * @return true if the disc has been updated, false if not
     */

    public boolean update() {
        boolean update = false;
        Disc disc=new Disc(this.getName(),this.getReleaseDate(),this.getPhoto(),this.getArtist(),this.getSongs());
        
        manager=Connect.getManager();
        manager.getTransaction().begin();
        if(disc!=null) {
        	manager.merge(this);
        	update=true;
        }
        
        manager.getTransaction().commit();
        manager.close();
        
        return update;
    }

    /**
     * Remove the disc with that id
     *
     * @param id unique for all the discs
     * @return true if the artist has been removed, false if not
     */

    public static boolean remove(Integer id) {
    	 Disc disc=new Disc();
    	 boolean removed = false;
         manager=Connect.getManager();
         manager.getTransaction().begin();
         
         if(disc!=null) {
          disc= manager.find(Disc.class, id);
         manager.remove(disc);
         removed=true;
         }
         
        
         
         manager.getTransaction().commit();
         manager.close();
   

         return removed;
    }

    /**
     * Remove the disc
     *
     * @return true if the disc has been removed, false if not
     */

    public boolean remove() {
    	  boolean removed = false;

          EntityManager manager = Connect.getManager();
          manager.getTransaction().begin();
          Disc disc = manager.find(Disc.class, this.getId());
          if (disc != null) {
              manager.remove(disc);
              removed = true;
          }
          manager.getTransaction().commit();
          manager.close();

          return removed;
    }

    /**
     * Save and insert a disc
     *
     * @return true if the disc has been inserted, false if not
     */

    public boolean save() {
    	   boolean saved = false;
    	   Disc disc=new Disc(this.getName(),this.getReleaseDate(),this.getPhoto(),this.getArtist(),this.getSongs());
           EntityManager manager = Connect.getManager();
           manager.getTransaction().begin();
           
           manager.persist(disc);
           saved = manager.contains(disc);
           manager.getTransaction().commit();
           manager.close();
           return saved;
    }

    /**
     * List  the disc with that artist
     *
     * @param artist of that disc
     * @return Discs with that artist
     */

    public static List<Disc> searchByAuthor(Artist artist) {
        List<Disc> discs = new ArrayList<>();
        manager=Connect.getManager();
        
   	 List<Disc> disc=(List<Disc>) manager.createQuery("SELECT DISTINCT d FROM Disc d JOIN d.artist Artist "
   	 		+ "WHERE Artist._id = :id ").setParameter("id", artist.getId()).getResultList();
   	 manager.close();
   	 

        return discs;

    }

    /**
     * Load all the song by disc
     *
     * @return true if the song has been loaded, false if not
     */

    public boolean loadSongs() {
        boolean loaded = false;

        return loaded;
    }

}
