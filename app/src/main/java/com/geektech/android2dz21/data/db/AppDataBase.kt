package com.geektech.android2dz21.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.geektech.android2dz21.data.db.daos.NotDao
import com.geektech.android2dz21.models.NoteModel

@Database(entities = [NoteModel::class], version = 2)

abstract class AppDataBase : RoomDatabase() {
    abstract fun noteDao(): NotDao
}