package com.droidsmith.petri.injection.modules

import android.support.v4.app.Fragment
import com.droidsmith.petri.injection.components.FeedFragmentComponent
import com.droidsmith.petri.injection.components.PrivateChatFragmentComponent
import com.droidsmith.petri.ui.chat.PrivateChatFragment
import com.droidsmith.petri.ui.feed.FeedFragment
import dagger.Binds
import dagger.Module
import dagger.android.support.FragmentKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap


@Module
abstract class FragmentModule {

    @Binds
    @IntoMap
    @FragmentKey(FeedFragment::class)
    internal abstract fun provideHomeFragmentFactory(builder: FeedFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>

    @Binds
    @IntoMap
    @FragmentKey(PrivateChatFragment::class)
    internal abstract fun providePrivateChatFragmentFactory(builder: PrivateChatFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>

}