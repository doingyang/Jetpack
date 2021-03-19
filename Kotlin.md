kotlin相关

一：变量的声明跟赋值
kotlin默认是public的，可以省略
kotlin里声明变量使用var，val关键字
kotlin变量名在前，变量类型在后，java则相反
kotlin里变量名跟类型之间用“:”冒号隔开
kotlin里变量必须要有初始化的值，java有默认
kotlin末尾不需要写“;”分号

二：Kotlin空安全设计
Kotlin 里 ?表示可空，!!表示非空，如果使用!!那么该变量一定不能为空，否则会报空指针
Kotlin中的?:表示如果空则使用?:后面的值

三：函数的声明
kotlin默认是public的，可以省略
kotlin是fun关键在声明函数
kotlin里无返回值的函数，默认类型是Unit，Unit可以省略，Java是Void
kotlin参数在前，参数类型在后，并且用":"冒号隔开，Java是相反
kotlin返回值类型是在函数的右边并且用":"冒号隔开，Java是在前面
kotlin返回类型，跟入参都可以是可空类型
kotlin中函数可以简化return跟{} 直接用”=“号来代替

四：函数的重载
Kotlin 中可以通过对入参添加默认值，从而来实现函数的重载

五：类型
Kotlin中的Any相当于Java中的Object

六：类的继承跟实现
Kotlin 中继承跟实现都是使用":"冒号，如果有多个的话，用","逗号隔开，Java继承用extends，实现用implements
Java中重写方法使用@Override注解，Kotlin是override关键字
Kotlin 中类默认是final的，如果想要继承，那么可以用open修饰

七：类型判断跟强转
Kotlin 中is关键字相当于Java的instanceof
Kotlin 中as关键字相当于Java的强转
Kotlin 中as?表示如果强转成功就执行之后的调用，如果强转不成功就不执行
Kotlin 中!is表示不是某个类型

八：类的构造器
Java中构造器的写法是：类名的形式
public class HeadOverlayView extends LinearLayout{
    public String name = "";
    public HeadOverlayView(Context context){
        this(context,null);
    }
    public HeadOverlayView(Context context, AttributeSet attrs){
        this(context,attrs,0);
    }
     public HeadOverlayView(Context context, AttributeSet attrs,int defStyleAttr){
        super(context,attrs,defStyleAttr);
        name = "xxx";
    }
}
Kotlin构造器的写法有两种
次级构造器：使用关键字constructor声明的构造器是次级构造器
主构造器：直接在HeadOverlayView(context:Context) 后面写这种方式是主构造器方式
Kotlin中this的用法跟Java中一样，super也是，只是Kotlin次级构造参数后面是用：接着调用super或者this，而Java是写在{}中
class HeadOverlayView : LinearLayout{
    var name = ""
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        name = "xxx"
    }
}
// 也可以这么写
class HeadOverlayView(context:Context):LinearLayout(context){}

九：init
Kotlin中经常在init{}里去写一些初始化的代码,等价于Java的{} 都是在实例化时执行，并且都在构造器之前调用

十：object
object是Kotlin的关键字，并非Java的Object，Java的Object是表示所有类的基类，Kotlin中表示所有类的基类用的是Any
object关键在，在Kotlin中的意思是，创建一个类，并且创建一个这个类的对象，他是生成一个单例对象
通过object 实现的单例是个线程安全的，饿汉式的单例。 当我们想要使用单例，或者想要通过类名.的方式去调用某个类里面的变量跟方法的时候，可以用object
真正变成一个静态：
@JvmField 是用来修饰变量的
@JvmStatic是用来修饰方法的，暴露为静态方法

十一：伴生对象companion object
在Java里我们在类里面写一些静态常量，静态方法，让类直接就可以调用，而在Kotlin中我们就可以用伴生对象了
当我们只想让类中的一部分函数和变量是静态的，我们就可以用companion object

Java中声明常量用static final ,而Kotlin中用const val
Java中创建一个对象是用new关键字，而Kotlin不需要new，比如Java:new Intent(),Kotlin是Intent()
Kotlin中拿到java的class对象是使用  类名::class.java的形式，例如MainActivity::class.java，
Kotlin中新建一个对象之后，要对对象里面的属性赋值，可以通过apply{} 并在{}里对属性进行初始化
Student().apply{
    this.name = "ccm"
}

十二：匿名内部类
Kotlin写匿名内部类的时候，是通过object: 写的
button.setOnClickListener(object:View.OnClickListener{
     override fun onClick(v: View?) {
    }
})
// 通过lambda改写
button.setOnClickListener({v:View->
})
// 如果lambda是函数的最后一个参数，那么可以把Lambda写在()小括号外面
button.setOnClickListener(){v:View->
}
// 如果Lambda的函数是唯一的参数，可以把()小括号去掉
button.setOnClickListener{v:View->
}
// 如果这个Lambda是单个参数的，那么参数也可以省略，表达式可以简写成
button.setOnClickListener{
}

