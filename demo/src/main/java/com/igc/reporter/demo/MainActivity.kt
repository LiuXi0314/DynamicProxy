package com.igc.reporter.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


import com.igc.reporter.IGCReporter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    init {
        IGCReporter.setDefaultReporter(NormalReporter::class)
        IGCReporter.setParameterCacheLimit(100)
        IGCReporter.setReporterCacheLimit(5)
    }

    private val home = IGCReporter.create(Home::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        test1.setOnClickListener {
            home.homePage("暂无")
        }

        test2.setOnClickListener {
            val map = HashMap<String, Any?>()
            map["name"] = "首页"
            map["pageName"] = "首页"
            map["test"] = "33322"
            home.homePageMap(map)
        }

        test3.setOnClickListener {

            val str = "{\n" +
                    "   \"productId\": \"327539590412623872\",\n" +
                    "   \"name\": \"泉灵的语文课三年级上-测试\",\n" +
                    "   \"type\": \"click\",\n" +
                    "   \"imgUrl\": \"https://coolcdn.igetcool.com/t/20190520/18b382cbe01b5085209671e0a3e3999e.jpg\",\n" +
                    "   \"subhead\": \"泉灵的语文课三年级上-测试\"" +
                    "  }"

            home.homePageJson(str)

        }

        test4.setOnClickListener {
            val map = HashMap<String, Any?>()
            map["name"] = "首页"
            map["pageName"] = "首页"
            map["test"] = "33322"

            val str = "{\n" +
                    "   \"productId\": \"327539590412623872\",\n" +
                    "   \"name\": \"泉灵的语文课三年级上-测试\",\n" +
                    "   \"type\": \"click\",\n" +
                    "   \"imgUrl\": \"https://coolcdn.igetcool.com/t/20190520/18b382cbe01b5085209671e0a3e3999e.jpg\",\n" +
                    "   \"subhead\": \"泉灵的语文课三年级上-测试\"" +
                    "  }"

            home.homePageMulti("混合", str, map)

        }

//        test5.setOnClickListener {
//            home.testErrorJson(1)
//        }
//
//        test6.setOnClickListener {
//            val map = HashMap<Boolean, String>()
//            map[true] = "00"
//            map[false] = "11"
//            home.testErrorMap(map)
//        }

    }

}
