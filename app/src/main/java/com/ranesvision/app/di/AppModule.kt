package com.ranesvision.app.di

import android.content.Context
import androidx.room.Room
import com.ranesvision.app.data.local.AppDatabase
import com.ranesvision.app.data.local.dao.ImageDao
import com.ranesvision.app.data.local.dao.LogItDao
import com.ranesvision.app.data.local.dao.TagDao
import com.ranesvision.app.data.sdk.GlassDeviceService
import com.ranesvision.app.data.sdk.HeyCyanManager
import com.ranesvision.app.data.sdk.MockSmartGlassService
import com.ranesvision.app.domain.service.SmartGlassService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
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
        ).fallbackToDestructiveMigration().build()
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

    @Provides
    @Singleton
    fun provideLogItDao(database: AppDatabase): LogItDao {
        return database.logItDao()
    }

    @Provides
    @Singleton
    @Named("mock")
    fun provideMockSmartGlassService(
        mockService: MockSmartGlassService
    ): SmartGlassService {
        return mockService
    }

    @Provides
    @Singleton
    @Named("real")
    fun provideRealSmartGlassService(
        realManager: HeyCyanManager
    ): SmartGlassService {
        return realManager
    }

    /**
     * Primary SmartGlassService binding. GlassDeviceService delegates
     * to mock or real at runtime based on GlassesConfig.mode.
     */
    @Provides
    @Singleton
    fun provideSmartGlassService(
        glassDeviceService: GlassDeviceService
    ): SmartGlassService {
        return glassDeviceService
    }
}
