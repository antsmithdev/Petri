package com.droidsmith.petri.injection.modules

import com.droidsmith.petri.ui.chat.PrivateChatViewModel
import dagger.Module
import dagger.Provides

@Module
class PrivateChatFragmentModule {

    @Provides
    fun providePrivateChatViewModel(): PrivateChatViewModel = PrivateChatViewModel()

}