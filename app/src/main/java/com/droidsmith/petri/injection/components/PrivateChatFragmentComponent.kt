package com.droidsmith.petri.injection.components

import com.droidsmith.petri.injection.modules.PrivateChatFragmentModule
import com.droidsmith.petri.ui.chat.PrivateChatFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector


@Subcomponent(modules = arrayOf(PrivateChatFragmentModule::class))
interface PrivateChatFragmentComponent: AndroidInjector<PrivateChatFragment> {

    @Subcomponent.Builder
    abstract class Builder: AndroidInjector.Builder<PrivateChatFragment>()

}