package com.wilsonrc.favoritemovies.ui.movies

import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class TabAdapter internal constructor(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val mFragmentList : MutableList<Fragment> = mutableListOf()
    private val mFragmentTitleList :  MutableList<String> = mutableListOf()

    init{

        mFragmentList.add(MoviesFragment.newInstance("GENERAL"))
        mFragmentTitleList.add("General")
        mFragmentList.add(MoviesFragment.newInstance("FAVORITES"))
        mFragmentTitleList.add("Favorites")
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