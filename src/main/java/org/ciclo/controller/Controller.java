package org.ciclo.controller;

import org.ciclo.model.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Controller implements IController{
   @Override
    public List<Artist> listAllArtist() {
        List<Artist> artists = ArtistDAO.List_All_Artist();
        return artists;
    }

    @Override
    public Artist listArtistById(Integer id) {
        Artist artist = ArtistDAO.List_Artist_By_Id(id);
        return artist;
    }

    @Override
    public Artist listArtistByName(String name) {
        Artist artist = ArtistDAO.List_Artist_By_Name(name);
        return artist;
    }

    @Override
    public boolean createArtist(String name, String photo, String from) {
        Artist artist = new Artist(name, photo, from);             
        ArtistDAO artistDAO = new ArtistDAO(artist);
        return artistDAO.Insert_Artist();
    }

    @Override
    public boolean updateArtist(Artist artist, String name, String from, String photo) {
        artist.setName(name);
        artist.setNationality(from);
        artist.setPhoto(photo);
        ArtistDAO artistDAO = new ArtistDAO(artist);
        return artistDAO.Update_Artist();
    }

    @Override
    public boolean removeArtist(Integer id) {
        return ArtistDAO.Remove_Artist_by_Id(id);
    }

    @Override
    public boolean removeArtist(String name) {
        return ArtistDAO.Remove_Artist_by_Name(name);
    }

    @Override
    public List<Disc> listAllDisc() {
        List<Disc> discs = DiscDAO.listAll();
        return discs;
    }

    @Override
    public Disc listDiscById(Integer id) {
       Disc disc = DiscDAO.listById(id);
        return disc;
    }

    @Override
    public List<Disc> listDiscByName(String name) {
        List<Disc> discs = DiscDAO.listByName(name);
        return discs;
    }

    @Override
    public boolean createDisc(String name, String photo, LocalDate releaseDate, Artist creator, List<Song> songs) {
        Disc disc = new Disc(name, releaseDate, photo, creator, null);             
        DiscDAO discDAO = new DiscDAO(disc);
        return discDAO.save();
    }

    @Override
    public boolean updateDisc(Disc disc, String name, String photo, LocalDate releaseDate, Artist creator) {
        disc.setName(name);
        disc.setPhoto(photo);
        disc.setReleaseDate(releaseDate);
        disc.setArtist(creator);
        DiscDAO discDAO = new DiscDAO(disc);
        return discDAO.update();
    }


    @Override
    public boolean removeDisc(Integer id) {
        return DiscDAO.remove(id);
    }

    @Override
    public List<Song> listAllSong() {
        List<Song> songs = SongDAO.listAll();
        return songs;
    }

    @Override
    public Song listSongById(Integer id) {
        return SongDAO.listById(id);
    }


    @Override
    public List<Song> listSongByName(String name) {
        return SongDAO.listByName(name);
    }

    @Override
    public boolean createSong(String name, Disc disc, Integer duration, List<Playlist> playlist) {
        Set<IPlaylists> play=new TreeSet<>(playlist);
        Song song = new Song(name, duration, play, disc);
        SongDAO songdao = new SongDAO(song);
        return songdao.save();
      
    }

    @Override
    public boolean updateSong(Song song, String name, Disc disc, Integer duration) {
        song.setName(name);
        song.setDisc(disc);
        song.setDuration(duration);
        SongDAO songdao = new SongDAO(song);
        return songdao.update();
        
    }


    @Override
    public boolean removeSong(Integer id) {
        return SongDAO.remove(id);
    }

    @Override
    public List<User> listAllUser() {
        return UserDAO.listAll();
    }

    @Override
    public User listUserById(Integer id) {
        return UserDAO.listById(id);
    }


    @Override
    public List<User> listUserByName(String name) {
        return UserDAO.listByName(name);
    }

    @Override
    public User listUserByEmail(String email) {
        return UserDAO.listByEmail(email);
    }

    @Override
    public boolean createUser(String name, String email, String photo) {
          User user = new User(name, photo, email);
        UserDAO userdao = new UserDAO(user);
        return userdao.save();
        
    }

    @Override
    public boolean updateUser(User user, String name, String email, String photo) {
        user.setName(name);
        user.setEmail(email);
        user.setPhoto(photo);
        UserDAO userdao = new UserDAO(user);
        return userdao.update();
        
    }


    @Override
    public boolean removeUser(Integer id) {
        return UserDAO.remove(id);
    }

    @Override
    public boolean removeUser(String email) {
        return UserDAO.remove(email);
    }

    @Override
    public List<Playlist> listAllPlaylist() {
        return PlaylistDAO.List_All_Playlist();
    }

    @Override
    public Playlist listPlaylistById(Integer id) {
        return PlaylistDAO.List_Playlist_By_Id(id);
    }


    @Override
    public List<Playlist> listPlaylistByName(String name) {
        return PlaylistDAO.listByName(name);
    }

    @Override
    public boolean createPlaylist(String name, String description, User creator) {
        Playlist playlist = new Playlist(name, -1, description, creator, null, null,
                null);
        PlaylistDAO playlistDAO = new PlaylistDAO(playlist);
        return playlistDAO.save();
    }

    @Override
    public boolean updatePlaylist(Playlist playlist, String name, String description, User creator) {
        playlist.setName(name);
        playlist.setDescription(description);
        playlist.setCreator(creator);
        PlaylistDAO playlistDAO = new PlaylistDAO(playlist);
        return playlistDAO.update();
    }


    @Override
    public boolean removePlaylist(Integer id) {
        return PlaylistDAO.remove(id);
    }

    @Override
    public boolean addSongToPlaylist(Playlist playlist, Song song) {
        PlaylistDAO playlistDAO = new PlaylistDAO(playlist);
        return playlistDAO.addSong(song);
    }

    @Override
    public boolean removeSongToPlaylist(Playlist playlist, Song song) {
        PlaylistDAO playlistDAO = new PlaylistDAO(playlist);
        return playlistDAO.removeSong(song);
    }

    @Override
    public List<Playlist> listPlaylistBySong(Song song) {
        return PlaylistDAO.listBySong(song);
    }

    @Override
    public List<Song> listSongByPlaylist(Playlist playlist) {
        return SongDAO.ListSongByPlaylist(playlist);
    }

    @Override
    public boolean userSubscribePlaylist(User user, Playlist playlist) {
        UserDAO userDAO = new UserDAO(user);
        return userDAO.unsubscribe(playlist);
    }

    @Override
    public boolean userUnSubscribePlaylist(User user, Playlist playlist) {
        UserDAO userDAO = new UserDAO(user);
        return userDAO.susbcribe(playlist);
    }

    @Override
    public List<Playlist> listPlaylistSubscribed(User user) {
        return PlaylistDAO.listPlaylistSuscribers(user);
    }

    @Override
    public List<Playlist> listUserCreated(User user) {
        return PlaylistDAO.listUserCreated(user);
    }

    @Override
    public List<User> listUserSubscribed(Playlist playlist) {
        return UserDAO.listUserSubscribed(playlist);
    }
}
