package org.ciclo.controller;

import org.ciclo.model.*;

import java.time.LocalDate;
import java.util.List;

public interface IController {
    //Artist Agustin
    public List<Artist> listAllArtist();
    public List<Artist> listArtistById(Integer id);
    public List<Artist> listArtistByName(String name);
    public boolean createArtist(String name, String from, String photo);
    public boolean updateArtist(String name, String from, String photo);
    public boolean removeArtist(Integer id);
    public boolean removeArtist(String name);

    //Disc Santos
    public List<Disc> listAllDisc();
    public List<Disc> listDiscById(Integer id);
    public List<Disc> listDiscByName(String name);
    public boolean createDisc(String name, String photo, LocalDate releaseDate, Artist creator, List<Song> songs);
    public boolean updateDisc(String name, String photo, LocalDate releaseDate, Artist creator);
    public boolean removeDisc(Integer id);

    //Song Angel
    public List<Song> listAllSong();
    public List<Song> listSongById(Integer id);
    public List<Song> listSongByName(String name);
    public boolean createSong(String name, Disc disc, Integer duration, List<Playlist> playlist);
    public boolean updateSong(String name, Disc disc, Integer duration);
    public boolean removeSong(Integer id);

    //User
    public List<User> listAllUser();
    public List<User> listUserById(Integer id);
    public List<User> listUserByName(String name);
    public List<User> listUserByEmail(String email);
    public boolean createUser(String name, String email, String photo);
    public boolean updateUser(String name, String email, String photo);
    public boolean removeUser(Integer id);
    public boolean removeUser(String email);

    //Playlist
    public List<Playlist> listAllPlaylist();
    public List<Playlist> listPlaylistById(Integer id);
    public List<Playlist> listPlaylistByName(String name);
    public boolean createPlaylist(String name, String description, User creator);
    public boolean updatePlaylist(String name, String description, User creator);
    public boolean removePlaylist(Integer id);

    //Song-Playlist
    public boolean addSongToPlaylist(Playlist playlist, Song song);
    public boolean removeSongToPlaylist(Playlist playlist, Song song);
    public List<Playlist> listPlaylistBySong(Song song);
    public List<Song> listSongByPlaylist(Playlist playlist);

    //User-Playlist
    public boolean userSubscribePlaylist(User user, Playlist playlist);
    public boolean userUnSubscribePlaylist(User user, Playlist playlist);
    public List<Playlist> listPlaylistSubscribed(User user);
    public List<Playlist> listUserCreated(User user);
    public List<User> listUserSubscribed(Playlist playlist);


}
