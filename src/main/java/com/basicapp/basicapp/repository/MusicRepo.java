package com.basicapp.basicapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.basicapp.basicapp.models.Music;

public interface MusicRepo extends JpaRepository<Music, Long> {

}
