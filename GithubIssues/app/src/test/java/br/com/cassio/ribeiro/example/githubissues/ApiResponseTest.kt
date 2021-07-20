package br.com.cassio.ribeiro.example.githubissues


import androidx.lifecycle.Observer
import br.com.cassio.ribeiro.example.githubissues.model.Issue
import br.com.cassio.ribeiro.example.githubissues.network.GithubIssuesServices
import br.com.cassio.ribeiro.example.githubissues.ui.issues.IssuesViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.test.KoinTest


class ApiResponseTest : KoinTest {

    @MockK
    private lateinit var mockService: GithubIssuesServices

    private lateinit var vm : IssuesViewModel

    @MockK
    val observer2 = mockk<Observer<List<Issue>>>()


    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler({ Schedulers.trampoline()})
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @After
    fun tearDown() {

    }


    @Test
    fun `checar se chamada posta a livedata`() {
        val issues = listOf(Issue("1"), Issue("2"))
        every { mockService.getKotlinIssues() } returns Observable.just(issues)
        vm = IssuesViewModel(mockService)
        vm.makeApiCall()
    }
}