package com.basicapp.basicapp.services;


import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.basicapp.basicapp.models.Music;
import com.basicapp.basicapp.repository.MusicRepo;

@Service
public class MusicService {

    @Autowired
    private MusicRepo musicRepo;

    public ResponseEntity<ArrayList<Music>> getAllSongs(){
        try{
            ArrayList<Music> allSongs = new ArrayList<>();
            musicRepo.findAll().forEach(allSongs::add);
            if(allSongs.isEmpty()){
                return new ResponseEntity<>(allSongs, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(allSongs, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    public ResponseEntity<Music> getSongById(Long id){
        try {
            if(musicRepo.existsById(id)){
                return new ResponseEntity<>(musicRepo.findById(id).get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(new Music(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(new Music(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    public ResponseEntity<Music> insertNewMusic(Music music){
        try {
            Music saved = musicRepo.save(music);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new Music(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Music> updateById(Long id, Music music){
        try {

            Optional<Music> musicById = musicRepo.findById(id);
            if(!musicById.isPresent()){
                return new ResponseEntity<>(new Music(), HttpStatus.NOT_FOUND);
            }

            Music updateSong = musicById.get();
            updateSong.setTitle(music.getTitle());
            updateSong.setAuthor(music.getAuthor());
            updateSong.setDuration(music.getDuration());
            updateSong.setPrice(music.getPrice());
            updateSong.setType(music.getType());

            Music updatedSong = musicRepo.save(updateSong);
            return new ResponseEntity<>(updatedSong, HttpStatus.OK);
            
        } catch (Exception e) {
            return new ResponseEntity<>(new Music(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    public ResponseEntity<String> deleteById(Long id){
        try{
            if(musicRepo.existsById(id)){
                musicRepo.deleteById(id);
                return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
            }
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
