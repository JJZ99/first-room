package com.example.firstroom

import android.app.Application
import com.example.firstroom.database.WordRoomDatabase
import com.example.firstroom.repository.WordRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MyApplication:Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { WordRoomDatabase.getDatabase(this, applicationScope) }

    val repository by lazy { WordRepository(database.wordDao()) }

}