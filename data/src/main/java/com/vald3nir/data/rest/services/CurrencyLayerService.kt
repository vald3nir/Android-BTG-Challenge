package com.vald3nir.data.rest.services

import com.vald3nir.data.rest.models.ExchangeResponse
import com.vald3nir.data.rest.models.CurrenciesResponse
import retrofit2.Response
import retrofit2.http.GET

interface CurrencyLayerService {

    @GET("list")
    suspend fun listCurrencies(): Response<CurrenciesResponse>

    @GET("live")
    suspend fun listExchanges(): Response<ExchangeResponse>
}