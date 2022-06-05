package com.example.jonas_hultn.repository.local.callbacks

import androidx.room.*
import com.example.hepsi.repository.local.DBs.MessageDb

@Dao
interface MessageDao {
    @Query("SELECT * FROM message")
    fun getmessages() : List<MessageDb>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(vararg todo: MessageDb)

    @Update
    fun update(vararg todo: MessageDb)

    @Delete
    fun delete(vararg todo: MessageDb)

    @Query("DELETE FROM message")
    fun truncate()


    @Query("SELECT * FROM message where msgid=:id")
    fun getmessages(id:String) : MessageDb

}