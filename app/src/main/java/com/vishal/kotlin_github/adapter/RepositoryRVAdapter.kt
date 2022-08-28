package com.vishal.kotlin_github.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.vishal.kotlin_github.R
import com.vishal.kotlin_github.model.RepositoryItem
import com.vishal.kotlin_github.model.RepositoryItemModel
import com.vishal.kotlin_github.util.RepositoryUtil

interface RepositoryClickListner {
    fun onRepositoryClickListener(username: String, repository: String)
}

//REPOSITORY RECYCLER VIEW ADAPTER
class RepositoryRVAdapter(
    private val repositoryList: List<RepositoryItemModel>,
    private val repositoryClickListner: RepositoryClickListner
    ) :
    RecyclerView.Adapter<RepositoryHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.repository, parent, false)
        return RepositoryHolder(itemView)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RepositoryHolder, position: Int) {
        val repositoryData = repositoryList[position]
        holder.bind(repositoryData, repositoryClickListner)
    }

    override fun getItemCount(): Int { return repositoryList.size }

}

//REPOSITORY VIEW HOLDER
class RepositoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    private lateinit var username: String
    private  lateinit var repository: String
    private val repoImg: ImageView = itemView.findViewById(R.id.repoImg)
    private val name: TextView = itemView.findViewById(R.id.name)
    private val description: TextView = itemView.findViewById(R.id.desc)
    private val language: TextView = itemView.findViewById(R.id.lang)
    private val stars: TextView = itemView.findViewById(R.id.stars)
    private val forks: TextView = itemView.findViewById(R.id.fork)
    private val lastUpdated: TextView = itemView.findViewById(R.id.lastUpdated)

    @RequiresApi(Build.VERSION_CODES.O)
    fun bind(repositoryData: RepositoryItemModel, repositoryClickListner: RepositoryClickListner){

        name.text = repositoryData.name
        description.text = RepositoryUtil.getDescription(repositoryData.description)
        language.text = repositoryData.language
        stars.text = repositoryData.stargazersCount.toString()
        forks.text = repositoryData.forks.toString()
        lastUpdated.text = repositoryData.updatedAt!!.let { RepositoryUtil.getLastUpdated(it) }
        repoImg.setImageResource(RepositoryUtil.getImage())
        username = repositoryData.fullName!!.let { RepositoryUtil.getUsername(it) }
        repository = repositoryData.name.toString()

        itemView.setOnClickListener(){
            repositoryClickListner.onRepositoryClickListener(username, repository)
        }
    }

}