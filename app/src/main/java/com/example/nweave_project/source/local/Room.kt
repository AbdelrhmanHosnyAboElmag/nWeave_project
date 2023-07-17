package com.example.nweave_project.source.local

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.Deferred

@Dao
interface ProductDao {
    @Query("select * from productdatabase")
     fun getAsteroid(): MutableList<ProductDatabase>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg product: ProductDatabase)
}

@Database(entities = [ProductDatabase::class], version = 1)
abstract class databaseProduct : RoomDatabase() {
    abstract val productDao: ProductDao
}

private lateinit var INSTANCE: databaseProduct

fun getDatabase(context: Context): databaseProduct {
    synchronized(databaseProduct::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                databaseProduct::class.java,
                "asteroids"
            ).build()
        }
    }
    return INSTANCE
}
