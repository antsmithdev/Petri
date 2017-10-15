package com.droidsmith.petri.ui.feed

import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.droidsmith.petri.R
import com.droidsmith.petri.databinding.FragmentFeedBinding
import javax.inject.Inject


class FeedFragment : Fragment() {


    @Inject
    lateinit var viewModel: FeedViewModel


    companion object {
        fun newInstance(): FeedFragment {
            val fragment = FeedFragment()
            val args = Bundle()
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentFeedBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_feed, container, false)
        return binding.root
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }



}