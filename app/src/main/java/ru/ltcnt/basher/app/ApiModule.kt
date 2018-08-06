package ru.ltcnt.basher.app

import dagger.Module
import dagger.Provides
import ru.ltcnt.basher.data.ApiRepository
import ru.ltcnt.basher.domain.repository.ApiRepositoryImpl
import javax.inject.Singleton

@Module
class ApiModule {
    @Provides
    @Singleton
    fun provideApiRepository(): ApiRepositoryImpl = ApiRepository()
}