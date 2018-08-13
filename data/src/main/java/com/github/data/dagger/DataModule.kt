package com.github.data.dagger

import android.arch.persistence.room.Room
import android.content.Context
import com.github.data.Database
import com.github.data.DbDao
import com.github.data.remote.Api
import com.github.data.repositories.CommentsRepository
import com.github.data.repositories.PostRepository
import com.github.data.repositories.UserRepository
import com.github.scopes.AppScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class DataModule {

    @AppScope
    @Provides
    fun provideCommentRepository(
            dbDao: DbDao,
            api: Api
    ): CommentsRepository {
        return CommentsRepository(dbDao, api)
    }

    @AppScope
    @Provides
    fun providePostRepository(
            dbDao: DbDao,
            api: Api
    ): PostRepository {
        return PostRepository(dbDao, api)
    }

    @AppScope
    @Provides
    fun provideUserRepository(
            dbDao: DbDao,
            api: Api
    ): UserRepository {
        return UserRepository(dbDao, api)
    }

    @AppScope
    @Provides
    fun provideDbDao(
            database: Database
    ): DbDao {
        return database.createDbDao()
    }

    @AppScope
    @Provides
    fun provideDatabase(
            appContext: Context
    ): Database {
        return Room.databaseBuilder(
                appContext,
                Database::class.java,
                DATABASE_NAME).build()
    }

    @AppScope
    @Provides
    fun provideApi(
            client: OkHttpClient
    ): Api {
        return Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(Api::class.java)
    }

    @AppScope
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }
}

private const val BASE_URL = "http://jsonplaceholder.typicode.com/"
private const val DATABASE_NAME = "arc_database"