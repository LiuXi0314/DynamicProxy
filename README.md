# 处理事件的文档化、高复用、高扩展的简易框架
**Version：**![img](https://jitpack.io/v/LiuXi0314/DynamicProxy.svg)

To get  the project into your build:

### Gradle

**Step 1.** Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

```gradle
	allprojects {
		repositories {
			...
			maven {url 'https://jitpack.io'}
		}
	}
```

**Step 2.** Add the dependency

```gradle
	dependencies {
	        implementation 'com.github.LiuXi0314:DynamicProxy:$version'
	}
```

### Maven

**Step 1.** Add the JitPack repository to your build file

```markup
<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```

**Step 2.** Add the dependency

```markup
	<dependency>
	    <groupId>com.github.LiuXi0314</groupId>
	    <artifactId>DynamicProxy</artifactId>
	    <version>Tag</version>
	</dependency>
```

# 使用说明

### 推荐在使用**IGCReporter**之前，设置默认的**IReporter** :

```
/**
 * 设置默认的执行者
 */
fun setDefaultReport(kClass: KClass<out IReporter>) {
    DEFAULT_REPORTER_KCLASS = kClass
}
```

```kotlin
IGCReporter.setDefaultReport(NormalReporter::class)
```

如果未设置默认的`IReporter::class`，则必需在事件接口上去用`@EventReporter`定义执行者。推荐在`Application`中设置默认执行者。

### 定义事件的执行者:

```kotlin
/**
 * 上报Event行为的执行者
 */
interface IReporter {

    /**
     * 上报Event
     * @param parameter 事件的参数集合
     */
    fun report(parameter: Parameter)

}
```

执行者的` fun report(parameter: Parameter)`会获取到我们定义的所有事件参数的集合`Parameter`

```kotlin
/**
 * Event参数
 */
class Parameter(

    /**
     * 事件名称
     */
    val eventName: String?,

    /**
     * 事件行为
     */
    val eventAction: String?,

    /**
     * 事件参数集合
     */
    val eventMap: Map<String, Any?>,

    /**
     * 上报者类的Kotlin反射类
     */
    val reporterClass: KClass<out IReporter>

)
```

通过实现`IReporter`自定义上报行为:

```kotlin

class NormalReporter : IReporter {

    override fun report(parameter: Parameter) {

        d("event name -> ${parameter.eventName}")

        d("event action -> ${parameter.eventAction}")

        d("event map  -> ${parameter.eventMap.toString()}")

    }

}
```



### 有以下几种Annotation提供使用：

| EventName         | 在类体使用，用来定义事件的名字                               |
| :---------------- | :----------------------------------------------------------- |
| EventAction       | 在方法体上使用，用来定义事件的具体行为描述                   |
| **EventParam**    | **在方法参数上使用，用来定义事件行为的属性**                 |
| EventParamExt     | 在方法参数上使用，用来定义事件行为的属性，只接受固定格式的Json |
| EventParamMap     | 在方法参数上使用，用来定义事件行为的属性，只接受固定格式的map |
| **EventReporter** | **在类体上使用，用来定义事件的执行者**                       |

注解使用范例：

```kotlin
import com.igc.reporter.annotations.*

@EventName("home")
@EventReporter(CustomReporter::class)//如果设置了默认执行者，此注解为非必须操作。
interface Home {
    @EventAction("进入首页的事件")
    fun homePage(
        @EventParam("from_page") from: String,
        @EventParam("event_type") type: String = "page",
        @EventParam("page_name") pageName: String = "首页",
        @EventParam("name") name: String = "首页"
    )

    @EventAction("进入首页的事件-map")
    fun homePageMap(@EventParamMap map: Map<String, Any?>)

    @EventAction("进入首页的事件-json")
    fun homePageJson(@EventParamExt json: String)

    @EventAction("进入首页的事件-multi")
    fun homePageMulti(
        @EventParam("from_page") from: String,
        @EventParamExt json: String,
        @EventParamMap map: Map<String, Any?>
    )
}  		
```

 

### 最后一步	

使用``IGCReporter``生成事件：

```kotlin
private val home ：Home = IGCReporter.create(Home::class.java) 
```

执行事件：

```kotlin
home.homePage("暂无")
```

想查看完整例子点[这里](https://github.com/LiuXi0314/DynamicProxy/blob/master/demo/src/main/java/com/igc/reporter/demo/MainActivity.kt)

### 补充一点

``IGCReporter``会对你使用过的`IReporter`和事件产生的`Parameter`进行缓存处理，你可以通过它的如下方法对缓存数量进行修改(在使用`IGCReporter`之前修改)：

```kotlin
/**
 * 设置Reporter缓存数量
 */
fun setReporterCacheLimit(limit: Int) {
    DEFAULT_REPORTER_CACHE_LIMIT = limit
}

/**
 * 设置Parameter缓存数量
 */
fun setParameterCacheLimit(limit: Int) {
    DEFAULT_PARAMETER_CACHE_LIMIT = limit
}
```

