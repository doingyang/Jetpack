package com.project.jetpack.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.jetpack.R
import com.project.jetpack.paging3.RepoFooterAdapter
import com.project.jetpack.paging3.RepoViewModel
import com.project.jetpack.paging3.RepoAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class Paging3Activity : AppCompatActivity() {

    private val recyclerView: RecyclerView by lazy { findViewById<RecyclerView>(R.id.recycler_view) }
    private val progressBar: ProgressBar by lazy { findViewById<ProgressBar>(R.id.progress_bar) }

    private val viewModel by lazy { ViewModelProvider(this).get(RepoViewModel::class.java) }

    private val repoAdapter = RepoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging3)
        initEvent()
    }

    /**
     * 调用了RepoAdapter的submitData()函数。这个函数是触发Paging 3分页功能的核心，调用这个函数之后，Paging 3就开始工作了。
     * submitData()接收一个PagingData参数，这个参数我们需要调用ViewModel中返回的Flow对象的collect()函数才能获取到，collect()函数有点类似于Rxjava中的subscribe()函数，总之就是订阅了之后，消息就会源源不断往这里传。
     * 不过由于collect()函数是一个挂起函数，只有在协程作用域中才能调用它，因此这里又调用了lifecycleScope.launch()函数来启动一个协程。
     */
    private fun initEvent() {
        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.adapter = repoAdapter
        // 调用RepoAdapter的withLoadStateFooter()函数即可将FooterAdapter集成到RepoAdapter当中。
        recyclerView.adapter = repoAdapter.withLoadStateFooter(RepoFooterAdapter { repoAdapter.retry() })

        lifecycleScope.launch {
            viewModel.getPagingData().collect { pagingData ->
                repoAdapter.submitData(pagingData)
            }
        }
        repoAdapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.NotLoading -> {
                    progressBar.visibility = View.INVISIBLE
                    recyclerView.visibility = View.VISIBLE
                }
                is LoadState.Loading -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.INVISIBLE
                }
                is LoadState.Error -> {
                    val state = it.refresh as LoadState.Error
                    progressBar.visibility = View.INVISIBLE
                    Toast.makeText(this, "Load Error: ${state.error.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}