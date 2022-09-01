package com.vishal.kotlin_github.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vishal.kotlin_github.R
import com.vishal.kotlin_github.adapter.ContributorClickListner
import com.vishal.kotlin_github.adapter.ContributorRVAdapter
import com.vishal.kotlin_github.databinding.ActivityContributorBinding
import com.vishal.kotlin_github.model.ContributorsItemModel
import com.vishal.kotlin_github.network.NetworkRepositoryImpl
import com.vishal.kotlin_github.network.RetrofitService
import com.vishal.kotlin_github.viewmodel.vmfactory.ContributorVMFactory
import com.vishal.kotlin_github.viewmodel.ContributorViewModel


class ContributorActivity : AppCompatActivity(), ContributorClickListner {

    private lateinit var binding: ActivityContributorBinding
    private lateinit var viewModel: ContributorViewModel
    private lateinit var contributorList: List<ContributorsItemModel>
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //INFLATE LAYOUT FOR THIS FRAGMENT
        binding = ActivityContributorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView = findViewById(R.id.recyclerView)

        //INSTANTIATE CONTRIBUTOR VIEW MODEL
        //val github = RetrofitService.getInstance(false)
        viewModel = ViewModelProvider(
            this,
            ContributorVMFactory(NetworkRepositoryImpl())
        )[ContributorViewModel::class.java]

        viewModel.username = intent.getStringExtra("username").toString()
        viewModel.repository = intent.getStringExtra("repository").toString()
        viewModel.contributorApiCall()

        //RESPONSE OBSERVER
        viewModel.response.observe(this) {
            if (it != null) {
                contributorList = it
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.adapter = ContributorRVAdapter(contributorList, this)
            }
        }

        //LOADING OBSERVER
        viewModel.loading.observe(this) { binding.progressBar.isVisible = it }

        //FAILURE OBSERVER
        viewModel.failure.observe(this) { binding.errorScreen.isVisible = it }
    }

    override fun onContributorClickListener(username: String) {
        startActivity(Intent(this, ProfileActivity::class.java)
            .putExtra("username", username))
    }

}