十三：可见性修饰符
Kotlin中的public表示都可见
kotlin中internal修饰付表示对module内可见
protected表示private+子类可见
private表示类中可见或文件里可见，作为内部类时，外部类不可访问到

十四：when，if，if else
Kotlin中if else使用跟java没有太大区别，唯一区别是kotlin中的if else可以有返回值，因为在Kotlin中函数其实中可以作为一个对象
Kotlin中的when基本替代了Java的switch的用法，Kotlin中没有了switch这种用法

十五：字符串拼接
Kotlin中使用$拼接

十六：== 和 ===
java中== 如果是用在基本数据类型，那么是判断值是否相等，如果是放在字符串Stirng是表示引用地址是否相等，Java中判断字符串值是否相等用equals
Kotlin中==是判断值是否相等，如果是===则判断引用地址是否相等，当然Kotlin也可以用equals

十七：数组，集合的遍历
// 数组遍历
val strs = arrayOf("xx","xxx","xxx")
for(str in strs){
    ...
}
// 集合遍历
val list = listOf(1,2,3)
for(i in 0 until list.size){
    .....
}
for(item in list){
    ....
}
// 甚至Kotlin中遍历可以跳指定step
// 下面意思是0，接着是2...
for(i in 0 until list.size step 2){
    ...
}
// 更经常用的是
list.forEach{
    // it就是指集合里面的值
    println(it)
}
list.forEachIndex{index,i->
   // index是位置索引，i是list里index索引下具体的值
}
// Map的话
var map = mapOf<String,String>("name" to "ccm","b" to "cjh")
for ((k, v) in map) {
    println("$k -> $v")
}
val value = map.get("a")//这个是访问map
map.forEach {
    // 这里的it值Map.Entry<String,String>
    it.key
    it.value
}
// 一起讲一下kotlin中的while，continue，break用法跟java的没有什么区别
var x = 6
while (x > 0) {
    x--
    if (x == 4) {
        continue
    }
    if (x == 2) {
        break
    }
    Log.e("ccm", "x=$x")
}
输出结果是 5，3

十八：Kotlin不需要findViewById
在app的build.gradle里加入插件 apply plugin: 'kotlin-android-extensions'
然后在使用的地方导入对应的布局的包，就不需要findViewById了：import kotlinx.android.synthetic.main.activity_main.*

十九：顶层声明
Kotlin的顶层声明的函数跟变量是属于package的。在同一包名内，都可以直接调用，不是同一包名需要引入包

二十：扩展函数
Kotlin中的扩展函数是可以给具体的类，添加扩展的函数。按顶层声明方式使用。

二十一：内联函数
Kotlin中声明内联函数关键字是inline
为何要写内联函数？
我们每调用一个方法的时候，都会创建出一个栈帧，并且把这个栈帧压入到方法栈，当方法调用完成之后又需要把该栈帧出栈，这整个调用方法的过程是会消耗资源的。
我们在开发过程当中，我们会把我们经常使用到的代码抽成方法，而这些方法是我们经常调用的，比如判断字符串是否为空类似的方法，那么这时候是比较耗资源的。
我们使用内联函数之后，编译之后，我们方法调用的地方，都会被替换成方法的内容，这样就不用去创建栈帧了，因为在编译时候自动做的事情，所以不影响我们开发时候使用。从而减少了资源的消耗，同时不影响使用。
如果很多函数经常被使用到，那么我们可以把这个方法定义为内联函数。

二十二：高阶函数
Kotlin中的高阶函数，如果方法的参数是函数类型，或者方法的返回值是函数类型，那么我们就把这个函数称为高阶函数
fun getName(name:String):String = "my name is ${name}"
// 参数是函数类型的例子 method:(name:String)->String 这个是表示参数是String，并且返回类型是String的函数
fun join(s1:String,method:(name:String)->String):String{
    return method(s1)
}
// 调用
fun test(){
    // 调用结果输出是  my name is xxx
    println(join("xxx",::getName))
}
// 返回类型是函数类型的例子 (Int)->Unit 这个表示参数是int无返回类型的函数
fun test2(num: Int): (Int) -> String {
   ...
}

Kotlin中的双冒号“::”表示函数引用，对于一个声明好的函数，当我们要把它作为参数传递给函数，还是要把它赋值给变量，都得在函数名的左边加上双冒号，来进行调用
fun getName(name:String):String = "my name is ${name}"
// 把声明好的函数getName，赋值给变量
val b = ::getName
// 把声明好的函数，作为参数调用
join("cc",::getName)

二十三：嵌套函数
Kotlin中可以在函数里面写函数，这种就是嵌套函数
// 比如
fun main(){
    fun test(){
        xxx
    }
    //调用，test方法只属于main方法
    test()
}















