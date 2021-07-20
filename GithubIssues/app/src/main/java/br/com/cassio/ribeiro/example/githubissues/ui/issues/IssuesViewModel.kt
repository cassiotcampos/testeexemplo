package br.com.cassio.ribeiro.example.githubissues.ui.issues

import androidx.lifecycle.MutableLiveData
import br.com.cassio.ribeiro.example.githubissues.model.Issue
import br.com.cassio.ribeiro.example.githubissues.network.GithubIssuesServices
import br.com.cassio.ribeiro.example.githubissues.ui.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class IssuesViewModel(private val githubIssuesServices: GithubIssuesServices) : BaseViewModel() {


    var issueListLiveData: MutableLiveData<List<Issue>> = MutableLiveData()

    fun getIssueListObserver(): MutableLiveData<List<Issue>> {
        return issueListLiveData
    }

    init {
        makeApiCall()
    }

    fun makeApiCall() {
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            githubIssuesServices.getKotlinIssues()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ response -> onResponse(response) }, { t -> onFailure(t) })
        )

    }

    private fun onResponse(response: List<Issue>?) {
        issueListLiveData.postValue(response)
    }

    private fun onFailure(t: Throwable?) {

    }
}
