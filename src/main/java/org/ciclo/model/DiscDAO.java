package org.ciclo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

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
        EntityManager manager = Connect.getManager();
        manager.getTransaction().begin();
        Artist a = manager.find(Artist.class, d.getArtist().getId());
        
        this.setId(d.getId());
        this.setName(d.getName());
        this.setPhoto(d.getPhoto());
        this.setReleaseDate(d.getReleaseDate());
        this.setSongs(d.getSongs());
        this.setArtist(a);
        manager.getTransaction().commit();
        manager.close();

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
    	List<Disc> disc= new ArrayList<>();
       try {
    	   manager=Connect.getManager();
           disc=manager.createQuery("FROM Disc").getResultList();
            
          
            manager.close();
	} catch (PersistenceException e) {
		if(manager != null)
            manager.close();
		// TODO: handle exception
	}
      

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
    	 Disc disc= new Disc();
    	 try {
    		 disc=manager.find(Disc.class, id);
    		 manager.close();
			
		} catch (PersistenceException e) {
			// TODO: handle exception
			if(manager != null)
                manager.close();
		}
    	 
    	 
        

        return disc;
    }

    /**
     * List all disc with that name
     *
     * @param name of the discs
     * @return the discs with that name
     */


    public static List<Disc> listByName(String name) {
    	List<Disc> disc= new ArrayList<>();
    	 manager=Connect.getManager();
    	 try {
    		 disc=(List<Disc>) manager.createQuery("Select d FROM Disc d WHERE d._name = :discName").setParameter("discName", name).getResultList();
	    	 manager.close();
		} catch (PersistenceException e) {
			if(manager != null)
                manager.close();
			
			
			// TODO: handle exception
		}
    	 
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
        disc.setId(this.getId());
        try {
        	 manager=Connect.getManager();
             manager.getTransaction().begin();
            
             	
             	manager.merge(disc);
             	
            
             
             manager.getTransaction().commit();
             manager.close();
             if(this.equals(disc)) {
                 update=true;
             }
			
		} catch (PersistenceException e) {
			if(manager != null)
                manager.close();
			// TODO: handle exception
		}
        
    

        
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
    	 try {
    		  manager=Connect.getManager();
    	         manager.getTransaction().begin();
    	         
    	         if(disc!=null) {
    	          disc= manager.find(Disc.class, id);
    	         manager.remove(disc);
    	         removed=true;
    	         }
    	         
    	        
    	         
    	         manager.getTransaction().commit();
    	         manager.close();
			
		} catch (PersistenceException e) {
			if(manager != null)
                manager.close();
		}
       
   

         return removed;
    }

    /**
     * Remove the disc
     *
     * @return true if the disc has been removed, false if not
     */

    public boolean remove() {
    	  boolean removed = false;
    	  Disc disc=new Disc();
    	  try {
    		     EntityManager manager = Connect.getManager();
    	          manager.getTransaction().begin();
    	           disc = manager.find(Disc.class, this.getId());
    	          if (disc != null) {
    	              manager.remove(disc);
    	              removed = true;
    	          }
    	          manager.getTransaction().commit();
    	          manager.close();
			
		} catch (Exception e) {
			if(manager != null)
                manager.close();
			// TODO: handle exception
		}

          

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
    	   try {
    		   EntityManager manager = Connect.getManager();
               manager.getTransaction().begin();
               disc.setArtist(manager.merge(disc.getArtist()));
               
               manager.persist(disc);
               saved = manager.contains(disc);
               manager.getTransaction().commit();
               manager.close();
			
		} catch (PersistenceException e) {
			if(manager != null)
                manager.close();
			// TODO: handle exception
		}
        
           return saved;
    }

    /**
     * List  the disc with that artist
     *
     * @param artist of that disc
     * @return Discs with that artist
     */

    public static List<Disc> searchByAuthor(Artist artist) {
    	List<Disc> disc= new ArrayList<>();
    	try {
    		 manager=Connect.getManager();
    	  	  disc=(List<Disc>) manager.createQuery("SELECT d FROM Disc d JOIN d._artist Artist "
    	     	 		+ "WHERE Artist._id = :id ").setParameter("id", artist.getId()).getResultList();
    	  	 manager.close();
			
		} catch (PersistenceException e) {
			if(manager != null)
                manager.close();
			// TODO: handle exception
		}
       

   	 

        return disc;

    }

    /**
     * Load all the song by disc
     *
     * @return true if the song has been loaded, false if not
     */

    public boolean loadSongs() {
    	Disc d= new Disc();
        boolean loaded = false;
        try {
        	  manager = Connect.getManager();
              manager.getTransaction().begin();
            
                d= manager.find(Disc.class, this.getId());
               
               
              
               d.setId(this.getId());
              
               d.getSongs().forEach(song -> {
               });
               this.setSongs(d.getSongs());
              
              
               manager.getTransaction().commit();
               manager.close();
               if(this.getSongs() != null){
                   loaded = true;
               }
			
		} catch (PersistenceException e) {
			if(manager != null)
                manager.close();
			// TODO: handle exception
		}
         
         
        
        
      
        return loaded;
    }
}



