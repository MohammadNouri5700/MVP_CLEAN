package com.example.hepsi.repository.local.DBs

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "message")
data class MessageDb (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "msgid") val msgid: String,
    @ColumnInfo(name = "content") var content: String,
    @ColumnInfo(name = "dataclass") var dataclass: String
)