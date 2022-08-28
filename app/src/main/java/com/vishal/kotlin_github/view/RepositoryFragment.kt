package com.vishal.kotlin_github.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vishal.kotlin_github.R
import com.vishal.kotlin_github.adapter.RepositoryClickListner
import com.vishal.kotlin_github.adapter.RepositoryRVAdapter
import com.vishal.kotlin_github.databinding.FragmentRepositoryBinding
import com.vishal.kotlin_github.model.RepositoryItem
import com.vishal.kotlin_github.model.RepositoryItemModel
import com.vishal.kotlin_github.network.NetworkRepositoryImpl
import com.vishal.kotlin_github.network.RetrofitService
import com.vishal.kotlin_github.viewmodel.ProfileViewModel
import com.vishal.kotlin_github.viewmodel.vmfactory.ProfileVMFactory


class RepositoryFragment : Fragment(), RepositoryClickListner{

    private var _binding: FragmentRepositoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ProfileViewModel
    private lateinit var repositoryList: List<RepositoryItemModel>
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        //INFLATE LAYOUT FOR THIS FRAGMENT
        _binding = FragmentRepositoryBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        recyclerView = view.findViewById(R.id.recyclerView)

        //INSTANTIATE PROFILE VIEW MODEL FROM PARENT ACTIVITY
        //val github = RetrofitService.getInstance(false)
        viewModel = activity?.let {
            ViewModelProvider(
                it,
                ProfileVMFactory(NetworkRepositoryImpl())
            )[ProfileViewModel::class.java] }!!

        //OBSERVING LIVE DATA
        activity?.let{ parent ->
            viewModel.repoResponse.observe(parent) {
                if(it != null){
                    repositoryList = it
                    recyclerView.layoutManager = LinearLayoutManager(context)
                    recyclerView.adapter = RepositoryRVAdapter(repositoryList, this)
                }
            }

            viewModel.repoLoading.observe(parent) { binding.progressBar.isVisible = it }

            viewModel.repoFailure.observe(parent) { binding.errorScreen.isVisible = it }
        }

        return view
    }

    override fun onRepositoryClickListener(username: String, repository: String) {
        startActivity(
            Intent(context, ContributorActivity::class.java)
                .putExtra("username", username)
                .putExtra("repository", repository)
        )

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
