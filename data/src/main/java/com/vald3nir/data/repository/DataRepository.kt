package com.vald3nir.data.repository

import com.vald3nir.data.database.DatabaseHandler
import com.vald3nir.data.database.model.CurrencyView
import com.vald3nir.data.database.model.Exchange
import com.vald3nir.data.database.model.Flag
import com.vald3nir.data.rest.RestClient

class DataRepository(private val database: DatabaseHandler, private val restClient: RestClient) {

    suspend fun fillDatabase() {
        try {
            database.updateCurrencies(restClient.listCurrencies())
            database.updateExchanges(restClient.listExchanges())
            database.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun listFlags(): List<Flag>? {
        return database.listAllFlag()
    }

    suspend fun listExchanges(): List<Exchange>? {
        return database.listAllExchanges()
    }

    suspend fun getUSDExchange(currencyCode: String?): Double {
        val exchange = database.getExchange(quote = "USD${currencyCode}")
        return if (exchange?.value != null) exchange.value else 0.0
    }

    suspend fun listCurrencies(): List<CurrencyView>? {
        return database.listAllCurrenciesWithFlag()
    }

    suspend fun getCurrency(code: String?): CurrencyView? {
        return database.getCurrencyWithFlag(code)
    }
}