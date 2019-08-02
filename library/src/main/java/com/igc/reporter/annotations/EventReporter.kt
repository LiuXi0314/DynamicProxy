package com.igc.reporter.annotations

import com.igc.reporter.IReporter
import kotlin.reflect.KClass


/**
 * 声明Event上报者类的Kotlin反射类
 * @see IReporter
 * @param clazz
 */
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class EventReporter(val clazz: KClass<out IReporter>)