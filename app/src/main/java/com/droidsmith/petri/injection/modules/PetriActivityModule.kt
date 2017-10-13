package com.droidsmith.petri.injection.modules

import com.droidsmith.petri.injection.components.FeedFragmentComponent
import com.droidsmith.petri.injection.components.PrivateChatFragmentComponent
import com.droidsmith.petri.ui.petri.PetriViewModel
import dagger.Module
import dagger.Provides


@Module(subcomponents = arrayOf(
        FeedFragmentComponent::class,
        PrivateChatFragmentComponent::class
))
//TODO: The viewModel will most likely needed stuff injected into it so this will probably provide more
class PetriActivityModule {


    @Provides
    fun providePetriViewModel(): PetriViewModel = PetriViewModel()

}