package com.droidsmith.petri.injection.modules

import android.app.Application
import android.content.Context
import com.droidsmith.petri.injection.components.PetriActivityComponent
import com.droidsmith.petri.ui.map.PetriActivity
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = arrayOf(PetriActivityComponent::class))
class PetriApplicationModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application


}