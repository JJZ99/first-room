package com.example.firstroom.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.firstroom.dao.WordDao
import com.example.firstroom.pojo.Word
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Word::class],version = 1, exportSchema = false)
public abstract class WordRoomDatabase :RoomDatabase(){
    abstract fun wordDao(): WordDao

    class WordDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let{ database->
                scope.launch {
                    var wordDao = database.wordDao()

                    wordDao.deleteAll()

                    var word = Word("Hello")
                    wordDao.insert(word)

                    word = Word("World!")
                    wordDao.insert(word)

                    word = Word("TODO!")
                    wordDao.insert(word)
                }
            }
        }
    }

    companion object{

        @Volatile
        private var INSTANCE : WordRoomDatabase? = null

        fun getDatabase(context: Context,scope: CoroutineScope): WordRoomDatabase {

            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDatabase::class.java,
                    "word_database"
                ).addCallback(WordDatabaseCallback(scope)).build()
                INSTANCE = instance
                return@synchronized instance
            }
        }
    }
}