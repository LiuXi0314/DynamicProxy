package com.example.dynamicproxy.reporter

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy
import kotlin.reflect.KClass

object IGCReporter {

    var DEFAULT_REPORTER_KCLASS: KClass<IReporter>? = null

    /**
     * 设置默认的执行者
     */
    fun setDefaultReport(kClass: KClass<IReporter>) {
        DEFAULT_REPORTER_KCLASS = kClass
    }

    private val parameterBuilders = HashMap<Method, Parameter>()

    fun <T : Any> create(event: Class<T>): T {

        validateEventInterface(event)

        return Proxy.newProxyInstance(event.classLoader, arrayOf(event), object : InvocationHandler {

            override fun invoke(proxy: Any, method: Method, args: Array<out Any>): Any? {

                //TODO 不太懂这一块的原理，抄的，别问。
                if (method.declaringClass == Any::class.java) {
                    return method.invoke(this, args)
                }

                //解析参数
                val parameter: Parameter = loadParameter(method, args)

                //执行
                execute(parameter)

                return null
            }

        }) as T
    }

    /**
     * 加载参数
     */
    private fun loadParameter(method: Method, args: Array<out Any>): Parameter {
        var parameter = parameterBuilders[method]
        if (parameter != null) {
            return parameter
        }
        parameter = ParamParser.parse(method, args)
        parameterBuilders[method] = parameter
        return parameter
    }

    /**
     * 执行Event上报
     */
    fun execute(parameter: Parameter) {
        //创建IReport实例
        val instance = parameter.reporterClass.objectInstance ?: parameter.reporterClass.java.newInstance()
        //执行Report()
        instance.report(parameter)
    }

    private fun <T> validateEventInterface(event: Class<T>) {

        if (!event.isInterface) {
            throw IllegalArgumentException("API declarations must be interfaces.")
        }
        // Prevent API interfaces from extending other interfaces. This not only avoids a bug in
        // Android (http://b.android.com/58753) but it forces composition of API declarations which is
        // the recommended pattern.
        if (event.interfaces.isNotEmpty()) {
            throw IllegalArgumentException("API interfaces must not extend other interfaces.")
        }
    }


}
