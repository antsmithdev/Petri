package com.droidsmith.petri.injection.modules

import com.droidsmith.petri.ui.feed.FeedViewModel
import dagger.Module
import dagger.Provides

@Module
class FeedFragmentModule {

    @Provides
    fun provideFeedViewModel(): FeedViewModel = FeedViewModel()

}