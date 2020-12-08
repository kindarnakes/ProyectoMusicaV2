package org.ciclo.controller;

import org.ciclo.model.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Controller implements IController {
    /**
     * List all artist
     *
     * @return All artist
     */
    @Override
    public List<Artist> listAllArtist() {
        List<Artist> artists = ArtistDAO.List_All_Artist();
        return artists;
    }

    /**
     * List the artist with that id
     *
     * @param id of the artist
     * @return the artist with that id
     */

    @Override
    public Artist listArtistById(Integer id) {
        Artist artist = ArtistDAO.List_Artist_By_Id(id);
        return artist;
    }

    /**
     * List all artist whit the samen name
     *
     * @param name of the artist
     * @return the artist with same name
     */

    @Override
    public Artist listArtistByName(String name) {
        Artist artist = ArtistDAO.List_Artist_By_Name(name);
        return artist;
    }

    /**
     * Crate a new artist
     *
     * @param name  of the artis
     * @param photo of the artist
     * @param from  of the artist
     * @return the method of create an artist
     */

    @Override
    public boolean createArtist(String name, String photo, String from) {
        Artist artist = new Artist(name, photo, from);
        ArtistDAO artistDAO = new ArtistDAO(artist);
        return artistDAO.Insert_Artist();
    }

    /**
     * Update a artist
     *
     * @param artist the artist that you want to update
     * @param name   the new name of the artist
     * @param from   the new from of the artist
     * @param photo  the new photo of the artist
     * @return the method of update an artist
     */

    @Override
    public boolean updateArtist(Artist artist, String name, String from, String photo) {
        artist.setName(name);
        artist.setNationality(from);
        artist.setPhoto(photo);
        ArtistDAO artistDAO = new ArtistDAO(artist);
        return artistDAO.Update_Artist();
    }

    /**
     * Remove an artist by the id
     *
     * @param id unique for all the artist
     * @return the method of remove an artist
     */

    @Override
    public boolean removeArtist(Integer id) {
        return ArtistDAO.Remove_Artist_by_Id(id);
    }

    /**
     * Remove an artist by the name
     *
     * @param name unique for all the artist
     * @return the method of remove an artist
     */

    @Override
    public boolean removeArtist(String name) {
        return ArtistDAO.Remove_Artist_by_Name(name);
    }

    /**
     * List all disc
     *
     * @return all the disc
     */

    @Override
    public List<Disc> listAllDisc() {
        List<Disc> discs = DiscDAO.listAll();
        return discs;
    }

    /**
     * List a disc with an id
     *
     * @param id unique for all the disc
     * @return the disc with the same id
     */

    @Override
    public Disc listDiscById(Integer id) {
        Disc disc = DiscDAO.listById(id);
        return disc;
    }

    /**
     * List all disc with the same name
     *
     * @param name of the disc that you want to list
     * @return disc with the same name
     */

    @Override
    public List<Disc> listDiscByName(String name) {
        List<Disc> discs = DiscDAO.listByName(name);
        return discs;
    }

    /**
     * Create a new disc
     *
     * @param name        of the disc
     * @param photo       of the disc
     * @param releaseDate of the disc
     * @param creator     of the disc
     * @param songs       of the disc
     * @return the method create a disc
     */

    @Override
    public boolean createDisc(String name, String photo, LocalDate releaseDate, Artist creator, List<Song> songs) {
        Disc disc = new Disc(name, releaseDate, photo, creator, null);
        DiscDAO discDAO = new DiscDAO(disc);
        return discDAO.save();
    }

    /**
     * Update a disc
     *
     * @param disc        you want to update
     * @param name        the new name
     * @param photo       the new photo
     * @param releaseDate the new releaseDate
     * @param creator     the new creator
     * @return the method update a disc
     */

    @Override
    public boolean updateDisc(Disc disc, String name, String photo, LocalDate releaseDate, Artist creator) {
        disc.setName(name);
        disc.setPhoto(photo);
        disc.setReleaseDate(releaseDate);
        disc.setArtist(creator);
        DiscDAO discDAO = new DiscDAO(disc);
        return discDAO.update();
    }

    /**
     * Remove a disc
     *
     * @param id unique for all disc
     * @return the method remove a disc
     */


    @Override
    public boolean removeDisc(Integer id) {
        return DiscDAO.remove(id);
    }

    /**
     * List all the song
     *
     * @return all the song
     */

    @Override
    public List<Song> listAllSong() {
        List<Song> songs = SongDAO.listAll();
        return songs;
    }

    /**
     * List a song by id
     *
     * @param id unique for all song
     * @return the song with that id
     */

    @Override
    public Song listSongById(Integer id) {
        return SongDAO.listById(id);
    }

    /**
     * List all the song with the same name
     *
     * @param name you want to list
     * @return the song with the same name
     */


    @Override
    public List<Song> listSongByName(String name) {
        return SongDAO.listByName(name);

    }

    /**
     * Create a new song
     *
     * @param name     of the song
     * @param disc     of the song
     * @param duration of the song
     * @param playlist of the song
     * @return the method create a song
     */

    @Override
    public boolean createSong(String name, Disc disc, Integer duration, List<Playlist> playlist) {
        Set<Playlist> play = playlist != null ? new TreeSet<>(playlist) : new TreeSet<>();
        Song song = new Song(name, duration, play, disc);
        SongDAO songdao = new SongDAO(song);
        return songdao.save();

    }

    /**
     * Update a song
     *
     * @param song     the song you want to update
     * @param name     the new name
     * @param disc     the new disc
     * @param duration the new duration
     * @return the method update a song
     */

    @Override
    public boolean updateSong(Song song, String name, Disc disc, Integer duration) {
        song.setName(name);
        song.setDisc(disc);
        song.setDuration(duration);
        SongDAO songdao = new SongDAO(song);
        return songdao.update();

    }

    /**
     * Remove a song by id
     *
     * @param id unique for all the song
     * @return the method remove a song
     */


    @Override
    public boolean removeSong(Integer id) {
        return SongDAO.remove(id);
    }

    /**
     * List all user
     *
     * @return all the user
     */

    @Override
    public List<User> listAllUser() {
        return UserDAO.listAll();
    }

    /**
     * List a user by id
     *
     * @param id unique for all the user
     * @return the user with that id
     */

    @Override
    public User listUserById(Integer id) {
        return UserDAO.listById(id);
    }

    /**
     * List all the user with the same name
     *
     * @param name you want to list
     * @return the user with the samen name
     */


    @Override
    public List<User> listUserByName(String name) {
        return UserDAO.listByName(name);
    }

    /**
     * List the user by email
     *
     * @param email unique for all the user
     * @return the user with the samen email
     */

    @Override
    public User listUserByEmail(String email) {
        return UserDAO.listByEmail(email);
    }

    /**
     * Create a user
     *
     * @param name  of the user
     * @param email of the user
     * @param photo of the user
     * @return the method create a user
     */

    @Override
    public boolean createUser(String name, String email, String photo) {
        User user = new User(name, photo, email);
        UserDAO userdao = new UserDAO(user);
        return userdao.save();

    }

    /**
     * Update a user
     *
     * @param user  you want to update
     * @param name  the new name
     * @param email the new email
     * @param photo the new photo
     * @return the method update a user
     */

    @Override
    public boolean updateUser(User user, String name, String email, String photo) {
        user.setName(name);
        user.setEmail(email);
        user.setPhoto(photo);
        UserDAO userdao = new UserDAO(user);
        return userdao.update();

    }

    /**
     * Remove a user by id
     *
     * @param id unique for all user
     * @return the method remove a user
     */


    @Override
    public boolean removeUser(Integer id) {
        return UserDAO.remove(id);
    }


    /**
     * List all playlist
     *
     * @return all the playlist
     */

    @Override
    public List<Playlist> listAllPlaylist() {
        return PlaylistDAO.List_All_Playlist();
    }

    /**
     * List a playlist with the samen id
     *
     * @param id unique for all the playlist
     * @return the playlist with the same id
     */

    @Override
    public Playlist listPlaylistById(Integer id) {
        return PlaylistDAO.List_Playlist_By_Id(id);
    }

    /**
     * List all playlist with the same name
     *
     * @param name you want to list
     * @return the playlist with the same name
     */


    @Override
    public List<Playlist> listPlaylistByName(String name) {
        return PlaylistDAO.listByName(name);
    }

    /**
     * Create a playlist
     *
     * @param name        of the playlist
     * @param description of the playlist
     * @param creator     of the playlist
     * @return the method create a playlist
     */

    @Override
    public boolean createPlaylist(String name, String description, User creator) {
        Playlist playlist = new Playlist(name, description, creator, null, null);
        PlaylistDAO playlistDAO = new PlaylistDAO(playlist);
        System.out.println(playlist.getClass());
        return playlistDAO.save();
    }

    /**
     * Update a playlist
     *
     * @param playlist    you want to update
     * @param name        the new name
     * @param description the new description
     * @param creator     the new creator
     * @return the method update a playlist
     */

    @Override
    public boolean updatePlaylist(Playlist playlist, String name, String description, User creator) {
        playlist.setName(name);
        playlist.setDescription(description);
        playlist.setCreator(creator);
        PlaylistDAO playlistDAO = new PlaylistDAO(playlist);
        return playlistDAO.update();
    }

    /**
     * Remove a playlist
     *
     * @param id unique for all the playlist
     * @return the method remove a playlist
     */


    @Override
    public boolean removePlaylist(Integer id) {
        return PlaylistDAO.remove(id);
    }

    /**
     * Add song to a playlist
     *
     * @param playlist the playlist that you want to add the song
     * @param song     the song that you want to add
     * @return the method add song to a playlist
     */

    @Override
    public boolean addSongToPlaylist(Playlist playlist, Song song) {
        PlaylistDAO playlistDAO = new PlaylistDAO(playlist);
        return playlistDAO.addSong(song);
    }

    /**
     * Remove a song from a playlist
     *
     * @param playlist the playlist that you want to remove the song
     * @param song     the song that you want to remove
     * @return the method remove song from a playlist
     */

    @Override
    public boolean removeSongToPlaylist(Playlist playlist, Song song) {
        PlaylistDAO playlistDAO = new PlaylistDAO(playlist);
        return playlistDAO.removeSong(song);
    }

    /**
     * List the playlist wich contains the song
     *
     * @param song of the playlist
     * @return the method List playlist by a song
     */

    @Override
    public List<Playlist> listPlaylistBySong(Song song) {
        return PlaylistDAO.listBySong(song);
    }

    /**
     * List the song wich contains the playlist
     *
     * @param playlist of the song
     * @return the method List song by a playlist
     */

    @Override
    public List<Song> listSongByPlaylist(Playlist playlist) {
        return SongDAO.ListSongByPlaylist(playlist);
    }

    /**
     * User who subcribe in a playlist
     *
     * @param user     who subcribe
     * @param playlist wich the user susbcribe
     * @return the method user subcribe to a playlist
     */

    @Override
    public boolean userSubscribePlaylist(User user, Playlist playlist) {
        UserDAO userDAO = new UserDAO(user);
        return userDAO.unsubscribe(playlist);
    }

    /**
     * User who unsubcribe in a playlist
     *
     * @param user     who unsubcribe
     * @param playlist wich the user unsusbcribe
     * @return the method user unsubcribe to a playlist
     */

    @Override
    public boolean userUnSubscribePlaylist(User user, Playlist playlist) {
        UserDAO userDAO = new UserDAO(user);
        return userDAO.subscribe(playlist);
    }

    /**
     * List all the playlists in which the user is subscribed
     *
     * @param user who is subscribed
     * @return the method list playlist subcribers
     */

    @Override
    public List<Playlist> listPlaylistSubscribed(User user) {
        return PlaylistDAO.listPlaylistSuscribers(user);
    }

    /**
     * List all the playlists in which the user is created
     *
     * @param user who is created
     * @return the method list playlist created
     */

    @Override
    public List<Playlist> listUserCreated(User user) {
        return PlaylistDAO.listUserCreated(user);
    }

    /**
     * List all the subcribers in a playist
     *
     * @param playlist users are subscribed to
     * @return the method List user subcribed
     */

    @Override
    public List<User> listUserSubscribed(Playlist playlist) {
        return UserDAO.listUserSubscribed(playlist);
    }
}

