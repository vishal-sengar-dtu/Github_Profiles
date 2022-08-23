package com.vishal.kotlin_github.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.vishal.kotlin_github.view.RepositoryFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle){

    override fun getItemCount(): Int { return 2; }

    override fun createFragment(position: Int): Fragment {
        val repositoryFragment = RepositoryFragment()

        when(position){
            0 -> RepositoryFragment()
            1 -> RepositoryFragment()
        }
        return RepositoryFragment()
    }

}