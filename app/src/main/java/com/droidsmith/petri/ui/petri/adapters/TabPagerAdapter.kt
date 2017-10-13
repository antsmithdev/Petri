package com.droidsmith.petri.ui.petri.adapters

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.droidsmith.petri.ui.chat.PrivateChatFragment
import com.droidsmith.petri.ui.feed.FeedFragment


class TabPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm)  {
    internal val PAGE_COUNT = 2
    private val tabTitles = arrayOf("Feed", "Private")

    override fun getCount(): Int {
        return PAGE_COUNT
    }

    override fun getItem(position: Int) = when(position){
        0 -> FeedFragment.newInstance()
        1 -> PrivateChatFragment.newInstance()
        else -> FeedFragment.newInstance()
    }

    override fun getPageTitle(position: Int): CharSequence {
        // Generate title based on item position
        return tabTitles[position]
    }
}