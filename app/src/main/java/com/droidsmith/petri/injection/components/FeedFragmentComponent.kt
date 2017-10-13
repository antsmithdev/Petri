package com.droidsmith.petri.injection.components

import com.droidsmith.petri.injection.modules.FeedFragmentModule
import com.droidsmith.petri.ui.feed.FeedFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = arrayOf(FeedFragmentModule::class))
interface FeedFragmentComponent: AndroidInjector<FeedFragment> {

    @Subcomponent.Builder
    abstract class Builder: AndroidInjector.Builder<FeedFragment>()


}