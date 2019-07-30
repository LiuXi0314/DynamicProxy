package com.example.dynamicproxy

import com.example.dynamicproxy.reporter.IReporter
import com.example.dynamicproxy.reporter.Parameter

class NormalReporter : IReporter {

    override fun report(parameter: Parameter) {

        d("event name -> ${parameter.eventName}")

        d("event action -> ${parameter.eventAction}")

        d("event map  -> ${parameter.eventMap.toString()}")

    }

}