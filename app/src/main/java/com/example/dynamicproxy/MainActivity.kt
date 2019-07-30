package com.example.dynamicproxy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dynamicproxy.reporter.IGCReporter
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Demo目标效果：用定义的接口在运行期动态生成对应的Java类，并将接口中的（被注解声明的）参数用Logger打印出来。
 */
class MainActivity : AppCompatActivity() {

    private val home by lazy { IGCReporter.create(Home::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        test1.setOnClickListener {
            home.homePage("暂无")
        }


    }

}
