package com.tamayo.jetmovies.di

import com.tamayo.jetmovies.data.rest.MoviesRepository
import com.tamayo.jetmovies.data.rest.MoviesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {


    @Binds
    abstract fun providesRepository(moviesRepositoryImpl: MoviesRepositoryImpl): MoviesRepository
}