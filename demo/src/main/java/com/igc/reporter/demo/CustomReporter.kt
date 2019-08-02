package com.igc.reporter.demo

import com.igc.reporter.IReporter
import com.igc.reporter.param.Parameter

class CustomReporter : IReporter {

    override fun report(parameter: Parameter) {

        d("custom event name -> ${parameter.eventName}")

        d("custom event action -> ${parameter.eventAction}")

        d("custom event map  -> ${parameter.eventMap}")

    }

}