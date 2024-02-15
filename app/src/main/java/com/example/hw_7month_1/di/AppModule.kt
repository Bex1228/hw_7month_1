package com.example.hw_7month_1.di

import android.content.Context
import androidx.room.Room
import com.example.hw_7month_1.data.local.SmartHomeDao
import com.example.hw_7month_1.data.local.SmartHomeDatabase
import com.example.hw_7month_1.data.remote.ApiService
import com.example.hw_7month_1.data.repositories.Repository
import com.example.hw_7month_1.domain.repositories.CamerasRepository
import com.example.hw_7month_1.domain.repositories.DoorsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://cars.cprogroup.ru/api/rubetek/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .callTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideCamerasRepository(apiService: ApiService): CamerasRepository {
        return Repository(apiService)
    }

    @Provides
    fun provideDoorsRepository(apiService: ApiService): DoorsRepository {
        return Repository(apiService)
    }

    @Provides
    @Singleton
    fun provideRoomDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
            context,
            SmartHomeDatabase::class.java,
            "home_database"
        ).allowMainThreadQueries().build()

    @Provides
    @Singleton
    fun provideSmartHomeDao(
        roomDatabase: SmartHomeDatabase
    ):SmartHomeDao {
        return roomDatabase.smartHomeDao()
    }


}