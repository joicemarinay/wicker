package io.rcm.wicker.quotes.domain.usecase

import io.rcm.wicker.base.domain.UseCase
import io.rcm.wicker.quotes.domain.model.QuoteEntity
import io.rcm.wicker.quotes.presentation.QuoteUi

/**
 * Created by joicemarinay on 17/07/2018.
 */
internal interface SoftDeleteQuote: UseCase<SoftDeleteQuote.Result> {

  sealed class Result {
    object OnSuccess: Result()
    object OnError: Result()
  }

  fun execute(quote: QuoteUi)
}