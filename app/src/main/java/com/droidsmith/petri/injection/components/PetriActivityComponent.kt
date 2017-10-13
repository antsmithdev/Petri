package com.droidsmith.petri.injection.components

import com.droidsmith.petri.injection.modules.FragmentModule
import com.droidsmith.petri.injection.modules.PetriActivityModule
import com.droidsmith.petri.ui.map.PetriActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = arrayOf(PetriActivityModule::class, FragmentModule::class))
interface PetriActivityComponent: AndroidInjector<PetriActivity>{

    @Subcomponent.Builder
    abstract class Builder: AndroidInjector.Builder<PetriActivity>()

}