package org.ciclo.controller;

import org.ciclo.model.*;

import java.time.LocalDate;
import java.util.List;

public interface IController {
    //Artist Agustin
    List<Artist> listAllArtist();

    Artist listArtistById(Integer id);

    Artist listArtistByName(String name);

    boolean createArtist(String name, String from, String photo);

    boolean updateArtist(Artist artist, String name, String from, String photo);

    boolean removeArtist(Integer id);

    boolean removeArtist(String name);

    //Disc Santos
    List<Disc> listAllDisc();

    Disc listDiscById(Integer id);

    List<Disc> listDiscByName(String name);

    boolean createDisc(String name, String photo, LocalDate releaseDate, Artist creator, List<Song> songs);

    boolean updateDisc(Disc disc, String name, String photo, LocalDate releaseDate, Artist creator);

    boolean removeDisc(Integer id);

    //Song Angel
    List<Song> listAllSong();

    Song listSongById(Integer id);

    List<Song> listSongByName(String name);

    boolean createSong(String name, Disc disc, Integer duration, List<Playlist> playlist);

    boolean updateSong(Song song, String name, Disc disc, Integer duration);

    boolean removeSong(Integer id);

    //User
    List<User> listAllUser();

    User listUserById(Integer id);

    List<User> listUserByName(String name);

    User listUserByEmail(String email);

    boolean createUser(String name, String email, String photo);

    boolean updateUser(User user, String name, String email, String photo);

    boolean removeUser(Integer id);

    //Playlist
    List<Playlist> listAllPlaylist();

    Playlist listPlaylistById(Integer id);

    List<Playlist> listPlaylistByName(String name);

    boolean createPlaylist(String name, String description, User creator);

    boolean updatePlaylist(Playlist playlist, String name, String description, User creator);

    boolean removePlaylist(Integer id);

    //Song-Playlist
    boolean addSongToPlaylist(Playlist playlist, Song song);

    boolean removeSongToPlaylist(Playlist playlist, Song song);

    List<Playlist> listPlaylistBySong(Song song);

    List<Song> listSongByPlaylist(Playlist playlist);

    //User-Playlist
    boolean userSubscribePlaylist(User user, Playlist playlist);

    boolean userUnSubscribePlaylist(User user, Playlist playlist);

    List<Playlist> listPlaylistSubscribed(User user);

    List<Playlist> listUserCreated(User user);

    List<User> listUserSubscribed(Playlist playlist);


}
