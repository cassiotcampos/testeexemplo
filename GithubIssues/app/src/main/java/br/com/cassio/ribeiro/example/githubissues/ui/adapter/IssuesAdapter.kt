package br.com.cassio.ribeiro.example.githubissues.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.cassio.ribeiro.example.githubissues.R
import br.com.cassio.ribeiro.example.githubissues.model.Issue

class IssuesAdapter : RecyclerView.Adapter<IssuesAdapter.IssueViewHolder>() {

    var items = ArrayList<Issue>()

    class IssueViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val issueNameTv : TextView = view.findViewById(R.id.issue_name_tv)
        fun bind(data: Issue){
            issueNameTv.text = data.url
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssueViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_issue, parent, false)
        return IssueViewHolder(view)
    }

    override fun onBindViewHolder(holder: IssueViewHolder, position: Int) {
        holder.bind(items.get(position))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateData(it: List<Issue>) {
        items = it as ArrayList<Issue>
        notifyDataSetChanged()
    }
}