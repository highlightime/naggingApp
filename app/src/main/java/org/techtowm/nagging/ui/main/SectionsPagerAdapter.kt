package org.techtowm.nagging.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import org.techtowm.nagging.R

private val TAB_TITLES = arrayOf(
        R.string.tab_text_1,
        R.string.tab_text_2,
        R.string.tab_text_3
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager)
    : FragmentPagerAdapter(fm) {
    val PAGE_MAX_CNT = 3
    override fun getItem(position: Int): Fragment {
        val fragment = when(position)
        {
            0 -> MainFragment().newInstance()
            1 -> LabFragment().newInstance()
            2 -> SettingFragment().newInstance()
            else -> MainFragment().newInstance()
        }
        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        val title = when(position)
        {
            0 -> "Alarm"
            1 -> "Lab"
            2 -> "Setting"
            else -> "main"
        }
        return title
    }

    override fun getCount(): Int {
        return PAGE_MAX_CNT
    }
}