package com.zkhy.fenggang.community.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.comm.base.BaseFragment
import com.zkhy.library.widget.ListItemPopupWindow
import kotlinx.android.synthetic.main.fragment_community.*

class CommunityFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_community, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTitleBar(view, "社区", R.drawable.search) {

        }

        communityPublicBtn.setOnClickListener {
            val mWindow = ListItemPopupWindow(context)
            mWindow.showAsDropDown(topView)
        }
    }
}
