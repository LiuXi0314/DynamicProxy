package com.example.dynamicproxy


import com.example.dynamicproxy.reporter.annotations.EventAction
import com.example.dynamicproxy.reporter.annotations.EventName
import com.example.dynamicproxy.reporter.annotations.EventParam
import com.example.dynamicproxy.reporter.annotations.EventReporter

@EventName("info")
@EventReporter(CustomReporter::class)
interface Home {

    @EventAction("进入首页的事件")
    fun homePage(
        @EventParam("from_page") from: String,
        @EventParam("event_type") type: String = "page",
        @EventParam("page_name") pageName: String = "首页",
        @EventParam("name") name: String = "首页"
    )


}

