package br.com.cassio.ribeiro.example.githubissues.network

import br.com.cassio.ribeiro.example.githubissues.model.Issue
import io.reactivex.Observable
import retrofit2.http.GET

interface GithubIssuesServices {
    @GET("repos/JetBrains/kotlin/issues")
    fun getKotlinIssues() : Observable<List<Issue>>
}