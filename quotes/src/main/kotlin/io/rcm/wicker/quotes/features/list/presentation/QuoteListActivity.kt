package io.rcm.wicker.quotes.features.list.presentation

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.view.View
import io.rcm.wicker.base.common.observe
import io.rcm.wicker.base.presentation.BaseActivity
import io.rcm.wicker.quotes.QuotesDependencyHolder
import io.rcm.wicker.quotes.R
import io.rcm.wicker.quotes.features.details.presentation.QuoteDetailsActivity
import io.rcm.wicker.quotes.features.list.injection.QuoteListComponent
import io.rcm.wicker.quotes.features.list.presentation.adapter.QuoteListAdapter
import io.rcm.wicker.quotes.features.list.presentation.adapter.QuoteListViewHolder
import io.rcm.wicker.quotes.features.list.presentation.QuoteListState.*
import io.rcm.wicker.quotes.presentation.QuoteUi
import io.rcm.wicker.quotes.features.writer.presentation.QuoteWriterActivity
import kotlinx.android.synthetic.main.wicker_quote_list_view.*

/**
 * Created by joicemarinay on 09/05/2018.
 */
internal class QuoteListActivity(override val layoutResourceId: Int = R.layout.wicker_quote_list_view):
    BaseActivity<QuoteListViewModel, QuoteListState>(), QuoteListViewHolder.Listener {

  private val component: QuoteListComponent by lazy { QuotesDependencyHolder.listComponent() }
  private val quoteListAdapter: QuoteListAdapter = QuoteListAdapter(this)

  override fun onCreate(savedInstanceState: Bundle?) {
    component.inject(this)
    super.onCreate(savedInstanceState)
    setClickListeners()
    setDataObservers()
    setQuoteListRecyclerView()
  }

  override fun onQuoteClicked(quote: QuoteUi) {
    startActivity(QuoteDetailsActivity.intent(this, quote))
  }

  override fun onStateChange(state: QuoteListState) = when(state) {
    is GetQuotesFailed -> showError()
    is Loading -> showLoading()
    is QuotesEmpty -> showEmptyView()
    is QuotesLoaded -> showQuoteList(state.quotes)
  }

  private fun setClickListeners() {
    quoteList_fab_addQuote.setOnClickListener { openQuoteWriter() }
  }

  private fun setDataObservers() {
    observe(viewModel.state()) { onStateChange(it) }
  }

  private fun setQuoteListRecyclerView() {
    quoteList_recyclerView_quotes.adapter = quoteListAdapter
    quoteList_recyclerView_quotes.addItemDecoration(
        DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
  }

  private fun showEmptyView() {
    quoteList_empty.visibility = View.VISIBLE
    quoteList_recyclerView_quotes.visibility = View.GONE
  }

  private fun showError() {
    //TODO
  }

  private fun showLoading() {
    //TODO
  }

  private fun showQuoteList(quotes: List<QuoteUi>) {
    quoteListAdapter.setQuoteList(quotes)
    quoteList_empty.visibility = View.GONE
    quoteList_recyclerView_quotes.visibility = View.VISIBLE
  }

  private fun openQuoteWriter() {
    startActivity(Intent(this, QuoteWriterActivity::class.java))
  }
}