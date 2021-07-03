关注公众号学习更多知识

![](https://files.mdnice.com/user/15648/404c2ab2-9a89-40cf-ba1c-02df017a4ae8.jpg)

### StateFlow和Livedata
### 两者异同
如果你仔细去了解的话会发现StateFlow和Livedata有很多相似之处。两者都是用来观察数据的，非常适合用来做响应式开发。

不过两者也有不同之处的：

- StateFlow构造中有默认值，Livedata没有
- Livedata只有生命周期处于started或resumed状态的时候才能收到数据更新，而StateFlow即使退到后台也可以收到数据。
### 冷流热流
冷流：消费者调用收集collect方法的时候生产者才会开始发射数据，如下代码示例就是冷流。

```
 (2..9).asFlow().collect{
            println(it)
        }
```

热流：本文中StateFlow就是热流，即使不调用collect方法生产者也会发射数据。

### Livedata使用
关于Livedata可以查看我前些天的文章

https://juejin.cn/post/6975689084173811743

https://juejin.cn/post/6978312292999888933

### StateFlow使用
#### 使用StateFlow实现数据监听
StateFlow可以通过setValue来更改数据状态

下面简单介绍一下用法：
1. 添加activity和lifecycle扩展库


```
implementation 'androidx.activity:activity-ktx:1.2.2'
    implementation "androidx.lifecycle:lifecycle-livedata-ktx:2.2.0"
```
2. viewmodel中添加StateFlow代码


```
 private val mutableStateFlow = MutableStateFlow("安安安安卓")//构造中需要有初始值
    val stateFlow: StateFlow<String> = mutableStateFlow//这里为什么要赋值给stateFlow呢，因为stateFlow是不能对value进行赋值的，MutableStateFlow是可以的，这样做可以避免用户在activity中有更新数据的行为

    fun changeData(data: String) {
        mutableStateFlow.value = data//发送数据
    }
```
3. activity中的数据监听处理

当我们点击按钮的时候调用viewModel的changeData方法，更新flow的数据。
```
  btnSendData.setOnClickListener {
            viewModel.changeData("公众号：安安安安卓")
        }
        lifecycleScope.launch {//这里是运行在主线程的，所以下面的代码可以进行更新ui
            viewModel.stateFlow.collect {
                tvShowData.text = it
            }
        }
```

4. 实现效果
![](https://files.mdnice.com/user/15648/8dafba1f-4dd5-4e2f-9a1a-c1091eec83be.gif)

本例实现了下面的效果，首次进入页面StateFlow发送默认值："安安安安卓",点击按钮后调用ViewModel的changeData方法更新数据，在TextView中展示。

这里的使用与Livedata基本一致，不过下一个例子你就会发现StateFlow的模板代码更少，更简洁

#### 使用StateFlow实现MediatorLiveData的效果

关于MediatorLiveData你可以看我这篇文章：

https://juejin.cn/post/6975689084173811743#heading-9

那么同样的效果使用StateFlow改如何实现呢，下面上代码：

上StateFlow实现的代码：

1. ViewModel中代码声明

```
class StateFlowMediatorViewModel : ViewModel() {
    private var count1 = 0//第一个按钮点击的次数
    private var count2 = 0//第二个按钮点击的次数
    private val flow1 = MutableStateFlow(0)
    private val flow2 = MutableStateFlow(0)
    val flow = flow1.combine(flow2) { data1, data2 ->
        data1 + data2//将两个flow融合，分别点击的数量相加
    }

    fun flow1pp() {
        flow1.value = ++count1//点击第一个按钮数量加1
    }

    fun flow2pp() {
        flow2.value = ++count2//点击第二个按钮数量加1
    }
}
```

2. activity中代码声明


```
 findViewById<Button>(R.id.btn_mediator1).setOnClickListener {
            model.flow1pp()
        }
        findViewById<Button>(R.id.btn_mediator2).setOnClickListener {
            model.flow2pp()
        }

        lifecycleScope.launch {
            model.flow.collect {
                textView?.text = if (it > 10) "亲爱的安安安安卓同学您已经点击了$it 次了，再点也不和你玩了" else "您已经点击了$it 次"
            }
        }
```
3. 实现效果

![](https://files.mdnice.com/user/15648/6a62cc7c-d447-4e63-9d17-94c294711c08.gif)

这里只使用了很少量的代码就实现了上一篇[Livadata](https://juejin.cn/post/6975689084173811743#heading-9)中的demo效果,所以StateFlow真的很好，而且它的好肯定不止这些的，需要进一步挖掘

### StateFlow的一些特点
2021-07-03新增本节内容（关注公众号：安安安安卓 学更多知识）

#### ViewModel中同时注册多个StateFlow
先说结论：ViewModel中同时注册多个StateFlow只有第一个注册的StateFlow可以收到数据更新的回调

下面用代码进行验证：

activity中代码
```
  lifecycleScope.launch {
            multipleViewModel.state11.collect {
                logEE(it)
                count++
                tv.text="收到StateFlow1数据更新 $count 次"
            }
            multipleViewModel.state22.collect {
                logEE(it)
                count++
                tv.text="收到StateFlow2数据更新 $count 次"
            }
        }
        findViewById<Button>(R.id.btn_state1_senddata).setOnClickListener {
            multipleViewModel.sendData1()//点击按钮更新第一个StateFlow的值
        }
        findViewById<Button>(R.id.btn_state2_senddata).setOnClickListener {
            multipleViewModel.sendData2()//点击按钮更新第二个StateFlow的值
        }
```
 ViewModel中代码：


```
 private val state1 = MutableStateFlow<String>("哈哈哈1")
    private val state2 = MutableStateFlow<String>("哈哈哈哈2")
    private var count = 0
    val state11 = state1.asStateFlow()
    val state22 = state2.asStateFlow()


    fun sendData1() {
        count++
        state1.value = "state1被更改 $count"
    }

    fun sendData2() {
        count++
        state2.value = "state2被更改 $count"
    }
```

2.实现效果

![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/85f27817a1cc423a83d41c0da374ac5f~tplv-k3u1fbpfcp-zoom-1.image)

通过我们的实现效果，我们发现：

第一个注册的state1每次点击都会回调一次collect，更新TextView的展示值

第二个注册的state2虽然我们也调用了设置state2.value="？？"，但是从来不会回调

这证明了我们本节开始声明的结论



### StateFlow和Livedata异同
2021-07-03新增本节内容（关注公众号：安安安安卓 学更多知识）

#### StateFlow多次更新同一个数据只会收到一次回调，Livedata会收到多次
比如我们设置一个按钮每次点击都会重新设置Livedata或StateFlow的value值（值不变，比如设置value=4），StateFlow只会收到一次回调，但是Livedata可以无限收到回调。下面两小节分别用代码和效果证明
> 这个特点不能说StateFlow和Livedata孰好孰坏，只能说各有优缺
##### StateFlow多次设置相同的值给value
1. 代码

activity中注册代码：


```
lifecycleScope.launch {
            multipleViewModel.state11.collect {
                logEE(it)
                count++
                tv.text="收到数据更新 $count 次"
            }
            multipleViewModel.state22.collect {
                logEE(it)
            }
        }

```
StateFlow中修改值代码：


```
fun sendData1() {
        state1.value = "state1被更改"//我们设置设置这个值
    }
```

2. 运行效果图



![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/4d632643a1364bcf9549c5be63fd058b~tplv-k3u1fbpfcp-zoom-1.image)

我们发现，尽管我们点击了多次发送数据，但是StateFlow的collect只回调了一次数据

##### Livedata多次设置相同的值给value

1. 代码

Activity中的代码
```
 btn_updatewith_simdata.setOnClickListener {
            model.setData()//点击按钮发送数据
        }
        model.livedata.observe(this){
            logEE("收到的数据是：${it}")
            count++
            tv_beupdate.text="收到livedata数据更新请求 $count 次"//收到数据更新后更新ui中textview的值
        }
```

ViewModel中的代码：


```
val livedata = MutableLiveData<Int>()
    fun setData(){
        livedata.value=124
    }
```

2. 效果

![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/23a3525b1131477d9d4cd691573d0634~tplv-k3u1fbpfcp-zoom-1.image)
我们发现，相同的数据我们发送了多次，每次Livedata都收到了数据更新的回调



