package com.raviolini.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.raviolini.core.data.source.local.entity.KosEntity

@Database(entities = [KosEntity::class],version=1, exportSchema = false)
abstract class KosDatabase : RoomDatabase(){
    abstract fun kosDao() : KosDao
}