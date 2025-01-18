package com.basicapp.basicapp.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.basicapp.basicapp.models.Music;
import com.basicapp.basicapp.services.MusicService;




@RestController
@RequestMapping("/music")
public class MusicController {

    @Autowired
    private MusicService musicService;

    @GetMapping("/")
    public ResponseEntity<ArrayList<Music>> getAllsongs() {
        return musicService.getAllSongs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Music> getMethodName(@PathVariable Long id) {
        return musicService.getSongById(id);
    }
    
    @PostMapping("/")
    public ResponseEntity<Music> postMethodName(@RequestBody Music music) {
        return musicService.insertNewMusic(music);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Music> putMethodName(@PathVariable Long id, @RequestBody Music music) {
        return musicService.updateById(id, music);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBydId(@PathVariable Long id){
        return musicService.deleteById(id);
    }

}
