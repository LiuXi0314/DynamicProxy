package com.example.dynamicproxy

import com.example.dynamicproxy.reporter.IReporter
import com.example.dynamicproxy.reporter.Parameter

class CustomReporter : IReporter {

    override fun report(parameter: Parameter) {

        d("custom event name -> ${parameter.eventName}")

        d("custom event action -> ${parameter.eventAction}")

        d("custom event map  -> ${parameter.eventMap.toString()}")

    }

}