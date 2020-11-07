package org.ciclo.controller;

import org.ciclo.model.*;

import java.time.LocalDate;
import java.util.List;

public class Controller implements IController{
    @Override
    public List<Artist> listAllArtist() {
        return null;
    }

    @Override
    public Artist listArtistById(Integer id) {
        return null;
    }

    @Override
    public Artist listArtistByName(String name) {
        return null;
    }


    @Override
    public boolean createArtist(String name, String from, String photo) {
        return false;
    }

    @Override
    public boolean updateArtist(Artist artist, String name, String from, String photo) {
        return false;
    }

    @Override
    public boolean removeArtist(Integer id) {
        return false;
    }

    @Override
    public boolean removeArtist(String name) {
        return false;
    }

    @Override
    public List<Disc> listAllDisc() {
        return null;
    }

    @Override
    public Disc listDiscById(Integer id) {
        return null;
    }


    @Override
    public List<Disc> listDiscByName(String name) {
        return null;
    }

    @Override
    public boolean createDisc(String name, String photo, LocalDate releaseDate, Artist creator, List<Song> songs) {
        return false;
    }

    @Override
    public boolean updateDisc(Disc disc, String name, String photo, LocalDate releaseDate, Artist creator) {
        return false;
    }


    @Override
    public boolean removeDisc(Integer id) {
        return false;
    }

    @Override
    public List<Song> listAllSong() {
        return null;
    }

    @Override
    public Song listSongById(Integer id) {
        return null;
    }


    @Override
    public List<Song> listSongByName(String name) {
        return null;
    }

    @Override
    public boolean createSong(String name, Disc disc, Integer duration, List<Playlist> playlist) {
        return false;
    }

    @Override
    public boolean updateSong(Song song, String name, Disc disc, Integer duration) {
        return false;
    }


    @Override
    public boolean removeSong(Integer id) {
        return false;
    }

    @Override
    public List<User> listAllUser() {
        return null;
    }

    @Override
    public User listUserById(Integer id) {
        return null;
    }


    @Override
    public List<User> listUserByName(String name) {
        return null;
    }

    @Override
    public List<User> listUserByEmail(String email) {
        return null;
    }

    @Override
    public boolean createUser(String name, String email, String photo) {
        return false;
    }

    @Override
    public boolean updateUser(User user, String name, String email, String photo) {
        return false;
    }


    @Override
    public boolean removeUser(Integer id) {
        return false;
    }

    @Override
    public boolean removeUser(String email) {
        return false;
    }

    @Override
    public List<Playlist> listAllPlaylist() {
        return null;
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
