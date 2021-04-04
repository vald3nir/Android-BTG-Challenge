package com.vald3nir.data.rest.models

import com.google.gson.annotations.SerializedName
import com.vald3nir.data.database.model.Exchange

data class ExchangeResponse(
    @SerializedName("success") val success: Boolean?,
    @SerializedName("terms") val terms: String?,
    @SerializedName("privacy") val privacy: String?,
    @SerializedName("quotes") val quotes: HashMap<String, Double>
)