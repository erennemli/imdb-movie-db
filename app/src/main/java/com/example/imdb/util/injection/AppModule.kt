package com.example.imdb.util.injection

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module(includes = [MoshiModule::class])
internal class AppModule {

    @Provides
    @Singleton
    internal fun provideApplicationContext(@ApplicationContext context: Context): Context {
        return context
    }
}