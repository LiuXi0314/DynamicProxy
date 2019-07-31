package com.example.dynamicproxy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dynamicproxy.reporter.IGCReporter
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Demo目标效果：用定义的接口在运行期动态生成对应的Java类，并将接口中的（被注解声明的）参数用Logger打印出来。
 */
class MainActivity : AppCompatActivity() {

    init {
        IGCReporter.setDefaultReport(NormalReporter::class)
    }

    private val home by lazy { IGCReporter.create(Home::class.java) }

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


        test5.setOnClickListener {
            home.testErrorJson(1)
        }

        test6.setOnClickListener {
            val map = HashMap<Boolean, String>()
            map[true] = "00"
            map[false] = "11"
            home.testErrorMap(map)
        }

    }

}
