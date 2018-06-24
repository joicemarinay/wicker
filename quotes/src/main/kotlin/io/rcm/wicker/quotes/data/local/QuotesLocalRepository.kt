package io.rcm.wicker.quotes.data.local

import io.rcm.wicker.quotes.domain.QuotesRepository
import io.rcm.wicker.quotes.domain.model.QuoteEntity
import io.reactivex.Completable
import javax.inject.Inject

/**
 * Created by joicemarinay on 6/24/18.
 */
internal class QuotesLocalRepository @Inject constructor(private val localSource: QuotesLocalSource):
    QuotesRepository {

  override fun save(quote: QuoteEntity): Completable = localSource.saveQuote(quote)
}