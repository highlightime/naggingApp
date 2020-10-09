package org.techtowm.nagging.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.techtowm.nagging.R

open class SettingFragment: Fragment()
{
    fun newInstance(): SettingFragment
    {
        val args = Bundle()

        val frag = SettingFragment()
        frag.arguments = args

        return frag
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val v = inflater.inflate(R.layout.fragment_setting, container, false)
        return v
    }
}