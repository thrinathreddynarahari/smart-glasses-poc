package com.ranesvision.app.di

import android.content.Context
import androidx.room.Room
import com.ranesvision.app.data.local.AppDatabase
import com.ranesvision.app.data.local.dao.ImageDao
import com.ranesvision.app.data.local.dao.TagDao
import com.ranesvision.app.data.sdk.HeyCyanManager
import com.ranesvision.app.data.sdk.MockSmartGlassService
import com.ranesvision.app.domain.service.SmartGlassService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "ranes_vision_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideImageDao(database: AppDatabase): ImageDao {
        return database.imageDao()
    }

    @Provides
    @Singleton
    fun provideTagDao(database: AppDatabase): TagDao {
        return database.tagDao()
    }

    /**
     * Helper to switch between Mock and Real SDK.
     * Currently set to Real SDK (HeyCyanManager).
     */
    @Provides
    @Singleton
    fun provideSmartGlassService(
        manager: HeyCyanManager
    ): SmartGlassService {
        return manager
    }
    
    // Uncomment for Mock service:
    /*
    @Provides
    @Singleton
    fun provideSmartGlassService(
        mockService: MockSmartGlassService
    ): SmartGlassService {
        return mockService
    }
    */
}
