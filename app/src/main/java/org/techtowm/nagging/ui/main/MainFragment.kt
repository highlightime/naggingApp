package org.techtowm.nagging.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import org.techtowm.nagging.R


open class MainFragment: Fragment()
{
    fun newInstance(): MainFragment
    {
        val args = Bundle()

        val frag = MainFragment()
        frag.arguments = args

        return frag
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val v = inflater.inflate(R.layout.fragment_main, container, false)
        return v
    }

}