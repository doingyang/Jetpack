package com.project.jetpack.paging3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.project.jetpack.R

/**
 * 第一点，我们使用Kotlin的高阶函数来给重试按钮注册点击事件，这样当点击重试按钮时，构造函数中传入的函数类型参数就会被回调，我们待会将在那里加入重试逻辑。
 * 第二点，在onBindViewHolder()中会根据LoadState的状态来决定如何显示底部界面，如果是正在加载中那么就显示加载进度条，如果是加载失败那么就显示重试按钮。
 */
class RepoFooterAdapter(val retry: () -> Unit) : LoadStateAdapter<RepoFooterAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val progressBar: ProgressBar = itemView.findViewById(R.id.progress_bar)
        val retryButton: Button = itemView.findViewById(R.id.retry_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.footer_item, parent, false)
        val holder = ViewHolder(view)
        holder.retryButton.setOnClickListener {
            retry()
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.progressBar.isVisible = loadState is LoadState.Loading
        holder.retryButton.isVisible = loadState is LoadState.Error
    }

}