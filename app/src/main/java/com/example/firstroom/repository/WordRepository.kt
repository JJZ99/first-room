package com.example.firstroom.repository

import androidx.annotation.WorkerThread
import com.example.firstroom.dao.WordDao
import com.example.firstroom.pojo.Word
import kotlinx.coroutines.flow.Flow

class WordRepository(private val wordDao: WordDao) {

    //当数据库更新的时候会调用getAlphabetizedWords，数据
    val allWords : Flow<List<Word>> = wordDao.getAlphabetizedWords()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Word){
        wordDao.insert(word)
    }

}