package com.vishal.kotlin_github.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vishal.kotlin_github.R
import com.vishal.kotlin_github.model.ContributorsItem
import com.vishal.kotlin_github.model.ContributorsItemModel
import com.vishal.kotlin_github.util.CircleTransform


interface ContributorClickListner {
    fun onContributorClickListener(username: String)
}

//CONTRIBUTOR RECYCLER VIEW ADAPTER
class ContributorRVAdapter(
    private val contributorList: List<ContributorsItemModel>,
    private val contributorClickListner: ContributorClickListner
    ) :
    RecyclerView.Adapter<ContributorHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContributorHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.contributor, parent, false)
        return ContributorHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContributorHolder, position: Int) {
        val contributorData = contributorList[position]
        holder.bind(contributorData, contributorClickListner)
    }

    override fun getItemCount(): Int { return contributorList.size }
}

//CONTRIBUTOR VIEW HOLDER
class ContributorHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private lateinit var username: String
    private val avatar: ImageView = itemView.findViewById(R.id.avatar)
    private val user: TextView = itemView.findViewById(R.id.login)
    private val contributions: TextView = itemView.findViewById(R.id.contributions)

    fun bind(contributorData: ContributorsItemModel, contributorClickListner: ContributorClickListner){
        username = contributorData.login.toString()

        user.text = username
        contributions.text = contributorData.contributions.toString()
        Picasso.get()
            .load(contributorData.avatarUrl)
            .placeholder(R.drawable.hint)
            .transform(CircleTransform())
            .into(avatar)

        itemView.setOnClickListener(){
            contributorClickListner.onContributorClickListener(username)
        }

    }
}