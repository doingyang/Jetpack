package com.project.jetpack.paging3

import androidx.paging.PagingSource
import androidx.paging.PagingState

/**
 * PagingSource<Int, Repo>
 * 两个泛型类型:
 * 第一个类型表示页数的数据类型，所以直接用整型就可以了。
 * 第二个类型表示每一项数据（注意不是每一页）所对应的对象类型，这里使用刚才定义的Repo。
 */
class RepoPagingSource(private val gitHubService: GitHubService) : PagingSource<Int, Repo>() {

    /**
     * 先通过params参数得到key，这个key就是代表着当前的页数。
     * 注意key是可能为null的，如果为null的话，我们就默认将当前页数设置为第一页。
     * 还可以通过params参数得到loadSize，表示每一页包含多少条数据，这个数据的大小我们可以在稍后设置
     *
     * 接下来调用刚才在GitHubService中定义的searchRepos()接口，并把page和pageSize传入，从服务器获取当前页所对应的数据。
     *
     * 最后需要调用LoadResult.Page()函数，构建一个LoadResult对象并返回。
     * 注意LoadResult.Page()函数接收3个参数:
     * 第一个参数传入从响应数据解析出来的Repo列表即可;
     * 第二和第三个参数分别对应着上一页和下一页的页数。
     * 针对于上一页和下一页，我们还额外做了个判断，如果当前页已经是第一页或最后一页，那么它的上一页或下一页就为null。
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repo> {
        return try {
            val page = params.key ?: 1 // set page 1 as default
            val pageSize = params.loadSize

            val repoResponse = gitHubService.searchRepos(page, pageSize)
            val repoItems = repoResponse.items

            val prevKey = if (page > 1) page - 1 else null
            val nextKey = if (repoItems.isNotEmpty()) page + 1 else null
            LoadResult.Page(repoItems, prevKey, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    /**
     * 这个函数是Paging 3.0.0-beta01版本新增的，以前的alpha版中并没有。
     * 它是属于Paging 3比较高级的用法，暂时涉及不到，所以直接返回null就可以了。
     */
    override fun getRefreshKey(state: PagingState<Int, Repo>): Int? = null

}