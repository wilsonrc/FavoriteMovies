package com.wilsonrc.favoritemovies.ui.movies

import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class TabAdapter internal constructor(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val mFragmentList: MutableList<Fragment> = mutableListOf()
    private val mFragmentTitleList: MutableList<String> = mutableListOf()

    private val GENERAL_ARG = "GENERAL"
    private val FAVORITES_ARG = "FAVORITES"
    private val GENERAL_FRAGMENT_TITLE = "General"
    private val FAVORITES_FRAGMENT_TITLE = "Favorites"

    init {
        mFragmentList.add(MoviesFragment.newInstance(GENERAL_ARG))
        mFragmentTitleList.add(GENERAL_FRAGMENT_TITLE)
        mFragmentList.add(MoviesFragment.newInstance(FAVORITES_ARG))
        mFragmentTitleList.add(FAVORITES_FRAGMENT_TITLE)
    }


    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    fun addFragment(fragment: Fragment, title: String) {
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }

    @Nullable
    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitleList[position]
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

}