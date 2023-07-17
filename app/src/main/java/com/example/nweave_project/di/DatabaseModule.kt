package com.example.nweave_project.di

import android.content.Context
import androidx.room.Room
import com.example.nweave_project.source.local.databaseProduct
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): databaseProduct {
        return Room.databaseBuilder(
            context.applicationContext,
            databaseProduct::class.java,
            "asteroids"
        ).build()
    }

}