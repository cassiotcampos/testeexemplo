package br.com.cassio.ribeiro.example.githubissues.ui.issues

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.cassio.ribeiro.example.githubissues.databinding.FragmentDashboardBinding
import br.com.cassio.ribeiro.example.githubissues.model.Issue
import br.com.cassio.ribeiro.example.githubissues.ui.adapter.IssuesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class IssuesFragment : Fragment() {

    private lateinit var adapter : IssuesAdapter
    private val issuesViewModel: IssuesViewModel by viewModel()
    private var _binding: FragmentDashboardBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root : View = binding.root

        initLayout()

        observeViewModel()

        return root
    }

    private fun observeViewModel() {
        issuesViewModel.getIssueListObserver().observe(
            viewLifecycleOwner, {
                updateList(it)
            }
        )
    }

    private fun updateList(issues: List<Issue>?) {
        if (issues != null) {
            adapter.updateData(issues)
        }else{
            Toast.makeText(activity, "Error", Toast.LENGTH_LONG).show()
        }
    }

    private fun initLayout() {
        val recyclerView: RecyclerView = binding.rvIssues
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = IssuesAdapter()
        recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}