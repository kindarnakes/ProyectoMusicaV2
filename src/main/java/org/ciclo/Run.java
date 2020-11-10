package org.ciclo;

public class Run {
    public static void main(String[] args) {
        MainApp.main(args);

        /*Artist a = ArtistDAO.List_Artist_By_Id(2);
        ArtistDAO artistDAO = new ArtistDAO(a);

        artistDAO.loadDiscs();

        artistDAO.getDiscs().forEach((Disc) ->{
            DiscDAO disc = new DiscDAO((Disc) Disc);
            disc.loadSongs();
            System.out.println("Autor: " + disc.getArtist().getName() + " Disco: " + disc.getName());
            disc.getSongs().forEach((song) ->{
                System.out.println("\t" + song.getName());
            });
        } );
        System.out.println();*/

    }
}
