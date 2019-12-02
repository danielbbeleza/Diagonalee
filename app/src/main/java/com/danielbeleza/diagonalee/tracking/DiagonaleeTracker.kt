package com.danielbeleza.diagonalee.tracking

import android.app.Application
import android.os.Bundle
import android.os.Parcelable
import com.danielbeleza.common_core.EnumEventName
import com.danielbeleza.diagonalee.entity.Product
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.FirebaseAnalytics.*
import com.google.firebase.analytics.FirebaseAnalytics.Event.*
import timber.log.Timber
import java.lang.Exception

object DiagonaleeTracker {

    private lateinit var firebase: FirebaseAnalytics

    fun logCustomEvent(eventType: String, eventsList: Parameters = emptyList()) {
        eventsList.validateEventsList { parameters ->
            val bundle = buildBundleWithParams(parameters)
            firebase.logEvent(eventType, bundle)
        }
    }

    fun trackProductClick(product: Product) {
        val bundle = Bundle().apply {
            putString(Param.ITEM_ID, "${product.id}")
            putString(Param.ITEM_NAME, product.name)
            putString(Param.PRICE, "${product.priceWithDiscount}")
        }
        firebase.logEvent(SELECT_CONTENT, bundle)
    }

    fun init(application: Application) {
        if(!DiagonaleeTracker::firebase.isInitialized) {
            firebase = getInstance(application)
        } else {
            throw IllegalStateException("Firebase instance is already initialized")
        }
    }

    private fun Parameters.validateEventsList(triggerEvent: (Parameters) -> Unit) {
        if(this.isEmpty()) {
            Timber.w("Event not triggered: eventsList is empty!")
        } else {
            triggerEvent(this)
        }
    }

    private fun buildBundleWithParams(parameters: Parameters) : Bundle {
        val bundle = Bundle()
        parameters.forEach { parameter ->
            when(val value = parameter.second) {
                is String -> bundle.putString(parameter.toString(), value)
                is Int -> bundle.putInt(parameter.toString(), value)
                is Float -> bundle.putFloat(parameter.toString(), value)
                is Parcelable -> bundle.putParcelable(parameter.toString(), value)
                else -> throw Exception("Unknown parameter type: $value")
            }
        }
        return bundle
    }
}

typealias Parameter = Pair<EnumEventName, Any>
typealias Parameters = List<Parameter>