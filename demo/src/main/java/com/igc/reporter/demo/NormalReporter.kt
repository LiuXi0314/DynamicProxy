package com.igc.reporter.demo

import com.igc.reporter.IReporter
import com.igc.reporter.param.Parameter


class NormalReporter : IReporter {

    override fun report(parameter: Parameter) {

        d("event name -> ${parameter.eventName}")

        d("event action -> ${parameter.eventAction}")

        d("event map  -> ${parameter.eventMap.toString()}")

    }

}