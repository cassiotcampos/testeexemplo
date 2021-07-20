package br.com.cassio.ribeiro.example.githubissues.module

import android.content.Context
import br.com.cassio.ribeiro.example.githubissues.network.GithubIssuesServices
import br.com.cassio.ribeiro.example.githubissues.ui.issues.IssuesViewModel
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val myViewModels = module{
    viewModel {
        IssuesViewModel(get())
    }
}

val postModule = module {

    single {
        val downloder = okHttp3Downloader(get())
    }

    single {
        okHttp()
    }

    single {
        retrofit("https://api.github.com/")
    }

    single {
        get<Retrofit>().create(GithubIssuesServices::class.java)
    }
}


private fun okHttp3Downloader(client: OkHttpClient) = OkHttp3Downloader(client)
private fun picasso(context: Context, downloader: OkHttp3Downloader) = Picasso.Builder(context)
    .downloader(downloader)
    .build()


private fun okHttp() = OkHttpClient.Builder()
    .build()

private fun retrofit(baseUrl: String) = Retrofit.Builder()
    .callFactory(OkHttpClient.Builder().build())
    .baseUrl(baseUrl)
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .build()

val mModules = listOf(postModule, myViewModels)