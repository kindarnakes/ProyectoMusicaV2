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
    public List<Artist> listArtistById(Integer id) {
        return null;
    }

    @Override
    public List<Artist> listArtistByName(String name) {
        return null;
    }

    @Override
    public boolean createArtist(String name, String from, String photo) {
        return false;
    }

    @Override
    public boolean updateArtist(String name, String from, String photo) {
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
    public List<Disc> listDiscById(Integer id) {
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
    public boolean updateDisc(String name, String photo, LocalDate releaseDate, Artist creator) {
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
    public List<Song> listSongById(Integer id) {
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
    public boolean updateSong(String name, Disc disc, Integer duration) {
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
    public List<User> listUserById(Integer id) {
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
    public boolean updateUser(String name, String email, String photo) {
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
    public List<Playlist> listPlaylistById(Integer id) {
        return null;
    }

    @Override
    public List<Playlist> listPlaylistByName(String name) {
        return null;
    }

    @Override
    public boolean createPlaylist(String name, String description, User creator) {
        return false;
    }

    @Override
    public boolean updatePlaylist(String name, String description, User creator) {
        return false;
    }

    @Override
    public boolean removePlaylist(Integer id) {
        return false;
    }

    @Override
    public boolean addSongToPlaylist(Playlist playlist, Song song) {
        return false;
    }

    @Override
    public boolean removeSongToPlaylist(Playlist playlist, Song song) {
        return false;
    }

    @Override
    public List<Playlist> listPlaylistBySong(Song song) {
        return null;
    }

    @Override
    public List<Song> listSongByPlaylist(Playlist playlist) {
        return null;
    }

    @Override
    public boolean userSubscribePlaylist(User user, Playlist playlist) {
        return false;
    }

    @Override
    public boolean userUnSubscribePlaylist(User user, Playlist playlist) {
        return false;
    }

    @Override
    public List<Playlist> listPlaylistSubscribed(User user) {
        return null;
    }

    @Override
    public List<Playlist> listUserCreated(User user) {
        return null;
    }

    @Override
    public List<User> listUserSubscribed(Playlist playlist) {
        return null;
    }
}
