package com.hads.digikala.utils

import com.hads.digikala.BuildConfig

object Constants {
    const val BASE_URL = "https://dig-za0p.onrender.com/api/"
    const val ENGLISH_LANG = "en"
    const val PERSIAN_LANG = "fa"
    const val DATASTORE_NAME = "DIGIKALA_NAME"
    const val TIMEOUT_IN_SECOND: Long = 60
    const val API_KEY = BuildConfig.X_API_KEY
    var USER_LANGUAGE = PERSIAN_LANG
}