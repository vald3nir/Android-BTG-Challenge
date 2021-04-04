package com.vald3nir.data.database

import android.content.Context
import androidx.room.Room
import com.vald3nir.data.database.dao.CurrencyDao
import com.vald3nir.data.database.dao.CurrencyViewDao
import com.vald3nir.data.database.dao.ExchangeDao
import com.vald3nir.data.database.dao.FlagDao
import com.vald3nir.data.database.model.Currency
import com.vald3nir.data.database.model.CurrencyView
import com.vald3nir.data.database.model.Exchange
import com.vald3nir.data.database.model.Flag

class DatabaseHandler(context: Context) {

    private val db: AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "database.db")
            .createFromAsset("database/database_embedded.db")
            .build()

    private val exchangeDao: ExchangeDao = db.ExchangeDao()
    private val currencyDao: CurrencyDao = db.CurrencyDao()
    private val flagDao: FlagDao = db.FlagDao()
    private val currencyWithFlagDao: CurrencyViewDao = db.CurrencyWithFlagDao()

    fun listAllExchanges(): List<Exchange>? {
        return exchangeDao.getAll()
    }

    fun updateExchanges(list: List<Exchange>?) {
        exchangeDao.deleteAll()
        exchangeDao.insertAll(list)
    }

    fun getExchange(quote: String?): Exchange? {
        return exchangeDao.loadByQuote(quote)
    }

    fun listAllCurrencies(): List<Currency>? {
        return currencyDao.getAll()
    }

    fun listAllCurrenciesWithFlag(): List<CurrencyView>? {
        return currencyWithFlagDao.listAll()
    }

    fun getCurrencyWithFlag(code: String?): CurrencyView? {
        return currencyWithFlagDao.get(code)
    }

    fun updateCurrencies(list: List<Currency>?) {
        currencyDao.deleteAll()
        currencyDao.insertAll(list)
    }

    fun listAllFlag(): List<Flag>? {
        return flagDao.getAll()
    }

    fun getFlagFromID(code: String): Flag? {
        return flagDao.loadById(code)
    }

    fun close() {
        db.close()
    }

}