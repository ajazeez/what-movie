package com.aj.whatmovie.di.module

import com.aj.whatmovie.data.repositories.DiscoverMoviesRepositoryImpl
import com.aj.whatmovie.data.repositories.MovieGenreRepositoryImpl
import com.aj.whatmovie.data.services.MoviesAPI
import com.aj.whatmovie.domain.repository.DiscoverMoviesRepository
import com.aj.whatmovie.domain.repository.MovieGenreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): MoviesAPI = retrofit.create(MoviesAPI::class.java)

    @Provides
    internal fun providesDiscoverMoviesRepository(
        moviesAPI: MoviesAPI
    ): DiscoverMoviesRepository{
        return DiscoverMoviesRepositoryImpl(moviesAPI)
    }

    @Provides
    internal fun providesMovieGenreRepository(
        moviesAPI: MoviesAPI
    ): MovieGenreRepository{
        return MovieGenreRepositoryImpl(moviesAPI)
    }
}