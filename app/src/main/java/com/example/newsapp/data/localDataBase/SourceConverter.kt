package com.example.newsapp.data.localDataBase

import androidx.room.TypeConverter
import com.example.newsapp.models.Source

class SourceConverter {



    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name, name)
    }


}