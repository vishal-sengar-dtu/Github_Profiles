package com.vishal.kotlin_github.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso
import com.vishal.kotlin_github.R
import com.vishal.kotlin_github.adapter.ViewPagerAdapter
import com.vishal.kotlin_github.network.GithubInterface
import com.vishal.kotlin_github.network.RetrofitService
import com.vishal.kotlin_github.databinding.ActivityProfileBinding
import com.vishal.kotlin_github.network.NetworkRepositoryImpl
import com.vishal.kotlin_github.util.CircleTransform
import com.vishal.kotlin_github.viewmodel.ProfileViewModel
import com.vishal.kotlin_github.viewmodel.vmfactory.ProfileVMFactory

class ProfileActivity : AppCompatActivity(){

    private lateinit var binding: ActivityProfileBinding
    private lateinit var viewModel: ProfileViewModel
    private lateinit var username : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //INSTANTIATE PROFILE VIEW MODEL
        //val github = RetrofitService.getInstance(false)
        viewModel = ViewModelProvider(
            this,
            ProfileVMFactory(NetworkRepositoryImpl())
        )[ProfileViewModel::class.java]

        //GETTING DATA FROM LOGIN ACTIVITY
        username = intent.getStringExtra("username").toString()
        viewModel.username = username
        viewModel.userApiCall()
        viewModel.repoApiCall()

        //RESPONSE OBSERVER
        viewModel.userResponse.observe(this){
            if(it!=null) {

                //DATA BINDING
                binding.user = it
                Picasso.get().load(it.avatarUrl)
                    .resize(250, 250)
                    .placeholder(R.drawable.hint)
                    .transform(CircleTransform())
                    .into(binding.imageView)

                //SETTING VIEW PAGER
                binding.viewPager.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
                TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
                    when(position) {
                        0 -> tab.text = "REPOSITORIES"
                        1 -> tab.text = "PINNED REPOSITORIES"
                    }
                }.attach()
            }
        }

        //LOADING OBSERVER
        viewModel.userLoading.observe(this){
            binding.progressBar.isVisible = it
            binding.rootContainer.isVisible = !it
        }

        //FAILURE OBSERVER
        viewModel.userFailure.observe(this){
            if(it){
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.rootContainer, NotFoundFragment())
                    .commit()
            }
        }
    }

}