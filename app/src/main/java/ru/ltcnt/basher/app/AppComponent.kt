package ru.ltcnt.basher.app

import dagger.Component
import ru.ltcnt.basher.presentation.MainActivity
import ru.ltcnt.basher.presentation.MainPresenter
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApiModule::class))
interface AppComponent{
    fun inject(mainActivity: MainActivity)
    fun inject(mainActivity: MainPresenter)

}