package com.geekbrains.githubclient.mvp.model.entity.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.geekbrains.githubclient.mvp.model.entity.room.RoomGithubRepository;

import java.util.List;

@Dao
public interface RepositoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(RoomGithubRepository repo);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<RoomGithubRepository> repo);

    @Update
    void update(RoomGithubRepository repo);

    @Update
    void update(List<RoomGithubRepository> repo);

    @Delete
    void delete(RoomGithubRepository repo);

    @Delete
    void delete(List<RoomGithubRepository> repo);


    @Query("SELECT * FROM RoomGithubRepository")
    List<RoomGithubRepository> getAll();

    @Query("SELECT * FROM RoomGithubRepository WHERE userId = :userId")
    List<RoomGithubRepository> findByUser(String userId);
}
